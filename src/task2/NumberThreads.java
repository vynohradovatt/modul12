package task2;

import java.util.concurrent.atomic.AtomicBoolean;

public class NumberThreads extends Thread {

    private int number;
    private AtomicBoolean isCheck = new AtomicBoolean(false);

    private CheckCondition condition;

    public NumberThreads(CheckCondition condition){
        this.condition = condition;
    }

    public void process(int number){
        this.number = number;
        isCheck.set(false);
    }

    public boolean isChecked(){
        return isCheck.get();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                if(isCheck.get()){
                continue;
            }
                condition.process(number);

                isCheck.set(true);
        }

    }

}
