package com.ucsal.estacionamento.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ucsal.estacionamento.model.Veiculo;
import com.ucsal.estacionamento.model.HibernateUtil;

public class VeiculoDAO {

	public void adicionar(Veiculo veiculo) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(veiculo);
			transaction.commit();
			System.out.println("Veículo adicionado com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao adicionar veículo: " + e.getMessage());
		}
	}

	public void atualizar(Veiculo veiculo) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(veiculo);
			transaction.commit();
			System.out.println("Veículo atualizado com sucesso!");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao atualizar veículo: " + e.getMessage());
		}
	}

	public void remover(String placa) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Veiculo veiculo = session.get(Veiculo.class, placa);
			if (veiculo != null) {
				session.delete(veiculo);
				System.out.println("Veículo removido com sucesso!");
			} else {
				System.out.println("Veículo não encontrado!");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Erro ao remover veículo: " + e.getMessage());
		}
	}

	public Veiculo buscarPorPlaca(String placa) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Veiculo.class, placa);
		} catch (Exception e) {
			System.out.println("Erro ao buscar veículo por placa: " + e.getMessage());
			return null;
		}
	}

	public List<Veiculo> listar() {
		List<Veiculo> veiculos = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			veiculos = session.createQuery("FROM Veiculo", Veiculo.class).list();
			return veiculos;
		} catch (Exception e) {
			System.out.println("Erro ao listar veículos: " + e.getMessage());
			return null;
		}
	}

}
