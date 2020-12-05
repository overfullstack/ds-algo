package concurrency;

/**
 * Created by Gopala Akshintala on 5/20/17.
 */
public class PrintEvenOdd {
    public static void main(String[] args) {
        var max = 10;
        var printer = new SharedPrinter();
        var evenThread = new MyThread(true, printer, max);
        var oddThread = new MyThread(false, printer, max);
        evenThread.start();
        oddThread.start();
    }
}

// This is common code for both threads. You could instead have two different thread classes for even and odd.
class MyThread extends Thread {
    private final boolean isEven; // Switch-1, this is constant for a Thread, used to initialize first number to 2 or 1, then increment +=2
    private final SharedPrinter sharedPrinter;
    private final int max;

    MyThread(boolean isEven, SharedPrinter sharedPrinter, int max) {
        this.isEven = isEven;
        this.sharedPrinter = sharedPrinter;
        this.max = max;
    }

    @Override
    public void run() {
        var num = isEven ? 2 : 1;
        while (num <= max) { // In a thread only if or else is always called.
            if (isEven) {
                sharedPrinter.printEven(num);
            } else {
                sharedPrinter.printOdd(num);
            }
            num += 2;
        }
    }
}

// This is shared code for both threads, so has synchronized functions.
class SharedPrinter {
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
