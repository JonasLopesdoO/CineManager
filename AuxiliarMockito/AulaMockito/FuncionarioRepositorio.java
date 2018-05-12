package AulaMockito;

import java.util.ArrayList;

public class FuncionarioRepositorio {
	private ArrayList<Funcionario> funcionarios;
	
	public FuncionarioRepositorio(){
		this.funcionarios = new ArrayList<Funcionario>();
	}

	public int adicionaFuncionario(Funcionario funcionario){
		this.funcionarios.add(funcionario); 
		return this.funcionarios.indexOf(funcionario);
	}
	
	public Funcionario encontreFuncionario(int indice){
		return this.funcionarios.get(indice);
	}
}
