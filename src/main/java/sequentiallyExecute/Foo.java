package sequentiallyExecute;

import java.util.Random;

/**
 * Created by Alikin E.A. on 08.10.17.
 */
public class Foo {
    public void first() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("first");
    }
    public void second() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second");
    }
    public void third() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("third");
    }

}
