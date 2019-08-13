package com.company.multithreading;
/*
 * ProducerConsumer: main class
 * Q: class for Queue
 * Producer: Producer class
 * Consumer: Consumer class
 * Behavior:
 *   1. Producer generates an inter and put the integer to Q.
 *   2. Producer cannot put an integer to Q if Q has the integer, i.e., Consumer has not yet removed it.
 *   3. Consumer cannot remove an integer from the Q if it is empty, i.e., Producer has not yet add it.
 *   4. Q should be injected to the constructors of the Producer and Consumer after being created in the ProducerConsumer class.
 *   5. Producer and Consumer should run as separate thread.
 *   6. Try to implement it using synchronized.
 */
class Q {
    int n;
    boolean writable;

    public Q() {
        writable = true;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}

class Producer implements Runnable {
    Q q;
    Thread t;

    public Producer(Q q) {
        this.q = q;
        t = new Thread(this, "Producer");
        t.start();
    }

    public void run() {
        int i = 0;
        while(true) {
            synchronized (q) {
                if (q.writable) {
                    System.out.println("Set: " + i);
                    q.setN(i++);
                    q.writable = false;
                }
            }
        }
    }
}

class Consumer implements Runnable {
    Q q;
    Thread t;

    public Consumer(Q q) {
        this.q = q;
        t = new Thread(this, "Consumer");
        t.start();
    }

    public void run() {
        while(true) {
            synchronized (q) {
                if (!q.writable) {
                    int n = q.getN();
                    q.writable = true;
                    System.out.println("Got: " + n);
                }
            }
        }
    }
}

public class ProducerConsumer1 {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);


    }
}