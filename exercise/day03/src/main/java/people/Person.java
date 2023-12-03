package people;

public record Person(String firstName, String lastName, OwnedPets pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, OwnedPets.none());
    }

    public Person addPet(PetType petType, String name, int age) {
        pets.add(new Pet(petType, name, age));
        return this;
    }
}
