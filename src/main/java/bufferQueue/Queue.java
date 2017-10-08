package bufferQueue;

import java.util.Optional;

/**
 * Created by Alikin E.A. on 08.10.17.
 */
public interface Queue<T> {

    Optional<T> getAndRemove();

    void add(T item);

}
