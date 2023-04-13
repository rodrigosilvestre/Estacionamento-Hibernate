package com.ucsal.estacionamento.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ucsal.estacionamento.model.Estadia;
import com.ucsal.estacionamento.model.HibernateUtil;

public class EstadiaDAO {

    public void adicionar(Estadia estadia) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(estadia);
            transaction.commit();
            System.out.println("Estadia adicionada com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao adicionar estadia: " + e.getMessage());
        }
    }

    public void atualizar(Estadia estadia) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(estadia);
            transaction.commit();
            System.out.println("Estadia atualizada com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao atualizar estadia: " + e.getMessage());
        }
    }

    public void remover(String identificador) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Estadia estadia = session.get(Estadia.class, identificador);
            if (estadia != null) {
                session.delete(estadia);
                System.out.println("Estadia removida com sucesso!");
            } else {
                System.out.println("Estadia não encontrada!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao remover estadia: " + e.getMessage());
        }
    }

    public List<Estadia> listar() {
        List<Estadia> estadias = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            estadias = session.createQuery("from Estadia", Estadia.class).list();
        } catch (Exception e) {
            System.out.println("Erro ao listar estadias: " + e.getMessage());
        }
        return estadias;
    }

    public Estadia buscarPorIdentificador(String identificador) {
        Estadia estadia = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            estadia = session.get(Estadia.class, identificador);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a estadia por identificador: " + e.getMessage());
        }
        return estadia;
    }
}
