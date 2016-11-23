package chap8;

import java.util.concurrent.*;

/**
 * Created by spark on 11/23/16.
 */
//public class Renderer {
//    private final ExecutorService executorService;
//
//    public Renderer(ExecutorService executorService) {
//        this.executorService = executorService;
//    }
//
//    void renderPage(CharSequence source){
//        List<ImageInfo> info = scanForImageInfo(source);
//            CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
//        for(final ImageInfo imageInfo:info){
//            completionService.submit(new Callable<ImageData>() {
//                @Override
//                public ImageData call() throws Exception {
//                    return imageInfo.downloadImage();
//                }
//            });
//        }
//
//        renderText(source);
//        try {
//            for(int t = 0,n = info.size();t < n;t++){
//                Future<ImageData> f = completionService.take();
//                ImageData imageData = f.get();
//                renderImage(imageData);
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//}
