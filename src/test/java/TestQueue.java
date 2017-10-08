import bufferQueue.QueueImpl;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.junit.gen5.api.Assertions.assertTrue;

/**
 * Created by Alikin E.A. on 08.10.17.
 */
public class TestQueue {

    QueueImpl<Integer> queue ;
    CopyOnWriteArrayList<Integer> result ;

    @Test
    public void testQueue() {
        queue = new QueueImpl<>(10);
        result = new CopyOnWriteArrayList();

        new Thread(() -> {
            int i = 0 ;
            while (i < 10) {
                i++;
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(i);
                System.out.println(i);
            }}).start();

        new Thread(() -> {
            int i = 10 ;
            while (i < 20) {
                i++;
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(i);
                System.out.println(i);
            }}).start();

        new Thread(() -> {
            int i = 20 ;
            while (i < 30) {
                i++;
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(i);
                System.out.println(i);
            }}).start();


        while (result.size() != 30) {
            Optional<Integer> item = queue.getAndRemove();
            if (item.isPresent()) {
                result.add(item.get());
            }
        }

        for (int i = 1 ;i < 31 ; i++) {
            assertTrue(result.contains(i));
        }


    }
}
