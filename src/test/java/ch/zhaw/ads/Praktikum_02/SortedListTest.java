package ch.zhaw.ads.Praktikum_02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedListTest {
    SortedList sortedList;

    @Before
    public void setUp() throws Exception {
        sortedList = new SortedList();
    }

    @Test
    public void testCompare() {
        ListNode listNode1 = new ListNode("1");
        ListNode listNode2 = new ListNode("2");
        System.out.println(listNode1.compareTo(listNode2));
        System.out.println(listNode2.compareTo(listNode2));
        System.out.println(listNode2.compareTo(listNode1));
    }
}
