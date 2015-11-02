package com.amitinside.thread;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */

public class Calculator implements Runnable{
    
    private int num;

    public Calculator(int num) {
        this.num = num;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(),num,i,i*num);
        }
    }
    

}
