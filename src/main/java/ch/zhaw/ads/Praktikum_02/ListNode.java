package ch.zhaw.ads.Praktikum_02;

import java.util.Objects;

public class ListNode {
    Object data;
    ListNode next, prev;

    ListNode (Object o) {
        data = o;
        next = null;
        prev = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return data.equals(listNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
