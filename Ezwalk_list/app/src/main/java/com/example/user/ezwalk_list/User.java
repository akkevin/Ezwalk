package com.example.user.ezwalk_list;



public class User {
    private int age;
    private String gender;
    private String interest;
    private String Saying;
    private int Pertime;
    private int Perdistance;

    public User() {
    }

    public User(int age,String gender,String interest,String saying,int pertime,int perdistance) {
        this.age = age;
        this.gender=gender;
        this.interest=interest;
        this.Saying=saying;
        this.Pertime=pertime;
        this.Perdistance=perdistance;
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

    public String getSaying(){return Saying;}

    public void setSaying(String saying){this.Saying=saying;}

    public int getPertime(){return Pertime;}

    public void setPertime(int pertime){this.Pertime=pertime;}

    public int getPerdistance(){return Perdistance;}

    public void setPerdistance(int perdistance){this.Perdistance=perdistance;}
}
