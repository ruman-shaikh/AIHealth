package org.scorp.aihealth;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String username;
    private String name;
    private String specialization;
    //private String organization;
    private String address;
    private int age;
    private String ph_no;

    Doctor(String username, String name, int age, String specialization, String address, String ph_no) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.specialization = specialization;
        this.address = address;
        this.ph_no = ph_no;
    }

    public int getAge() { return age; }

    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getPh_no() { return ph_no; }

    public String getAddress() { return address; }

    public String getSpecialization() { return specialization; }
}

