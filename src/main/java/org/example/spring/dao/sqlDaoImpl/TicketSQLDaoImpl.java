package org.example.spring.dao.sqlDaoImpl;


import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketSQLDaoImpl {

    private final String SELECT_ALL_QUERY = "SELECT * FROM ticketEntity";
    private final String ADD_TICKET = "INSERT INTO ticketEntity (eventId, userId, category, place) values (?, ?, ?, ?)";
    private final String ADD_TICKET_WITH_ID = "INSERT INTO ticketEntity (id, eventId, userId, category, place) values (?, ?, ?, ?, ?)";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketSQLDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TicketEntity> getAllTicketEntity() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new TicketEntityRowMapper());
    }

    public void addTicketEntity(TicketEntity ticketEntity) {
        jdbcTemplate.update(ADD_TICKET, ticketEntity.getEventId(), ticketEntity.getUserId(), ticketEntity.getCategory().getName(), ticketEntity.getPlace());
    }

    public void addTicketEntityWithID(TicketEntity ticketEntity) {
        jdbcTemplate.update(ADD_TICKET_WITH_ID, ticketEntity.getId(), ticketEntity.getEventId(), ticketEntity.getUserId(), ticketEntity.getCategory().getName(), ticketEntity.getPlace());
    }

    private class TicketEntityRowMapper implements RowMapper<TicketEntity> {

        @Override
        public TicketEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

           TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setId(rs.getInt("id"));
            ticketEntity.setEventId(rs.getInt("eventId"));
            ticketEntity.setUserId(rs.getInt("userId"));
            ticketEntity.setCategory(Ticket.Category.valueOf(rs.getString("category")));
            ticketEntity.setPlace(rs.getInt("place"));
            return ticketEntity;
        }
    }
}
