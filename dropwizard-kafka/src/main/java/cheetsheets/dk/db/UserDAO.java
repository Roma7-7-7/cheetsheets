package cheetsheets.dk.db;

import cheetsheets.dk.core.Role;
import cheetsheets.dk.core.User;
import com.google.common.collect.Multimap;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.config.RegisterJoinRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {

    @RegisterJoinRowMapper({UserMapper.class, UserRoleMapper.class})
    @SqlQuery("SELECT u.*, r.id AS role_id, r.name AS role_name FROM \"user\" u " +
            "LEFT JOIN user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN role r ON r.id = ur.role_id")
    Multimap<User, Role> getUsers();

    @RegisterJoinRowMapper({UserMapper.class, UserRoleMapper.class})
    @SqlQuery("SELECT u.*, r.id AS role_id, r.name AS role_name FROM \"user\" u " +
            " LEFT JOIN user_role ur ON u.id = ur.user_id " +
            " LEFT JOIN role r ON r.id = ur.role_id " +
            " WHERE u.email = :email ")
    Multimap<User, Role> getUserByEmail(@Bind("email") String email);

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

    class UserRoleMapper implements RowMapper<Role> {

        @Override
        public Role map(ResultSet rs, StatementContext ctx) throws SQLException {
            return new Role()
                    .setId(rs.getInt("role_id"))
                    .setName(rs.getString("role_name"));
        }
    }

}
