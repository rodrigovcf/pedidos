package model.pedido;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.PedidoDAO;

public class PedidoTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pedido> Pedidos;
	private List<String> titulosColunas;	

	public PedidoTableModel (PedidoDAO dao) {
		this.Pedidos = dao.findAll();
		this.titulosColunas = Arrays.asList("Id", "Nome do Bebê", "Observações");
	}

	@Override
	public int getRowCount() {
		return Pedidos.size();
	}

	@Override
	public int getColumnCount() {
		return titulosColunas.size();
	}

	@Override
	public Object getValueAt(int linhaIndice, int colunaIndice) {
		Pedido pedido = Pedidos.get(linhaIndice);
		
		switch (colunaIndice) {
		
		case 0:
			return pedido.getId();
		case 1: 
			return pedido.getNomeBebe();

		case 2: 
			return pedido.getObservacoes();
		}

		return null;
	}

	public String getColumnName(int i) {
		return titulosColunas.get(i);
	}

}
