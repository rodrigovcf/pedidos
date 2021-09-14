package model.pedido;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import model.cliente.Cliente;
import model.servico.Servico;

@Entity(name="TAB_PEDIDO")
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Pedido() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nomeBebe;
	private Instant dataPedido;
	private Instant dataEntrega;	
	private String observacoes;
	private PedidoStatus status;
	private OrigemPedido origem;
	private Double total;
	
	@ManyToMany
	@JoinTable(name = "TAB_PEDIDO_CLIENTE",
			joinColumns = @JoinColumn(name = "pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "cliente_id"))
	private Set<Cliente> clientes = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "TAB_PEDIDO_CLIENTE",
			joinColumns = @JoinColumn(name = "pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "servico_id"))
	private Set<Servico> servicos = new HashSet<>();

	public Pedido(String nomeBebe, Instant dataPedido, Instant dataEntrega, String observacoes, PedidoStatus status,
			OrigemPedido origem) {
		super();
		this.nomeBebe = nomeBebe;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.observacoes = observacoes;
		this.status = status;
		this.origem = origem;
		
	}
		
	public String getNomeBebe() {
		return nomeBebe;
	}

	public void setNomeBebe(String nomeBebe) {
		this.nomeBebe = nomeBebe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Instant dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

	public OrigemPedido getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemPedido origem) {
		this.origem = origem;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}
	
	public Set<Servico> getServicos() {
		return servicos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}		
	
	
}
