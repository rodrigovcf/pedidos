package model.cliente;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="TAB_CLIENTE")
public class Cliente implements Serializable{

	public Cliente() {}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Calendar dataNascimento;
	private String email;
	private String telefone1;
	private String telefone2;		
	private String rua;	
    private String numero;
    private String bairro;
    private String cep;
    private String estado;
    private String cidade;    
	
	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
		

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente(Long id, String nome, Calendar dataNascimento, String telefone1, String telefone2, String email,
			String rua, String numero, String bairro, String cep, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.email = email;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone1="
				+ telefone1 + ", telefone2=" + telefone2 + ", email=" + email + ", rua=" + rua + ", numero=" + numero
				+ ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + "]";
	}	
	
	
}
