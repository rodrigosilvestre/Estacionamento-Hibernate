package com.ucsal.estacionamento.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ucsal.estacionamento.model.Cliente;
import com.ucsal.estacionamento.model.HibernateUtil;

public class ClienteDAO {

	private SessionFactory sessionFactory;

	public ClienteDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void adicionar(List<Cliente> clientes) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			for (Cliente cliente : clientes) {
				session.save(cliente);
			}
			transaction.commit();
			System.out.println("Clientes adicionados com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void atualizar(Cliente cliente) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(cliente);
			transaction.commit();
			System.out.println("Cliente atualizado com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void excluir(String cpf) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Cliente where cpf = :cpf");
			query.setParameter("cpf", cpf);
			query.executeUpdate();
			transaction.commit();
			System.out.println("Cliente excluído com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Cliente> buscarTodos() {
		List<Cliente> clientes = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			clientes = session.createQuery("from Cliente", Cliente.class).list();
			System.out.println("Lista de clientes encontrada!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public Cliente buscarPorCPF(String cpf) {
		Cliente cliente = null;
		try (Session session = sessionFactory.openSession()) {
			cliente = session.get(Cliente.class, cpf);
			if (cliente != null) {
				System.out.println("Cliente encontrado!");
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

}
