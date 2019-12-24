/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.controller;

import br.edu.ifg.view.MensagemBdView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Aristeu
 */
public class MensagemBdController {

    private MensagemBdView mensagemBdView;

    public MensagemBdController() {
        mensagemBdView = new MensagemBdView();
        acoes();

    }

    public void acoes() {
        mensagemBdView.getJbOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

}
