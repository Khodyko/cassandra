package org.example.spring.dao.cassandraDao;

import org.example.spring.model.Entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class EventCassandraDao implements CassandraRepository<EventEntity, Long> {


    private CqlTemplate template;

    @Autowired
    public EventCassandraDao(CqlTemplate template) {
        this.template = template;
    }


    @Override
    public EventEntity save(EventEntity entity) {
        LocalDate localDate= LocalDate.from(entity.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        System.out.println(localDate);
        if(entity.getId()==0){
            Integer new_id=null;
            Long max=template.queryForObject("SELECT MAX(id) FROM booking_ticket.eventEntity", Long.class);
            if(max==null){
                max= Long.valueOf(0);
            }
            System.out.println("max "+max);
            template.execute("INSERT INTO booking_ticket.eventEntity (id, title, date) VALUES (?, ?, ?);",max+1, entity.getTitle(), localDate);
        }
        else{
            //final string fixme everywhere
            template.execute("INSERT INTO booking_ticket.eventEntity (id, title, date) VALUES (?, ?, ?);",entity.getId(), entity.getTitle(), localDate);
        }
        return null;
    }

    @Override
    public <S extends EventEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<EventEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<EventEntity> findAll() {
        return null;
    }

    @Override
    public List<EventEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(EventEntity eventEntity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends EventEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Slice<EventEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends EventEntity> S insert(S s) {
        return null;
    }

    @Override
    public <S extends EventEntity> List<S> insert(Iterable<S> iterable) {
        return null;
    }
}
