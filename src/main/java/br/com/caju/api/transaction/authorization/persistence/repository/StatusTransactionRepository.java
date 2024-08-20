package br.com.caju.api.transaction.authorization.persistence.repository;

import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusTransactionRepository extends JpaRepository<StatusTransaction, Long> {

    Optional<StatusTransaction> findStatusTransactionById(@Param("id") Integer id);

}
