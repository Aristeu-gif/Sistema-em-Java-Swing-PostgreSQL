/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.controller;

import br.edu.ifg.ado.ConectaAoBD;
import br.edu.ifg.ado.LancesAdo;
import br.edu.ifg.ado.LeilaoAdo;
import br.edu.ifg.ado.PessoaAdo;
import br.edu.ifg.model.Lance;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Situacao;
import br.edu.ifg.view.ViewLances;
import br.edu.ifg.view.ViewLeilao;
import br.edu.ifg.view.MensagemBdView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class LeilaoController {

    ViewLeilao frmLeilao;

    MensagemBdController mensagemBdController;

    private Situacao situacao;
    LeilaoModel leilaoModel;
    Boolean clicado = false;

    public LeilaoController() {
        instanciaTudo();
    }

    public void instanciaTudo() {
        if (verificaConexaoComBd()) {
        } else {
            this.frmLeilao = new ViewLeilao();
            this.leilaoModel = new LeilaoModel();

            acoes();
        }
    }

    public void acoes() {
        insereLeilao();
        ativaButtons();
        apagaLeilao();
        alteraLeilao();
        iniciaLeilao();
        buscaLeilaoPorSituacao();
    }

    public void buscaLeilaoPorSituacao() {
        frmLeilao.getJbBusca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicado == false) {

                    String situacao = frmLeilao.getJcSituacaoBusca().getSelectedItem().toString();

                    frmLeilao.getModeloTabelaLeiao().setLeiloes(LeilaoAdo.listaLeilaoPorSituacao(situacao));
                    frmLeilao.getModeloTabelaLeiao().fireTableDataChanged();
                    frmLeilao.getJbBusca().setText("cancelar");
                    clicado = true;
                } else {
                    frmLeilao.getJbBusca().setText("Buscar");
                    frmLeilao.getModeloTabelaLeiao().setLeiloes(LeilaoAdo.listaLeiloes());
                    frmLeilao.getModeloTabelaLeiao().fireTableDataChanged();
                    clicado = false;
                }
            }
        });
    }

    public void insereLeilao() {
        this.frmLeilao.getBtSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (frmLeilao.validaEntrada()) {
                    Double preco = frmLeilao.formataPrecoParaBd(frmLeilao.getJftfprecoInicial().getText());
                    //System.out.println(frmLeilao.formataPrecoParaBd(frmLeilao.getJftfprecoInicial().getText().toString()));
                    // System.out.println(preco);
                    LeilaoModel leilaoModel = new LeilaoModel(0, frmLeilao.getJtfItem().getText(), preco, Situacao.valueOf(frmLeilao.getJcSituacao().getSelectedItem().toString()));
                    if (LeilaoAdo.insereLeilao(leilaoModel)) {
                        frmLeilao.modeloTabelaLeiao.setLeiloes(LeilaoAdo.listaLeiloes());
                        frmLeilao.modeloTabelaLeiao.fireTableDataChanged();
                        frmLeilao.getJlConfirmacao().setText("O Item foi adicionado");
                        frmLeilao.getBtExcluir().setEnabled(false);
                    } else {
                        frmLeilao.getJlConfirmacao().setText("Não foi possível adicionar o Item");
                        frmLeilao.getBtExcluir().setEnabled(false);
                    }
                }
            }
        });
    }

    public void apagaLeilao() {
        frmLeilao.getBtExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LeilaoAdo.removeLeilao((int) frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 0));
                frmLeilao.modeloTabelaLeiao.setLeiloes(LeilaoAdo.listaLeiloes());
                frmLeilao.modeloTabelaLeiao.fireTableDataChanged();
                frmLeilao.getJlConfirmacao().setText("O Item foi removido");
                frmLeilao.getBtExcluir().setEnabled(false);
                frmLeilao.getBtEditar().setEnabled(false);
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

    public void ativaButtons() {
        this.frmLeilao.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                frmLeilao.getBtExcluir().setEnabled(true);
                frmLeilao.getBtEditar().setEnabled(true);
                frmLeilao.getBtIniciarLeilao().setEnabled(true);

                frmLeilao.getJtfItem().setText(frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 1).toString());
                frmLeilao.getJftfprecoInicial().setText(frmLeilao.formataPrecoRecebidoDoBD(frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 2).toString()).toString());
                frmLeilao.getJcSituacao().setSelectedItem(frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 3).toString());

                leilaoModel = recebeDados();

                frmLeilao.getJlValorLanceInicial().setText("Lance inicial: "+String.valueOf(LancesAdo.buscaMenorLance(recebeDados().getId())));
                frmLeilao.getJlValorLanceFinal().setText("Lance final: "+String.valueOf(LancesAdo.buscaLanceFinal(recebeDados().getId())));

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

    public void alteraLeilao() {
        frmLeilao.getBtEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frmLeilao.validaEntrada();
                if (recebeDados().getSituacao() == Situacao.LIQUIDADO) {
                    JOptionPane.showMessageDialog(null, "Esse leilão é liquidado. O preço inicial e o estado não serão alterados na edição");
                    int resposta = JOptionPane.showConfirmDialog(null, "Confirma a Alteracao?", "Alteração", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        leilaoModel.setItem(frmLeilao.getJtfItem().getText());
                        if (LeilaoAdo.alteraLeilao(leilaoModel, leilaoModel.getId())) {
                            frmLeilao.getJlConfirmacao().setText("Foi alterado");
                            frmLeilao.modeloTabelaLeiao.setLeiloes(LeilaoAdo.listaLeiloes());
                            frmLeilao.modeloTabelaLeiao.fireTableDataChanged();
                            frmLeilao.getBtExcluir().setEnabled(false);
                            frmLeilao.getBtEditar().setEnabled(false);
                        } else {
                            frmLeilao.getJlConfirmacao().setText("Nao foi alterado");
                            frmLeilao.getBtExcluir().setEnabled(false);
                            frmLeilao.getBtEditar().setEnabled(false);
                        }
                    }

                } else {
                    int resposta = JOptionPane.showConfirmDialog(null, "Confirma a Alteracao?", "Alteração", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        Double preco = frmLeilao.formataPrecoParaBd(frmLeilao.getJftfprecoInicial().getText());
                        LeilaoModel leilaoModel = new LeilaoModel(0, frmLeilao.getJtfItem().getText(), preco, Situacao.valueOf(frmLeilao.getJcSituacao().getSelectedItem().toString()));
                        int id = (int) frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 0);
                        if (LeilaoAdo.alteraLeilao(leilaoModel, id)) {
                            frmLeilao.getJlConfirmacao().setText("Foi alterado");
                            frmLeilao.modeloTabelaLeiao.setLeiloes(LeilaoAdo.listaLeiloes());
                            frmLeilao.modeloTabelaLeiao.fireTableDataChanged();
                            frmLeilao.getBtExcluir().setEnabled(false);
                            frmLeilao.getBtEditar().setEnabled(false);
                        } else {
                            frmLeilao.getJlConfirmacao().setText("Nao foi alterado");
                            frmLeilao.getBtExcluir().setEnabled(false);
                            frmLeilao.getBtEditar().setEnabled(false);
                        }
                    }
                }
            }
        });
    }

    public LeilaoModel recebeDados() {
        int id = (int) frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 0);
        String item = frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 1).toString();
        double preco = Double.valueOf(frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 2).toString());
        Situacao situacaoAux = Situacao.valueOf(frmLeilao.getTable().getValueAt(frmLeilao.getTable().getSelectedRow(), 3).toString());
        ArrayList<Lance> lances = new ArrayList<>();
        LeilaoModel leilaoModel = new LeilaoModel(id, item, preco, situacaoAux);
        return leilaoModel;
    }

    public void iniciaLeilao() {
        frmLeilao.getBtIniciarLeilao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (recebeDados().getSituacao() == Situacao.FECHADO) {
                    int abrir = JOptionPane.showConfirmDialog(null, "Esse leilão está fechado."
                            + "\n" + " Você deseja abri-lo?", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    if (abrir == JOptionPane.YES_OPTION) {
                        leilaoModel.setSituacao(Situacao.ABERTO);
                        LeilaoAdo.alteraLeilao(leilaoModel, recebeDados().getId());
                        LanceController lanceController = new LanceController(recebeDados());
                    }
                }
                if (recebeDados().getSituacao() == Situacao.LIQUIDADO) {
                    JOptionPane.showMessageDialog(null, "Esse leilão está liquidado.");
                } else {
                    LanceController lanceController = new LanceController(recebeDados());
                }

            }
        });
    }

    public static void main(String args[]) {
        new LeilaoController();
    }
}
