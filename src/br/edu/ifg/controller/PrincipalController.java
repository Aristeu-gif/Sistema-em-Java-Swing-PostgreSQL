/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.controller;

import br.edu.ifg.view.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.Clock;

/**
 *
 * @author Aristeu
 */
public class PrincipalController {

	ViewPrincipal viewPrincipal;

	public PrincipalController() {
		viewPrincipal = new ViewPrincipal();
		acoesItensDeMenu();
	}

	public void acoesItensDeMenu() {
		viewPrincipal.getCadastroLeilaoMenuItem().addActionListener(listenerCadastraLeilao());
		viewPrincipal.getCadastroPessoaMenuItem().addActionListener(listenerCadastroPessoa());
	}

	private ActionListener listenerCadastroPessoa() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PessoaController  pessoaController = new PessoaController();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
	}

	private ActionListener listenerCadastraLeilao() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LeilaoController controllerLeilao = new LeilaoController();
				
			}
		};
	}

	
	
}
