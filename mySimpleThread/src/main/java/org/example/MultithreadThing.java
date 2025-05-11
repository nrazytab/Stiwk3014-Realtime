package org.example;

public class MultithreadThing extends Thread {

    private int threadNumber;

    public MultithreadThing (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);

            try {
                Thread.sleep(1000); // Corrected sleep syntax
            } catch (InterruptedException e) {

            }
        }
    }
}