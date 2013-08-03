package com.lagnada.xmx1024.controller.report;

public class Customer
{

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Double balance;
    private String notes;

    public Customer()
    {
    }

    public Customer(Long id)
    {
        this.id = id;
    }

    public Customer getMe()
    {
        return this;
    }

    public void setMe(Customer customer)
    {
        // do nothing
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
