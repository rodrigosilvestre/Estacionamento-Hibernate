package com.ucsal.estacionamento.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "mensalidade")
public class Mensalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "identificador")
    private String identificador;
    
    @Column(name = "data_inicio")
    private Date dataInicio;
    
    @Column(name = "data_fim")
    private Date dataFim;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacionamento_id", referencedColumnName = "id")
    private Estacionamento estacionamento;

    public Mensalidade(String identificador, Date dataInicio, Date dataFim, Cliente cliente, Estacionamento estacionamento) {
        this.identificador = identificador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.estacionamento = estacionamento;
    }
    
    public Mensalidade() {
       
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    @Override
    public String toString() {
        return "Mensalidade{" +
                "identificador='" + identificador + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", cliente=" + cliente +
                ", estacionamento=" + estacionamento +
                '}';
    }
}
