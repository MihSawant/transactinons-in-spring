package sawant.mihir.springtransactions.repository;

import org.springframework.data.repository.CrudRepository;

import sawant.mihir.springtransactions.model.Account;


public interface AccountRepository extends CrudRepository<Account, Long> {
}
