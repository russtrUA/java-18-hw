package ua.goit.homework8.print;

import ua.goit.homework8.shape.Drawable;
import ua.goit.homework8.shape.Shape;

public interface Printer {
    void printName(Shape shape);
    void draw(Drawable drawable);
}
