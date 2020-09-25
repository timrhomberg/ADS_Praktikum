package ch.zhaw.ads.Praktikum_02;

import java.util.*;

public class MyList extends AbstractList<Object> {
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
            }
            return true;
        } else {
            return false;
        }
    }

    public ListNode getLastNode() {
        ListNode listNode = head;
        while(listNode.getNextNode() != null){
            listNode = listNode.getNextNode();
        }
        return listNode;
    }

    @Override
    public boolean remove(Object o) {
        ListNode listNode = head;
        while(listNode.getNextNode() != null && !listNode.getData().equals(o)){
            if(listNode.getNextNode().getData().equals(o)){
                if(listNode.getNextNode().getNextNode()!=null){
                    listNode.setNextNode(listNode.getNextNode().getNextNode());
                    listNode.getNextNode().setPrevNode(listNode);
                }else{
                    listNode.getNextNode();
                    break;
                }
            }
            listNode = listNode.getNextNode();
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
