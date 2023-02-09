package com.ryzhov_andrei.rest_api.repository.impl;

import com.ryzhov_andrei.rest_api.repository.FileRepository;
import com.ryzhov_andrei.rest_api.utils.HibernateUtil;
import com.ryzhov_andrei.rest_api.model.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {

    private Transaction transaction;

    @Override
    public File getById(Integer id) {
        File file = new File();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            file = session.get(File.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> fileList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            fileList = session.createQuery("FROM File", File.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public File create(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File update(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            File file = session.get(File.class, id);
            session.delete(file);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
