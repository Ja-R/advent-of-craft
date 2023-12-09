import account.Client;
import account.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class ClientTests {
    private final Client client = new Client(List.of(
        new OrderItem("Tenet Deluxe Edition", 45.99),
        new OrderItem("Inception", 30.50),
        new OrderItem("The Dark Knight", 30.50),
        new OrderItem("Interstellar", 23.98)
    ));

    @Test
    void client_should_return_statement() {
        String statement = client.toStatement();

        assertThat(statement).isEqualTo(
                "Tenet Deluxe Edition for 45.99€" + lineSeparator() +
                        "Inception for 30.5€" + lineSeparator() +
                        "The Dark Knight for 30.5€" + lineSeparator() +
                        "Interstellar for 23.98€" + lineSeparator() +
                        "Total : 130.97€");
    }

    @Test
    void client_should_return_total_amount() {
        assertThat(client.getTotalAmount()).isEqualTo(130.97);
    }

    @Test
    void client_can_order_duplicate_items() {
        Client client = new Client(List.of(
                new OrderItem("Tenet Deluxe Edition", 45.99),
                new OrderItem("Inception", 30.50),
                new OrderItem("Inception", 30.50),
                new OrderItem("Interstellar", 23.98)
        ));

        String statement = client.toStatement();

        assertThat(statement).isEqualTo(
                "Tenet Deluxe Edition for 45.99€" + lineSeparator() +
                        "Inception for 30.5€" + lineSeparator() +
                        "Inception for 30.5€" + lineSeparator() +
                        "Interstellar for 23.98€" + lineSeparator() +
                        "Total : 130.97€");
    }
}
