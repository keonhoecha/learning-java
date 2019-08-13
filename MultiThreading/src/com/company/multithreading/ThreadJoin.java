package com.company.multithreading;

class MyNewThread implements Runnable {
    String name;
    Thread t;

    public MyNewThread(String name) {
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    public MyNewThread(String name, int priority) {
        this.name = name;
        t = new Thread(this, name);
        t.setPriority(priority);
        t.start();
    }

    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e) {
            System.out.println(name + " interrupted.");
        }

        System.out.println(name + " exiting");
    }
}
public class ThreadJoin {
    public static void main(String[] args) {
        MyNewThread t1 = new MyNewThread("Thread 1",1);
        MyNewThread t2 = new MyNewThread("Thread 2", 10);
        MyNewThread t3 = new MyNewThread("Thread 3", 5);

        try {
            t1.t.join();
            t2.t.join();
            t3.t.join();

            System.out.println("Now all child threads finished");
        }
        catch(InterruptedException e){
            System.out.println("Main thread interrupted.");
        }
    }

}
