package ch.zhaw.ads.Praktikum_02;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

public class ListTest {

    List<Object> list;

    @Before
    public void setUp() throws Exception {
        list = new MyList();
    }

    @Test
    public void testAdd() {
        list.clear();
        list.add("A");
        Object o = list.get(0);
        assertEquals(o, "A");
    }

    @Test
    public void testAdd2() {
        list.clear();
        list.add("A");
        list.add("B");
        Object o = list.get(0);
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
    }

    @Test
    public void testAdd3() {
        list.clear();
        list.add("A");
        list.add("B");
        list.add("C");
        Object o = list.get(0);
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
        o = list.get(2);
        assertEquals(o, "C");
    }



    @Test
    public void testSize() {
        list.clear();
        assertEquals(0, list.size());
        testAdd2();
        assertEquals(2, list.size());
    }

    @Test
    public void testRemove() {
        list.clear();
        list.add("A");
        list.remove("A");
        assertEquals(0, list.size());
        list.add("A");
        list.remove("B");
        assertEquals(1, list.size());
        list.remove("A");
        assertEquals(0, list.size());
    }

    @Test
    public void testListNodeEquals() {
        ListNode characternode = new ListNode('a');
        Object c = 'a';
        System.out.println(characternode.getData().equals(c));
    }

    @Test
    public void testMixed() {
        list.clear();
        List list2 = new LinkedList();
        for (int i = 0; i < 100; i++) {
            Character c = (char) ('A' + (Math.random()*26));
            int op = (int)(Math.random()*2);
            //System.out.println("operation: " + op + " Character: " + c + " Liste: ");
            //list.forEach(System.out::println);
            if (i == 50) System.out.println("000000000000000000000 List size: " + list.size() + " List2 size: " + list2.size());
            switch (op) {
                case 0 : {
                    list.add(c);
                    list2.add(c);
                    System.out.println("\033[31;1m Füge: " + c + "\033[0m");
                    break;
                }
                case 1 : {
                    list.remove(c);
                    list2.remove(c);
                    System.out.println(c);
                    if (list2.size() != list.size()) {
                        System.out.println("ID: " + i + " Character: " + c + " list size: " + list.size() + " List2 size: " + list2.size());
                    }
                    break;
                }
            }
        }
        assertEquals(list2.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            char c1 = (char)list.get(i);
            char c2 = (char)list2.get(i);
            assertEquals(c1,c2);
        }
    }

}
