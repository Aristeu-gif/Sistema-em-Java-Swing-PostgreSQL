package br.edu.ifg.resource;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class JtextFielEntrada extends JTextField {
	private int maxCaracteres = -1;

	public JtextFielEntrada() {
		super();
		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});

	}

	public JtextFielEntrada(int maximo) {
		super();
		setMaxCaracteres(maximo);

		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});
	}

	private void jTextFieldKeyTyped(KeyEvent evt) {
		String caracteres = "0987654321,.;{}[]/?<>*-+/=";
		if (caracteres.contains(evt.getKeyChar() + "")) {
			evt.consume();
		}
		// verifica o tamanho do campo
		if ((getText().length() >= getMaxCaracteres()) && (getMaxCaracteres() != -1)) {
			evt.consume();
			setText(getText().substring(0, getMaxCaracteres()));
		}

	}

	public int getMaxCaracteres() {
		return maxCaracteres;
	}

	public void setMaxCaracteres(int maxCaracteres) {
		this.maxCaracteres = maxCaracteres;
	}
}
