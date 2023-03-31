package task2;

import java.util.ArrayList;
import java.util.List;

public class ThreadTests {

    public static void main(String[] args) throws InterruptedException {

        NumberThreads fizzbuzz = new NumberThreads((n) ->{
            if(n%3 == 0 && n%5 == 0){
                System.out.println("fizzbuzz");
            }
        } );

        NumberThreads fizz = new NumberThreads((n) -> {
            if(n%3 == 0 && n%5 != 0){
                System.out.println("fizz");
            }
        });

        NumberThreads buzz = new NumberThreads((n) -> {
            if(n%5 == 0 && n%3 != 0){
                System.out.println("buzz");
            }
        });

        NumberThreads numbers = new NumberThreads((n) ->{
            if(n%3 != 0 && n%5 != 0){
                System.out.println(n);
            }
        });

        List<NumberThreads> threads = new ArrayList<>();
        threads.add(fizzbuzz);
        threads.add(fizz);
        threads.add(buzz);
        threads.add(numbers);

        for (NumberThreads thread : threads) {
            thread.start();
        }

        for (int i = 1; i < 55 ; i++) {
            for (NumberThreads thread1 : threads) {
                thread1.process(i);
            }
        }
        while (true){
            int checkCount = 0;
            for (NumberThreads thread2 : threads) {
                if(thread2.isChecked()){
                    checkCount++;
                }
                if(checkCount == threads.size()){
                    break;
               }

            }
        }
    }
}