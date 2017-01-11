package com.example.user.ezwalk_list;



public class User {
    private int age=0;
    private String gender;
    private String interest;
    private String saying;
    private int pertime;
    private int perdistance;
    private String name;
    public User() {
    }

    public User(int age,String gender,String interest,String saying,int pertime,int perdistance,String name) {
        this.age = age;
        this.gender=gender;
        this.interest=interest;
        this.saying=saying;
        this.pertime=pertime;
        this.perdistance=perdistance;
        this.name=name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender(){return gender;}

    public void setGender(String gender){this.gender=gender;}

    public String getInterest(){return interest;}

    public void setInterest(String interest){this.interest=interest;}

    public String getSaying(){return saying;}

    public void setSaying(String saying){this.saying=saying;}

    public int getPertime(){return pertime;}

    public void setPertime(int pertime){this.pertime=pertime;}

    public int getPerdistance(){return perdistance;}

    public void setPerdistance(int perdistance){this.perdistance=perdistance;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}
}
