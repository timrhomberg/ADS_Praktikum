package ch.zhaw.ads.Praktikum_02;

import java.util.*;

public class MyList extends AbstractList<Object> {
    private ListNode head;
    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

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
                head.setPrevNode(lastNode);
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    public ListNode getLastNode() {
        ListNode listNode = head;
        while(listNode.getNextNode() != head){
            listNode = listNode.getNextNode();
        }
        return listNode;
    }

    @Override
    public boolean remove(Object o) {
        ListNode listNode = head;
        if (o == null || head == null) return false;
        if (listNode.getNextNode() == head && listNode.getData().equals(o)) {
            clear();
            System.out.println("hhee");
            return true;
        }
        boolean stop = false;
        while (listNode.getNextNode() != head && !stop) {
            listNode = listNode.getNextNode();
            if(listNode.getData().equals(o)) {
                ListNode nextNode = listNode.getNextNode();
                ListNode prevNode = listNode.getPrevNode();
                //System.out.println("next node: " + nextNode);
                //System.out.println("prevNode: " + prevNode);
                prevNode.setNextNode(nextNode);
                nextNode.setPrevNode(prevNode);
                //System.out.println("size entfernen aktuel " + this.size);
                size--;
                //System.out.println("size entfernt danach " + this.size);
                System.out.println(head);
                stop = true;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
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
