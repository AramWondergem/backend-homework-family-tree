package org.wonderboom;

import java.sql.SQLOutput;
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
            for (Person p : parents.get("mother")) {
                if (!this.containsParent(p)) {
                    this.mothers.add(p);
                } else {
                    System.out.println(p.getName() + " is already your one of your parents");
                }
            }
        } else if (parents.containsKey("father")) {
            for (Person p : parents.get("father")) {
                if (!this.containsParent(p)) {
                    this.fathers.add(p);
                }else {
                    System.out.println(p.getName() + " is already your one of your parents");
                }
            }
        }

        for (List<Person> list : parents.values()) {
            for (Person p : list) {
                if (!p.containsChild(this)) {
                    p.addChild(this);
                }
            }
        }
    }

    public void addOneParent(Person parent) {
        if (!this.containsParent(parent)) {
            if (parent.getSex()=='m') {
                fathers.add(parent);
            } else if (parent.getSex()=='v') {
                mothers.add(parent);
            } else {
                System.out.println("Give " + parent.getName() + " a sex, otherwise she will not be added as a parent of " + this.getName());
            }

            if (!parent.containsChild(this)) {
                parent.addChild(this);
            }
        }

    }

    public boolean containsParent(Person parent) {
        boolean placeholderContainsParent = false;
        for (Person p : fathers) {
            if (p == parent) {
                placeholderContainsParent = true;
            }
        }
        for (Person p : mothers) {
            if (p == parent) {
                placeholderContainsParent = true;
            }
        }
        return placeholderContainsParent;
    }

    public void addChild(Person child) {
        if (!this.containsChild(child)) {
            this.children.add(child);
        } else {
            System.out.println(child.getName() + " is already a child of " + this.name);
        }
        if (!child.containsParent(this)) {
            child.addOneParent(this);
        }
    }

    public boolean containsChild(Person child) {
        boolean placeholderContainsChild = false;
        for (Person p : children) {
            if (p == child) {
                placeholderContainsChild = true;
            }
        }
        return placeholderContainsChild;
    }


    public void addPet(Pet pet) {
        if (!pets.contains(pet)) {
            this.pets.add(pet);
            pet.setOwner(this);
        }
    }

    public void removePet(Pet pet) {
        if (this.pets.contains(pet)) {
            this.pets.remove(pet);
        }
        if (pet.getOwner() != null && pet.getOwner().equals(this)) {
            pet.removeOwner();
        }
    }

    public void addSibling(Person sibling) {
        if (!this.containsSibling(sibling)) {
            this.sibilings.add(sibling);
        } else {
            System.out.println(sibling.getName() + " is already a sibling of " + this.name);
        }
        if (!sibling.containsSibling(this)) {
            sibling.addSibling(this);
        }
    }

    public boolean containsSibling(Person sibling) {
        boolean placeholderContainsSibling = false;
        for (Person p : sibilings) {
            if (p == sibling) {
                placeholderContainsSibling = true;
            }
        }
        return placeholderContainsSibling;
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
