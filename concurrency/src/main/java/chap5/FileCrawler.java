package chap5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by spark on 11/12/16.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
//        try {
//            processTask(fileQueue.take())
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if(entries != null){
            for(File entry:entries){
                if(entry.isDirectory()){
                    crawl(entry);
                }else if(entry.exists()){
                    fileQueue.put(entry);
                }
            }
        }

    }


}
