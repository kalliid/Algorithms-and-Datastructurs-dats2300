package io.osvold.algdat.requirements.three;

import io.osvold.algdat.requirements.interfaces.Container;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

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
    public boolean remove(T t) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
