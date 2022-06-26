package calculadora.br.com.jeanvictor0.calc.visao;

import calculadora.br.com.jeanvictor0.calc.modelo.Memoria;
import calculadora.br.com.jeanvictor0.calc.modelo.MemoriaObservador;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calculadora.br.com.jeanvictor0.calc.modelo.Memoria;

@SuppressWarnings({ "serial", "unused" })
public class Display extends JPanel implements MemoriaObservador {
	private JLabel label;
	
	public Display(){
		Memoria.getInstancia().adicionarObservador(this);
		
		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN,20));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT,10,25));
		add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}
}
