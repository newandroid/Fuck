package css.com.fuck;

import com.example.MyClass;
import com.example.fullcontainer.SimpleContainer;
import com.example.generics.Sets;
import com.example.generics.WaterColor;
import com.example.nio.CopyCharacters;
import com.example.nio.ScanSum;
import com.example.nio.ScanText;

import org.junit.Test;

import java.util.EnumSet;
import java.util.Random;

/**
 * Created by Administrator on 2017/11/21.
 */


public class TestJava {
    @Test
    public void testGenerics() {
        MyClass myClass = new MyClass();
        myClass.testGenerics();

        Sets.union(EnumSet.range(WaterColor.RED, WaterColor.GREEN), EnumSet.range(WaterColor.GREEN, WaterColor.BLUE));
    }

    @Test
    public void testContainer() {
        SimpleContainer simpleContainer = new SimpleContainer();
        try {
            simpleContainer.stack();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Random(50).nextInt();

    }

    @Test
    public void testIo() {
//        CopyBytes copyBytes = new CopyBytes();
//        copyBytes.isInputExist();
//        copyBytes.copy();
//        scan();

//        scanSum();
        System.out.format("%f, %<+020.10f %n", Math.PI);
    }

    private void copyC() {
        CopyCharacters copyC = new CopyCharacters();
        copyC.copyLines();
    }

    private void scan() {
        ScanText s = new ScanText();
        s.show();
    }

    private void scanSum() {
        ScanSum scanSum = new ScanSum();
        scanSum.sum();
    }
}
