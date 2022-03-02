package org.example.spring.dao.sqlDaoImpl;

import org.example.spring.model.Entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventSqlDaoImpl {

    private final String SELECT_ALL_QUERY = "SELECT * FROM eventEntity";
    private final String ADD_EVENT = "INSERT INTO eventEntity (title, date) values (?, ?)";
    private final String ADD_EVENT_WITH_ID = "INSERT INTO eventEntity (id, title, date) values (?, ?, ?)";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventSqlDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EventEntity> getAllEventEntity() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new EventEntityRowMapper());
    }

    public void addEventEntity(EventEntity eventEntity) {
        jdbcTemplate.update(ADD_EVENT, eventEntity.getTitle(), eventEntity.getDate());
    }
    public void addEventEntityWithID(EventEntity eventEntity) {
        jdbcTemplate.update(ADD_EVENT_WITH_ID, eventEntity.getId(), eventEntity.getTitle(), eventEntity.getDate());
    }
    private class EventEntityRowMapper implements RowMapper<EventEntity> {

        @Override
        public EventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

            EventEntity eventEntity = new EventEntity();
            eventEntity.setId(rs.getInt("id"));
            eventEntity.setTitle(rs.getString("title"));
            eventEntity.setDate(rs.getDate("date"));

            return eventEntity;
        }
    }
}
