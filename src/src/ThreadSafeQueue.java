import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class ThreadSafeQueue {
    private LinkedList<Integer[]> queue = new LinkedList<>();

    ThreadSafeQueue(){};

    public synchronized void addTask(int position, int value){
        Integer[] item = new Integer[2];
        item[0] = position;
        item[1] = value;
        queue.add(item);
    }

    public synchronized Integer[] deleteTask(){
        return queue.pop();
    }

    public synchronized LinkedList<Integer[]> getQueue() {
        return queue;
    }

    public synchronized  boolean isEmpty(){
        return queue.getFirst()[0] < 0;
    }

    public synchronized  void reverse(){
        Collections.reverse(queue);
    }

    public synchronized  void shuffle(){
        Collections.shuffle(queue);
    }
}
