import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JiangLiwei {
    ArrayList<String> slist = new ArrayList<>();
    String[] data = {"1","2","3","4","5"};

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp(){
        List list = Arrays.asList(data);
        slist.addAll(list);
    }

    @Test
    public void testRemoveIndex(){
        thrown.expect(IndexOutOfBoundsException.class);
        slist.remove(6);
        assertTrue(slist.remove(4)=="5");
        assertTrue(slist.remove(0)=="1");
        System.out.println("remove(int index) ok");
    }

    @Test
    public void testRemoveObject(){
        assertTrue(slist.remove("5"));
        assertFalse(slist.remove("6"));
        assertFalse(slist.remove(null));
        slist.add(null);
        assertTrue(slist.remove(null));
        System.out.println("remove(Object o) ok");

    }
    @Test
    public void test(){
        System.out.println("{\"中文\"}".getBytes().length);
    }
}
