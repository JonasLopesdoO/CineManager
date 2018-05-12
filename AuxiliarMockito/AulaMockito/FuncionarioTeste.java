package AulaMockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FuncionarioTeste {
	@Test
	public void adicionarFuncionarioTeste() {
		MockitoAnnotations.initMocks(this);
		//configurando o stub usando mockito
		//stub da dependencia
		
		DepartamentoControlador departamentoControlador = Mockito.mock(DepartamentoControlador.class);

		//definicao do retorno do m�todo do stub
		Departamento departamento = new Departamento();
		Mockito.when(departamentoControlador.leDepartamentoPorID(1)).thenReturn(departamento);

		//exercitar a classe a ser testada
		FuncionarioControlador funcionarioControlador = new FuncionarioControlador(departamentoControlador);
		int indice = funcionarioControlador.adicionaFuncionario("paulyne", "senha", 1);
		
		//verificacao
		Funcionario funcionarioEsperado = new Funcionario("paulyne", "senha", departamento);
		Funcionario funcionarioRecebido = funcionarioControlador.encontreFuncionario(indice);
		Assert.assertEquals(funcionarioEsperado.getNome(), funcionarioRecebido.getNome());
	}
	
	@Test
	public void aoAdicionarFuncionarioChamaLeDepartamentoPorIDApenasUmaVezTeste() {
		MockitoAnnotations.initMocks(this);
		
		//configurando o mock usando mockito
		//mock da dependencia
		DepartamentoControlador departamentoControlador = Mockito.mock(DepartamentoControlador.class);
		Departamento departamento = new Departamento();
		
		//definicao do retorno do m�todo do stub
		//continuo precisando disso para que o Controlador de Funcion�rio receba a resposta correta de Controlador de Departamento
		Mockito.when(departamentoControlador.leDepartamentoPorID(1)).thenReturn(departamento);
		
		//exercitar a classe a ser testada
		FuncionarioControlador funcionarioControlador = new FuncionarioControlador(departamentoControlador);
		funcionarioControlador.adicionaFuncionario("paulyne", "senha", 1);
		
		//verificacao sobre a classe mockada
		Mockito.verify(departamentoControlador, Mockito.times(1)).leDepartamentoPorID(1);
	}
}
