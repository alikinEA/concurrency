package bufferQueue;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Alikin E.A. on 02.10.17.
 *
 *
 * Реализуйте потоки производителя (Producer) и потребителя (Consumer), совместно пользующихся буфером фиксированного размера.
 * Первый поток должен помещать числа в буфер в бесконечном цикле, а второй — бесконечно извлекать их оттуда. Порядок добавления и извлечения чисел не имеет значения.
 * Данные производителя не должны теряться: либо считаться потребителем, либо остаться в буфере.
 * Решение по организации ожидания чтения, в случае пустого буфера, или записи, в случае заполненного буфера, остается за вами.
 *
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
