package org.example.spring.service;

import org.example.spring.dao.cassandraDao.EventCassandraDao;
import org.example.spring.dao.cassandraDao.TicketCassandraDao;
import org.example.spring.dao.cassandraDao.UserCassandraDao;
import org.example.spring.dao.sqlDaoImpl.EventSqlDaoImpl;
import org.example.spring.dao.sqlDaoImpl.TicketSQLDaoImpl;
import org.example.spring.dao.sqlDaoImpl.UserSqlDaoImpl;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FromSQLToCassandraMigrationService {
    private List<EventEntity> eventEntityList;
    private List<UserEntity> userEntityList;
    private List<TicketEntity> ticketEntityList;
    private EventSqlDaoImpl eventSqlDao;
    private UserSqlDaoImpl userSqlDao;
    private TicketSQLDaoImpl ticketSQLDao;
    private UserCassandraDao userCassandraDao;
    private EventCassandraDao eventCassandraDao;
    private TicketCassandraDao ticketCassandraDao;

    @Autowired
    public FromSQLToCassandraMigrationService(
            EventSqlDaoImpl eventSqlDao, UserSqlDaoImpl userSqlDao,
            TicketSQLDaoImpl ticketSQLDao, UserCassandraDao userCassandraDao,
            EventCassandraDao eventCassandraDao, TicketCassandraDao ticketCassandraDao) {
        this.eventSqlDao = eventSqlDao;
        this.userSqlDao = userSqlDao;
        this.ticketSQLDao = ticketSQLDao;
        this.userCassandraDao = userCassandraDao;
        this.eventCassandraDao = eventCassandraDao;
        this.ticketCassandraDao = ticketCassandraDao;
    }

    public FromSQLToCassandraMigrationService() {
    }

    @Transactional
    public void getSqlData() {
        eventEntityList = eventSqlDao.getAllEventEntity();
        userEntityList = userSqlDao.getAllUserEntity();
        ticketEntityList = ticketSQLDao.getAllTicketEntity();
    }

    public void migrateDataToCassandra() {
        userCassandraDao.saveBatchList(userEntityList);
        eventCassandraDao.saveBatchList(eventEntityList);
        ticketCassandraDao.saveBatchList(ticketEntityList);
    }
}
