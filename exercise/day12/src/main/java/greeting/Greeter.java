package greeting;

import java.util.Optional;

public class Greeter {
    private static final String DEFAULT_GREETING = "Hello.";

    private final Formality formality;

    public Greeter(Formality formality) {
        this.formality = formality;
    }

    public Greeter() {
        this.formality = null;
    }

    public String greet() {
        return Optional.ofNullable(this.formality)
                .map(Formality::getGreet)
                .orElse(DEFAULT_GREETING);
    }

}
