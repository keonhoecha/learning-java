package com.company;

class Callme {
    public void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {

        }
        System.out.println("]");
    }
}
class Caller implements Runnable {
    Thread t;
    Callme callme;
    String msg;

    public Caller(Callme callme, String msg) {
        this.callme = callme;
        this.msg = msg;
        t = new Thread(this);
        t.start();
    }
    public void run() {
        synchronized (callme) {
            callme.call(msg);
        }
    }
}
public class Synch {
    public static void main(String[] args) {
        Callme callme = new Callme();
        Caller c1 = new Caller(callme, "Hello");
        Caller c2 = new Caller(callme, "Synchronized");
        Caller c3 = new Caller(callme, "World");

        try {
            c1.t.join();
            c2.t.join();
            c3.t.join();
        }
        catch(InterruptedException e) {
            System.out.println("main interrupted");
        }

    }
}
