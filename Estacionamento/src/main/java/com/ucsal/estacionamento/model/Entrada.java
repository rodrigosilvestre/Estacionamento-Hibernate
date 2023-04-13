package com.ucsal.estacionamento.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "entrada")
public class Entrada {

    @Id
    @Column(name = "identificador")
    private String identificador;

    @Column(name = "entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;

    @Column(name = "saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;

    @Column(name = "placa_cpf")
    private String placaCPF;

    @Column(name = "estacionamento_identificador")
    private String estacionamentoIdentificador;

    public Entrada(String identificador, Date entrada, Date saida, String placaCPF, String estacionamentoIdentificador) {
        this.identificador = identificador;
        this.entrada = entrada;
        this.saida = saida;
        this.placaCPF = placaCPF;
        this.estacionamentoIdentificador = estacionamentoIdentificador;
    }
    
    public Entrada() {

    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public String getPlacaCPF() {
        return placaCPF;
    }

    public void setPlacaCPF(String placaCPF) {
        this.placaCPF = placaCPF;
    }

    public String getEstacionamentoIdentificador() {
        return estacionamentoIdentificador;
    }

    public void setEstacionamentoIdentificador(String estacionamentoIdentificador) {
        this.estacionamentoIdentificador = estacionamentoIdentificador;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "identificador='" + identificador + '\'' +
                ", entrada=" + entrada +
                ", saida=" + saida +
                ", placaCPF='" + placaCPF + '\'' +
                ", estacionamentoIdentificador='" + estacionamentoIdentificador + '\'' +
                '}';
    }
}
