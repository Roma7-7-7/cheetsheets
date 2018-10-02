package cheetsheets.dk.db;

import cheetsheets.dk.core.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    @SqlQuery("SELECT * FROM \"user\" ORDER BY name")
    List<User> getUsers();

    class UserMapper implements RowMapper<User>{

        @Override
        public User map(ResultSet rs, StatementContext ctx) throws SQLException {
            return new User()
                    .setId(rs.getLong("id"))
                    .setEmail(rs.getString("email"))
                    .setPassword(rs.getString("password"))
                    .setName(rs.getString("name"))
                    .setLastName(rs.getString("last_name"));
        }
    }

}
