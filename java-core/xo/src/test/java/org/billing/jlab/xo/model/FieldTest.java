package org.billing.jlab.xo.model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void getFieldSize() throws Exception {
        final Field field = new Field();

        assertEquals(3, field.getFieldSize());
    }

    @Test
    public void setFigure() throws Exception {
        final Field field = new Field();
        final Point inputPoint = new Point(0,0);
        final Figure inputFigure = Figure.O;

        field.setFigure(inputFigure, inputPoint);
        final Figure actualFigure = field.getFigure(inputPoint);

        assertEquals(inputFigure, actualFigure);
    }

    // Обычно геттер тестят вместе с сеттером
//    @Test
//    public void getFigure() throws Exception {
//    }
}