package br.edu.ifg.model;

import java.util.ArrayList;
import java.util.List;

public class LeilaoModel {

    private int id;
    private String item;
    private Double precoInicial;
    private Enum situacao;
    private List<Lance> lances;

    public LeilaoModel() {

    }

    public LeilaoModel(int id, String item, Double precoInicial, Enum situacao) {
        this.id = id;
        this.item = item;
        this.precoInicial = precoInicial;
        this.situacao = situacao;
        
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(Double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public Enum getSituacao() {
        return situacao;
    }

    public void setSituacao(Enum situacao) {
        this.situacao = situacao;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
