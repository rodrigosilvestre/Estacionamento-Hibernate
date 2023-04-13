package com.ucsal.estacionamento.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ucsal.estacionamento.model.Estacionamento;
import com.ucsal.estacionamento.model.HibernateUtil;

public class EstacionamentoDAO {

    public void adicionar(Estacionamento estacionamento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(estacionamento);
            transaction.commit();
            System.out.println("Estacionamento adicionado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao adicionar o estacionamento: " + e.getMessage());
        }
    }

    public void atualizar(Estacionamento estacionamento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(estacionamento);
            transaction.commit();
            System.out.println("Estacionamento atualizado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao atualizar o estacionamento: " + e.getMessage());
        }
    }

    public void excluir(String identificador) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Estacionamento estacionamento = session.get(Estacionamento.class, identificador);
            if (estacionamento != null) {
                session.delete(estacionamento);
                transaction.commit();
                System.out.println("Estacionamento removido com sucesso!");
            } else {
                System.out.println("Estacionamento não encontrado para exclusão.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Erro ao excluir o estacionamento: " + e.getMessage());
        }
    }

    public Estacionamento buscarPorIdentificador(String identificador) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Estacionamento.class, identificador);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o estacionamento por identificador: " + e.getMessage());
            return null;
        }
    }

    public List<Estacionamento> buscarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Estacionamento", Estacionamento.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os estacionamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
