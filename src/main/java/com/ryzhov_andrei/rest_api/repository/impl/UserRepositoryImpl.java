package com.ryzhov_andrei.rest_api.repository.impl;

import com.ryzhov_andrei.rest_api.repository.UserRepository;
import com.ryzhov_andrei.rest_api.utils.HibernateUtil;
import com.ryzhov_andrei.rest_api.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Transaction transaction;

    @Override
    public User getById(Integer id) {
        User user = new User();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
//        String hql = "Select distinct user from User user left join fetch user.eventList order by user.id asc ";
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userList = session.createQuery("FROM User ", User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User create(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
