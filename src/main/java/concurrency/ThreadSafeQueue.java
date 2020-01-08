package concurrency;/*
 * Copyright (c) 2016
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gakshintala on 6/16/16.
 */
public class ThreadSafeQueue {
    private Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        var queue = new ThreadSafeQueue();
        var producer = new Producer(queue);
        var consumer = new Consumer(queue);

        producer.start();
        consumer.start();
    }

    // The below two methods hold shared code
    synchronized int get() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }

    synchronized void put(int i) {
        while (queue.size() >= 5) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(i);
        notifyAll();
    }
}

class Producer extends Thread {
    private ThreadSafeQueue queue;

    Producer(ThreadSafeQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) {
            System.out.println("Put: " + i);
            queue.put(i);
        }
    }
}

class Consumer extends Thread {
    private ThreadSafeQueue queue;

    Consumer(ThreadSafeQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) {
            System.out.println("Get: " + queue.get());
        }
    }
}