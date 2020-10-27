package ch.zhaw.ads.Praktikum_06;

class TreeNode<T extends Comparable<T>> {
    T element;
    TreeNode left, right;
    int height;
    int count;

    TreeNode(T element){
        this.element = element;
        this.count = 1;
        this.height = 1;
    }

    TreeNode(T element, TreeNode left, TreeNode right){
        this(element); this.left = left; this.right = right;
    }

    T getValue(){return element;}
}