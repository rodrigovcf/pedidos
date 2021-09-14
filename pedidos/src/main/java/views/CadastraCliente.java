package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.ClienteDAO;
import model.cliente.Cliente;
import model.cliente.ClienteTableModel;
import model.cliente.DateModel;

public class CadastraCliente extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ClienteDAO dao = ClienteDAO.getInstance();
	private static ClienteTableModel tm = new ClienteTableModel(dao); 

	//Painéis
	private JPanel painelPrincipal = new JPanel();
	//Painel para campos
	private JPanel painelDados = new JPanel();
	private JPanel painelEndereco = new JPanel();
	//Painel para tabela
	private static JPanel painelClientes = new JPanel();
	//Painel para ações
	private JPanel painelAcoes = new JPanel();

	//Rótulos
	private JLabel jlNome = new JLabel("Nome ");
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
	private JTextField txtNome = new JTextField(30);
	private JTextField txtEmail = new JTextField(20);
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

	//Tabela Clientes
	private static JScrollPane scroll = new JScrollPane();
	private static JTable tabela = new JTable(tm);

	public static void atualizaTabela() {		
		tm = new ClienteTableModel(dao);
		tabela = new JTable(tm);
		scroll.setViewportView(tabela);
		painelClientes.add(scroll);
	}

	public CadastraCliente() {

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

		painelDados.add(jlNome);
		layout.putConstraint(SpringLayout.WEST, jlNome, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlNome, 20, SpringLayout.NORTH, painelPrincipal);

		painelDados.add(txtNome);
		layout.putConstraint(SpringLayout.WEST, txtNome, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, txtNome, 45, SpringLayout.NORTH, jlNome);		

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
		layout.putConstraint(SpringLayout.NORTH, jlEmail, 40, SpringLayout.NORTH, txtNome);

		painelDados.add(txtEmail);
		layout.putConstraint(SpringLayout.WEST, txtEmail, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtEmail, 20, SpringLayout.NORTH, jlEmail);

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
		layout.putConstraint(SpringLayout.NORTH, jlTelefone1, 40, SpringLayout.NORTH, txtNome);


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
		layout.putConstraint(SpringLayout.SOUTH, jlRuaAvLog, -100, SpringLayout.SOUTH, txtEmail);

		painelEndereco.add(txtRuaAvLog);
		layout.putConstraint(SpringLayout.WEST, txtRuaAvLog, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtRuaAvLog, 20, SpringLayout.NORTH, jlRuaAvLog);

		painelEndereco.add(jlNumero);
		layout.putConstraint(SpringLayout.WEST, jlNumero, 400, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlNumero, -100, SpringLayout.SOUTH, txtEmail);

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

		painelPrincipal.setBorder(configTituloBordas("Dados do Cliente"));		
		painelPrincipal.setLayout(new GridLayout(3,1));
		painelPrincipal.add(painelDados);
		painelPrincipal.add(painelEndereco);

		configIconsBotoes();

		painelAcoes.setLayout(new GridLayout(6,1));
		painelAcoes.setBorder(configTituloBordas("Ações"));

		jbInserir.setToolTipText("Incluir um novo Cliente");
		jbEditar.setToolTipText("Carregar um Cliente, selecionado, no Formulário");
		jbAtualizar.setToolTipText("Atualizar um Registro de Cliente");
		jbLimparCampos.setToolTipText("Limpar todos os Campos");
		jbApagar.setToolTipText("Apagar o Cliente Selecionado");
		jbCancelar.setToolTipText("Cancelar Transação");

		painelAcoes.add(jbInserir);
		painelAcoes.add(jbEditar);
		painelAcoes.add(jbAtualizar);
		painelAcoes.add(jbLimparCampos);
		painelAcoes.add(jbApagar);
		painelAcoes.add(jbCancelar);

		painelClientes.setLayout(new BorderLayout());
		painelClientes.setBorder(configTituloBordas("Lista de Clientes"));

		painelClientes.add(scroll);

		painelPrincipal.add(painelClientes);

	}

	public void configJFrame() {

		setTitle("Cadastro de Clientes");
		setResizable(false);
		add(painelPrincipal);
		getContentPane().add(painelAcoes, BorderLayout.EAST);
		setSize(660,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public boolean validaCampos(Cliente cliente) {
		
		if(cliente.getNome().equals("") ||
				cliente.getDataNascimento().equals(null) ||
				cliente.getRua().equals("") ||
				cliente.getNumero().equals("") ||
				cliente.getBairro().equals("") ||
				cliente.getEstado().equals("") ||
				cliente.getCep().equals("") ||
				cliente.getCidade().equals("") ||
				cliente.getTelefone1().equals("")){			
			return true;
		}else {
			return false;
		}
	}

	public Cliente carregaCliente() {
		Cliente cliente = new Cliente();

		cliente.setNome(txtNome.getText());
		cliente.setEmail(txtEmail.getText());

		cliente.setRua(txtRuaAvLog.getText());
		cliente.setNumero(txtNumero.getText());
		cliente.setBairro(txtBairro.getText());
		cliente.setCep(txtCEP.getText());
		cliente.setEstado(cbbEstadoCid.getModel().getSelectedItem().toString());
		cliente.setCidade(txtCidade.getText());

		Calendar dataNascimento = Calendar.getInstance();					

		Date date = model.getValue();

		dataNascimento.setTimeInMillis(date.getTime()); 

		cliente.setDataNascimento(dataNascimento);						

		cliente.setTelefone1(txtTelefone1.getText());
		cliente.setTelefone2(txtTelefone1.getText());

		return cliente;		
	}

	public void adicionaCliente(Cliente cliente) {	

		if (validaCampos(cliente) == false) {
			dao.merge(cliente);
			limpaCampos();
			atualizaTabela();
			
			JOptionPane.showMessageDialog(null, 
					"Cliente cadastrado com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);
			
		} else if (validaCampos(cliente)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Preencha ao menos os campos obrigatórios \n" +
							"para poder incluir um Novo Cliente!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}

	}

	public void removeCliente(Cliente cliente) {		
		dao.removeById(cliente.getId());
		atualizaTabela();
	}

	public void editarCliente() {		

		txtNome.setText(recuperaCliente().getNome());
		txtEmail.setText(recuperaCliente().getEmail());			

		Date date = recuperaCliente().getDataNascimento().getTime();			
		model.setValue(date);

		txtTelefone1.setText(recuperaCliente().getTelefone1());
		txtTelefone2.setText(recuperaCliente().getTelefone1());

		txtRuaAvLog.setText(recuperaCliente().getRua());
		txtNumero.setText(recuperaCliente().getNumero());
		txtBairro.setText(recuperaCliente().getBairro());
		txtCEP.setText(recuperaCliente().getCep());

		cbbEstadoCid.setSelectedItem(recuperaCliente().getEstado());

		txtCidade.setText(recuperaCliente().getCidade());

	}

	public void atualizarCliente(Cliente cliente) {

		cliente.setNome(txtNome.getText());
		cliente.setEmail(txtEmail.getText());

		try {

			Calendar dataNascimento = Calendar.getInstance();					

			Date date = model.getValue();

			dataNascimento.setTimeInMillis(date.getTime()); 

			cliente.setDataNascimento(dataNascimento);	


		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, 
					"Data de nascimento inválida ou não preenchida!", 
					"Atenção", 
					JOptionPane.WARNING_MESSAGE,
					iconAlerta2);
		}

		cliente.setRua(txtRuaAvLog.getText());
		cliente.setNumero(txtNumero.getText());
		cliente.setBairro(txtBairro.getText());
		cliente.setCep(txtCEP.getText());
		cliente.setEstado(cbbEstadoCid.getModel().getSelectedItem().toString());
		cliente.setCidade(txtCidade.getText());	

		cliente.setTelefone1(txtTelefone1.getText());
		cliente.setTelefone2(txtTelefone1.getText());

		if (validaCampos(cliente) == false) {
			dao.merge(cliente);
			limpaCampos();
			atualizaTabela();
			
			JOptionPane.showMessageDialog(null, 
					"Cliente com dados atualizados com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);

		} else if (validaCampos(cliente)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Não apague os dados obrigatórios \n" +
							"para poder alterar o Cliente selecionado!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}
			
		
	}

	public void limpaCampos() {
		txtNome.setText("");
		txtEmail.setText("");
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

	public Cliente recuperaCliente() {
		Cliente cliente = new Cliente();

		int index = tabela.getSelectedRow();
		cliente = (Cliente) dao.findAll().get(index);

		return cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbInserir) {
			try {
				adicionaCliente(carregaCliente());
				
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
					removeCliente(recuperaCliente());	
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione o registro, que você deseja apagar, na Lista de Clientes.", 
						"Atenção", 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta);
			}			
		}else if (e.getSource() == jbAtualizar) {
			try {
				atualizarCliente(recuperaCliente());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione um registro na Lista de Clientes. Em seguida,"
								+ " clique no botão que carrega o registro no formulário!", 
								"Atenção", 
								JOptionPane.WARNING_MESSAGE,
								iconAlerta);
			}			
		}else if (e.getSource() == jbEditar) {
			try {
				editarCliente();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione um registro na Lista de Clientes!", 
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
