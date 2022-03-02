package org.example.spring.dao.cassandraDao;

import org.example.spring.model.Entity.TicketEntity;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

public class TicketCassandraDao {
    private CqlTemplate template;

    public TicketCassandraDao(CqlTemplate template) {
        this.template = template;
    }

    public TicketEntity save(TicketEntity entity) {
        if (entity.getId() == 0) {
            Integer new_id = null;
            Long max = template.queryForObject("SELECT MAX(id) FROM booking_ticket.ticketEntity", Long.class);
            if (max == null) {
                max = Long.valueOf(0);
            }
            System.out.println("max " + max);
            template.execute("INSERT INTO booking_ticket.ticketEntity (id, eventId, userId, category, place) VALUES (?, ?, ?, ?, ?);", max + 1, entity.getEventId(), entity.getUserId(), entity.getCategory().getName(), entity.getPlace());
        } else {
            //final string fixme everywhere
            template.execute("INSERT INTO booking_ticket.ticketEntity (id, eventId, userId, category, place) VALUES (?, ?, ?, ?, ?);", entity.getId(), entity.getEventId(), entity.getUserId(), entity.getCategory().getName(), entity.getPlace());
        }
        return null;
    }
}