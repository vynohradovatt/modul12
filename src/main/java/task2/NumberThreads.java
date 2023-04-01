package task2;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberThreads extends Thread {

    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>();
    private static final AtomicInteger CURRENT_NUMBER = new AtomicInteger(1);

    public static void fizz() throws InterruptedException {
        while (true) {
            synchronized (QUEUE) {
                if (CURRENT_NUMBER.get() % 3 == 0 && CURRENT_NUMBER.get() % 5 != 0) {
                    QUEUE.add("fizz");
                    CURRENT_NUMBER.incrementAndGet();
                    QUEUE.notifyAll();
                } else {
                    try {
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }

    public static void buzz() throws InterruptedException {
        while (true) {
            synchronized (QUEUE) {
                if (CURRENT_NUMBER.get() % 3 != 0 && CURRENT_NUMBER.get() % 5 == 0) {
                    QUEUE.add("buzz");
                    CURRENT_NUMBER.incrementAndGet();
                    QUEUE.notifyAll();
                } else {
                    try {
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }
    public static void fizzbuzz() throws InterruptedException {
        while (true) {
            synchronized (QUEUE) {
                if (CURRENT_NUMBER.get() % 3 == 0 && CURRENT_NUMBER.get() % 5 == 0) {
                    QUEUE.add("fizzbuzz");
                    CURRENT_NUMBER.incrementAndGet();
                    QUEUE.notifyAll();
                } else {
                    try {
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Thread.sleep(1000);
        }
    }
    public static void number() throws InterruptedException {
        while (true) {
            synchronized (QUEUE) {
                if (CURRENT_NUMBER.get() % 3 != 0
                        && CURRENT_NUMBER.get() % 5 != 0) {
                    QUEUE.add(String.valueOf(CURRENT_NUMBER.get()));
                    CURRENT_NUMBER.incrementAndGet();
                    QUEUE.notifyAll();
                } else {
                    try {
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                while (!QUEUE.isEmpty()) {
                    System.out.print(QUEUE.poll() + " ");
                }
            }
            Thread.sleep(1000);
        }
    }
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
