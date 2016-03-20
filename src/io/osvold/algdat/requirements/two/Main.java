package io.osvold.algdat.requirements.two;

/**
 * Created by hans on 19.03.16.
 */
public class Main {

    public static void main(String[] args)
    {
        DoublyLinkedList<String> stringList = new DoublyLinkedList<>();
        System.out.println("stringList.isEmpty(): " + stringList.isEmpty());
        System.out.println("stringList.size(): " + stringList.size());
        stringList.add("hello");
        stringList.add("world");
        System.out.println("stringList.isEmpty(): " + stringList.isEmpty());
        System.out.println("stringList.size(): " + stringList.size());
        stringList.remove(1);
        System.out.println("stringList.isEmpty(): " + stringList.isEmpty());
        System.out.println("stringList.size(): " + stringList.size());
        stringList.remove(0);
        System.out.println("stringList.isEmpty(): " + stringList.isEmpty());
        System.out.println("stringList.size(): " + stringList.size());
        stringList.add("world");
        stringList.insert(0, "hello");
        System.out.println(stringList.toString());
        System.out.println(stringList.reversedString());
        DoublyLinkedList<String> os = new DoublyLinkedList<>();
        os.insert(0,"OS X");
        os.insert(0,"Ubuntu");
        os.insert(0,"Windows");
        os.insert(os.size(), "CentOs");
        os.insert(os.size(), "Fedora");
        System.out.println(os.toString());
        System.out.println(os.reversedString());

        for(String string : os)
            System.out.println(string);

    }

}
