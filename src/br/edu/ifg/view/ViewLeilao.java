package br.edu.ifg.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.LineNumberInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.edu.ifg.ado.ConectaAoBD;
import br.edu.ifg.ado.LeilaoAdo;
import br.edu.ifg.model.Lance;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Situacao;
import br.edu.ifg.resource.JMoneyField;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.plaf.ColorUIResource;

public class ViewLeilao extends JFrame {

    public ModeloTabelaLeilao modeloTabelaLeiao = new ModeloTabelaLeilao(LeilaoAdo.listaLeiloes());
    private JTable table = new JTable(modeloTabelaLeiao);
    private JPanel jpanelGeral;
    private JPanel jpanelInclusao;
    private JPanel jpanelAcao;

    private JLabel jlItem;
    private JTextField jtfItem;

    private JLabel jlprecoInicial;
    private JFormattedTextField jftfprecoInicial;

    private JLabel jlSituacao;
    private JComboBox<String> jcSituacao;

    private JLabel jlSituacaoBusca;
    private JComboBox<String> jcSituacaoBusca;

    private JButton jbBusca;
    private String[] situacao = {"ESCOLHA", "ABERTO", "FECHADO", "LIQUIDADO"};

    private JButton btSalvar;
    private JButton btExcluir;
    private JButton btEditar;
    private JButton btIniciarLeilao;

    private JLabel jlConfirmacao;
    private JLabel jlValorLanceInicial;
    private JLabel jlValorLanceFinal;
    

    public ViewLeilao() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 550, 737);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Formulario Leilao");
        jpanelGeral = new JPanel();
        jpanelGeral.setBounds(0, 0, 550, 700);
        jpanelGeral.setLayout(null);
        iniciaComponentesDoPanelInclusao();
        iniciaButtons();
        iniciaComponentesDoJscrollPaneLeiloes();
        iniciaComponentesDoPanelGeral();
        add(jpanelGeral);
        setLayout(null);
        setVisible(true);
    }

    public void iniciaComponentesDoPanelInclusao() {
        jpanelInclusao = new JPanel();
        jpanelInclusao.setBorder(BorderFactory.createTitledBorder("Inclusao"));
        jpanelGeral.setLayout(null);
        jpanelInclusao.setBounds(2, 2, 546, 190);
        jpanelInclusao.setLayout(null);

        jlSituacaoBusca = new JLabel("Buscar");
        jlSituacaoBusca.setBounds(250, 192, 80, 30);
        jpanelGeral.add(jlSituacaoBusca);

        jcSituacaoBusca = new JComboBox<>(situacao);
        jcSituacaoBusca.setBounds(170, 222, 90, 35);
        jpanelGeral.add(jcSituacaoBusca);

        jbBusca = new JButton("busca");
        jbBusca.setBounds(270, 222, 90, 35);
        jpanelGeral.add(jbBusca);

        jlItem = new JLabel("Item:");
        jlItem.setBounds(20, 10, 40, 40);
        jtfItem = new JTextField();
        jtfItem.setBounds(20, 45, 150, 20);

        jftfprecoInicial = new JMoneyField();
        // formataPreco();

        jlprecoInicial = new JLabel("Preco Inicial:");
        jlprecoInicial.setBounds(20, 65, 100, 40);

        jftfprecoInicial.setBounds(20, 99, 150, 20);

        jlSituacao = new JLabel("Situacao:");
        jlSituacao.setBounds(20, 120, 100, 40);

        jcSituacao = new JComboBox<>(situacao);
        jcSituacao.setBounds(20, 155, 100, 20);

        jlValorLanceInicial = new JLabel();
        jlValorLanceInicial.setBounds(340, 90, 190, 60);

        jlValorLanceFinal = new JLabel();
        jlValorLanceFinal.setBounds(340, 110, 190, 60);

        jlConfirmacao = new JLabel();
        jlConfirmacao.setBackground(Color.yellow);
        jlConfirmacao.setBounds(340, 140, 190, 60);

        jpanelInclusao.add(jlItem);
        jpanelInclusao.add(jtfItem);
        jpanelInclusao.add(jlprecoInicial);
        jpanelInclusao.add(jftfprecoInicial);
        jpanelInclusao.add(jlSituacao);
        jpanelInclusao.add(jcSituacao);

        jpanelInclusao.add(jlConfirmacao);
        jpanelInclusao.add(jlValorLanceInicial);
        jpanelInclusao.add(jlValorLanceFinal);
    }

    public void iniciaButtons() {
        jpanelAcao = new JPanel();
        jpanelAcao.setBounds(2, 285, 546, 65);
        jpanelAcao.setBorder(BorderFactory.createTitledBorder("Acao"));

        btSalvar = new JButton("Salvar");
        btExcluir = new JButton("Excluir");
        btExcluir.setEnabled(false);
        btEditar = new JButton("Editar");
        btEditar.setEnabled(false);
        btIniciarLeilao = new JButton("Iniciar Leilao");
        btIniciarLeilao.setEnabled(false);

        btSalvar.setBounds(20, 552, 80, 25);
        btExcluir.setBounds(105, 552, 80, 25);
        btEditar.setBounds(190, 552, 100, 25);
        btIniciarLeilao.setBounds(200, 665, 150, 30);
        jpanelAcao.add(btSalvar);
        jpanelAcao.add(btExcluir);
        jpanelAcao.add(btEditar);
        add(btIniciarLeilao);

    }

    public void iniciaComponentesDoPanelGeral() {
        jpanelGeral.add(jpanelInclusao);
        jpanelGeral.add(jpanelAcao);
    }

    public void iniciaComponentesDoJscrollPaneLeiloes() {

        JScrollPane barraRolagem = new JScrollPane(table);
        table.setSize(504, 300);
        barraRolagem.setBounds(2, 350, 545, 300);
        barraRolagem.setBorder(BorderFactory.createTitledBorder("Itens"));
        jpanelGeral.add(barraRolagem);
    }

