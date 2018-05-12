package src.br.quixada.teste.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import bin.br.quixada.teste.calculadora.Calculadora;


class CalculadoraTeste {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void somaCorretamenteDoisInteiros() {
		Calculadora calculadora = new Calculadora();
		int result = calculadora.soma(2, 2);
		int resultadoEsperado = 4;
		assertEquals(resultadoEsperado , result);
	}

	@Test
	void somaErradoDoisInteiros() {
		Calculadora calculadora = new Calculadora();
		int result = calculadora.soma(2, 2);
		int resultadoNaoEsperado = 5;
		assertNotEquals(resultadoNaoEsperado, result);
	}
	
	@Test
	void divideCorretamenteDoisInteiros() {
		Calculadora calculadora = new Calculadora();
		float result = calculadora.divisao(2, 3);
		float resultadoEsperado = (float)2/3;
		assertEquals(resultadoEsperado, result, 0.001);
	}
	
	@Test
	void dividePorZero() {
		thrown.expect(ArithmeticException.class);
		Calculadora calculadora = new Calculadora();
		float result = calculadora.divisao(2, 0);
	}
}
