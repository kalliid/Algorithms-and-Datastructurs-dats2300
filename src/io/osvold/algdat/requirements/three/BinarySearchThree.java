package io.osvold.algdat.requirements.three;

import io.osvold.algdat.requirements.interfaces.*;

import java.util.*;

/**
 * Created by hans on 20.03.16.
 */
public class BinarySearchThree<T> implements Container<T> {

    private static final class Node<T>
    {
        private T value;
        private Node<T> left, right;
        private Node<T> root;

        private Node(T value, Node<T> left, Node<T> right, Node<T> root)
        {
            this.value = value;
            this.left = left;
            this.right = right;
            this.root = root;
        }

        private Node(T value, Node<T> root)
        {
            this(value, null, null, root);
        }

        @Override
        public String toString()
        {
            return "" + value;
        }
    }

    private Node<T> root;
    private int size;
    private int changes = 0;

    private final Comparator<? super T> comparator;

    public BinarySearchThree(Comparator<? super T> comparator)
    {
        root = null;
        size = 0;
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if(!isEmpty()) clear(root);
        root = null;
        size = 0;
    }

    private void clear(Node<T> p)
    {
        if(p.left != null)
        {
            clear(p.left);
            p.left = null;
        }
        if(p.right != null)
        {
            clear(p.right);
            p.right = null;
        }
        p.value = null;
        p.root = null;
    }

    @Override
    public boolean contains(T value) {
        if(value == null) return false;

        Node<T> p = root;

        while(p != null)
        {
            int cmp = comparator.compare(value, p.value);
            if(cmp < 0) p = p.left;
            else if(cmp > 0) p = p.right;
            else return true;
        }

        return false;
    }

    @Override
    public boolean add(T value) {
        Objects.requireNonNull(value);


        // p starts in the root
        Node<T> p = root, q = null;
        int cmp = 0;

        while(p != null) // continues until p is outside the three
        {
            // q is root of p
            q = p;

            cmp = comparator.compare(value, p.value);

            // moves p down the three
            p = cmp < 0 ? p.left : p.right;
        }

        // p is now null, outside the three, q is the last passed Node.

        p = new Node<>(value, q);

        if(q == null) root = p;
        else if(cmp < 0) q.left = p;
        else q.right = p;

        size++;
        changes++;

        return true;
    }

    @Override
    public boolean remove(T value) {
        if(value == null)
            return false;

        Node<T> p = root, q = null;

        while(p != null)
        {
            int cmp = comparator.compare(value, p.value);
            if(cmp < 0)
            {
                q = p;
                p = p.left;
            }
            else if(cmp > 0)
            {
                q = p;
                p = p.right;
            } else break;
        }

        if(p == null) return false;

        if(p.left == null || p.right == null)
        {
            Node<T> b = p.left != null ? p.left : p.right;

            if(b != null) b.root = q;

            if(p == root) root = b;
            else if(p == q.left) q.left = b;
            else q.right = b;
        } else {

            Node<T> s = p, r = p.right;
            while(r.left != null)
            {
                s = r;
                r = r.left;
            }

            p.value = r.value;

            if(r.right != null) r.right.root = s;

            if(s != p) s.left = r.right;
            else s.right = r.right;
        }

        size--;
        changes++;
        return true;
    }

    public int removeAll(T value)
    {
        int nRemoved = 0;
        while(remove(value)) nRemoved++;
        return nRemoved;
    }

    public int count(T value)
    {
        Node<T> p = root;
        int nValues = 0;

        while(p != null)
        {
            int cmp = comparator.compare(value, p.value);
            if(cmp < 0) p = p.left;
            else
            {
                if(cmp == 0) nValues++;
                p = p.right;
            }
        }
        return nValues;
    }

    private static <T> Node<T> nextInorder(Node<T> node)
    {
        if(node.right != null)
        {
            node = node.right;
            while(node.left != null) node = node.left;
        } else
            while(node.root != null && node == node.root) node = node.root;

        node = node.root;

        return node;
    }

    @Override
    public String toString()
    {
        if(isEmpty()) return "[]";

        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        Node<T> node = root;

        while(node.left != null) node = node.left;

        while(node != null) {
            stringJoiner.add(node.value.toString());
            node = nextInorder(node);
        }

        return stringJoiner.toString();
    }

    public String reversedString()
    {
        if(isEmpty()) return "[]";

        Stack<Node<T>> stack = new Stack<>();
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        Node<T> node = root;
        while(root.right != null)
        {
            stack.add(node);
            node = node.right;
        }

        while(true)
        {
            stringJoiner.add(node.value.toString());

            if(node.left != null)
            {
                node = node.left;
                while(node.right != null)
                {
                    stack.add(node);
                    node = node.right;
                }
            }
            else if(!stack.isEmpty()) node = stack.pop();
            else break;
        }

        return stringJoiner.toString();
    }

    public String rightBranch()
    {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        if(!isEmpty())
        {
            Node<T> node = root;
            while(true)
            {
                stringJoiner.add(node.value.toString());
                if(node.right != null) node = node.right;
                else if(node.left != null) node = node.left;
                else break;
            }
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LeafNodeIterator();
    }

    private class LeafNodeIterator implements Iterator<T>
    {

        private Node<T> p = root, q = null;
        private boolean removeOK = false;

        public LeafNodeIterator() {
            if (p == null) return;

            while (true) {
                if (p.left != null) p = p.left;
                else if (p.right != null) p = p.right;
                else break;
            }
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException("No more values left.");

            removeOK = true;

            q = p;

            while(true)
            {
                p = nextInorder(p);
                if(p == null || (p.left == null && p.right == null)) break;
            }

            return q.value;
        }
    }
}
