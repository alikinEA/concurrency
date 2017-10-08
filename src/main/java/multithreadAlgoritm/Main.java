package multithreadAlgoritm;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Alikin E.A. on 08.10.17.
 *
 * Напишите многопоточную версию этого алгоритма.
 * Один поток проверяет кратность З и выводит «Fizz». Другой поток отвечает за проверку кратности 5 и выводит «Buzz».
 * Третий поток отвечает за проверку кратности З и 5 и выводит «FizzBuZZ». Четвертый поток работает с числами.
 */
public class Main {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int n = 10;
        for (int i = 1; i <= n; i++) {
            Callable<Boolean> task1 = new MyCallable1(i);
            Callable<Boolean> task2 = new MyCallable2(i);
            Callable<Boolean> task3 = new MyCallable3(i);
            Future<Boolean> future1 = executor.submit(task1);
            Future<Boolean> future2 = executor.submit(task2);
            Future<Boolean> future3 = executor.submit(task3);

            if (future1.get()) {
                System.out.print("[FizzBuzz]");
            } else if (future2.get()) {
                System.out.print("[Fizz]");
            } else if (future3.get()) {
                System.out.print("[Buzz]");
            } else {
                System.out.print("[" + i + "]");
            }
        }
        executor.shutdown();
    }

    static class MyCallable1 implements Callable {
        private final int i;

        MyCallable1 (int i) {
            this.i = i;
        }
        @Override
        public Object call() throws Exception {
            Thread.sleep(new Random().nextInt(1000));
            return  (this.i % 3 == 0 && this.i % 5 == 0);
        }
    }

    static class MyCallable2 implements Callable {
        private final int i;

        MyCallable2 (int i) {
            this.i = i;
        }
        @Override
        public Object call() throws Exception {
            Thread.sleep(new Random().nextInt(1000));
            return  (this.i % 3 == 0);
        }
    }

    static class MyCallable3 implements Callable {
        private final int i;

        MyCallable3 (int i) {
            this.i = i;
        }
        @Override
        public Object call() throws Exception {
            Thread.sleep(new Random().nextInt(1000));
            return  (this.i % 5 == 0);
        }
    }
}
