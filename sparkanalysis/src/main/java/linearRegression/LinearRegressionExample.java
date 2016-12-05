package linearRegression;

import my.util.LogSetting;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.regression.LinearRegressionTrainingSummary;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * used to
 * Created by tianjin on 12/5/16.
 */
public class LinearRegressionExample {

    static{
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }


    public static void main(String[] args) {
        String resources = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        PropertyConfigurator.configure(resources + "log4j.properties");
        System.out.println(resources);
        SparkConf conf = new SparkConf().setAppName("Linear Regression Example").setMaster("local[2]");
        SparkContext sc = new SparkContext(conf);
        SQLContext sql = new SQLContext(sc);

        String path  = resources + "libsvm_data.txt";
        DataFrame training = sql.createDataFrame(MLUtils.loadLibSVMFile(sc, path).toJavaRDD(), LabeledPoint.class);
        LinearRegression lr = new LinearRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8);
        LinearRegressionModel lrModel = lr.fit(training);
        System.out.println("Weights: " + lrModel.weights() + " Intercept: " + lrModel.intercept());
        LinearRegressionTrainingSummary trainingSummary = lrModel.summary();
        System.out.println("numIterations: " + trainingSummary.totalIterations());
        System.out.println("objectiveHistory: " + Vectors.dense(trainingSummary.objectiveHistory()));
        trainingSummary.residuals().show();
        System.out.println("RMSE: " + trainingSummary.rootMeanSquaredError());
        System.out.println("r2: " + trainingSummary.r2());
    }
}
