package br.com.caju.api.transaction.authorization.persistence.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mcc_code")
    private String mccCode;

    @PositiveOrZero
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String merchant;

    private String account;

    @ManyToOne(optional = false)
    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status_transaction_id", referencedColumnName = "id")
    private StatusTransaction statusTransaction;

    @ManyToOne(optional = false)
    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "mcc_id", referencedColumnName = "id")
    private Mcc mccType;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;
}
