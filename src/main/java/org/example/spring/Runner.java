package org.example.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.dao.cassandraDao.EventCassandraDao;
import org.example.spring.dao.cassandraDao.UserCassandraDao;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.service.FromSQLToCassandraMigrationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.apache.logging.log4j.Level.DEBUG;

public class Runner {
    private final static Logger logger = LogManager.getLogger();

    public Runner() {
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    public static void main(String[] args) {
    }
}
