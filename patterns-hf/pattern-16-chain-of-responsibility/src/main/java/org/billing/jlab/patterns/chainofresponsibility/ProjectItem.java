package org.billing.jlab.patterns.chainofresponsibility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Интерфейс Projectltem определяет общие методы для любого элемента, который может быть частью проекта
 */
public interface ProjectItem extends Serializable{
    public static final String EOL_STRING = System.getProperty("line.sepatator");

    public ProjectItem getParent();

    public Contract getOwner();

    public String getDetails();

    public ArrayList getProjectItems();

}
