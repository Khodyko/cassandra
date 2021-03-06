package org.example.spring.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.model.Event;
import org.example.spring.model.Ticket;
import org.example.spring.model.User;
import org.example.spring.service.ServiceException.ServiceException;
import org.example.spring.service.serviceImpl.EventServiceImpl;
import org.example.spring.service.serviceImpl.TicketServiceImpl;
import org.example.spring.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.WARN;

public class FacadeImpl implements BookingFacade {

    private final static Logger logger = LogManager.getLogger();

    private EventServiceImpl eventServiceImpl;
    private TicketServiceImpl ticketServiceImpl;
    private UserServiceImpl userServiceImpl;

    public FacadeImpl() {
    }

    @Autowired
    public FacadeImpl(EventServiceImpl eventServiceImpl, TicketServiceImpl ticketServiceImpl,
                      UserServiceImpl userServiceImpl) {
        this.eventServiceImpl = eventServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
        this.userServiceImpl = userServiceImpl;
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    @Override
    public Event getEventById(long eventId) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return eventServiceImpl.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize,
                                        int pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return eventServiceImpl.getEventsByTitle(title, pageSize, pageNum);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return eventServiceImpl.getEventsForDay(day, pageSize, pageNum);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return eventServiceImpl.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return eventServiceImpl.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return eventServiceImpl.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userServiceImpl.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userServiceImpl.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return userServiceImpl.getUsersByName(name, pageSize, pageNum);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userServiceImpl.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userServiceImpl.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {

        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return userServiceImpl.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place,
                             Ticket.Category category) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return ticketServiceImpl.bookTicket(userId, eventId, place, category);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return ticketServiceImpl.getBookedTickets(user, pageSize, pageNum);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        try {
            return ticketServiceImpl.getBookedTickets(event, pageSize, pageNum);
        } catch (ServiceException e) {
            logger.log(WARN, e.getMessage());
        }
        return null;
    }

    public Ticket getTicketById(long id) {

        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return ticketServiceImpl.getTicketById(id);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        return ticketServiceImpl.cancelTicket(ticketId);
    }
}
