package course.requirements.two;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by hans on 18.03.16.
 */
public class DoublyLinkedList<T> implements List<T>  {

    private static final class Node<T>
    {
        private T value;
        private Node<T> previous, next;

        private Node(T value, Node<T> previous, Node<T> next)
        {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int numberOfChanges;

    private Node<T> findNode(int index)
    {
        if(index < (size / 2))
        {
            Node<T> p = head;
            for(int i = 0; i < index; i++) p = p.next;
            return p;
        } else {
            Node<T> p = tail;
            for(int i = (size - 1); i > index; i--) p = p.previous;
            return p;
        }
    }

    public DoublyLinkedList()
    {
        head = tail = null;
        size = numberOfChanges = 0;
    }

    public DoublyLinkedList(T[] a)
    {
        if(a == null)
            throw new NullPointerException("Table is null.");

        for(T node : a)
        {
            if(node != null)
            {
                if(size == 0)
                {
                    head = new Node<>(node, null, null);
                    tail = head;
                    size++;
                } else {
                    Node<T> p = head;
                    while(p.next != null)
                        p = p.next;

                    p.next = new Node<>(node, p, null);
                    tail = p.next;
                    size++;
                }
            }
        }
    }

    @Override
    public boolean add(T value) {

        Objects.requireNonNull(value);

        if(size == 0)
        {
            head = new Node<>(value, null, null);
            tail = head;
            size++;
            numberOfChanges++;
            return true;
        }

        Node<T> temp = head;

        while(temp.next != null) temp = temp.next;

        temp.next = new Node<>(value, temp, null);
        tail = temp.next;

        numberOfChanges++;
        size++;

        return true;
    }

    @Override
    public void insert(int index, T value) {
        Objects.requireNonNull(value);

        if(index < 0 || ((head == null) && index > 0) || index > size)
            throw new IndexOutOfBoundsException("Error");

        if(index == 0 && size == 0)
        {

            head = new Node<>(value, null, null);
            tail = head;
            size++;
            numberOfChanges++;

        } else if(index == size){

            tail.next = new Node<>(value, tail, null);
            tail = tail.next;
            size++;
            numberOfChanges++;

        } else if(index == 0){

            Node<T> temp = head;

            head = new Node<>(value, null, head);
            head.next.previous = head;

            size++;
            numberOfChanges++;

        } else {

            Node<T> temp = head;
            Node<T> temp2 = head.next;
            int i = 1;
            while(i < index)
            {
                i++;
                temp = temp.next;
                temp2 = temp2.next;
            }

            temp.next = new Node<>(value, temp, temp.next);
            temp2.previous = temp.next;

            size++;
            numberOfChanges++;
        }
    }

    @Override
    public boolean contains(T value) {
        return indexTo(value) != -1;
    }

    @Override
    public T get(int index) {
        indexControl(index, false);
        return findNode(index).value;
    }

    @Override
    public int indexTo(T value) {
        if(value == null)
            return -1;

        Node<T> p = head;

        for(int i = 0; i < size; i++)
        {
            if(p.value.equals(value)) return i;
            p = p.next;
        }

        return -1;
    }

    @Override
    public T update(int index, T value) {
        return null;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not done yet.");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<T>
    {
        public Node<T> current;
        private boolean removeOk;
        private int expectedNumChanges;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException("Not done yet.");
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not done yet.");
        }
    }
}
