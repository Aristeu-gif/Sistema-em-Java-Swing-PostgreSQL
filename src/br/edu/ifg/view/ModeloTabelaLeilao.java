package br.edu.ifg.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifg.model.LeilaoModel;
import java.awt.event.ActionListener;

public class ModeloTabelaLeilao extends AbstractTableModel {

    String[] cabecalho = {"Codigo", "Item", "Preco Inicial", "Situacao"};
    private List<LeilaoModel> leiloes;

    public List<LeilaoModel> getLeiloes() {
        return leiloes;
    }

    public void setLeiloes(List<LeilaoModel> leiloes) {
        this.leiloes = leiloes;
    }

    public ModeloTabelaLeilao(List<LeilaoModel> leiloes) {
        this.leiloes = leiloes;

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return leiloes.size();
    }

    @Override
    public String getColumnName(int posi) {
        // TODO Auto-generated method stub
        return cabecalho[posi];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LeilaoModel leilao = this.leiloes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return leilao.getId();
            case 1:
                return leilao.getItem();
            case 2:
                return leilao.getPrecoInicial();
            case 3:
                return leilao.getSituacao();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return cabecalho.length;
    }

    public void getListeners(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
