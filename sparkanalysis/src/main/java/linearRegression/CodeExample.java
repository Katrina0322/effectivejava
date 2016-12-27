package linearRegression;

import my.util.LogSetting;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.param.ParamMap;
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
 * @filename: CodeExample
 * @Description:
 * @Author: ubuntu
 * @Date: 12/26/16 11:16 AM
 */
public class CodeExample {

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
            .appName("JavaLinearRegressionExample")
            .getOrCreate();


    public static void main(String[] args) {
        List<Row> dataTraining = Arrays.asList(
                RowFactory.create(1.0, Vectors.dense(0.0, 1.1, 0.1)),
                RowFactory.create(0.0, Vectors.dense(2.0, 1.0, -1.0)),
                RowFactory.create(0.0, Vectors.dense(2.0, 1.3, 1.0)),
                RowFactory.create(1.0, Vectors.dense(0.0, 1.2, -0.5)));

        StructType schema = new StructType(
                new StructField[]{
                        new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
                        new StructField("features", new VectorUDT(), false, Metadata.empty())
                });

        Dataset<Row> training = spark.createDataFrame(dataTraining,schema);

        //创建一个Estimator
        LogisticRegression lr = new LogisticRegression();

        System.out.println("________________" + lr.explainParams());

        lr.setMaxIter(10).setRegParam(0.01);
        LogisticRegressionModel model1 = lr.fit(training);

        System.out.println("__________________Model 1 was fit using parameters: " + model1.parent().extractParamMap() + "_________" + model1.explainParams());

        ParamMap paramMap = new ParamMap()
                .put(lr.maxIter().w(20))
                .put(lr.maxIter(),30)
                .put(lr.regParam().w(0.1),lr.threshold().w(0.55));

        ParamMap paramMap2 = new ParamMap()
                .put(lr.probabilityCol().w("myProbability"));

        ParamMap paramMapCombined = paramMap.$plus$plus(paramMap2);

        LogisticRegressionModel model2 = lr.fit(training,paramMapCombined);

        System.out.println("_______________________Model 2 was fit using parameters: " + model2.parent().extractParamMap());

        List<Row> dataTest = Arrays.asList(
                RowFactory.create(1.0, Vectors.dense(-1.0, 1.5, 1.3)),
                RowFactory.create(0.0, Vectors.dense(3.0, 2.0, -0.1)),
                RowFactory.create(1.0, Vectors.dense(0.0, 2.2, -1.5))
        );
        Dataset<Row> test = spark.createDataFrame(dataTest, schema);

        Dataset<Row> results = model2.transform(test);
        Dataset<Row> rows = results.select("features", "label", "myProbability", "prediction");

        for (Row r: rows.collectAsList()) {
            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") -> prob=" + r.get(2)
                    + ", prediction=" + r.get(3));
        }
    }

}
