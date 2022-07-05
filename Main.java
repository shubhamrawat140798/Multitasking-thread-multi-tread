// Use a singleThreadExecutor to submit multiple threads.
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long sT = System.nanoTime();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Callable for future has returned";
        });

        while(!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            double eTS = (System.nanoTime() - sT)/1000000000.0;

            if(eTS> 1) {
                future.cancel(true);
            }
        }
if(!future.isCancelled()) {
    System.out.println("Task has been completed");
    String res = future.get();
    System.out.println(res);
} else {
    System.out.println("Task was cancelled");
}
        executorService.shutdown();
    }
}
