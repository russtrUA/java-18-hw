package ua.goit.homework8.print;

import ua.goit.homework8.shape.Drawable;
import ua.goit.homework8.shape.Shape;

public class ConsolePrinter implements Printer{
    @Override
    public void printName(Shape shape) {
        System.out.println("My name is " + shape);
    }

    @Override
    public void draw(Drawable drawable) {
        System.out.println(drawable + " has been drawn");
    }
}
