package com.nexign.runtimeAnnotations;

import java.awt.event.*;
import java.lang.reflect.*;

/**
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
public class ActionListenerInstaller
{
   /**
    * Processes all ActionListenerFor annotations in the given object.
    * @param obj an object whose methods may have ActionListenerFor annotations
    */
   public static void processAnnotations(Object obj)
   {
      try
      {
         Class<?> cl = obj.getClass();
         for (Method m : cl.getDeclaredMethods())
         {
            ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
            if (a != null)
            {
               Field f = cl.getDeclaredField(a.source());
               f.setAccessible(true);
               Object source = f.get(obj); // источник событий JButton, экземпляр которого указан в source = "yellowButton"

               /*
                 Здесь для каждой аннотации создаётся прокси-объект и уже он добавляется к источнику событий
                 присоединяем источник f.get(obj) к приёмнику obj на метод 'm'
                 в данном случае, каждому объекту кнопки, класс которой указан в аннотации, создаётся прокси-объект для приёмника
               */
               addListener(source, obj, m);
            }
         }
      }
      catch (ReflectiveOperationException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Adds an action listener that calls a given method.
    * @param source источник событий, к которому добавляется прослушиватель действий
    * @param reseiver неявный параметр метода, вызываемого прослушивателем
    * @param m метод, который вызывает слушатель
    */
   public static void addListener(Object source, final Object reseiver, final Method m)
         throws ReflectiveOperationException
   {

      /*
        это по сути обёртка - прокси объект к приёмнику событий
        Но тут следующий финт ушами: прокси является анонимным классом, внутри которого доступны  все локальные конечные переменные, а именно:
          'reseiver' - объект приёмник и
          'm' - метод при созданиии этого прокси.
        таким образом, когда для прокси вызывается метод actionPerformed в источнике, то он перехватывается в invoke, а в
        нём, в конечном счете, вызывается ИМЕННО метод 'm' на исходном объекте, а не метод actionPerformed
        Собственно это и есть идея прокси обектов - управлять доступом к объекту, где мы перехватываем вызов actionPerformed и вместо него вызваем нужный нам метод на приёмнике.
      */
      InvocationHandler handler = new InvocationHandler()
         {
            /*
            * МЕТОДЫ интерфейса ActionListener.class БУДУТ ПЕРЕХВАТЫВАТЬСЯ InvocationHandler-ом.
            * mm - java.awt.event.ActionListener.actionPerformed(java.awt.event.ActionEvent)
            * proxy - это обект текущиго класса ActionListenerInstaller
            * args - обект ActionEvent передаваемый при вызове actionPerformed
            */
            public Object invoke(Object proxy, Method mm, Object[] args) throws Throwable
            {
               return m.invoke(reseiver);  // здесь вызывается метод m - yellowBackground() без аргументов на приёмнике - объекте ButtonFrame передаваемом в reseiver.
            }
         };

      Object listener = Proxy.newProxyInstance(null, new Class[] { java.awt.event.ActionListener.class }, handler);

      Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
      adder.invoke(source, listener); // добавляем прокси-объект к источнику событий, т.е. вызываем метод myButton.addActionListener(proxyObj) у кнопки
      /* ActionListener по сути функциональный интерфейс с одним методом actionPerformed. Когда кнопка его вызывает, управление передаётся в метод invoke прокси объекта

       */
   }
}
