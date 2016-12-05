package my.util;

import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;

import java.util.List;

/**
 * used to
 * Created by tianjin on 12/5/16.
 */
public class ConfigUtil {
    public static void main(String[] args) {
//		TopicCommandOptions options = new TopicCommandOptions(new String[] {"--list"});
//		ZkClient zkClient = new ZkClient("192.168.200.6:2181");


        List<String> topics = scala.collection.JavaConversions.seqAsJavaList(ZkUtils.getAllTopics(new ZkClient("112.65.234.8:2181")));


        for (String topic : topics) {
            System.out.println(topic);
        }

    }

    static String getJarPath() {

        String path = ConfigUtil.class.getProtectionDomain().getCodeSource()
                .getLocation().getFile();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (java.io.UnsupportedEncodingException ex) {
            LogFactory.getLog(ConfigUtil.class).error(ex);
        }
        java.io.File jarFile = new java.io.File(path);

        return jarFile.getAbsolutePath();
    }

    public static SparkConf getSparkConf(final String appName, final String master, final String memory, final int maxCores, final boolean notDebug) {
        //String master = "local[2]";String memory = "2g";

        SparkConf sparkConf = new SparkConf()
                .setAppName(appName).setMaster(master)
                .set("spark.executor.memory", memory)
                //.set("spark.akka.frameSize", "10000")
                // .set("spark.streaming.blockInterval", "20")
                .set("spark.rdd.compress", "true");
        //.set("spark.local.dir", "/home/spark-tmp");
        if (maxCores > 0)
            sparkConf.set("spark.cores.max", String.valueOf(maxCores));

        // sparkConf.set("spark.executor.extraJavaOptions",
        // "-Dcom.sun.management.jmxremote.port=19999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false");
        // sparkConf.set("spark.executor.extraJavaOptions",
        // "-XX:MaxGCPauseMillis=1 -Dcom.sun.management.jmxremote.port=19999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false");
        // sparkConf.set("spark.executor.extraJavaOptions",
        // "-XX:+UnlockExperimentalVMOptions -XX:+DoEscapeAnalysis -XX:+UseFastAccessorMethods -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:MaxGCPauseMillis=5 -XX:+AggressiveOpts -XX:+UseCompressedStrings -XX:+UseBiasedLocking -XX:+AlwaysPreTouch -XX:ParallelGCThreads=4 -Dcom.sun.management.jmxremote.port=19999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false");
        // -XX:MaxGCPauseMillis=10
        String javaOpts = "-Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly";

        if (!notDebug) { // if isDebug
            javaOpts += " -Dcom.sun.management.jmxremote.port=18888 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false";
        }

        sparkConf.set("spark.executor.extraJavaOptions", javaOpts);
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");

        if (notDebug) {
            String jarPath = getJarPath();
            LogFactory.getLog(ConfigUtil.class).info(jarPath);
            sparkConf.setJars(new String[]{jarPath});
        }
        return sparkConf;
    }

}
