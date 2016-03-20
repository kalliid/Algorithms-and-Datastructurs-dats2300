package io.osvold.algdat.requirements.interfaces;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by hans on 18.03.16.
 */

public interface Container<T> extends Iterable<T>
{
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(T t);
    boolean add(T t);
    boolean remove(T t);
    Iterator<T> iterator();

    default boolean removeIf(Predicate<? super T> predicate)
    {
        Objects.requireNonNull(predicate);

        boolean removed = false;

        for(Iterator<T> iterator = iterator(); iterator.hasNext(); )
        {
            if(predicate.test(iterator.next()))
            {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }
}
