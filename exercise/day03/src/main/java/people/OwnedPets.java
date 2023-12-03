package people;

import java.util.ArrayList;
import java.util.List;

public class OwnedPets implements Comparable<OwnedPets> {
    private final List<Pet> pets;

    private OwnedPets(List<Pet> pets) {
        this.pets = pets;
    }

    public static OwnedPets none() {
        return new OwnedPets(new ArrayList<>());
    }

    public OwnedPets add(Pet newPet) {
        pets.add(newPet);
        return this;
    }

    @Override
    public int compareTo(OwnedPets o) {
        return Integer.compare(this.getYoungestAgePet(), o.getYoungestAgePet());
    }

    private int getYoungestAgePet() {
        return this.pets
                .stream()
                .mapToInt(Pet::age)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

}
