package org.billing.jlab.patterns.chainofresponsibility;

/**
 * Created by Миша on 09.10.2016.
 */
public class ContractImpl implements Contract {
    private String firstName;
    private String lastName;
    private String title;
    private String organization;

    /**
     *
     * @param firstName
     * @param lastName
     * @param title
     * @param organization
     */
    public ContractImpl(String firstName, String lastName, String title, String organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.organization = organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganization() {
        return organization;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "ContractImpl{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
