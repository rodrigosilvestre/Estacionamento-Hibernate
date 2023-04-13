package com.ucsal.estacionamento.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ucsal.estacionamento.model.Entrada;
import com.ucsal.estacionamento.model.HibernateUtil;

public class EntradaDAO {

	public void adicionar(Entrada entrada) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(entrada);
			transaction.commit();
			System.out.println("Entrada adicionada com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao adicionar entrada: " + e.getMessage());
		}
	}

	public void atualizar(Entrada entrada) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(entrada);
			transaction.commit();
			System.out.println("Entrada atualizada com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao atualizar entrada: " + e.getMessage());
		}
	}

	public void remover(String identificador) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Entrada entrada = session.get(Entrada.class, identificador);
			if (entrada != null) {
				session.delete(entrada);
				transaction.commit();
				System.out.println("Entrada removida com sucesso!");
			} else {
				System.out.println("Entrada não encontrada!");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao remover entrada: " + e.getMessage());
		}
	}

	public List<Entrada> listar() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Entrada", Entrada.class).list();
		} catch (Exception e) {
			System.err.println("Erro ao listar entradas: " + e.getMessage());
			return null;
		}
	}

	public Entrada buscarPorIdentificador(String identificador) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Entrada.class, identificador);
		} catch (Exception e) {
			System.out.println("Erro ao buscar a entrada por identificador: " + e.getMessage());
			return null;

		}
	}
}
