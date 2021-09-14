package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.PedidoDAO;
import model.cliente.DateModel;
import model.pedido.Pedido;
import model.pedido.PedidoTableModel;

public class CadastraPedido extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static PedidoDAO dao = PedidoDAO.getInstance();
	private static PedidoTableModel tm = new PedidoTableModel(dao); 

	//Painéis
	private JPanel painelPrincipal = new JPanel();
	//Painel para campos
	private JPanel painelDados = new JPanel();
	private JPanel painelEndereco = new JPanel();
	//Painel para tabela
	private static JPanel painelPedidos = new JPanel();
	//Painel para ações
	private JPanel painelAcoes = new JPanel();

	//Rótulos
	private JLabel jlCliente = new JLabel("Cliente");
	private JLabel jlEmail = new JLabel("E-mail (opcional)");


	private JLabel jlRuaAvLog = new JLabel("Rua / Avenida / Logradouro");
	private JLabel jlNumero = new JLabel("Nº");
	private JLabel jlBairro = new JLabel("Bairro");
	private JLabel jlCEP = new JLabel("CEP");
	private JLabel jlUF = new JLabel("UF");
	private JLabel jlCidade = new JLabel("Cidade");
	private JLabel jlTelefone1 = new JLabel("Telefone 1 ");
	private JLabel jlTelefone2 = new JLabel("Telefone 2 (opcional)");
	private JLabel jlDataNasc = new JLabel("Data de Nascimento ");


	//private DateEdit dateEdit = new DateEdit(this);

	private UtilDateModel model = new UtilDateModel();
	private Properties propriedadesData = new Properties();

	private JDatePanelImpl datePanel; 
	private JDatePickerImpl datePicker;	

	//Campos
	private JTextField txtNomeBebe = new JTextField(30);
	private JTextArea txtObs = new JTextArea(4, 30);
	private JTextField txtRuaAvLog = new JTextField(30);
	private JTextField txtNumero = new JTextField(9);
	private JTextField txtBairro = new JTextField(30);
	private JTextField txtCEP = new JTextField(9);
	private JComboBox<String> cbbEstadoCid = new JComboBox<String>();
	private JTextField txtCidade = new JTextField(23);
	private JTextField txtTelefone1 = new JTextField(15);
	private JTextField txtTelefone2 = new JTextField(15);
	private MaskFormatter mascaraFones;
	private MaskFormatter mascaraCEP;

	//Botões
	private JButton jbInserir = new JButton();
	private JButton jbEditar = new JButton();
	private JButton jbAtualizar = new JButton();
	private JButton jbLimparCampos = new JButton();
	private JButton jbApagar = new JButton();
	private JButton jbCancelar = new JButton();

	private ImageIcon iconValidacao = new ImageIcon(getClass().getResource("/icons/checked.png"));
	private ImageIcon iconAlerta = new ImageIcon(getClass().getResource("/icons/alert.png"));
	private ImageIcon iconAlerta2 = new ImageIcon(getClass().getResource("/icons/alert2.png"));

	//Tabela Pedidos
	private static JScrollPane scroll = new JScrollPane();
	private static JTable tabela = new JTable(tm);

	public static void atualizaTabela() {		
		tm = new PedidoTableModel(dao);
		tabela = new JTable(tm);
		scroll.setViewportView(tabela);
		painelPedidos.add(scroll);
	}

	public CadastraPedido() {

		atualizaTabela();
		jbInserir.addActionListener(this);
		jbEditar.addActionListener(this);
		jbAtualizar.addActionListener(this);
		jbLimparCampos.addActionListener(this);
		jbApagar.addActionListener(this);
		jbCancelar.addActionListener(this);
		configData();
		configPaineis();
		configJFrame();
		setVisible(true);
	}

	public void configData() {	
		propriedadesData.put("text.today", "Hoje");
		propriedadesData.put("text.month", "Mês");
		propriedadesData.put("text.year", "Ano");			

	}

	public void configIconsBotoes() {
		jbInserir.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		jbApagar.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
		jbEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
		jbAtualizar.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
		jbLimparCampos.setIcon(new ImageIcon(getClass().getResource("/icons/clean-fields.png")));
		jbCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/cancel.png")));
	}

	public TitledBorder configTituloBordas(String texto) {
		TitledBorder titulo = BorderFactory.createTitledBorder(texto);
		titulo.setTitleFont(new Font("Arial", Font.BOLD, 14));
		titulo.setTitleColor(Color.black);
		return titulo;
	}



	public void configPaineis() {

		SpringLayout layout = new SpringLayout();

		painelDados.setLayout(layout);

		painelDados.add(jlCliente);
		layout.putConstraint(SpringLayout.WEST, jlCliente, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlCliente, 20, SpringLayout.NORTH, painelPrincipal);

		painelDados.add(txtNomeBebe);
		layout.putConstraint(SpringLayout.WEST, txtNomeBebe, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, txtNomeBebe, 45, SpringLayout.NORTH, jlCliente);		

		painelDados.add(jlDataNasc);
		layout.putConstraint(SpringLayout.WEST, jlDataNasc, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlDataNasc, 20, SpringLayout.NORTH, painelPrincipal);

		datePanel = new JDatePanelImpl(model,propriedadesData);

		datePicker = new JDatePickerImpl(datePanel, new DateModel());
		datePicker.setPreferredSize(new Dimension(135,20));
		datePicker.getComponent(0).setPreferredSize(new Dimension(100,20));
		datePicker.getComponent(1).setPreferredSize(new Dimension(20,20));	    

		painelDados.add(datePicker);
		layout.putConstraint(SpringLayout.WEST, datePicker, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, datePicker, 42, SpringLayout.NORTH, jlDataNasc);

		painelDados.add(jlEmail);
		layout.putConstraint(SpringLayout.WEST, jlEmail, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlEmail, 40, SpringLayout.NORTH, txtNomeBebe);

		painelDados.add(txtObs);
		layout.putConstraint(SpringLayout.WEST, txtObs, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtObs, 20, SpringLayout.NORTH, jlEmail);

		try {
			mascaraFones = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Padrão de telefone inválido!");
			e.printStackTrace();
		}

		txtTelefone1 = new JFormattedTextField(mascaraFones);

		txtTelefone1.setPreferredSize(new Dimension(116,20));

		painelDados.add(jlTelefone1);			
		layout.putConstraint(SpringLayout.WEST, jlTelefone1, 276, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlTelefone1, 40, SpringLayout.NORTH, txtNomeBebe);


		painelDados.add(txtTelefone1);
		layout.putConstraint(SpringLayout.WEST, txtTelefone1, 276, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtTelefone1, 23, SpringLayout.NORTH, jlEmail);				

		txtTelefone2 = new JFormattedTextField(mascaraFones);

		txtTelefone2.setPreferredSize(new Dimension(116,20));

		painelDados.add(jlTelefone2);
		layout.putConstraint(SpringLayout.WEST, jlTelefone2, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlTelefone2, -23, SpringLayout.NORTH, txtTelefone1);

		painelDados.add(txtTelefone2);
		layout.putConstraint(SpringLayout.WEST, txtTelefone2, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtTelefone2, 23, SpringLayout.NORTH, jlTelefone2);		

		painelEndereco.setLayout(layout);
		painelEndereco.setBorder(BorderFactory.createTitledBorder("Endereço"));

		painelEndereco.add(jlRuaAvLog);
		layout.putConstraint(SpringLayout.WEST, jlRuaAvLog, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlRuaAvLog, -100, SpringLayout.SOUTH, txtObs);

		painelEndereco.add(txtRuaAvLog);
		layout.putConstraint(SpringLayout.WEST, txtRuaAvLog, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtRuaAvLog, 20, SpringLayout.NORTH, jlRuaAvLog);

		painelEndereco.add(jlNumero);
		layout.putConstraint(SpringLayout.WEST, jlNumero, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlNumero, -100, SpringLayout.SOUTH, txtObs);

		painelEndereco.add(txtNumero);
		layout.putConstraint(SpringLayout.WEST, txtNumero, 396, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtNumero, 20, SpringLayout.NORTH, jlRuaAvLog);


		painelEndereco.add(jlBairro);
		layout.putConstraint(SpringLayout.WEST, jlBairro, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlBairro, 20, SpringLayout.SOUTH, txtRuaAvLog);

		painelEndereco.add(txtBairro);
		layout.putConstraint(SpringLayout.WEST, txtBairro, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtBairro, 20, SpringLayout.NORTH, jlBairro);

		painelEndereco.add(jlCEP);
		layout.putConstraint(SpringLayout.WEST, jlCEP, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlCEP, 20, SpringLayout.SOUTH, txtNumero);			

		try {

			mascaraCEP = new MaskFormatter("##.###-###");
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Padrão de CEP inválido!");
			e.printStackTrace();
		}

		txtCEP = new JFormattedTextField(mascaraCEP);

		txtCEP.setPreferredSize(new Dimension(116,20));
		painelEndereco.add(txtCEP);
		layout.putConstraint(SpringLayout.WEST, txtCEP, 396, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtCEP, 23, SpringLayout.NORTH, jlCEP);

		painelEndereco.add(jlUF);
		layout.putConstraint(SpringLayout.WEST, jlUF, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlUF, 20, SpringLayout.SOUTH, txtBairro);

		String[] estados = { "", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR",  "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO"};

		cbbEstadoCid.setFont(new Font("Dialog", 1, 12));
		cbbEstadoCid.setModel(new DefaultComboBoxModel<String>(estados));

		painelEndereco.add(cbbEstadoCid);
		layout.putConstraint(SpringLayout.WEST, cbbEstadoCid, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, cbbEstadoCid, 20, SpringLayout.NORTH, jlUF);

		painelEndereco.add(jlCidade);
		layout.putConstraint(SpringLayout.WEST, jlCidade, 100, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlCidade, 20, SpringLayout.SOUTH, txtBairro);

		painelEndereco.add(txtCidade);
		layout.putConstraint(SpringLayout.WEST, txtCidade, 100, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, txtCidade, 30, SpringLayout.SOUTH, jlCidade);

		painelPrincipal.setBorder(configTituloBordas("Dados do Pedido"));		
		painelPrincipal.setLayout(new GridLayout(3,1));
		painelPrincipal.add(painelDados);
		painelPrincipal.add(painelEndereco);

		configIconsBotoes();

		painelAcoes.setLayout(new GridLayout(6,1));
		painelAcoes.setBorder(configTituloBordas("Ações"));

		jbInserir.setToolTipText("Incluir um novo Pedido");
		jbEditar.setToolTipText("Carregar um Pedido, selecionado, no Formulário");
		jbAtualizar.setToolTipText("Atualizar um Registro de Pedido");
		jbLimparCampos.setToolTipText("Limpar todos os Campos");
		jbApagar.setToolTipText("Apagar o Pedido Selecionado");
		jbCancelar.setToolTipText("Cancelar Transação");

		painelAcoes.add(jbInserir);
		painelAcoes.add(jbEditar);
		painelAcoes.add(jbAtualizar);
		painelAcoes.add(jbLimparCampos);
		painelAcoes.add(jbApagar);
		painelAcoes.add(jbCancelar);

		painelPedidos.setLayout(new BorderLayout());
		painelPedidos.setBorder(configTituloBordas("Lista de Pedidos"));

		painelPedidos.add(scroll);

		painelPrincipal.add(painelPedidos);

	}

	public void configJFrame() {

		setTitle("Cadastro de Pedidos");
		setResizable(false);
		add(painelPrincipal);
		getContentPane().add(painelAcoes, BorderLayout.EAST);
		setSize(660,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	
	public boolean validaCampos(Pedido pedido) {
		
		if(pedido.getNomeBebe().equals("") ||
				pedido.getDataPedido().equals(null) ||
				pedido.getDataEntrega().equals(null)
		   ){ 						
			return true;
		}else {
			return false;
		}
	}

	public Pedido carregaPedido() {
		Pedido pedido = new Pedido();
		pedido.setNomeBebe(txtNomeBebe.getText());
		
		Instant dataPedido = Instant.now();
		pedido.setDataPedido(dataPedido);
		
		Instant dataEntrega = Instant.now();
		pedido.setDataPedido(dataEntrega);
			
		//private PedidoStatus status;
		//private OrigemPedido origem;

		pedido.setObservacoes(txtObs.getText());
		
		String total = txtCidade.getText().replace("R$ ", "");
		pedido.setTotal(Double.parseDouble(total.replace(",", ".").toString()));


		return pedido;		
	}

	public void adicionaPedido(Pedido Pedido) {	

		if (validaCampos(Pedido) == false) {
			dao.merge(Pedido);
			limpaCampos();
			atualizaTabela();
			
			JOptionPane.showMessageDialog(null, 
					"Pedido cadastrado com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);
			
		} else if (validaCampos(Pedido)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Preencha ao menos os campos obrigatórios \n" +
							"para poder incluir um Novo Pedido!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}

	}

	public void removePedido(Pedido Pedido) {		
		dao.removeById(Pedido.getId());
		atualizaTabela();
	}

	public void editarPedido() {		

		txtNomeBebe.setText(recuperaPedido().getNomeBebe());
		txtObs.setText(recuperaPedido().getObservacoes());			

		
	}

	public void atualizarPedido(Pedido pedido) {

		pedido.setNomeBebe(txtNomeBebe.getText());
		pedido.setObservacoes(txtObs.getText());

		try {

			Calendar dataNascimento = Calendar.getInstance();					

			Date date = model.getValue();

			dataNascimento.setTimeInMillis(date.getTime()); 

		//	pedido.setDataPedido(dataNascimento);	


		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, 
					"Data de nascimento inválida ou não preenchida!", 
					"Atenção", 
					JOptionPane.WARNING_MESSAGE,
					iconAlerta2);
		}

		String total = txtCidade.getText().replace("R$ ", "");
		pedido.setTotal(Double.parseDouble(total.replace(",", ".").toString()));

		if (validaCampos(pedido) == false) {
			dao.merge(pedido);
			limpaCampos();
			atualizaTabela();
			
			JOptionPane.showMessageDialog(null, 
					"Pedido com dados atualizados com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);

		} else if (validaCampos(pedido)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Não apague os dados obrigatórios \n" +
							"para poder alterar o Pedido selecionado!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}
			
		
	}

	public void limpaCampos() {
		txtNomeBebe.setText("");
		txtObs.setText("");
		txtRuaAvLog.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCEP.setText("");
		txtCidade.setText("");

		model.setValue(null);

		cbbEstadoCid.setSelectedIndex(0);
		txtTelefone1.setText("");
		txtTelefone2.setText("");
	}

	public Pedido recuperaPedido() {
		Pedido Pedido = new Pedido();

		int index = tabela.getSelectedRow();
		Pedido = (Pedido) dao.findAll().get(index);

		return Pedido;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbInserir) {
			try {
				adicionaPedido(carregaPedido());
				
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, 
						"A Data de Nascimento é um campo obrigatório!", 
						"Aviso", 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta2);
			}			

		}else if (e.getSource() == jbApagar) {
			try {
				Object[] options = { "SIM", "CANCELAR" };
				int opção = JOptionPane.showOptionDialog(null, 
						"Você tem certeza que quer excluir esse registro?", 
						"Aviso",
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta, 
						options, 
						options[1]);

				if(opção == JOptionPane.YES_NO_OPTION) {
					removePedido(recuperaPedido());	
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione o registro, que você deseja apagar, na Lista de Pedidos.", 
						"Atenção", 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta);
			}			
		}else if (e.getSource() == jbAtualizar) {
			try {
				atualizarPedido(recuperaPedido());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione um registro na Lista de Pedidos. Em seguida,"
								+ " clique no botão que carrega o registro no formulário!", 
								"Atenção", 
								JOptionPane.WARNING_MESSAGE,
								iconAlerta);
			}			
		}else if (e.getSource() == jbEditar) {
			try {
				editarPedido();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione um registro na Lista de Pedidos!", 
						"Atenção", 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta);
			}
		}

		else if (e.getSource() == jbLimparCampos)
			limpaCampos();

		else if (e.getSource() == jbCancelar)
			dispose();

	}
	
	
}
