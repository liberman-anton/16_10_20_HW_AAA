package tel_ran.security.dao.repo;

import org.springframework.data.repository.CrudRepository;

import tel_ran.security.Account;

public interface AccountsRepository extends CrudRepository<Account, String> {

}
