package ua.goit.homework9.hashmap;

import java.util.HashMap;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> h = new MyHashMap<>(75);
        for (int i = 0; i < 100; i++) {
            h.put("str" + (i + 1), i + 1);
        }

        System.out.println("h.size() = " + h.size());
        h.put(null, null);
        h.put(null, 10);
        System.out.println("h.size() = " + h.size());
        System.out.println("h.remove(\"str2\") = " + h.remove("str2"));
        System.out.println("h.size() = " + h.size());
        System.out.println("h.remove(\"tr\") = " + h.remove("tr"));
        System.out.println("h.size() = " + h.size());
        System.out.println("h.remove(null) = " + h.remove(null));
        System.out.println("h.size() = " + h.size());
        System.out.println("h.get(\"str57\") = " + h.get("str57"));
        h.put(null, null);
        h.put(null, 12);
        h.clear();
        h.put("str", null);
        h.put("str4", 123);
        System.out.println("h.size() = " + h.size());
        System.out.println("h = " + h);



//        HashMap<String, Integer> h = new HashMap<>();
//        h.put("s", 1);
//        h.put("d", 2);
//        h.put("d", null);
//        h.put(null, 6);
//        h.put(null, 7);
//        h.clear();
//        h.remove(null);
//        System.out.println("h.remove(\"d\") = " + h.remove("d"));
//        System.out.println(h);
    }

}
