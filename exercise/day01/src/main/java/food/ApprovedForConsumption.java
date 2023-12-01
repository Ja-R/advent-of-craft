package food;

public record ApprovedForConsumption(boolean value) {

    public static ApprovedForConsumption approved() {
        return new ApprovedForConsumption(true);
    }

    public static ApprovedForConsumption notApproved() {
        return new ApprovedForConsumption(false);
    }

    public boolean isApproved() {
        return this.value;
    }

}
