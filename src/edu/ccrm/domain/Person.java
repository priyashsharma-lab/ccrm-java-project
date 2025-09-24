package edu.ccrm.domain;

abstract public class Person 
{
    protected String full_name;
    protected String email;
    Person(String full_name,String email)
    {
        this.full_name=full_name;
        this.email=email;
    }    
    abstract void getProfile();
};