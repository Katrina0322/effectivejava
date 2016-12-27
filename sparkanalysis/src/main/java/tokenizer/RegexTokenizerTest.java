package tokenizer;

import my.util.LogSetting;
import org.apache.spark.ml.feature.RegexTokenizer;
import org.apache.spark.ml.feature.Tokenizer;
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
 * @filename: RegexTokenizerTest
 * @Description:
 * @Author: ubuntu
 * @Date: 12/27/16 10:53 AM
 */
public class RegexTokenizerTest {

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
            .appName("RegexTokenizerTest")
            .getOrCreate();
    public static void main(String[] args) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, "Hi I heard about Spark"),
                RowFactory.create(1, "I wish Java could use case classes"),
                RowFactory.create(2, "Logistic,regression,models,are,neat")
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("label", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });

        Dataset<Row> sentenceDataFrame = spark.createDataFrame(data, schema);

        Tokenizer tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words");

        Dataset<Row> wordsDataFrame = tokenizer.transform(sentenceDataFrame);
        for (Row r : wordsDataFrame.select("words", "label").takeAsList(3)) {
            java.util.List<String> words = r.getList(0);
            for (String word : words) System.out.print(word + " ");
            System.out.println();
        }

        RegexTokenizer regexTokenizer = new RegexTokenizer().setInputCol("sentence").setOutputCol("words").setPattern("\\W");
    }
}
