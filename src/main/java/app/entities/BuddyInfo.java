package app.entities;

import app.entities.AddressBook;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class BuddyInfo implements Serializable
{
    public BuddyInfo()
    {
        this("", "", "");
    }

    public BuddyInfo(String name, String address, String number)
    {
        setName(name);
        setAddress(address);
        setPhoneNumber(number);
    }

    @Id
    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    private AddressBook addressBook;

    public AddressBook getAddressBook()
    {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}