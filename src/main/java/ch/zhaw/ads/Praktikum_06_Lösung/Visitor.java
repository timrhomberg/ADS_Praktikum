package ch.zhaw.ads.Praktikum_06_Lösung;

/* interface of visitor ADT */
public interface Visitor<T extends Comparable<T>> {
    /* called for each element in the tree */
    public void visit(T obj);
}
