/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Aristeu
 */
public class ViewPrincipal extends JFrame {
	private JMenuBar barraMenu;
	private JMenu cadastrosMenu;
	private JMenuItem cadastroLeilaoMenuItem;
	private JMenuItem cadastroPessoaMenuItem;

	public ViewPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 255, 300);
		setResizable(false);
		setLayout(null);
		setTitle("Cadastros");
		setLocationRelativeTo(null);
		setVisible(true);
		iniciaComponentes();

	}

	public void iniciaComponentes() {
		barraMenu = new JMenuBar();
		barraMenu.setLocation(1, 1);
		barraMenu.setBounds(1, 1, 255, 20);

		cadastrosMenu = new JMenu("Menu");
		cadastrosMenu.setLocation(1, 1);
		cadastroLeilaoMenuItem = new JMenuItem("Cadastro de Leilão");
		cadastroPessoaMenuItem = new JMenuItem("Cadastro de Pessoas");

		cadastrosMenu.add(cadastroLeilaoMenuItem);
		cadastrosMenu.add(cadastroPessoaMenuItem);

		barraMenu.add(cadastrosMenu);

		add(barraMenu);

	}

	public static void main(String[] args) {
		new ViewPrincipal();
	}

	public JMenuBar getBarraMenu() {
		return barraMenu;
	}

	public void setBarraMenu(JMenuBar barraMenu) {
		this.barraMenu = barraMenu;
	}

	public JMenu getCadastrosMenu() {
		return cadastrosMenu;
	}

	public void setCadastrosMenu(JMenu cadastrosMenu) {
		this.cadastrosMenu = cadastrosMenu;
	}

	public JMenuItem getCadastroLeilaoMenuItem() {
		return cadastroLeilaoMenuItem;
	}

	public void setCadastroLeilaoMenuItem(JMenuItem cadastroLeilaoMenuItem) {
		this.cadastroLeilaoMenuItem = cadastroLeilaoMenuItem;
	}

	public JMenuItem getCadastroPessoaMenuItem() {
		return cadastroPessoaMenuItem;
	}

	public void setCadastroPessoaMenuItem(JMenuItem cadastroPessoaMenuItem) {
		this.cadastroPessoaMenuItem = cadastroPessoaMenuItem;
	}
	

}
