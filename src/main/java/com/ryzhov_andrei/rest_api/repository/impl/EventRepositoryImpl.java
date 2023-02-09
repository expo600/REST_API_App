package com.ryzhov_andrei.rest_api.repository.impl;

import com.ryzhov_andrei.rest_api.repository.EventRepository;
import com.ryzhov_andrei.rest_api.utils.HibernateUtil;
import com.ryzhov_andrei.rest_api.model.Event;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private Transaction transaction;

    @Override
    public Event getById(Integer id) {
        Event event = new Event();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            event = session.get(Event.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            eventList = session.createQuery("FROM Event", Event.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    @Override
    public Event create(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, id);
            session.delete(event);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
