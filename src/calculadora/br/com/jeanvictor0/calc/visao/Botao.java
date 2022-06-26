package calculadora.br.com.jeanvictor0.calc.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton implements ActionListener {

	
	public Botao(String texto,Color cor) {
		setText(texto);
		setFont(new Font("courier", Font.PLAIN,20));
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Executado");
		
	}
}
