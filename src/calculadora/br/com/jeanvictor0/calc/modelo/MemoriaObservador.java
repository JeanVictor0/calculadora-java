package calculadora.br.com.jeanvictor0.calc.modelo;

@FunctionalInterface
public interface MemoriaObservador {
	void valorAlterado (String novoValor);
}
