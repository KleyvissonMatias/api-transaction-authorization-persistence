package br.com.caju.api.transaction.authorization.persistence.repository;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountId(String accountId);

    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance, a.dtUpdated = CURRENT_TIMESTAMP WHERE a.id = :id")
    int updateAccountBalance(@Param("id") Long id, @Param("balance") BigDecimal balance);

    @Modifying
    @Query("UPDATE Account a SET a.foodBalance = :foodBalance, a.dtUpdated = CURRENT_TIMESTAMP WHERE a.id = :id")
    int updateAccountFoodBalance(@Param("id") Long id, @Param("foodBalance") BigDecimal foodBalance);

    @Modifying
    @Query("UPDATE Account a SET a.mealBalance = :mealBalance, a.dtUpdated = CURRENT_TIMESTAMP WHERE a.id = :id")
    int updateAccountMealBalance(@Param("id") Long id, @Param("mealBalance") BigDecimal mealBalance);

}
