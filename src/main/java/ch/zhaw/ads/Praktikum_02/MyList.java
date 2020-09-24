package ch.zhaw.ads.Praktikum_02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList implements List {
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
        if (pos == 0) head = head.next;
        else {

        }
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
