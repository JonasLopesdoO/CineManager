package AulaMockito;

public class Funcionario {
	
	private String nome;
	private String senha;
	private Departamento departamento;
	
	public Funcionario (String nome, String senha, Departamento departamento){
		this.nome = nome;
		this.senha = senha;
		this.departamento = departamento;
	}
	
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
}
