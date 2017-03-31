package derek.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    private static final Logger logger = LoggerFactory.getLogger(ThreadState.class);

    public static void main(String[] args) {

    /*    new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"waiting thread").start();

        //使用两个blocked县城，一个获得锁，另一个就是处在阻塞状态
        new Thread(new Blocked(),"blockedThread-1 ").start();
        new Thread(new Blocked(),"blockedThread-2 ").start();*/

        String a = " aa aa aa aa aa bb ";
        a = a.substring(0, a.length() - 1);
        String[] result = a.split(" ");
        logger.info("{}", result);

    }


    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
