package org.example.registry;

import lombok.Data;
import lombok.Getter;
import org.example.person.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class RegistryDataAccessService implements Serializable {

    private static final String FILE_PATH = "src/main/resources/registryDB.txt";
    /*private static List<Person> people = Arrays.asList(
            new Person(0,
                    "Norbert",
                    "Wrobel",
                    22,
                    "mezczyzna",
                    new Address(
                            "32-324",
                            "Konin",
                            "Kolorowa",
                            "3a",
                            "3"))
    );*/

    private List<Person> people = new ArrayList<>();

    public RegistryDataAccessService() {
        this.people = deserializePeople();
    }

    public void serializePeople(List<Person> people) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(people);
            objectOut.close();
            fileOut.close();
            System.out.println("Objects have been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> deserializePeople() {
        List<Person> deserializedPeople = null;
        try {
            FileInputStream fileIn = new FileInputStream(FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            deserializedPeople = (List<Person>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Objects have been deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(deserializedPeople);
    }
}
