package linearRegression;

import my.util.LogSetting;
import org.apache.spark.ml.feature.Word2Vec;
import org.apache.spark.ml.feature.Word2VecModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.Arrays;
import java.util.List;

/**
 * @filename: Word2vec
 * @Description:
 * @Author: ubuntu
 * @Date: 12/27/16 10:13 AM
 */
public class Word2vec {
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

    private static final SparkSession spark = SparkSession
            .builder()
            .master("local[2]")
            .appName("PipeLineExample")
            .getOrCreate();

    public static void main(String[] args) {
        List<Row> data = Arrays.asList(
                RowFactory.create(Arrays.asList("Hi I heard about Spark".split(" "))),
                RowFactory.create(Arrays.asList("I wish Java could use case classes".split(" "))),
                RowFactory.create(Arrays.asList("Logistic regression models are neat".split(" ")))
        );

        StructType schema = new StructType(new StructField[]{new StructField("text",new ArrayType(DataTypes.StringType,true),false, Metadata.empty())});

        Dataset<Row> documentDF = spark.createDataFrame(data,schema);

        Word2Vec word2Vec = new Word2Vec().setInputCol("text").setOutputCol("result").setVectorSize(3).setMinCount(0);

        Word2VecModel word2VecModel = word2Vec.fit(documentDF);
        Dataset<Row> result = word2VecModel.transform(documentDF);

        for(Row r:result.select("result").takeAsList(3)){
            System.out.println(r);
        }
    }
}
