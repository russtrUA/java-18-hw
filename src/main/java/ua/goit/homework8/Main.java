package ua.goit.homework8;

import ua.goit.homework8.print.*;
import ua.goit.homework8.shape.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {new Circle(), new Square(), new Rectangle(), new Triangle(), new Ellipse(), new Octagon()};
        Printer printer = new ConsolePrinter();
        for (Shape shape : shapes) {
            printer.printName(shape);
            printer.draw(shape);
        }
    }
}
