package ru.academits.ignatov.tree;

public class TreeNode<E> {
    private TreeNode<E> right;
    private TreeNode<E> left;
    private E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> leftChild) {
        left = leftChild;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> rightChild) {
        right = rightChild;
    }
}