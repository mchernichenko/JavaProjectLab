package org.billing.jlab.rmq.model;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {

    private static Player defaultPlayer;
    private static String defaultName;
    private static Figure defaultFigure;

    @BeforeClass
    public static void initClass() {
        defaultPlayer = new Player(defaultName, defaultFigure);
    }

    @Test
    public void getFigure() throws Exception {

        final Figure inputValue = Figure.X;
        final Figure expectedValue = inputValue;
        final Player player = new Player(null, inputValue);
        final Figure actualValue = player.getFigure();

        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void getName() throws Exception {
        /*
        Тестируем метод getName класса Player. Суть теста: создать экзампляр объекта куда передать на вход значение тестируемого атрибута
        Из геттера ожидаем получить такое же значение атрибута.
        Сравнимаем ожидаемое с актуальным значением
         */
        final String inputValue = "PlayerName";
        final String expectedValue = inputValue;
        final Player player = new Player(inputValue, null);
        final String actualValue = player.getName();

        assertEquals(expectedValue, actualValue);

    }

//    @Test
//    public void constructorPlayer() throws Exception {
//
//        Player actualPlayer = new Player(defaultPlayer.getName(), defaultPlayer.getFigure());
//        assertEquals("new Player(defaultReply) has to be equals defaultPlayer", defaultPlayer, actualPlayer);
//    }
}