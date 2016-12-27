package linearRegression;

import my.util.LogSetting;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature.IDFModel;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

/**
 * @filename: TfIdf
 * @Description:
 * @Author: ubuntu
 * @Date: 12/26/16 6:21 PM
 */
public class TfIdf {
    /**
     * 日志控制
     */
    static{
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
//        LogSetting.setWarningLogLevel("breeze");
    }

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("local[2]")
                .appName("PipeLineExample")
                .getOrCreate();

        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, "Hi I heard about Spark"),
                RowFactory.create(0.0, "I wish Java could use case classes"),
                RowFactory.create(1.0, "Logistic regression models are neat")
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });

        Dataset<Row> sentenceData = spark.createDataFrame(data,schema);

        Tokenizer tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words");

        Dataset<Row> wordsData = tokenizer.transform(sentenceData);

        int numFeatures = 20;
        HashingTF hashingTF = new HashingTF().setInputCol(tokenizer.getOutputCol()).setOutputCol("rawFeatures").setNumFeatures(numFeatures);

        Dataset<Row> featurizedData = hashingTF.transform(wordsData);

        IDF idf = new IDF().setInputCol(hashingTF.getOutputCol()).setOutputCol("features");

        IDFModel idfModel = idf.fit(featurizedData);

        Dataset<Row> rescaledData = idfModel.transform(featurizedData);

        for(Row r:rescaledData.select("features","label").takeAsList(3)){
            Vector feature = r.getAs(0);
            double label = r.getDouble(1);
            System.out.println(feature);
            System.out.println(label);
        }
    }
}
