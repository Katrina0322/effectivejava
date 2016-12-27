package classification;

import my.util.LogSetting;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.classification.BinaryLogisticRegressionSummary;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.classification.LogisticRegressionTrainingSummary;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;

/**
 * used to
 * Created by tianjin on 12/7/16.
 */
public class LogisticWithElasticNet {
    /**
     * 日志控制
     */
    static{
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }

    public static void main(String[] args) {
        String resources = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        SparkConf conf = new SparkConf().setAppName("Logistic Regression with Elastic Net Example").setMaster("local[2]");
        SparkContext sc = new SparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        String path  = resources + "libsvm_data.txt";
        Dataset<Row> training = sqlContext.read().format("libsvm").load(path);

        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8);

        // Fit the model
        LogisticRegressionModel lrModel = lr.fit(training);

        System.out.println("Coefficients: "
                + lrModel.coefficients() + " Intercept: " + lrModel.intercept());

        // Extract the summary from the returned LogisticRegressionModel instance trained in the earlier example
        LogisticRegressionTrainingSummary trainingSummary = lrModel.summary();

        // Obtain the loss per iteration.
        double[] objectiveHistory = trainingSummary.objectiveHistory();
        for (double lossPerIteration : objectiveHistory) {
            System.out.println(lossPerIteration);
        }

        // Obtain the metrics useful to judge performance on test data.
        // We cast the summary to a BinaryLogisticRegressionSummary since the problem is a binary classification problem.
        BinaryLogisticRegressionSummary binarySummary =
                (BinaryLogisticRegressionSummary) trainingSummary;

        // Obtain the receiver-operating characteristic as a dataframe and areaUnderROC.
        Dataset<Row> roc = binarySummary.roc();
        roc.show();
        roc.select("FPR").show();
        System.out.println(binarySummary.areaUnderROC());

        // Get the threshold corresponding to the maximum F-Measure and rerun LogisticRegression with this selected threshold.
        Dataset<Row> fMeasure = binarySummary.fMeasureByThreshold();
        double maxFMeasure = fMeasure.select(functions.max("F-Measure")).head().getDouble(0);
        double bestThreshold = fMeasure.where(fMeasure.col("F-Measure").equalTo(maxFMeasure))
                .select("threshold").head().getDouble(0);
        lrModel.setThreshold(bestThreshold);
    }
}
