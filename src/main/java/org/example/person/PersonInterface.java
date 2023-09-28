package org.example.person;

import java.util.List;

public interface PersonInterface {
    void addPerson(Person person);
    void updatePerson(int index, Person newPerson);
    void deletePerson(int index);
    Person getPerson(int index);
    List<Person> getPeople();
}
