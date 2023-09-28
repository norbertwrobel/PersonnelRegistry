package org.example.registry;

import org.example.person.Person;
import org.example.person.PersonInterface;

import java.util.List;
import java.util.stream.Collectors;

public class RegistryService implements PersonInterface {

    private final RegistryDataAccessService registryDataAccessService;

    public RegistryService (RegistryDataAccessService registryDataAccessService){
        this.registryDataAccessService = registryDataAccessService;
    }

    public void validCheckForCU(Person person){
        if (person == null) {
            throw new IllegalArgumentException("The person cannot be null.");
        }
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (person.getLastName() == null || person.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (person.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than zero.");
        }
        if (person.getGender() == null) {
            throw new IllegalArgumentException("Gender cannot be null.");
        }
        if (person.getAddress() == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        if (person.getAddress().getZipCode() == null || person.getAddress().getZipCode().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty.");
        }
        if (person.getAddress().getCity() == null || person.getAddress().getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty.");
        }
        if (person.getAddress().getStreet() == null || person.getAddress().getStreet().isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty.");
        }
        if (person.getAddress().getHouseNumber() == null || person.getAddress().getHouseNumber().isEmpty()) {
            throw new IllegalArgumentException("House number cannot be null or empty.");
        }
        if (person.getAddress().getApartmentNumber() == null || person.getAddress().getApartmentNumber().isEmpty()) {
            throw new IllegalArgumentException("Apartment number cant be null or empty.");
        }
    }

    @Override
    public void addPerson(Person person) {
        validCheckForCU(person);
        registryDataAccessService.getPeople().add(person);
    }

    public List<Person> getPeople() {
        if (registryDataAccessService.getPeople() == null || registryDataAccessService.getPeople().isEmpty()) {
            throw new IllegalStateException("No people found in the registry.");
        }
        return registryDataAccessService.getPeople();
    }

    public Person getPerson(int index) {
        if (index < 0 || index >= registryDataAccessService.getPeople().size()) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        return registryDataAccessService.getPeople().get(index);
    }

    @Override
    public void updatePerson(int index, Person newPerson) {
        if (index < 0 || index >= registryDataAccessService.getPeople().size()) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        validCheckForCU(newPerson);
        registryDataAccessService.getPeople().set(index, newPerson);
    }

    @Override
    public void deletePerson(int index) {
        if (index < 0 || index >= registryDataAccessService.getPeople().size()) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        registryDataAccessService.getPeople().remove(index);
    }

    public List<Person> searchPeople(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();        // case insensitive search
        return registryDataAccessService.getPeople().stream()
                .filter(person -> person.getFirstName().toLowerCase().contains(lowerCasePattern)
                        || person.getLastName().toLowerCase().contains(lowerCasePattern)
                        || String.valueOf(person.getAge()).contains(lowerCasePattern)
                        || person.getGender().toLowerCase().contains(lowerCasePattern)
                        || person.getAddress().getZipCode().toLowerCase().contains(lowerCasePattern)
                        || person.getAddress().getCity().toLowerCase().contains(lowerCasePattern)
                        || person.getAddress().getStreet().toLowerCase().contains(lowerCasePattern)
                        || person.getAddress().getHouseNumber().toLowerCase().contains(lowerCasePattern)
                        || person.getAddress().getApartmentNumber().toLowerCase().contains(lowerCasePattern))
                .collect(Collectors.toList());
    }
}
