package pedidos.aplicacao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.cliente.Cliente;
import model.pedido.OrigemPedido;
import model.pedido.Pedido;
import model.pedido.PedidoStatus;
import views.CadastraCliente;
import views.CadastraPedido;
import views.CadastraServico;

public class Main {

	public static void main(String[] args) {
		
		
		//CadastraCliente cadastro = new CadastraCliente();
		
		//CadastraServico servico = new CadastraServico();
		
		CadastraPedido pedido = new CadastraPedido();
		
		//cadastro.setVisible(true);
		
		/*
		Cliente c1 = new Cliente("Maria", "84","842","Rua","maria@maria.com.br","Bebe1");
		Cliente c2 = new Cliente("Julia", "84","842","Rua","julia@julia.com.br", "Bebe2");
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(c1);
		clientes.add(c2);
		
		Instant agora = Instant.now();
		PedidoStatus status = PedidoStatus.PENDENTE;	
		OrigemPedido origem = OrigemPedido.WHATS;
		
		Pedido p1 = new Pedido(agora, agora, "Observações", status, origem);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
					
		System.out.println("Cadastrado com sucesso!");
		
		em.close();
		emf.close();
		*/
	}

}
