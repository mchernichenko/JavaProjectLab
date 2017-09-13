package org.billing.jlab.pattern.builder;

/**
 * Created by Mikhail.Chernichenko on 10.09.2017.
 */
public class ContactBuilder {

    private String firstName;
    private String lastName;
    private String adderess;
    private String phone;

    public ContactBuilder firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactBuilder lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactBuilder adderess(final String address) {
        this.adderess = address;
        return this;
    }

    public ContactBuilder phone(final String phone) {
        this.phone = phone;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdderess() {
        return adderess;
    }

    public String getPhone() {
        return phone;
    }

    public Contact build() {
        return new Contact(this);
    }
}

