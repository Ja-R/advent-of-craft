package people;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.Comparator.comparing;

public record Population(List<Person> persons) {

    public static Population of(List<Person> persons) {
        return new Population(persons);
    }

    public Optional<String> getPersonOwningTheYoungestPet() {
        return this.persons
                .stream()
                .min(comparing(Person::pets))
                .map(Person::firstName);
    }

    @Override
    public String toString() {
        return this.formatPopulation();
    }

    private String formatPopulation() {
        return persons
                .stream()
                .map(Person::getInformation)
                .collect(Collectors.joining(lineSeparator()));
    }
}
