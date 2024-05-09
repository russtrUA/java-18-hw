package ua.goit.homework8.shape;

import ua.goit.homework8.print.Printer;

public abstract class Shape implements Drawable{
    public abstract String getName();

    @Override
    public void draw(Printer printer) {
        printer.draw(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}

