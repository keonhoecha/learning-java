package com.company.multithreading;

class NewThread implements Runnable {
    Thread t;

    public NewThread() {
        t = new Thread(this, "New Thread");
        t.start();
    }

    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println("New Thread: " + i);
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e) {
            System.out.println("New Thread interrupted: " + e.getMessage());
        }
        System.out.println("New thread existing.");
    }
}

public class Main {

    public static void main(String[] args) {
        new NewThread();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("Main thread existing.");
    }
}
