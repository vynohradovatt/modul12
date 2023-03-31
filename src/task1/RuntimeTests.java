package task1;

public class RuntimeTests extends Thread {


    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        System.out.println(System.currentTimeMillis() - time + ": мілісекунд минуло");
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(5000);
                    System.out.println("Пройшло 5 секунд");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });
        thread2.start();

    }
}
