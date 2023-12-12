package greeting;

public enum Formality {
    FORMAL("Good evening, sir."),
    CASUAL("Sup bro?"),
    INTIMATE("Hello Darling!");

    private final String greet;

    Formality(String greet) {
        this.greet = greet;
    }


    public String getGreet() {
        return greet;
    }
}
