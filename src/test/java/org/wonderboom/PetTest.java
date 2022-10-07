package org.wonderboom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    @Test

    void when_OwnerIsAdded_Expect_PetsOfOwnerContainThisPetAndTheOwnerOfthisPetisOwner (){
        //Arrange
        Person p = new Person("Hans");
        Pet pet = new Pet("snah");
        //Act
        pet.setOwner(p);
        //Assert
        assertTrue(p.getPets().contains(pet));
        assertSame(p,pet.getOwner());
    }

    @Test

    void when_petIsRemoved_Expect_ThisPetsNotContainPetANDOwnerOfPetIsNull() {
        //Arrange
        Person p = new Person("Hans");
        Pet pet = new Pet("snah");
        pet.setOwner(p);
        //Act
        pet.removeOwner();
        //Assert
        assertFalse(p.getPets().contains(pet));
        assertSame(null,pet.getOwner());
    }

}