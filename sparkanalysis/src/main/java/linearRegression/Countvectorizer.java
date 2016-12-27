package linearRegression;

import my.util.LogSetting;
import org.apache.spark.ml.feature.CountVectorizer;
import org.apache.spark.ml.feature.CountVectorizerModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.Arrays;
import java.util.List;

/**
 * @filename: Countvectorizer
 * @Description:
 * @Author: ubuntu
 * @Date: 12/27/16 10:40 AM
 */
public class Countvectorizer {
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
                RowFactory.create(Arrays.asList("a", "b", "c")),
                RowFactory.create(Arrays.asList("a", "b", "b", "c", "a"))
        );
        StructType schema = new StructType(new StructField[] {
                new StructField("text", new ArrayType(DataTypes.StringType, true), false, Metadata.empty())
        });
        Dataset<Row> df = spark.createDataFrame(data, schema);

        CountVectorizer countvectorizer = new CountVectorizer().setInputCol("text").setOutputCol("feature").setVocabSize(3).setMinDF(2);

        CountVectorizerModel model = countvectorizer.fit(df);

        model.transform(df).show();
    }
}
