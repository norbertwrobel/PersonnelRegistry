package org.example;

import org.example.address.Address;
import org.example.person.Person;
import org.example.registry.RegistryDataAccessService;
import org.example.registry.RegistryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RegistryDataAccessService registryDataAccessService = new RegistryDataAccessService();
        RegistryService registryService = new RegistryService(registryDataAccessService);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Person Registry! 📔");
            System.out.println("1️⃣ Add a person ➕");
            System.out.println("2️⃣ Modify a person 🔁");
            System.out.println("3️⃣ Delete a person 🚷");
            System.out.println("4️⃣ Search for a person 🔎");
            System.out.println("5️⃣ Get all people 👨‍👨‍👦‍👦");
            System.out.println("6️⃣ Write data to file 📩");
            System.out.println("7️⃣ Exit ❌");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Person newPerson = new Person();
                    Address address = new Address();

                    int lastIndex = registryService.getPeople().size() - 1;
                    int newIndex = lastIndex + 1;

                    System.out.print("Enter the name, lastname, age, gender, zip code, city, street, house number, and apartment number of the new person (separated by spaces): ");
                    String input = scanner.nextLine();
                    String[] values = input.split(" ");
                    if (values.length == 9) {
                        newPerson.setIndex(newIndex);
                        newPerson.setFirstName(values[0]);
                        newPerson.setLastName(values[1]);
                        newPerson.setAge(Integer.parseInt(values[2]));
                        newPerson.setGender(values[3]);

                        address.setZipCode(values[4]);
                        address.setCity(values[5]);
                        address.setStreet(values[6]);
                        address.setHouseNumber(values[7]);
                        address.setApartmentNumber(values[8]);

                        newPerson.setAddress(address);

                        registryService.addPerson(newPerson);
                        System.out.println("Person added successfully.");
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the ID of the person you want to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    Person updatedPerson = new Person();
                    Address newAddress = new Address();

                    System.out.print("Enter the name, lastname, age, gender, zip code, city, street, house number, and apartment number of the person (separated by spaces): ");
                    input = scanner.nextLine();
                    values = input.split(" ");

                    if (values.length == 9) {
                        updatedPerson.setIndex(index);
                        updatedPerson.setFirstName(values[0]);
                        updatedPerson.setLastName(values[1]);
                        updatedPerson.setAge(Integer.parseInt(values[2]));
                        updatedPerson.setGender(values[3]);

                        newAddress.setZipCode(values[4]);
                        newAddress.setCity(values[5]);
                        newAddress.setStreet(values[6]);
                        newAddress.setHouseNumber(values[7]);
                        newAddress.setApartmentNumber(values[8]);

                        updatedPerson.setAddress(newAddress);

                        try {
                            registryService.updatePerson(index, updatedPerson);
                            System.out.println("Person updated successfully.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid index. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the index of the person to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    registryService.deletePerson(deleteIndex);
                    System.out.println("Person deleted successfully.");

                    break;
                case 4:
                    System.out.print("Enter the search pattern: ");
                    String searchPattern = scanner.nextLine();

                    List<Person> searchResults = registryService.searchPeople(searchPattern);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Search results:");
                            System.out.println(searchResults);

                    } else {
                        System.out.println("No matching people found.");
                    }
                    break;
                case 5:
                    System.out.println("All people in the registry:");
                    List<Person> people = registryDataAccessService.getPeople();
                    for (Person person : people) {
                        System.out.println(person);
                    }
                    break;
                case 6:
                    registryDataAccessService.serializePeople(registryDataAccessService.getPeople());
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

}