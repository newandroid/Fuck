package css.com.fuck;

import com.example.concurrency.ContainerThread;

import org.junit.Test;

/**
 * Created by css on 2018/7/9.
 */
public class ConcurrentTestMain {
    @Test
    public void show(){
        new ContainerThread().go();
    }
}
