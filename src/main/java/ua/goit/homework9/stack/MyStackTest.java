package ua.goit.homework9.stack;

import java.util.ArrayDeque;
import java.util.Stack;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(3);
        stack.push(2);
        stack.push(6);
        stack.push(4);
        stack.push(6);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
