package br.com.caju.api.transaction.authorization.persistence.domain;

import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mcc")
public class Mcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MccTypeEnum category;

    @Column(name = "dt_created")
    private LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

}