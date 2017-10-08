package sequentiallyExecute;

/**
 * Created by Alikin E.A. on 08.10.17.
 * Экземпляр класса передается 3 потокам: ThreadA вызывает first(), ThreadB вызывает second(), ThreadC вызывает third().
 * Разработайте механизм, гарантирующий, что метод first() будет вызван перед second(), а метод second() будет вызван перед third().
 */

public class Main {

    private static final Foo foo = new Foo();
    private static volatile boolean first = false;
    private static volatile boolean second = false;

    public static void main(String[] args) {
        Thread firstT = new Thread(() -> {
            foo.first();
            first = true;
        });
        Thread secondT = new Thread(() -> {
            while (!first);
            foo.second();
            second = true;
        });
        Thread thirdT = new Thread(() -> {
            while (!second);
            foo.third();
        });

        thirdT.start();
        secondT.start();
        firstT.start();



    }
}
