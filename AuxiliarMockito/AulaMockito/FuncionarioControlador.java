package AulaMockito;

public class FuncionarioControlador {
	DepartamentoControlador departamentoControlador;
	
	private FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
	
	public FuncionarioControlador(DepartamentoControlador departamentoControlador){
		this.departamentoControlador = departamentoControlador;
	}
	
	public FuncionarioControlador() {
		// TODO Auto-generated constructor stub
	}
	
	public int adicionaFuncionario(String nome, String senha, int departamento){
		Departamento departamentoCompleto = departamentoControlador.leDepartamentoPorID(departamento);
		Funcionario funcionario = new Funcionario(nome, senha, departamentoCompleto);
		return funcionarioRepositorio.adicionaFuncionario(funcionario);
	}
	public Funcionario encontreFuncionario(int indice) {
		return funcionarioRepositorio.encontreFuncionario(indice);
	}

}
	

