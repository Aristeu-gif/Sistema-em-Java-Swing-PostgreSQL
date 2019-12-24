/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.controller;

import br.edu.ifg.ado.LancesAdo;
import br.edu.ifg.ado.LeilaoAdo;
import br.edu.ifg.ado.PessoaAdo;
import br.edu.ifg.model.Lance;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Situacao;
import br.edu.ifg.view.ViewLances;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Aristeu
 */
public class LanceController {

    ViewLances frmLance;
    LancesAdo lancesAdo;
    LeilaoModel leilaoModel;
    Lance lance;

    public LanceController(LeilaoModel leilaoModel) {
        this.frmLance = new ViewLances(leilaoModel);
        this.leilaoModel = leilaoModel;
        eventoDaTabela();
        insereLance();
        encerraLeilao();
    }

    public void ativaButton() {
        frmLance.getJbSalvar().setEnabled(true);
        frmLance.getJbEncerrarLeilao().setEnabled(true);
    }

    public void eventoDaTabela() {
        frmLance.getJtPessoas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                ativaButton();

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

    public boolean validaPreco() {
        if (Double.valueOf(frmLance.getJtfLance().getText()) >= leilaoModel.getPrecoInicial()) {
            return true;
        } else {
            return false;
        }
    }

    public void insereLance() {

        frmLance.getJbSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int idPessoa = Integer.parseInt(frmLance.getJtPessoas().getValueAt(frmLance.getJtPessoas().getSelectedRow(), 0).toString());
                if (!validaPreco()) {
                    JOptionPane.showMessageDialog(null, "Informe um valor maior que o lance inicial");
                } else {
                    if (verificaUltimoLance()) {
                        JOptionPane.showMessageDialog(null, "Não pode realizar 2 lances seguidos da mesma pessoa");
                    } else {
                        if (PessoaAdo.verificaSePessoaDeuMaisDeCincoLances(idPessoa,leilaoModel.getId())) {
                            JOptionPane.showMessageDialog(null, "Essa pessoa já realizou 5 lances");
                        } else {

                            int idLeilao = leilaoModel.getId();
                            double valorDoLance = Double.valueOf(frmLance.getJtfLance().getText());
                            lance = new Lance(valorDoLance, idPessoa, idLeilao);
                            boolean inseriu = LancesAdo.insereLance(lance);
                            if (inseriu) {
                                JOptionPane.showMessageDialog(null, "Lance inserido com sucesso");
                                frmLance.modeloTabelaLance.setLances(lancesAdo.listaLances(frmLance.getLeilaoModel().getId()));
                                frmLance.modeloTabelaLance.fireTableDataChanged();

                            } else {
                                JOptionPane.showMessageDialog(null, "Não foi possível inserir");
                            }
                        }
                    }
                }
            }
        });
    }

    public void encerraLeilao() {
        frmLance.getJbEncerrarLeilao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(LancesAdo.buscaQuantidadeDeLancesDoLeilao(leilaoModel.getId())==0){
                    JOptionPane.showMessageDialog(null, "Você não pode encerrar esse leilão sem realizar pelo menos 1 lance");
                }else{
                    leilaoModel.setSituacao(Situacao.LIQUIDADO);
                    LeilaoAdo.alteraLeilao(leilaoModel, leilaoModel.getId());
                    System.exit(0);
                }
            }
        });
    }

    public boolean verificaUltimoLance() {
        boolean iguais = false;
        int idPessoa = Integer.parseInt(frmLance.getJtPessoas().getValueAt(frmLance.getJtPessoas().getSelectedRow(), 0).toString());
        System.out.println("id ultima pessoa");
        if (idPessoa == PessoaAdo.buscaIdDaPessoaDoUltimoLance(lancesAdo.buscaOIdDoUltimoLance(),leilaoModel.getId())) {
            iguais = true;
        } else {
            iguais = false;
        }
        return iguais;
    }

}
