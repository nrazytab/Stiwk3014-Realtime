package org.example;

public class Multithreading {
    public static void main(String[] args) {

        for(int i = 0; i < 5; i++) {


            MultithreadThing myThing = new MultithreadThing(i);
            myThing.start();
        }
    }
}
