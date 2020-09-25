package ch.zhaw.ads.Praktikum_02;

import java.util.*;

public class MyList extends AbstractList {
    private ListNode head;
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(Object o) {
        if (head == null) {
            add(0, o);
        } else {
            ListNode n = new ListNode(o);
            ListNode f = head;
            while(f.next != null) f=f.next;
            f.next = n;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int pos = 0;
        if (pos == 0) head = head.next;
        else {

        }
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get (int pos) {
        ListNode node = this.head;
        while (pos > 0) {
        node = node.next;
        pos--;
        }
        return node.data;
    }
}
