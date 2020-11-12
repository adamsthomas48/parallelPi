import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double startTime = System.currentTimeMillis();
        int numCores = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numCores];
        ThreadSafeQueue taskQueue = new ThreadSafeQueue();
        setTasks(taskQueue);
        ThreadSafeQueue resultQueue = new ThreadSafeQueue();

        if(args.length > 0){
            if(args[0].equals("-reverse")){
                taskQueue.reverse();
            }
            else if(args[0].equals("-random")){
                taskQueue.shuffle();
            }

        }
        taskQueue.addTask(-5, 0);

        for(int i = 0; i < numCores; i++){
            //create new thread
            threads[i] = new Thread(new WorkingThread(taskQueue, resultQueue));

        }
        //threads[numCores - 1] = new Thread(new OutputThread(resultQueue));
        for(int i = 0; i < numCores; i++){
            threads[i].start();
        }
        for(int i = 0; i < numCores; i++){
            threads[i].join();
        }


        Thread resultThread = new Thread(new OutputThread(resultQueue));
        resultThread.start();
        resultThread.join();

        double endTime = System.currentTimeMillis();
        double totalTime = (endTime - startTime)/1000;
        System.out.println("Total Time: " + totalTime + " seconds.");

    }

    static void setTasks(ThreadSafeQueue queue){
        for(int i = 0; i <= 1024; i++){
            queue.addTask(i, 0);
        }
    }


}
