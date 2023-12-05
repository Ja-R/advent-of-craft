package people;

public record Person(String firstName, String lastName, OwnedPets pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, OwnedPets.none());
    }

    public Person addPet(PetType petType, String name, int age) {
        pets.add(new Pet(petType, name, age));
        return this;
    }

    public String getInformation() {
        return this.hasPet()
                ? "%s %s who owns : %s".formatted(this.firstName, this.lastName, this.getPetsNames())
                : this.firstName + " " + this.lastName;
    }

    private boolean hasPet() {
        return this.pets.petsExists();
    }

    private String getPetsNames() {
        return this.pets.getPetsNames();
    }
}
