package org.billing.jlab.xo.model;

import java.awt.*;

public class Field {

    private static final int FIELD_SIZE = 3;
    public static final int MIN_FIEND_SIZE = 0;
    public static final int MAX_FIELD_SIZE = FIELD_SIZE;

    private final Figure[][] field = new Figure[FIELD_SIZE][FIELD_SIZE];

    public int getFieldSize() {
        return FIELD_SIZE;
    }

    public Figure getFigure(final Point point) {
        return field[point.x][point.y];
    }

    public void setFigure(final Figure figure, final Point point) {
        field[point.x][point.y] = figure;
    }

    private boolean checkPoint(final Point point) {
        return checkCoordinate(point.x) && checkCoordinate(point.y);
    }

    private boolean checkCoordinate(final int coordinate) {
        return coordinate >= MIN_FIEND_SIZE && coordinate <= MAX_FIELD_SIZE;
    }

}
