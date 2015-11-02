package com.amitinside.app;

import com.amitinside.thread.Calculator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * @author Amit Kumar Mondal admin@amitinside.com
 */
public class thread {

    public static void main(String[] args) throws IOException {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if ((i % 2) == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);

            try (FileWriter file = new FileWriter("/Users/AMIT/Downloads/a.txt");
                    PrintWriter pw = new PrintWriter(file);) {
                for (int j = 0; j < 10; j++) {
                    Thread.State state = states[j];
                    pw.println("Main : Status of Thread " + j + ": " + threads[j].getState());
                    states[j] = threads[i].getState();
                }
                for (int j = 0; j < 10; j++) {
                    threads[i].start();
                }
                boolean finish = false;
                while (!finish) {
                    for (int j = 0; j < states.length; j++) {
                        if (threads[j].getState() != states[j]) {
                            writeThreadInfo(pw, threads[j], states[j]);
                            states[j] = threads[j].getState();
                        }

                    }
                    finish = true;
                    for (int j = 0; j < 10; j++) {
                        finish = finish && (threads[j].getState() == State.TERMINATED);

                    }
                }

            }
//            Calculator calc = new Calculator(i);
//            Thread t = new Thread(calc);
//            t.start();
        }
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
        pw.printf("Main : Id %d - %s \n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n ", thread.getPriority());
        pw.printf("Main : Old State :%s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ******************************** \n");
    }
}
