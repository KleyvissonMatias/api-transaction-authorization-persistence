package br.com.caju.api.transaction.authorization.persistence.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "account_id")
    private String accountId;

    @PositiveOrZero
    private BigDecimal balance;

    @PositiveOrZero
    @Column(name = "food_balance")
    private BigDecimal foodBalance;

    @PositiveOrZero
    @Column(name = "meal_balance")
    private BigDecimal mealBalance;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

}
