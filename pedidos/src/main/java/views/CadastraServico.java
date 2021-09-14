package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dao.ServicoDAO;
import model.servico.Servico;
import model.servico.ServicoTableModel;


public class CadastraServico extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServicoDAO dao = ServicoDAO.getInstance();
	private static ServicoTableModel tm = new ServicoTableModel(dao); 

	//Painéis
	private JPanel painelPrincipal = new JPanel();
	//Painel para campos
	private JPanel painelDados = new JPanel();
	private JPanel painelEndereco = new JPanel();
	//Painel para tabela
	private static JPanel painelServiços = new JPanel();
	//Painel para ações
	private JPanel painelAcoes = new JPanel();

	//Rótulos
	private JLabel jlNome = new JLabel("Nome ");
	private JLabel jlDesc = new JLabel("Descrição do Serviço");
	private JLabel jlPreco = new JLabel("Preço");

	//Campos
	private JTextField txtNome = new JTextField(30);
	private JTextArea txtDesc = new JTextArea(4, 30);
	private JTextField txtPreco = new JTextField(30);

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

	private static final NumberFormat brazilianFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


	//Tabela Serviços
	private static JScrollPane scroll = new JScrollPane();
	private static JScrollPane scrollServ = new JScrollPane();
	private static JTable tabela = new JTable(tm);

	public static void atualizaTabela() {		
		tm = new ServicoTableModel(dao);
		tabela = new JTable(tm);
		scroll.setViewportView(tabela);
		painelServiços.add(scroll);
	}

	public CadastraServico() {
		atualizaTabela();
		focusDescricao();
		focusPreco();
		jbInserir.addActionListener(this);
		jbEditar.addActionListener(this);
		jbAtualizar.addActionListener(this);
		jbLimparCampos.addActionListener(this);
		jbApagar.addActionListener(this);
		jbCancelar.addActionListener(this);
		configPaineis();
		configJFrame();
		setVisible(true);
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

		painelDados.add(jlDesc);
		layout.putConstraint(SpringLayout.WEST, jlDesc, 20, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, jlDesc, 40, SpringLayout.NORTH, txtNome);					

		txtDesc.setWrapStyleWord(true);
		txtDesc.setLineWrap(true);
		txtDesc.setSelectionStart(DISPOSE_ON_CLOSE);
		scrollServ.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrollServ.setViewportView(txtDesc);
		//txtDesc.setBorder(new LineBorder(Color.BLUE));
		painelDados.add(scrollServ);
		layout.putConstraint(SpringLayout.WEST, scrollServ, 25, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, scrollServ, 20, SpringLayout.NORTH, jlDesc);

		painelEndereco.setLayout(layout);
		painelEndereco.setBorder(BorderFactory.createTitledBorder("Valor do Serviço"));

		painelEndereco.add(jlPreco);
		layout.putConstraint(SpringLayout.WEST, jlPreco, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.SOUTH, jlPreco, -100, SpringLayout.SOUTH, scrollServ);
		//		try {
		//			//mascaraPreco = new MaskFormatter("R$ #.###,##");
		//			mascaraPreco = new MaskFormatter("R$ #,##");
		//		} catch (ParseException e) {
		//			JOptionPane.showMessageDialog(null, "Padrão de valor inválido!");
		//			e.printStackTrace();
		//		}
		//txtPreco = new JFormattedTextField(mascaraPreco);
		txtPreco.setPreferredSize(new Dimension(116,20));
		txtPreco.setText("R$ 0,00");
		painelEndereco.add(txtPreco);
		layout.putConstraint(SpringLayout.WEST, txtPreco, 16, SpringLayout.WEST, painelPrincipal);
		layout.putConstraint(SpringLayout.NORTH, txtPreco, 20, SpringLayout.NORTH, jlPreco);

		painelPrincipal.setBorder(configTituloBordas("Dados do Serviço"));		
		painelPrincipal.setLayout(new GridLayout(3,1));
		painelPrincipal.add(painelDados);
		painelPrincipal.add(painelEndereco);

		configIconsBotoes();

		painelAcoes.setLayout(new GridLayout(6,1));
		painelAcoes.setBorder(configTituloBordas("Ações"));

		jbInserir.setToolTipText("Incluir um novo Serviço");
		jbEditar.setToolTipText("Carregar um Serviço, selecionado, no Formulário");
		jbAtualizar.setToolTipText("Atualizar um Registro de Serviço");
		jbLimparCampos.setToolTipText("Limpar todos os Campos");
		jbApagar.setToolTipText("Apagar o Serviço Selecionado");
		jbCancelar.setToolTipText("Cancelar Transação");

		painelAcoes.add(jbInserir);
		painelAcoes.add(jbEditar);
		painelAcoes.add(jbAtualizar);
		painelAcoes.add(jbLimparCampos);
		painelAcoes.add(jbApagar);
		painelAcoes.add(jbCancelar);

		painelServiços.setLayout(new BorderLayout());
		painelServiços.setBorder(configTituloBordas("Lista de Serviços"));

		painelServiços.add(scroll);

		painelPrincipal.add(painelServiços);

	}

	public void configJFrame() {

		setTitle("Cadastro de Serviços");
		setResizable(false);
		add(painelPrincipal);
		getContentPane().add(painelAcoes, BorderLayout.EAST);
		setSize(660,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public boolean validaCampos(Servico servico) {
		if(servico.getNome().equals("") ||
				servico.getDescricao().equals("") ||
				servico.getValor() == 0){			
			return true;
		}else {
			return false;
		}
	}

	public Servico carregaServicos() {
		Servico servico = new Servico();

		servico.setNome(txtNome.getText());
		servico.setDescricao(txtDesc.getText());
		servico.setValor(Double.parseDouble(txtPreco.getText().replace(",", ".").substring(3)));

		return servico;		
	}

	public void adicionaServico(Servico servico) {	

		if (validaCampos(servico) == false) {
			dao.merge(servico);
			limpaCampos();
			atualizaTabela();

			JOptionPane.showMessageDialog(null, 
					"Servico cadastrado com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);

		} else if (validaCampos(servico)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Preencha ao menos os campos obrigatórios \n" +
							"para poder incluir um Novo Servico!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}

	}

	public void removeServico(Servico servico) {		
		dao.removeById(servico.getId());
		atualizaTabela();
	}

	public void editarServico() {		

		txtNome.setText(recuperaServico().getNome());
		txtDesc.setText(recuperaServico().getDescricao());	
		String valor = brazilianFormat.format(recuperaServico().getValor());
		txtPreco.setText(valor);

	}

	public void atualizarServico(Servico servico) {

		servico.setNome(txtNome.getText());
		servico.setDescricao(txtDesc.getText());
		String valor = txtPreco.getText().replace("R$ ", "");
		servico.setValor(Double.parseDouble(valor.replace(",", ".").toString()));
		
		if (validaCampos(servico) == false) {
			dao.merge(servico);
			limpaCampos();
			atualizaTabela();

			JOptionPane.showMessageDialog(null, 
					"Servico com dados atualizados com Sucesso!", 
					"Aviso", 
					JOptionPane.INFORMATION_MESSAGE, 
					iconValidacao);

		} else if (validaCampos(servico)){
			JOptionPane.showMessageDialog(null, 
					"Alguns campos são obrigatórios! \n" +
							"Não apague os dados obrigatórios \n" +
							"para poder alterar o Servico selecionado!", 
							"Alerta", 
							JOptionPane.WARNING_MESSAGE,
							iconAlerta2);
		}


	}

	public void limpaCampos() {
		txtNome.setText("");
		txtDesc.setText("");
		txtPreco.setText("");
	}

	public Servico recuperaServico() {
		Servico servico = new Servico();

		int index = tabela.getSelectedRow();
		servico = (Servico) dao.findAll().get(index);

		return servico;
	}

	public void focusPreco() {		

		txtPreco.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPreco.getText().equals("R$ ")){
					txtPreco.setText("R$ 0,00");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				txtPreco.setText("R$ ");
			}
		});

	}

	public void focusDescricao() {		

		txtDesc.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				txtDesc.setBorder(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtDesc.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(173, 216, 230)));
			}
		});

		txtDesc.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						txtDesc.transferFocusBackward();
					} else {
						txtDesc.transferFocus();
					}
					e.consume();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {		

		if (e.getSource() == jbInserir) {
			try {
				adicionaServico(carregaServicos());

			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, 
						"Problemas com o valor inserido! Erro: " + e1, 
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
					removeServico(recuperaServico());	
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione o registro, que você deseja apagar, na Lista de Serviços.", 
						"Atenção", 
						JOptionPane.WARNING_MESSAGE,
						iconAlerta);
			}			
		}else if (e.getSource() == jbAtualizar) {
			try {
				atualizarServico(recuperaServico());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Ocorreu um problema ao tentar atualizar o registro. ERRO: "
								+ e2.toString(), 
								"Atenção", 
								JOptionPane.WARNING_MESSAGE,
								iconAlerta);
			}			
		}else if (e.getSource() == jbEditar) {
			try {
				editarServico();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, 
						"Selecione um registro na Lista de Serviços!", 
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
