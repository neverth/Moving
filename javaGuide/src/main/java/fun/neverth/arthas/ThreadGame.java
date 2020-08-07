package fun.neverth.arthas;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * todo
 *
 * @author NeverTh
 * @date 23:21 2020/8/5
 */
public class ThreadGame {

    static final Object object = new Object();
    static final Object object1 = new Object();

    @Test
    public void a(){
        Thread a = new Thread(() -> {

            synchronized (object){
                System.out.println(object);
            }

        });

        Thread b = new Thread(() -> {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.setName("thread-a");
        b.setName("thread-b");

        a.start();
        b.start();
    }

    @Test
    public void b(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos =
                threadMXBean.dumpAllThreads(false, false);
        for(ThreadInfo threadInfo:threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+"]"+" "
                    +threadInfo.getThreadName());
        }
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
