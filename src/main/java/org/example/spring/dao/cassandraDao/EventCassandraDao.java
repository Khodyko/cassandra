package org.example.spring.dao.cassandraDao;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.example.spring.model.Entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EventCassandraDao {
    final String INSERT_INTO_EVENT_QUERY="INSERT INTO booking_ticket.eventEntity (id, title, date) " +
                                         "VALUES (?, ?, ?);";
    final String GET_MAX_ID_FROM_EVENT_QUERY ="SELECT MAX(id) FROM booking_ticket.eventEntity";

    private CqlTemplate template;
    private CassandraOperations cassandraTemplate;

    @Autowired
    @SuppressWarnings(value = "all")
    public EventCassandraDao(CqlTemplate template, CassandraOperations cassandraTemplate) {
        this.template = template;
        this.cassandraTemplate = cassandraTemplate;
    }

    public void save(EventEntity entity) {
        LocalDate localDate = LocalDate.from(entity.getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime());
        if (entity.getId() == 0) {
            Long max = getMaxId();
            template.execute(INSERT_INTO_EVENT_QUERY, max + 1, entity.getTitle(), localDate);
        } else {
            template.execute(INSERT_INTO_EVENT_QUERY, entity.getId(), entity.getTitle(), localDate);
        }
    }

    public void saveBatchList(List<EventEntity> list) {
        LocalDate localDate = null;
        CqlSession session = CqlSession.builder().withKeyspace("booking_ticket").build();
        for (int i = 0; i < list.size(); i++) {
            Date date = list.get(i).getDate();
            localDate = Instant.ofEpochMilli(date.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            SimpleStatement simpleStatement = SimpleStatement.newInstance(
                    "INSERT INTO booking_ticket.eventEntity (id, title, date) VALUES (?, ?, ?);",
                             list.get(i).getId(), list.get(i).getTitle(), localDate);
            BatchStatement batchStatements = BatchStatement.newInstance(BatchType.UNLOGGED, simpleStatement);
            session.execute(batchStatements);
        }
    }
    public Long getMaxId(){
        Long maxId = template.queryForObject(GET_MAX_ID_FROM_EVENT_QUERY, Long.class);
        if (maxId == null) {
            maxId = Long.valueOf(0);
        }
        return maxId;
    }
}
