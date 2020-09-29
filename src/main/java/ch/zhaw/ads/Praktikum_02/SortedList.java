package ch.zhaw.ads.Praktikum_02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SortedList extends MyList {
    private ListNode head;
    private int size = 0;

    @Override
    public boolean add(Object o) {
        if (o != null) {
            if (head == null) {
                head = new ListNode(o);
                head.setNextNode(head);
                head.setPrevNode(head);

            } else {
                ListNode newNode = new ListNode(o);
                ListNode lastNode = getLastNode();
                lastNode.setNextNode(newNode);
                newNode.setPrevNode(lastNode);
                newNode.setNextNode(head);
                head.setPrevNode(newNode);
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

}
