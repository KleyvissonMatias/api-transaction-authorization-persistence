package br.com.caju.api.transaction.authorization.persistence.repository;

import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
