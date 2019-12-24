/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.controller;

import br.edu.ifg.ado.ConectaAoBD;
import br.edu.ifg.ado.PessoaAdo;
import br.edu.ifg.model.Pessoa;
import br.edu.ifg.resource.ManipulacaoDeDatas;
import br.edu.ifg.view.ViewPessoa;
import br.edu.ifg.view.ModeloTabelaPessoa;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

/**
 *
 * @author Aristeu
 */
public class PessoaController {

    private ViewPessoa frmPessoa;
    private MensagemBdController mensagemBdController;
    private boolean clicado = false;

    public PessoaController() throws ParseException {
        this.frmPessoa = new ViewPessoa();

        acaoBotao();
    }

    public void acaoBotao() {
        cadastraPessoa();
        apagaPessoa();
        ativaButtonExcluir();
        alteraPessoa();
        buscaPessoa();
    }

    public void cadastraPessoa() {
        this.frmPessoa.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    if (frmPessoa.validaEntrada()) {
                        if (PessoaAdo.buscaPessoaPorCpf(frmPessoa.getJtCpf().getText().toString()) == true) {
                            JOptionPane.showMessageDialog(null, "Já existe um usuário com esse CPF cadastrado", "Atencao",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            try {
                                if (PessoaAdo.inserePessoa(new Pessoa(frmPessoa.getJtCpf().getText(),
                                        frmPessoa.getJtNome().getText(), frmPessoa.getSexo(),
                                        ManipulacaoDeDatas.formataData(frmPessoa.getJftDataNascimento().getText())))) {
                                    frmPessoa.modeloTabelaPessoa.atualizaTabela();
                                    frmPessoa.getJlMensagens().setText("O Item foi adicionado");
                                    frmPessoa.getBtExcluir().setEnabled(false);
                                    frmPessoa.getBtEditar().setEnabled(false);

                                } else {
                                    JOptionPane.showMessageDialog(null, "Não foi possível adicionar", "Atencao",
                                            JOptionPane.ERROR_MESSAGE);
                                    frmPessoa.getBtExcluir().setEnabled(false);
                                    frmPessoa.getBtEditar().setEnabled(false);
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (HeadlessException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    public void apagaPessoa() {
        frmPessoa.getBtExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int id = Integer.parseInt(
                        frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(), 0).toString());
                if (PessoaAdo.verificaSePessoaParticipouDeLances(id)) {
                    JOptionPane.showMessageDialog(null, "não é possível remover essa pessoa, pois ela participou de lances");
                } else {
                    PessoaAdo.removePessoa(id);
                    // System.out.println(frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(),
                    // 0).toString());
                    frmPessoa.modeloTabelaPessoa.atualizaTabela();
                    frmPessoa.getJlMensagens().setText("O Item foi removido");
                    frmPessoa.getBtExcluir().setEnabled(false);
                    frmPessoa.getBtEditar().setEnabled(false);
                }
            }
        });
    }

    public void buscaPessoa() {
        
        frmPessoa.getJbBusca().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (clicado == false) {
                    
                    String nome = frmPessoa.getJtfBusca().getText().toString();

                    frmPessoa.getModeloTabelaPessoa().setPessoas(PessoaAdo.listaPessoasPorNome(nome));
                    frmPessoa.getModeloTabelaPessoa().fireTableDataChanged();
                     frmPessoa.getJbBusca().setText("cancelar");
                    clicado = true;
                } else {
                    frmPessoa.getJbBusca().setText("Buscar");
                    frmPessoa.getModeloTabelaPessoa().atualizaTabela();
                    clicado=false;
                }
            }
        });
    }

    public boolean verificaConexaoComBd() {
        if (ConectaAoBD.getConexao() == null) {
            mensagemBdController = new MensagemBdController();
            return true;
        }

        return false;
    }

    public void ativaButtonExcluir() {
        frmPessoa.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {

                frmPessoa.getBtExcluir().setEnabled(true);
                frmPessoa.getBtEditar().setEnabled(true);
                frmPessoa.getJtCpf()
                        .setText(frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(), 2).toString());
                frmPessoa.setCpfAntigo(
                        frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(), 2).toString());
                frmPessoa.getJtNome()
                        .setText(frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(), 1).toString());
                frmPessoa.getJftDataNascimento().setText(frmPessoa.getTable()
                        .getValueAt(frmPessoa.getTable().getSelectedRow(), 4).toString().replace("-", "/"));
                if (frmPessoa.getTable().getValueAt(frmPessoa.getTable().getSelectedRow(), 3).toString().equals("M")) {
                    frmPessoa.getJrMasculino().setSelected(true);

                } else {
                    frmPessoa.getJrFeminino().setSelected(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {

            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    public void alteraPessoa() {
        frmPessoa.getBtEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (frmPessoa.validaEntrada()) {
                        int resposta = JOptionPane.showConfirmDialog(null, "Confirma a Alteracao?", "Exclusao",
                                JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            try {
                                if (PessoaAdo.alteraPessoa(new Pessoa(frmPessoa.getJtCpf().getText(),
                                        frmPessoa.getJtNome().getText(), frmPessoa.getSexo(),
                                        ManipulacaoDeDatas.formataData(frmPessoa.getJftDataNascimento().getText())))) {
                                    frmPessoa.getJlMensagens().setText("Foi alterado");
                                    frmPessoa.modeloTabelaPessoa.atualizaTabela();
                                    frmPessoa.getBtExcluir().setEnabled(false);
                                    frmPessoa.getBtEditar().setEnabled(false);
                                } else {
                                    frmPessoa.getJlMensagens().setText("Nao foi alterado");
                                    frmPessoa.getBtExcluir().setEnabled(false);
                                    frmPessoa.getBtEditar().setEnabled(false);
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (HeadlessException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String args[]) throws ParseException {
        new PessoaController();

    }

}
