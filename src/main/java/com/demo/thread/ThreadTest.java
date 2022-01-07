package com.demo.thread;

/**
 * ThreadTest
 *
 * @author lgn
 * @since 2021/12/16 9:58
 */

class MyRunnable implements Runnable {

    private int current = 1;
    private int count = 0;

    @Override
    public void run() {
        while (count < 100) {
            synchronized (this) {

                notifyAll();

                if (current == 1) {
                    System.out.print("A");
                    current++;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                if (current == 2) {
                    System.out.print("B");
                    current++;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                if (current == 3) {
                    System.out.print("C");
                    current = 1;

                    count ++;

                    if (count % 10 == 0) {
                        System.out.println();
                    }

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }
}

class MyRunnable2 implements Runnable {

    private int count = 0;

    @Override
    public void run() {
        while (true) {

            synchronized (this) {

                if (count < 100) {

                } else {
                    break;
                }

            }

        }
    }
}

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {

        MyRunnable runnable = new MyRunnable();

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        Thread.sleep(5);
        t2.start();
        Thread.sleep(5);
        t3.start();

    }
}
