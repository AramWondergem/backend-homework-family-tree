package org.wonderboom;

public class Pet {
    private String name;
    private int age;
    private String species;
    private Person owner;

    public Pet(){}

    public Pet(String name) {
        this.name = name;
    }

    public Pet(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public void setOwner(Person owner){
        if(this.owner==null||!this.owner.equals(owner)) {
            if (this.owner!=null){
                this.owner.removePet(this);
            }
            this.owner = owner;
            owner.addPet(this);
        }
    }

    public Person getOwner() {
        return owner;
    }

    public void removeOwner() {
        Person oldOwner=this.owner;
        this.owner=null;
        oldOwner.removePet(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
