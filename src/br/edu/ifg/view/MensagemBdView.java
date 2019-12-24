/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.view;

import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Aristeu
 */
public class MensagemBdView extends JFrame {

    private JPanel jPanel;
    private JLabel jlMensagem;
    private JButton jbOk;
    private String mensagem = "Não foi possivel conectar ao banco de dados";

    Icon driveErro;

    public MensagemBdView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 460, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Erro");
        iniciaComponentes();
        setVisible(true);
    }

    public void iniciaComponentes() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder(""));
        jPanel.setBounds(1, 1, 455, 145);

        jlMensagem = new JLabel("A aplicacao nao reconheceu o banco de dados. Contate o analista");
        jlMensagem.setBounds(40, 10, 400, 80);
        jPanel.add(jlMensagem);

        jbOk = new JButton("Sair");
        jbOk.setBounds(190, 80, 70, 30);
        jPanel.add(jbOk);
        add(jPanel);

    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JLabel getJlMensagem() {
        return jlMensagem;
    }

    public void setJlMensagem(JLabel jlMensagem) {
        this.jlMensagem = jlMensagem;
    }

    public JButton getJbOk() {
        return jbOk;
    }

    public void setJbOk(JButton jbOk) {
        this.jbOk = jbOk;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
