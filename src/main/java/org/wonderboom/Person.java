package org.wonderboom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Person {
    private String name, middleName, lastName;
    private char sex;
    private int age;
    private List<Person> mothers = new ArrayList<>();
    private List<Person> fathers = new ArrayList<>();
    private List<Person> sibilings = new ArrayList<>();
    private List<Person> children = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();

    public Person(String name) {
        this.name=name;
    }

    public Person(String name, String lastname, int age, char sex) {
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.sex = sex;
    }

    public Person(String name, String middleName, String lastname, int age, char sex) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastname;
        this.age = age;
        this.sex = sex;
    }

    // Own methods
    public void addParents(Map<String, List<Person>> parents) {
        if (parents.containsKey("mother")) {
            this.mothers.addAll(parents.get("mother"));
        } else if (parents.containsKey("father")) {
            this.fathers.addAll(parents.get("father"));
        }
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public void addPet(Pet pet) {
        if(!pets.contains(pet)) {
            this.pets.add(pet);
            pet.setOwner(this);
        }
    }

    public void removePet(Pet pet) {
        if (this.pets.contains(pet)){
            this.pets.remove(pet);
        }
        if (pet.getOwner()!=null&&pet.getOwner().equals(this)){
            pet.removeOwner();
        }
    }

    public void addSibling(Person sibling) {
        this.sibilings.add(sibling);
    }

    public List<Person> getGrandChildren() {
        List<Person> grandChildren = new ArrayList<>();

        for (Person child : children) {
            grandChildren.addAll(child.getChildren());
        }

        return grandChildren;
    }

    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getMothers() {
        return mothers;
    }

    public void setMothers(List<Person> mothers) {
        this.mothers = mothers;
    }

    public List<Person> getFathers() {
        return fathers;
    }

    public void setFathers(List<Person> fathers) {
        this.fathers = fathers;
    }

    public List<Person> getSibilings() {
        return sibilings;
    }

    public void setSibilings(List<Person> sibilings) {
        this.sibilings = sibilings;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
