package course.requirements.two;

import java.util.Iterator;

/**
 * Created by hans on 18.03.16.
 */
public interface List<T> extends Container<T> {

    boolean add(T value);
    void insert(int index, T value);
    boolean contains(T value);
    T get(int index);
    int indexTo(T value);
    T update(int index, T value);
    boolean remove(T value);
    T remove(int index);
    int size();
    boolean isEmpty();
    void clear();
    Iterator<T> iterator();

    default String message(int index)
    {
        return "Index: " + index + ", Size: " + size();
    }

    default void indexControl(int index, boolean insert)
    {
        if (index < 0 ? true : (insert ? index > size() : index >= size()))
            throw new IndexOutOfBoundsException(message(index));
    }

}
