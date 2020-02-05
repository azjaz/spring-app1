package data;

import entity.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.security.provider.ConfigFile;

import java.util.List;
@Repository
public abstract class JdbcSpitterRepository implements SpitterRepository{

    private static final String SQL_INSERT_SPITTER =
            "insert into spitter (username, password, firstName, lastName) values (?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID =
            "select id, username, firstName, lastName from spitter where id = ?";
    private static final String SQL_SELECT_BY_USERNAME =
            "select id, username, firstName, lastName from spitter where username = ?";
    private JdbcOperations jdbcOperations;
    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    public void addSpitter(Spitter spitter) {
        jdbcOperations.update(SQL_INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName());
    }

    public Spitter findById(long id) {
        return jdbcOperations.queryForObject(SQL_SELECT_BY_ID, (rs, rowNum) -> {return new Spitter(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("lastName")
        );}, id);
    }

    @Override
    public Spitter findByUsername(String username) {
        return jdbcOperations.queryForObject(SQL_SELECT_BY_USERNAME, (rs, rowNum) -> {return new Spitter(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("lastName")
        );}, username);
    };
}
