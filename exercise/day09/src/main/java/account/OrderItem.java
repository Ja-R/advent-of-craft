package account;

public record OrderItem(String name, double price) {

    @Override
    public String toString() {
        return this.formatLine();
    }

    private String formatLine() {
        return name + " for " + price + "€";
    }
}
