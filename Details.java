import java.util.Scanner;
import java.io.*;  

// details of a record

class Details {
    private String name;
    private int e_id;
    private int age;
    private String phone_no;
    private String designation;

    public Details(String name, int age, String phone_no, String designation) {
        this.e_id = (int) Math.floor(Math.random() * (1000 - 100 + 1 )+ 100);
        this.name = name;
        this.age = age;
        this.phone_no = phone_no;
        this.designation = designation;
    }

    public String getPhoneNo() {
        return this.phone_no;
    }

    public String getName() {
        return this.name;
    }

    public String getDesignation() {
        return this.designation;
    }

    public int getID() {
        return this.e_id;
    }

    public int getAge() {
        return this.age;
    }
}