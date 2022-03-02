package org.example.spring.dao.cassandraDao;


import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public class UserCassandraDao implements CassandraRepository<UserEntity, Long> {
    private CqlTemplate template;


    private CassandraOperations cassandraTemplate;

    @Autowired
    @SuppressWarnings(value = "all")
    public UserCassandraDao(CqlTemplate template, CassandraOperations cassandraTemplate) {
        this.template = template;
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public UserEntity save(UserEntity entity) {

        System.out.println("warn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1" + cassandraTemplate.count(UserEntity.class));
        System.out.println("warn!!!!!!!!!!!!!!!!!!1" + cassandraTemplate.count(UserEntity.class));
        if (entity.getId() == 0) {
            Integer new_id = null;
            Long max = template.queryForObject("SELECT MAX(id) FROM booking_ticket.userEntity;", Long.class);
            if (max == null) {
                max = Long.valueOf(0);
            }
            System.out.println("max " + max);
            template.execute("INSERT INTO booking_ticket.userEntity (id, name, email) VALUES (?, ?, ?);", max + 1, entity.getName(), entity.getEmail());
        } else {
            template.execute("INSERT INTO booking_ticket.userEntity (id, name, email) VALUES (?, ?, ?);", entity.getId(), entity.getName(), entity.getEmail());
        }
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(UserEntity userEntity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Slice<UserEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserEntity> S insert(S s) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> insert(Iterable<S> iterable) {
        return null;
    }
}