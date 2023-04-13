package com.ucsal.estacionamento.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.ucsal.estacionamento.model.Mensalidade;
import com.ucsal.estacionamento.model.HibernateUtil;

public class MensalidadeDAO {

    public void adicionar(Mensalidade mensalidade) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // Insere a mensalidade na tabela mensalidade
            session.save(mensalidade);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
        }
    }

    public void atualizar(Mensalidade mensalidade) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // Atualiza a mensalidade na tabela mensalidade
            session.update(mensalidade);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
        }
    }

    public void remover(String identificador) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // Remove a mensalidade da tabela mensalidade
            Mensalidade mensalidade = session.get(Mensalidade.class, identificador);
            if (mensalidade != null) {
                session.delete(mensalidade);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
        }
    }

    public List<Mensalidade> buscarPorIdentificador(String identificador) {
        List<Mensalidade> mensalidades = new ArrayList<>();

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // Busca a mensalidade na tabela mensalidade
            Mensalidade mensalidade = session.get(Mensalidade.class, identificador);
            if (mensalidade != null) {
                mensalidades.add(mensalidade);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
        }

        return mensalidades;
    }

}
