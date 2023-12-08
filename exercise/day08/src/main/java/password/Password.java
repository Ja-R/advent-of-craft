package password;

public record Password(String value) {

    private static final String REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[.*#@\\$%&]).{8,}$";

    public Password(String value) {
        this.validatePassword(value);

        this.value = value;
    }

    private void validatePassword(String password) {
        if (this.isInvalid(password)) {
            throw new IllegalArgumentException("Password is invalid");
        }
    }

    private boolean isInvalid(String password) {
        return !password.matches(REGEX);
    }
}
