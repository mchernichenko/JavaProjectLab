package org.billing.jlab.patterns.chainofresponsibility;

import java.io.Serializable;

/**
 * Интерфейс для определения владельца
 */
public interface Contract extends Serializable {
    public static final String SPACE = "  ";

    public String getFirstName();

    public String getLastName();

    public String getTitle();

    public String getOrganization();

    public void setFirstName(String firstName);

    public void setLastName(String lastName);

    public void setTitle(String title);

    public void setOrganization(String organization);
}
