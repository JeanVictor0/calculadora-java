package calculadora.br.com.jeanvictor0.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
		
		
		
		ZERAR,NUMERO,DIV,MULT,SUB,SOMA,IGUAL,VIRGULA,SINAL; 
	}
	
	private static final Memoria instancia = new Memoria();
	private TipoComando ultimaOperacao = null;
	private Boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	
	private final List<MemoriaObservador> observadores = new ArrayList<>();
	
	private Memoria() {
		
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObservador(MemoriaObservador o) {
		observadores.add(o);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando(String valor) {
		
		TipoComando tipoComando = detectarTipoComando(valor);
		
		System.out.println(tipoComando);
		
		if(tipoComando == null) {
			return;
		} else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
			
		} else if(tipoComando == TipoComando.SINAL && textoAtual.contains("-")) {
			
			textoAtual = textoAtual.substring(1);
			
		} else if(tipoComando == TipoComando.SINAL && !textoAtual.contains("-")) {
			
			textoAtual = "-" + textoAtual;
			
		} else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
			
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null 
				|| ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = 
				Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = 
				Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if(ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if(ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}
		
		String texto = Double.toString(resultado).replace(".", ",");
		boolean inteiro = texto.endsWith(",0");
		return inteiro ? texto.replace(",0", "") : texto;
	}

	private TipoComando detectarTipoComando(String valor) {
		System.out.println(valor);
		if (textoAtual.isEmpty() && "0".equals(valor)) {
			return null;
		}
		
		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;
		} catch (Exception e) {
			if("AC".equals(valor)) {
				
				return TipoComando.ZERAR;
				
			} else if("/".equals(valor)) {
				
				return TipoComando.DIV;
				
			} else if("*".equals(valor)) {
				
				return TipoComando.MULT;
				
			} else if("+".equals(valor)) {
				
				return TipoComando.SOMA;
				
			} else if("-".equals(valor)) {
				
				return TipoComando.SUB;
				
			} else if("=".equals(valor)) {
				
				return TipoComando.IGUAL;
				
			} else if("±".equals(valor)) {
				
				return TipoComando.SINAL;
				
			} else if(",".equals(valor) && !textoAtual.contains(",")) {
				
				return TipoComando.VIRGULA;
			}
		}
		return null;

	}


	
	
}
