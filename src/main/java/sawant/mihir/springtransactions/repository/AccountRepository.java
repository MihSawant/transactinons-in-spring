package sawant.mihir.springtransactions.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sawant.mihir.springtransactions.model.Account;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Account> accountRowMapper = (rs, n) -> new Account(rs.getInt("id"),
                                                                    rs.getString("name"),
                                                                    rs.getBigDecimal("amount"));

    public AccountRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account findById(int id){
        String sql = "SELECT * FROM account WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, accountRowMapper, id);
    }

    public void changeAmount(int id, BigDecimal newAmount){
        String sql = "UPDATE account SET amount = ? WHERE id = ?";
        jdbcTemplate.update(sql, newAmount, id);
    }

    public List<Account> findAllAccounts(){
        String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, accountRowMapper);
    }




}
