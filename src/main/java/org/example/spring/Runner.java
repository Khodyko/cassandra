package org.example.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.dao.cassandraDao.EventCassandraDao;
import org.example.spring.dao.cassandraDao.TicketCassandraDao;
import org.example.spring.dao.cassandraDao.UserCassandraDao;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.TicketEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Ticket;
import org.example.spring.service.FromSQLToCassandraMigrationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

import static org.apache.logging.log4j.Level.DEBUG;

public class Runner {
    private final static Logger logger = LogManager.getLogger();

    public Runner() {
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserCassandraDao userCassandraDao=ctx.getBean(UserCassandraDao.class);
        UserEntity user=new UserEntity( "name2", "email4");
        userCassandraDao.save(user);
//        System.out.println(userCassandraDao.findAll());
//        Date date=new Date(System.currentTimeMillis());
//        EventEntity eventEntity=new EventEntity(600,"title1", date);
//        EventCassandraDao eventCassandraDao=ctx.getBean(EventCassandraDao.class);
//        eventCassandraDao.save(eventEntity);
//        TicketCassandraDao ticketCassandraDao=ctx.getBean(TicketCassandraDao.class);
//        TicketEntity ticketEntity=new TicketEntity( 1,1, Ticket.Category.BAR, 1);
//        ticketCassandraDao.save(ticketEntity);
//        FromSQLToCassandraMigrationService fromSQLToCassandraMigrationService=ctx.getBean(FromSQLToCassandraMigrationService.class);
//        fromSQLToCassandraMigrationService.getSqlData();
//        fromSQLToCassandraMigrationService.migrateDataToCassandra();

    }
}
