package com.example.androidapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Human implements Parcelable {
    private String name;
    private String lastName;
    private String age;

    public Human(String name, String lastName, String age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    protected Human(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        age = in.readString();
    }

    public static Human saveHuman(String name, String surname, String age){
        return new Human(name, surname, age);
    }

    public static final Creator<Human> CREATOR = new Creator<Human>() {
        @Override
        public Human createFromParcel(Parcel in) {
            return new Human(in);
        }

        @Override
        public Human[] newArray(int size) {
            return new Human[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(lastName);
        parcel.writeString(age);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) &&
                Objects.equals(lastName, human.lastName) &&
                Objects.equals(age, human.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age);
    }
}
