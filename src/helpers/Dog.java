package helpers;

import java.io.Serializable;

public class Dog implements Serializable {
    String name;
    String breed;
    // important thing with serialization
    // because with every read/write operation, version will be random and different
    private static final long serialVersionUID = 1L;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }
}
