package concurrency;/*
 * Copyright (c) 2016 - 2017
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 */

/**
 * Created by Gopala Akshintala on 5/20/17.
 */
public class PrintEvenOdd {
    public static void main(String[] args) {
        var max = 10;
        var printer = new Printer();
        var evenThread = new MyThread(true, printer, max);
        var oddThread = new MyThread(false, printer, max);
        evenThread.start();
        oddThread.start();
    }
}

// This is common code for both threads. You could instead have two different thread classes for even and odd.
class MyThread extends Thread {
    private boolean isEven; // Switch-1, this is constant for a Thread, used to initialize first number to 2 or 1, then increment +=2
    private Printer printer;
    private int max;

    MyThread(boolean isEven, Printer printer, int max) {
        this.isEven = isEven;
        this.printer = printer;
        this.max = max;
    }

    @Override
    public void run() {
        var num = isEven ? 2 : 1;
        while (num <= max) { // The idea is to prevent this loop to run only once per thread invocation and go on wait state.
            if (isEven) {
                printer.printEven(num);
            } else {
                printer.printOdd(num);
            }
            num += 2;
        }
    }
}

// This is shared code for both threads, so has synchronized functions.
class Printer {
    private boolean printOddNow = true; // Switch-2, this is common switch and gets switched by threads

    synchronized void printOdd(int numToPrint) {
        while (!printOddNow) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Odd: " + numToPrint);
        printOddNow = false;
        notifyAll();
    }

    synchronized void printEven(int numToPrint) {
        while (printOddNow) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Even: " + numToPrint);
        printOddNow = true;
        notifyAll();
    }
}