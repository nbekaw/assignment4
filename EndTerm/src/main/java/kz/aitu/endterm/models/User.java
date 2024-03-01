package kz.aitu.endterm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String number;
    private double balance;

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("NO NEGATIVE");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("NOT ENOUGH");
        }
        this.balance -= amount;
    }
}

