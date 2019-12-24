package br.edu.ifg.view;

import br.edu.ifg.ado.LancesAdo;
import br.edu.ifg.ado.LeilaoAdo;
import br.edu.ifg.ado.PessoaAdo;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifg.model.Lance;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Pessoa;

public class ModeloTabelaLance extends AbstractTableModel {

    String[] cabecalho = {"CPF", "Nome", "Lance"};
    private List<Lance> lances;

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public ModeloTabelaLance(List<Lance> lances) {
        this.lances = lances;

    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return lances.size();
    }

    @Override
    public String getColumnName(int posi) {
        // TODO Auto-generated method stub
        return cabecalho[posi];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lance lance = this.lances.get(rowIndex);
        Pessoa pessoa = PessoaAdo.buscaPessoaPorId(lance.getIdPessoa());
        if (pessoa == null) {

        } else {
            switch (columnIndex) {
                case 0:
                    return pessoa.getCpf();
                case 1:
                    return pessoa.getNome();
                case 2:
                    return lance.getValor();
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return cabecalho.length;
    }

    public void getListeners(ActionListener actionListener) {

    }

    public void atualizaTabela(int id) {
        setLances(LancesAdo.listaLances(id));
        fireTableDataChanged();
    }
}
