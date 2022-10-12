package org.wonderboom;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void when_containsSibling_Expect_TheListOfSiblingContainsReferenceToTheObjectSibling() {
        Person sibling = new Person("Hans", "Romy", "Smit", 16, 'v');
        Person person = new Person("Romy", "Smit", 16, 'v');
        sibling.addSibling(person);
        //Act
        boolean containsSibling = sibling.containsSibling(person);
        //Assert
        assertTrue(containsSibling);
        assertSame(person,sibling.getSibilings().get(0));
    }

    @Test
    void when_containsParent_Expect_TheListOfParentsContainsReferenceToTheObjectParent() {
        Person child = new Person("Hans", "Romy", "Smit", 16, 'v');
        Person parent = new Person("Romy", "Smit", 16, 'v');
        child.addOneParent(parent);
        //Act
        boolean containsParent = child.containsParent(parent);
        //Assert
        assertTrue(containsParent);
        assertSame(parent,child.getMothers().get(0));
    }

    @Test
    void when_containsChild_Expect_TheListOfChildsContainsReferenceToTheObjectChild() {
        Person child = new Person("Hans", "Romy", "Smit", 16, 'v');
        Person parent = new Person("Romy", "Smit", 16, 'v');
        parent.addChild(child);
        //Act
        boolean containsChild = parent.containsChild(child);
        //Assert
        assertTrue(containsChild);
        assertSame(child,parent.getChildren().get(0));
    }
    @Test
    void when_addParentsIsCalled_Expect_getMotherAndgetFatherReturnListWithMinimalOnePersonAndBothParentsHavetheChildInTheirChildlist() {
        //Arrange
        Person child = new Person("Greet","Smit",16,'m');
        Person mother1 = new Person("Linda","Smit",16,'v');
        Person mother2 = new Person("Romy","Smit",16,'v');
        List<Person> mothers = new ArrayList<>();
        mothers.add(mother1);
        mothers.add(mother2);
        Map<String, List<Person>> parents = new HashMap<>();
        parents.put("mother", mothers);
        // Act
        child.addParents(parents);
        // Assert
        assertEquals(2, child.getMothers().size());
        assertEquals(0, child.getFathers().size());
        assertTrue(mother1.containsChild(child));
        assertTrue(mother2.containsChild(child));
    }

    @Test
    void when_addChildIsCalled_Expect_getChildrenReturnsListWithOneMorePersonAndChildHadParentInParentList() {
        //Arrange
        Person child = new Person("Greet","Smit",16,'m');
        Person mother1 = new Person("Linda","Smit",16,'v');
        // Act
        int numberOfChilderenBefore = mother1.getChildren().size();
        mother1.addChild(child);
        int numberOfChildrenAfter = mother1.getChildren().size();
        //Assert
        assertEquals(1, numberOfChildrenAfter - numberOfChilderenBefore);
        assertTrue(child.containsParent(mother1));
    }

    @Test
    void when_PetIsAddedToPerson_Expect_PetAddedIsSameAsPetReturned() {
        //Arrange
        Pet hond = new Pet();
        Person person = new Person("Romy","Smit",16,'v');
        //Act
        person.addPet(hond);
        //Assert
        assertSame(hond, person.getPets().get(0));
    }

    @Test
    void when_SiblingIsAdded_Expect_PersonAddedIsSameAsSiblingReturned() {
        //Arrange
        Person sibling = new Person("Hans","Romy","Smit",16,'v');
        Person person = new Person("Romy","Smit",16,'v');
        //Act
        person.addSibling(sibling);
        //Assert
        assertSame(sibling, person.getSibilings().get(0));
        assertSame(person, sibling.getSibilings().get(0));
    }

    @Test
    void when_getGrandChildren_Expect_ListgrandChildrenContainAllPersonAddedToTheChilderenOfThePerson() {

        //Arrange
        Person grandMother = new Person("Greetje","Romy","Smit",16,'v');
        Person daugther = new Person("Lies","Romy","Smit",16,'v');
        Person daugther2 = new Person("Linda","Romy","Smit",16,'v');
        Person grandChild1 = new Person("Storm","Romy","Smit",16,'m');
        Person grandChild2 = new Person("Melody","Romy","Smit",16,'v');
        Person grandChild3 = new Person("Hans","Romy","Smit",16,'m');
        Person grandChild4 = new Person("Greetje junior","Romy","Smit",16,'v');
        grandMother.addChild(daugther);
        grandMother.addChild(daugther2);
        daugther.addChild(grandChild1);
        daugther.addChild(grandChild2);
        daugther2.addChild(grandChild3);
        daugther2.addChild(grandChild4);
        List<Person> realGrandChildren = new ArrayList<>();
        realGrandChildren.add(grandChild1);
        realGrandChildren.add(grandChild2);
        realGrandChildren.add(grandChild3);
        realGrandChildren.add(grandChild4);
        //Act
        List<Person> grandChildren = grandMother.getGrandChildren();
        //Assert
        assertArrayEquals(realGrandChildren.toArray(), grandChildren.toArray());
    }

    @Test

    void when_PetIsAdded_Expect_ThisPetsOfPersonContainPetAndTheOwnerOfPetisThisOwner (){
        //Arrange
        Person p = new Person("Hans","Romy","Smit",16,'v');
        Pet pet = new Pet("snah");
        //Act
        p.addPet(pet);
        //Assert
        assertTrue(p.getPets().contains(pet));
        assertSame(p,pet.getOwner());
    }

    @Test

    void when_petIsRemoved_Expect_ThisPetsNotContainPetANDOwnerOfPetIsNull() {
        //Arrange
        Person p = new Person("Hans","Romy","Smit",16,'v');
        Pet pet = new Pet("snah");
        p.addPet(pet);
        //Act
        p.removePet(pet);
        //Assert
        assertFalse(p.getPets().contains(pet));
        assertSame(null,pet.getOwner());
    }
}