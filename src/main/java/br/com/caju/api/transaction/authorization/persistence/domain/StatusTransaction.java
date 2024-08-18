package br.com.caju.api.transaction.authorization.persistence.domain;

import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "status_transaction")
public class StatusTransaction {

    @Id
    private Integer id;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCodeTransactionEnum codeStatusTransactionEnum;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;
}


