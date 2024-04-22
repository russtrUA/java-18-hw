package ua.goit.homework9.linkedlist;

import java.util.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> nums = new MyLinkedList<>();
        nums.add("0");
        nums.add("1");
        nums.add("2");
        nums.add("3");
        nums.add("4");
        nums.add("5");
        nums.add("6");
        nums.add("7");
        nums.add("8");

//        nums.add("6");
//        System.out.println("nums.get(4) = " + nums.get(9));
//        System.out.println(
//        "nums.size() = " + nums.size());
//        System.out.println(nums);
        nums.remove(8);
        nums.remove(7);
        nums.remove(2);
        nums.remove(4);
        System.out.println(nums.remove(0));
        System.out.println(nums);
        nums.clear();
        System.out.println(nums);
        nums.add("Vasyl'");
        nums.add("Petro");
        System.out.println(nums);
        System.out.println(nums.remove(0));
        System.out.println("nums.size() = " + nums.size());

//        LinkedList
    }
}
