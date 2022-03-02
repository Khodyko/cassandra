package org.example.spring.dao.cassandraDao;

import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserCassandraDao {
    final String INSERT_INTO_USER_QUERY="INSERT INTO booking_ticket.userEntity (id, name, email)" +
                                        " VALUES (?, ?, ?);";
    final String GET_MAX_ID_FROM_USER_QUERY ="SELECT MAX(id) FROM booking_ticket.eventEntity";
    private CqlTemplate template;
    private CassandraOperations cassandraTemplate;
    @Autowired
    @SuppressWarnings(value = "all")
    public UserCassandraDao(CqlTemplate template, CassandraOperations cassandraTemplate) {
        this.template = template;
        this.cassandraTemplate = cassandraTemplate;
    }

    public void save(UserEntity entity) {
        List<UserEntity> list=new ArrayList<>();
        cassandraTemplate.batchOps().insert(list).execute();
        if (entity.getId() == 0) {
            Long max = getMaxId();
            template.execute(INSERT_INTO_USER_QUERY, max + 1, entity.getName(), entity.getEmail());
        } else {
            template.execute(INSERT_INTO_USER_QUERY, entity.getId(), entity.getName(), entity.getEmail());
        }
    }

    public void saveBatchList(List<UserEntity> list){
        for (int i = 0; i < list.size(); i++) {
            cassandraTemplate.batchOps().insert(list.get(i)).execute();
        }
    }

    public Long getMaxId(){
        Long maxId = template.queryForObject(GET_MAX_ID_FROM_USER_QUERY, Long.class);
        if (maxId == null) {
            maxId = Long.valueOf(0);
        }
        return maxId;
    }
}