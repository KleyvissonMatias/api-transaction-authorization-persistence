package br.com.caju.api.transaction.authorization.persistence.repository;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MccRepository extends JpaRepository<Mcc, Long> {

    @Query("SELECT m FROM Mcc m WHERE m.id = :id")
    Optional<Mcc> findMccById(@Param("id") Integer id);
}
