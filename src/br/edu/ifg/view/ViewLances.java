package br.edu.ifg.view;

import br.edu.ifg.ado.LancesAdo;
import br.edu.ifg.ado.PessoaAdo;
import br.edu.ifg.model.Lance;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Pessoa;
import br.edu.ifg.model.Situacao;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifg.resource.JtextFielEntrada;
import java.util.ArrayList;

public class ViewLances extends JFrame {

    public ModeloTabelaPessoa modeloTabelaPessoa;
    public ModeloTabelaLance modeloTabelaLance;
    private JPanel jpanelGeral;
    private JLabel jlItem;
    private JLabel jlprecoInicial;
    private JLabel jlLance;
    private JTextField jtfLance;
    private JScrollPane jsPessoas;
    private JScrollPane jsLances;
    private JTable jtPessoas;
    private JTable jtLances;
    private JButton jbSalvar;
    private JButton jbEncerrarLeilao;
    private LeilaoModel leilaoModel;

    public ViewLances(LeilaoModel leilao) {
        this.leilaoModel = leilao;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 1000, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Lances");
        setLayout(null);
        setVisible(true);
        jpanelGeral();
        jpanelInclusao();
        modeloTabelaPessoa = new ModeloTabelaPessoa(PessoaAdo.listaPessoas());
        modeloTabelaLance = new ModeloTabelaLance(LancesAdo.listaLances(leilaoModel.getId()));
        montaTabelas();

    }

    public void jpanelGeral() {
        jpanelGeral = new JPanel();
        jpanelGeral.setBounds(1, 1, 999, 549);
        jpanelGeral.setLayout(null);

        jlItem = new JLabel("Leilao do Item: " + leilaoModel.getItem());
        jlItem.setBounds(300, 4, 160, 15);
        jpanelGeral.add(jlItem);

        jlprecoInicial = new JLabel("Preco Inicial: " + leilaoModel.getPrecoInicial().toString());
        jlprecoInicial.setBounds(510, 4, 200, 15);
        jpanelGeral.add(jlprecoInicial);

        add(jpanelGeral);
    }

    public void jpanelInclusao() {
        jlLance = new JLabel("Lance");
        jlLance.setBounds(390, 100, 50, 15);
        jpanelGeral.add(jlLance);

        jtfLance = new JTextField();
        jtfLance.setBounds(390, 123, 210, 25);
        jpanelGeral.add(jtfLance);

        jbSalvar = new JButton("Salvar");
        jbSalvar.setBounds(400, 160, 70, 25);
        jbSalvar.setEnabled(false);
        jpanelGeral.add(jbSalvar);

        jbEncerrarLeilao = new JButton("Encerrar");
        jbEncerrarLeilao.setBounds(480, 160, 100, 25);
        jbEncerrarLeilao.setEnabled(false);
        jpanelGeral.add(jbEncerrarLeilao);

    }

    public void montaTabelas() {
        //modelos tabelas

        //tabela pessoas
        jtPessoas = new JTable(modeloTabelaPessoa);
        jsPessoas = new JScrollPane(jtPessoas);
        jsPessoas.setBorder(BorderFactory.createTitledBorder("Pessoas"));
        jsPessoas.setBounds(15, 100, 350, 405);
        jpanelGeral.add(jsPessoas);
        //tabela lances

        jtLances = new JTable(modeloTabelaLance);
        jsLances = new JScrollPane(jtLances);
        jsLances.setBorder(BorderFactory.createTitledBorder("Lances"));
        jsLances.setBounds(625, 100, 350, 405);
        jpanelGeral.add(jsLances);
    }

//    public static void main(String[] args) {
//        ArrayList<Lance>lances = new ArrayList<>();
//        new FrmLances(new LeilaoModel(0, "carro", 100.0, Situacao.ABERTO,lances ));
//    }
    public ModeloTabelaPessoa getModeloTabelaPessoa() {
        return modeloTabelaPessoa;
    }

    public ModeloTabelaLance getModeloTabelaLance() {
        return modeloTabelaLance;
    }

    public JPanel getJpanelGeral() {
        return jpanelGeral;
    }

    public JLabel getJlItem() {
        return jlItem;
    }

    public JLabel getJlprecoInicial() {
        return jlprecoInicial;
    }

    public JLabel getJlLance() {
        return jlLance;
    }

    public JTextField getJtfLance() {
        return jtfLance;
    }

    public JScrollPane getJsPessoas() {
        return jsPessoas;
    }

    public JScrollPane getJsLances() {
        return jsLances;
    }

    public JTable getJtPessoas() {
        return jtPessoas;
    }

    public JTable getJtLances() {
        return jtLances;
    }

    public JButton getJbSalvar() {
        return jbSalvar;
    }

    public JButton getJbEncerrarLeilao() {
        return jbEncerrarLeilao;
    }

    public LeilaoModel getLeilaoModel() {
        return leilaoModel;
    }

}
