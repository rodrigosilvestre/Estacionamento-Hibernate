package com.ucsal.estacionamento.dao;

import java.util.List;
import org.hibernate.Session;
import com.ucsal.estacionamento.model.Associacao;
import com.ucsal.estacionamento.model.HibernateUtil;


public class AssociacaoDAO {
    
	private Session session;
    
	public AssociacaoDAO() {
	    session = HibernateUtil.getSessionFactory().openSession();
	}

    
	public void adicionar(Associacao associacao) {
	    try {
	        session.beginTransaction();
	        session.save(associacao);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	}

	public void atualizar(Associacao associacao) {
	    try {
	        session.beginTransaction();
	        session.update(associacao);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	}

	public void remover(String identificador) {
	    try {
	        session.beginTransaction();
	        Associacao associacao = session.get(Associacao.class, identificador);
	        if (associacao != null) {
	            session.delete(associacao);
	            session.getTransaction().commit();
	        }
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	}

	public List<Associacao> listar() {
	    List<Associacao> associacoes = null;
	    try {
	        session.beginTransaction();
	        associacoes = session.createQuery("from Associacao", Associacao.class).getResultList();
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	    return associacoes;
	}

	public Associacao buscarPorIdentificador(String identificador) {
	    Associacao associacao = null;
	    try {
	        session.beginTransaction();
	        associacao = session.get(Associacao.class, identificador);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	    return associacao;
	}
}