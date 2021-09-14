package model.servico;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import dao.ServicoDAO;

public class ServicoTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Servico> Servicos;
	private List<String> titulosColunas;	
	//private MaskFormatter mascaraPreco;
	private static final NumberFormat brazilianFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	public ServicoTableModel (ServicoDAO dao) {
		this.Servicos = dao.findAll();
		this.titulosColunas = Arrays.asList("Id", "Nome", "Descrição", "Valor");
	}


	@Override
	public int getRowCount() {
		return Servicos.size();
	}

	@Override
	public int getColumnCount() {
		return titulosColunas.size();
	}

	@Override
	public Object getValueAt(int linhaIndice, int colunaIndice) {
		Servico Servico = Servicos.get(linhaIndice);
		
		String valor = brazilianFormat.format(Servico.getValor());
		
		switch (colunaIndice) {
		
		case 0:
			return Servico.getId();
		case 1: 
			return Servico.getNome();

		case 2: 
			return Servico.getDescricao();

		case 3: 
			
			return valor;
			
		}

		return null;
	}

	public String getColumnName(int i) {
		return titulosColunas.get(i);
	}

}
