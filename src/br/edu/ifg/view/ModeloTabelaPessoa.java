package br.edu.ifg.view;

import br.edu.ifg.ado.PessoaAdo;

import java.text.ParseException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifg.model.Pessoa;
import br.edu.ifg.resource.ManipulacaoDeDatas;

public class ModeloTabelaPessoa extends AbstractTableModel {

    String[] cabecalho = {"Id", "Nome", "CPF", "Sexo","Nascimento"};
    private List<Pessoa> pessoas;

    public ModeloTabelaPessoa(List<Pessoa> pessoas) {
        this.pessoas = pessoas;

    }

    public String[] getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String[] cabecalho) {
        this.cabecalho = cabecalho;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return pessoas.size();
    }

    @Override
    public String getColumnName(int posi) {
        // TODO Auto-generated method stub
        return cabecalho[posi];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pessoa pessoa = this.pessoas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pessoa.getId();
            case 1:
                return pessoa.getNome();
            case 2:
                return pessoa.getCpf();
            case 3:
                return pessoa.getSexo();
            case 4:
			try {
				return ManipulacaoDeDatas.inverteData(pessoa.getDataNascimento().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    public void atualizaTabela(){
        setPessoas(PessoaAdo.listaPessoas());
        fireTableDataChanged();
    }


}
