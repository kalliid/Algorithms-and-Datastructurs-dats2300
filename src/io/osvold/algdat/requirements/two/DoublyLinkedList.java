package io.osvold.algdat.requirements.two;

import io.osvold.algdat.requirements.interfaces.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by hans on 18.03.16.
 */
public class DoublyLinkedList<T> implements List<T> {

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
    public T update(int index, T value)
    {
        Objects.requireNonNull(value);

        indexControl(index, false);

        Node<T> temp = findNode(index);

        T old = temp.value;
        temp.value = value;
        numberOfChanges++;
        return old;
    }

    @Override
    public boolean remove(T value)
    {
        if(value == null || size == 0)
            return false;

        if(size == 1)
        {
            if(value.equals(head.value))
            {
                head.value = null;
                head = null;
                numberOfChanges++;
                size--;
                return true;
            }

            return false;
        }

        Node<T> q = head, p = null;

        while(q != null)
        {
            if(q.value.equals(value)) break;
            p = q;
            q = q.next;
        }

        if(q == null) return false;

        if(q == head)
        {
            head = head.next;
            head.previous = null;
        }
        else if(q == tail)
        {
            tail = p;
            tail.next = null;

        } else {
            p.next = q.next;
            p.next.previous = p;
        }

        q.value = null;
        q.previous = null;
        q.next = null;
        size--;
        numberOfChanges++;

        return true;

    }

    @Override
    public T remove(int index) {

        indexControl(index, false);

        if(size == 0)
            return null;

        if(index == 0)
        {
            if(size == 1)
            {
                Node<T> temp = head;
                head = tail = null;
                numberOfChanges++;
                size--;
                return temp.value;
            }

            Node<T> temp = head;
            head = head.next;
            head.previous = null;
            numberOfChanges++;
            size--;
            return temp.value;
        }

        if(index == (size - 1))
        {
            Node<T> temp = tail;
            tail = tail.previous;
            tail.next = null;
            numberOfChanges++;
            size--;
            return temp.value;
        }

        Node<T> temp = findNode(index);
        Node<T> p = temp.previous;
        p.next = p.next.next;
        p.next.previous = p;
        numberOfChanges++;
        size--;
        return temp.value;
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
        for(Node<T> temp = head; temp != null; temp = temp.next)
        {
            temp.value = null;
            if(temp.previous != null)
                temp.previous.next = null;
            temp.previous = null;
        }

        head = tail = null;

        size = 0;

        numberOfChanges++;
    }

    @Override
    public String toString()
    {
        Node<T> temp = head;
        StringBuilder sb = new StringBuilder("[");


        while(temp != null)
        {
            sb.append(temp.value);
            if(temp.next != null)
                sb.append(",");

            temp = temp.next;
        }

        sb.append("]");

        return sb.toString();
    }

    public String reversedString()
    {
        Node<T> temp = tail;
        StringBuilder sb = new StringBuilder("[");

        while(temp != null)
        {
            sb.append(temp.value);
            if(temp.previous != null)
                sb.append(",");

            temp = temp.previous;
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    public Iterator<T> iterator(int index){
        return new DoublyLinkedListIterator(index);
    }

    private class DoublyLinkedListIterator implements Iterator<T>
    {
        public Node<T> current;
        private boolean removeOk;
        private int expectedNumChanges;

        private DoublyLinkedListIterator()
        {
            current = head;
            removeOk = false;
            expectedNumChanges = numberOfChanges;
        }

        private DoublyLinkedListIterator(int index)
        {
            indexControl(index, false);
            current = head;
            removeOk = false;
            expectedNumChanges = numberOfChanges;
            for(int i = 0; i < index; i++) next();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next()
        {
            if(!hasNext())
                throw new NoSuchElementException("The list has no more entries.");

            if(expectedNumChanges != numberOfChanges)
                throw new ConcurrentModificationException("expected number of changes: "
                        + expectedNumChanges + "\nactual number of changes: " + numberOfChanges);

            Objects.requireNonNull(current);
            removeOk = true;
            T temp = current.value;
            current = current.next;
            return temp;
        }

        public void remove()
        {
            if(!removeOk)
                throw new IllegalStateException("Cannot remove object before use of next()");

            if(expectedNumChanges != numberOfChanges)
                throw new ConcurrentModificationException("expected number of changes: "
                        + expectedNumChanges + "\nactual number of changes: " + numberOfChanges);

            removeOk = false;

            if(size == 1) head = tail = null;
            else if(current == null){
                tail = tail.previous;
                tail.next = null;
            }
            else if(current.previous == head){
                head = head.next;
                head.previous = null;
            } else {
                current.previous = current.previous.previous;
                current.previous.next = current;
            }

            size--;
            numberOfChanges++;
            expectedNumChanges++;
        }
    }
}
