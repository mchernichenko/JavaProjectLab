package org.billing.jlab.xo.model;

public class Player {

    private final String name;
    private final Figure figure;

    public Player(final String name, final Figure figure) {
        this.name = name;
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }

    public String getName() {
        return name;
    }


}
