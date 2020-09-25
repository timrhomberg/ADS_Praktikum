package ch.zhaw.ads.Praktikum_02;

public class ListNode {
    Object data;
    ListNode next, prev;

    ListNode (Object o) {
        data = o;
        next = null;
    }

    public void setNextNode(ListNode next) {
        this.next = next;
    }

    public void setPrevNode(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNextNode() {
        return next;
    }

    public ListNode getPrevNode() {
        return this.prev;
    }

    public Object getData() {
        return data;
    }
}
