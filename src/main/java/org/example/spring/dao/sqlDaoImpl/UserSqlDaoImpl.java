package org.example.spring.dao.sqlDaoImpl;

import java.util.List;
import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserSqlDaoImpl {
    private final String SELECT_ALL_QUERY = "SELECT * FROM userEntity";
    private final String ADD_USER = "INSERT INTO userEntity (name, email) values (?, ?)";
    private final String ADD_USER_WITH_ID = "INSERT INTO userEntity (id, name, email) values (?, ?, ?)";

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public UserSqlDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserEntity> getAllUserEntity(){
        return jdbcTemplate.query(SELECT_ALL_QUERY, new UserEntityRowMapper());
    }

    public void addUserEntity(UserEntity eventEntity) {
        jdbcTemplate.update(ADD_USER, eventEntity.getName(), eventEntity.getEmail());
    }

    public void addUserEntityWithId(UserEntity userEntity) {
        jdbcTemplate.update(ADD_USER_WITH_ID, userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    private class UserEntityRowMapper implements RowMapper<UserEntity> {

        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

            UserEntity userEntity = new UserEntity();
            userEntity.setId(rs.getInt("id"));
            userEntity.setName(rs.getString("name"));
            userEntity.setEmail(rs.getString("email"));

            return userEntity;
        }
    }
}
