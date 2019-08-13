package com.company.ideatip;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyStudent.printStudent();
    }

    void methodA() {
        System.out.println("methodA is called");

    }

    void methodB() {
        for(int i=0; i<10; i++) {
            System.out.println(i);
        }
        System.out.println("methodB is called.");
    }
}

class MyStudent {
    public static void printStudent() {
        System.out.println("hello");
    }
}
