package sawant.mihir.springtransactions.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import sawant.mihir.springtransactions.model.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountRepository extends CrudRepository<Account, Integer> {


    @Query("SELECT * FROM account WHERE id = :id")
    public Account findAccountById(int id);

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    public void changeAmount(int id, BigDecimal amount);

    @Query("SELECT * FROM account")
    public List<Account> findAllAccounts();
}
