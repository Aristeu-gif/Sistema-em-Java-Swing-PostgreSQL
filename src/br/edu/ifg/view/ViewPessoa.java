package br.edu.ifg.view;

import br.edu.ifg.ado.PessoaAdo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;
import br.edu.ifg.resource.JtextFielEntrada;
import br.edu.ifg.resource.ManipulacaoDeDatas;
import br.edu.ifg.resource.ValidacoesDeDadosPessoais;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewPessoa extends JFrame {

    private JPanel jpanel;
    private javax.swing.JLabel jlNome;
    private JLabel jlcpf;
    private JtextFielEntrada jtNome;
    private javax.swing.JTextField jtCpf;
    private JLabel jlDataNascimento;
    private JFormattedTextField jftDataNascimento;
    private JButton btSalvar;
    private JButton btExcluir;
    private JButton btAlterar;
    private ButtonGroup grupoSexo;
    private JPanel jpsexo;
    private javax.swing.JRadioButton jrMasculino;
    private javax.swing.JRadioButton jrFeminino;
    private String sexo;
    public ModeloTabelaPessoa modeloTabelaPessoa = new ModeloTabelaPessoa(PessoaAdo.listaPessoas());
    private JLabel jlMensagens;
    private JTable table;
    private JPanel jpInclusao;
    private String cpfAntigo;
    private JtextFielEntrada jtfBusca;
    private JButton jbBusca;

    public ViewPessoa() throws ParseException {
        defineJframe();

    }

    public void defineJframe() throws ParseException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 550, 576);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Cadastro de Pessoas");
        iniciaComponentes();
        setVisible(true);
    }

    public void iniciaComponentes() throws ParseException {
        jpanel = new JPanel();
        jlNome = new JLabel();
        jlcpf = new JLabel();
        jtNome = new JtextFielEntrada();
        jlMensagens = new JLabel();
        btSalvar = new JButton("Salvar");
        btExcluir = new JButton("Excluir");
        btExcluir.setEnabled(false);
        btAlterar = new JButton("Alterar");
        jpsexo = new JPanel();
        jrMasculino = new JRadioButton("M");
        jrFeminino = new JRadioButton("F");
        grupoSexo = new ButtonGroup();
        jlDataNascimento = new JLabel("Data de Nascimento");
        jftDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        grupoSexo.add(jrMasculino);
        grupoSexo.add(jrFeminino);

        table = new JTable(modeloTabelaPessoa);
        JScrollPane barraRolagem = new JScrollPane(table);
        iniciaComponentesDoPanelInclusaoAlteracao();
        jpanel.setBounds(2, 2, 230, 100);
        jpanel.setLayout(null);

        jlcpf.setText("CPF:");
        jlcpf.setBounds(20, 10, 40, 40);

        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            jtCpf = new JFormattedTextField(mf);
        } catch (Exception e) {

        }
        jtCpf.setBounds(20, 45, 150, 20);

        jlNome.setText("Nome:");
        jlNome.setBounds(20, 70, 150, 20);

        jtNome.setBounds(20, 95, 300, 20);
        btSalvar.setBounds(20, 125, 80, 25);

        btExcluir.setBounds(105, 125, 80, 25);
        btAlterar.setBounds(190, 125, 100, 25);
        btAlterar.setEnabled(false);

        jpsexo.setBorder(BorderFactory.createTitledBorder("Sexo"));
        jpsexo.setBounds(335, 70, 150, 57);

        jlDataNascimento.setBounds(335, 20, 150, 20);
        jftDataNascimento.setBounds(335, 45, 80, 20);

        jlMensagens.setBounds(335, 130, 180, 30);
        table.setSize(504, 330);
        barraRolagem.setSize(509, 335);
        barraRolagem.setBounds(2, 200, 540, 335);
        barraRolagem.setBorder(BorderFactory.createTitledBorder("Pessoas Cadastradas"));

        jtfBusca = new JtextFielEntrada();
        jtfBusca.setBounds(150, 167, 100, 30);
        add(jtfBusca);

        jbBusca = new JButton("buscar");
        jbBusca.setBounds(255, 167, 100, 30);
        add(jbBusca);

        add(barraRolagem);
        add(jpanel);
        jpInclusao.add(jlNome);
        jpInclusao.add(jlcpf);
        jpInclusao.setLayout(null);
        jpInclusao.add(jtNome);
        jpInclusao.add(jtCpf);
        jpInclusao.add(btSalvar);
        jpInclusao.add(btExcluir);
        jpInclusao.add(btAlterar);
        jpInclusao.add(jlDataNascimento);
        jpInclusao.add(jftDataNascimento);
        jpsexo.add(jrMasculino);
        jpsexo.add(jrFeminino);

        jpInclusao.add(jpsexo);
        jpInclusao.add(jlMensagens);
    }

    public boolean validaEntrada() throws ParseException {
        if (jtNome.getText().equals("") || jtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o nome", "Atenção", JOptionPane.WARNING_MESSAGE);

            return false;
        }
        if (jtCpf.getText().equals("   .   .   -") || jtCpf.getText().trim().length() < 12) {
            JOptionPane.showMessageDialog(null, "Informe o CPF corretamente", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            if (!(ValidacoesDeDadosPessoais.isCPF(jtCpf.getText().toString().replace(".", "").replace("-", "")))) {
                JOptionPane.showMessageDialog(null, "Informe um CPF válido", "Atenção", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (jrFeminino.isSelected()) {
            sexo = "F";
        }
        if (jrMasculino.isSelected()) {
            sexo = "M";
        }
        if (sexo.equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione o sexo", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (jftDataNascimento.getText().equals("  /  /    \n" + "") || jftDataNascimento.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a data de nascimento corretamente", "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            if (isDateValid(jftDataNascimento.getText()) == false) {
                JOptionPane.showMessageDialog(null, "Informe a data de nascimento corretamente", "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else {
                if (ManipulacaoDeDatas.subtraiDatas(jftDataNascimento.getText().toString()) < 18) {
                    JOptionPane.showMessageDialog(null, "É necessário ter 18 anos ou mais para se cadastrar", "Atenção",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void iniciaComponentesDoPanelInclusaoAlteracao() {
        jpInclusao = new JPanel();
        jpInclusao.setBounds(2, 2, 540, 160);
        jpInclusao.setBorder(BorderFactory.createTitledBorder("Inclusao"));
        jpanel.add(jpInclusao);

    }

    public ModeloTabelaPessoa getModeloTabelaPessoa() {
        return modeloTabelaPessoa;
    }

    public void setModeloTabelaPessoa(ModeloTabelaPessoa modeloTabelaPessoa) {
        this.modeloTabelaPessoa = modeloTabelaPessoa;
    }

    public JLabel getJlMensagens() {
        return jlMensagens;
    }

    public void setJlMensagens(JLabel jlMensagens) {
        this.jlMensagens = jlMensagens;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JLabel getJlNome() {
        return jlNome;
    }

    public void setJlNome(JLabel jlNome) {
        this.jlNome = jlNome;
    }

    public JLabel getJlcpf() {
        return jlcpf;
    }

    public void setJlcpf(JLabel jlcpf) {
        this.jlcpf = jlcpf;
    }

    public JtextFielEntrada getJtNome() {
        return jtNome;
    }

    public void setJtNome(JtextFielEntrada jtNome) {
        this.jtNome = jtNome;
    }

    public JTextField getJtCpf() {
        return jtCpf;
    }

    public void setJtCpf(JTextField jtCpf) {
        this.jtCpf = jtCpf;
    }

    public JButton getBtSalvar() {
        return btSalvar;
    }

    public void setBtSalvar(JButton btSalvar) {
        this.btSalvar = btSalvar;
    }

    public JButton getBtExcluir() {
        return btExcluir;
    }

    public void setBtExcluir(JButton btExcluir) {
        this.btExcluir = btExcluir;
    }

    public JButton getBtEditar() {
        return btAlterar;
    }

    public void setBtEditar(JButton btEditar) {
        this.btAlterar = btEditar;
    }

    public ButtonGroup getGrupoSexo() {
        return grupoSexo;
    }

    public void setGrupoSexo(ButtonGroup grupoSexo) {
        this.grupoSexo = grupoSexo;
    }

    public JPanel getJpsexo() {
        return jpsexo;
    }

    public void setJpsexo(JPanel jpsexo) {
        this.jpsexo = jpsexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public JRadioButton getJrMasculino() {
        return jrMasculino;
    }

    public void setJrMasculino(JRadioButton jrMasculino) {
        this.jrMasculino = jrMasculino;
    }

    public JRadioButton getJrFeminino() {
        return jrFeminino;
    }

    public void setJrFeminino(JRadioButton jrFeminino) {
        this.jrFeminino = jrFeminino;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JFormattedTextField getJftDataNascimento() {
        return jftDataNascimento;
    }

    public void setJftData(JFormattedTextField jftDataNascimento) {
        this.jftDataNascimento = jftDataNascimento;
    }

    public JButton getBtAlterar() {
        return btAlterar;
    }

    public void setBtAlterar(JButton btAlterar) {
        this.btAlterar = btAlterar;
    }

    public JPanel getJpInclusao() {
        return jpInclusao;
    }

    public void setJpInclusao(JPanel jpInclusao) {
        this.jpInclusao = jpInclusao;
    }

    public String getCpfAntigo() {
        return cpfAntigo;
    }

    public void setCpfAntigo(String cpfAntigo) {
        this.cpfAntigo = cpfAntigo;
    }

    public JLabel getJlDataNascimento() {
        return jlDataNascimento;
    }

    public void setJlDataNascimento(JLabel jlDataNascimento) {
        this.jlDataNascimento = jlDataNascimento;
    }

    public JtextFielEntrada getJtfBusca() {
        return jtfBusca;
    }

    public void setJtfBusca(JtextFielEntrada jtfBusca) {
        this.jtfBusca = jtfBusca;
    }

    public JButton getJbBusca() {
        return jbBusca;
    }

    public void setJbBusca(JButton jbBusca) {
        this.jbBusca = jbBusca;
    }

    public static void main(String[] args) throws ParseException {
        new ViewPessoa();

    }

}
