package ua.goit.homework9.arraylist;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> numbers = new MyArrayList<>();
        for (int i = 0; i < 10000; i++) {
            numbers.add(String.valueOf(i));
        }
        System.out.println(numbers);
        System.out.println("numbers.size() = " + numbers.size());
        System.out.println("numbers.get(15) = " + numbers.get(15));
        System.out.println("numbers.get(99) = " + numbers.get(99));
        numbers.remove(15);
        System.out.println("numbers = " + numbers);
        System.out.println("numbers.get(98) = " + numbers.get(98));
        System.out.println("numbers.size() = " + numbers.size());
        numbers.clear();
        System.out.println("numbers.size() = " + numbers.size());
        System.out.println("numbers = " + numbers);
        numbers.add("12");
        System.out.println("numbers.size() = " + numbers.size());

    }
}
