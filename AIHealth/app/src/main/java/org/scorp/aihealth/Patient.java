package org.scorp.aihealth;

import java.io.Serializable;

public class Patient implements Serializable {
    private String username;
    private String name;
    private int age;
    private String ph_no;

    Patient(String username, String name, int age, String ph_no) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.ph_no = ph_no;
    }

    public String getUsername() { return username; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getPh_no() { return ph_no; }
}
