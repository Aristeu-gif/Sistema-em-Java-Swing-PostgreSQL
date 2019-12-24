package br.edu.ifg.model;

public class Lance {

    private int id;
    private int idLeilao;
    private int idPessoa;
    private double valorDoLance;

    public Lance(double valor, int idPessoa, int idLeilao) {
        this.idLeilao = idLeilao;
        this.valorDoLance = valor;
        this.idPessoa = idPessoa;
    }

    public double getValor() {
        return valorDoLance;
    }

    public void setValor(double valor) {
        this.valorDoLance = valor;
    }

    public int getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(int idLeilao) {
        this.idLeilao = idLeilao;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorDoLance() {
        return valorDoLance;
    }

    public void setValorDoLance(double valorDoLance) {
        this.valorDoLance = valorDoLance;
    }

    public int getId() {
        return id;
    }
}
