package bufferQueue;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Alikin E.A. on 02.10.17.
 */
public class QueueImpl<T> implements Queue<T> {

    private final int BUFFER_SIZE ;
    private final LinkedList<T> queue = new LinkedList<>();

    public QueueImpl(int bufferSize) {
        this.BUFFER_SIZE = bufferSize;
    }

    public synchronized void add(T item) {
        while(queue.size() >= BUFFER_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(item);

    }

    public synchronized Optional<T> getAndRemove() {
        Optional<T> removed ;
        try {
            removed = Optional.of(queue.remove());
        } catch (Exception e) {
            removed = Optional.empty();
        } finally {
            notifyAll();
        }
        return removed;
    }

}
