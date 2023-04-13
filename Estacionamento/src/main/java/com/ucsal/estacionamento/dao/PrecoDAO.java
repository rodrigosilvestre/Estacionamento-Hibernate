package com.ucsal.estacionamento.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ucsal.estacionamento.model.Preco;
import com.ucsal.estacionamento.model.HibernateUtil;

public class PrecoDAO {

	public void adicionar(Preco preco) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(preco);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao adicionar preco: " + e.getMessage());
		}
	}

	public void atualizar(Preco preco) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(preco);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao atualizar preco: " + e.getMessage());
		}
	}

	public void remover(Preco preco) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(preco);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao remover preco: " + e.getMessage());
		}
	}

	public Preco buscarPorIdentificador(String identificador) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Preco.class, identificador);
		} catch (Exception e) {
			System.out.println("Erro ao buscar preco por identificador: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Preco> listarTodos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Preco").list();
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os precos: " + e.getMessage());
			return null;
		}
	}

}
