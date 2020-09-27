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
                head.setPrevNode(newNode);
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
        boolean stop = false;
        do {
            if(listNode.equals(o)) {
                if (size == 1) {
                    clear();
                    System.out.println("Nur ein element");
                } else if (head.equals(o)) {
                    System.out.println("head equals o: " + o + " " + head.getData());
                    ListNode nextNode = listNode.getNextNode();
                    ListNode prevNode = listNode.getPrevNode();
                    head = nextNode;
                    head.setPrevNode(prevNode);
                    prevNode.setNextNode(head);
                    size--;
                } else {
                    System.out.println("equals o: " + o + " " + listNode.getData());

                    ListNode nextNode = listNode.getNextNode();
                    ListNode prevNode = listNode.getPrevNode();
                    prevNode.setNextNode(nextNode);
                    nextNode.setPrevNode(prevNode);
                    size--;
                }
                stop = true;
            }
            listNode = listNode.getNextNode();
        } while(listNode != head && !stop);

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
