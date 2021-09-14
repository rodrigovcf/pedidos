package model.cliente;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.ClienteDAO;

public class ClienteTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> clientes;
	private List<String> titulosColunas;	

	public ClienteTableModel (ClienteDAO dao) {
		this.clientes = dao.findAll();
		this.titulosColunas = Arrays.asList("Id", "Nome", "Email", "Nascimento", "Fone1", "Cidade");
	}


	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return titulosColunas.size();
	}

	@Override
	public Object getValueAt(int linhaIndice, int colunaIndice) {
		Cliente cliente = clientes.get(linhaIndice);

		switch (colunaIndice) {

		case 0:
			return cliente.getId();
		case 1: 
			return cliente.getNome();

		case 2: 
			return cliente.getEmail();

		case 3: 
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = df.format(cliente.getDataNascimento().getTime());
			
			return dataString;
		
		case 4: 
			return cliente.getTelefone1();
		
		case 5: 
			return cliente.getCidade();
		}

		return null;
	}

	public String getColumnName(int i) {
		return titulosColunas.get(i);
	}

}
