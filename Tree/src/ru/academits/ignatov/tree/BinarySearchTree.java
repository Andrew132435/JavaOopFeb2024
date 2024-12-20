package ru.academits.ignatov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<? super E>) data1).compareTo(data2);
    }

    public void insert(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        TreeNode<E> node = root;

        while (true) {
            int comparisonResult = compare(data, node.getData());

            if (comparisonResult < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }

                node = node.getLeft();
            } else {
                if (node.getRight() == null) {
                    node.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }

                node = node.getRight();
            }
        }
    }

    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> removedNode = root;
        TreeNode<E> removedNodeParent = null;
        boolean isLeft = false;

        while (removedNode != null) {
            int comparisonResult = compare(data, removedNode.getData());

            if (comparisonResult == 0) {
                break;
            }

            removedNodeParent = removedNode;

            if (comparisonResult < 0) {
                isLeft = true;
                removedNode = removedNode.getLeft();
            } else {
                isLeft = false;
                removedNode = removedNode.getRight();
            }
        }

        if (removedNode == null) {
            return false;
        }

        TreeNode<E> successorNode;

        if (removedNode.getLeft() == null || removedNode.getRight() == null) {
            successorNode = (removedNode.getLeft() == null) ? removedNode.getRight() : removedNode.getLeft();
        } else {
            TreeNode<E> minNode = removedNode.getRight();
            TreeNode<E> minNodeParent = removedNode;

            while (minNode.getLeft() != null) {
                minNodeParent = minNode;
                minNode = minNode.getLeft();
            }

            if (minNodeParent != removedNode) {
                minNodeParent.setLeft(minNode.getRight());
                minNode.setRight(removedNode.getRight());
            }

            minNode.setLeft(removedNode.getLeft());
            successorNode = minNode;
        }

        if (removedNodeParent == null) {
            root = successorNode;
        } else if (isLeft) {
            removedNodeParent.setLeft(successorNode);
        } else {
            removedNodeParent.setRight(successorNode);
        }

        size--;
        return true;
    }

    public int getSize() {
        return size;
    }

    public void traverseBreadthFirst(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();
            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseDepthFirstRecursively(Consumer<E> consumer) {
        traverseDepthFirstRecursively(root, consumer);
    }

    private void traverseDepthFirstRecursively(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        traverseDepthFirstRecursively(node.getLeft(), consumer);
        traverseDepthFirstRecursively(node.getRight(), consumer);
    }

    public void traverseDepthFirst(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            consumer.accept(node.getData());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        traverseBreadthFirst(data -> stringBuilder.append(data).append(", "));

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}