//    public void formataPreco() {
//        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
//        NumberFormatter numeroFormatado = new NumberFormatter(decimal);
//        numeroFormatado.setFormat(decimal);
//        numeroFormatado.setAllowsInvalid(false);
//        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numeroFormatado);
//        jftfprecoInicial.setFormatterFactory(dfFactory);
//    }
    public Double formataPrecoParaBd(String preco) {
        String novoPreco = "";
        novoPreco = preco.replace(".", "").replace(",", ".");

        return Double.valueOf(novoPreco);

    }

    public Double formataPrecoRecebidoDoBD(String preco) {

        return Double.valueOf(preco);
    }

    public boolean validaEntrada() {
        if (jtfItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do Item", "Atencao", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (jftfprecoInicial.getText().equals("") || jftfprecoInicial.getText().equals("0,00")
                || jftfprecoInicial.getText().equals(",00")) {
            JOptionPane.showMessageDialog(this, "Informe um preco inicial valido", "Atencao",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (jcSituacao.getSelectedItem().equals("Escolha") || jcSituacao.getSelectedItem().equals("ESCOLHA")) {
            JOptionPane.showMessageDialog(this, "Selecione uma situacao", "Atencao", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public ModeloTabelaLeilao getModeloTabelaLeiao() {
        return modeloTabelaLeiao;
    }

    public void setModeloTabelaLeiao(ModeloTabelaLeilao modeloTabelaLeiao) {
        this.modeloTabelaLeiao = modeloTabelaLeiao;
    }

    public JTable getTable() {
        return table;
    }

    public JPanel getJpanelGeral() {
        return jpanelGeral;
    }

    public JPanel getJpanelInclusao() {
        return jpanelInclusao;
    }

    public JPanel getJpanelAcao() {
        return jpanelAcao;
    }

    public JLabel getJlItem() {
        return jlItem;
    }

    public JTextField getJtfItem() {
        return jtfItem;
    }

    public JLabel getJlprecoInicial() {
        return jlprecoInicial;
    }

    public JFormattedTextField getJftfprecoInicial() {
        return jftfprecoInicial;
    }

    public JLabel getJlSituacao() {
        return jlSituacao;
    }

    public JComboBox<String> getJcSituacao() {
        return jcSituacao;
    }

    public String[] getSituacao() {
        return situacao;
    }

    public JButton getBtSalvar() {
        return btSalvar;
    }

    public JButton getBtExcluir() {
        return btExcluir;
    }

    public JButton getBtEditar() {
        return btEditar;
    }

    public JButton getBtIniciarLeilao() {
        return btIniciarLeilao;
    }

    public JLabel getJlConfirmacao() {
        return jlConfirmacao;
    }

    public JLabel getJlValorLanceInicial() {
        return jlValorLanceInicial;
    }

    public JLabel getJlValorLanceFinal() {
        return jlValorLanceFinal;
    }

    public static void main(String[] args) {
        new ViewLeilao();
    }

    public JLabel getJlSituacaoBusca() {
        return jlSituacaoBusca;
    }

    public JComboBox<String> getJcSituacaoBusca() {
        return jcSituacaoBusca;
    }

    public JButton getJbBusca() {
        return jbBusca;
    }

}
