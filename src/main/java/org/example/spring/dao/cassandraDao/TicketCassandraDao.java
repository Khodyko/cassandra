package org.example.spring.dao.cassandraDao;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.example.spring.model.Entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import java.util.List;

public class TicketCassandraDao {
    final String INSERT_INTO_TICKET_QUERY ="INSERT INTO booking_ticket.ticketEntity (id, eventId, " +
                                                "userId, category, place) VALUES (?, ?, ?, ?, ?);";
    final String GET_MAX_ID_FROM_TICKET_QUERY ="SELECT MAX(id) FROM booking_ticket.ticketEntity";
    private CqlTemplate template;
    private CqlSession session;

    @Autowired
    @SuppressWarnings("all")
    public TicketCassandraDao(CqlTemplate template, CqlSession session) {
        this.template = template;
        this.session = session;
    }

    public void save(TicketEntity entity) {
        if (entity.getId() == 0) {
            Integer new_id = null;
            Long max = getMaxId();
            template.execute(INSERT_INTO_TICKET_QUERY, max + 1, entity.getEventId(),
                             entity.getUserId(), entity.getCategory().getName(), entity.getPlace());
        } else {
            template.execute(INSERT_INTO_TICKET_QUERY, entity.getId(), entity.getEventId(),
                             entity.getUserId(), entity.getCategory().getName(), entity.getPlace());
        }
    }

    public void saveBatchList(List<TicketEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            SimpleStatement simpleStatement = SimpleStatement.newInstance(INSERT_INTO_TICKET_QUERY,
                    list.get(i).getId(), list.get(i).getCategory().getName(), list.get(i).getEventId(),
                    list.get(i).getUserId(),list.get(i).getPlace());
            BatchStatement batchStatements = BatchStatement.newInstance(BatchType.UNLOGGED, simpleStatement);
            session.execute(batchStatements);
        }
    }

    public Long getMaxId(){
        Long maxId = template.queryForObject(GET_MAX_ID_FROM_TICKET_QUERY, Long.class);
        if (maxId == null) {
            maxId = Long.valueOf(0);
        }
        return maxId;
    }
}