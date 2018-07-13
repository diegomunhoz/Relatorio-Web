package modelo;

public class Cliente {
	
	private String nome;
	private String endereco;
	private String email;
	private String cidade;
	
	
	
	public Cliente() {
		super();
	}
	public Cliente(String nome, String endereco, String email, String cidade) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.cidade = cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
