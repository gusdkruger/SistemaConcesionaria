package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import daos.DAOs;
import daos.CarroDAO;
import filtros.LimitaLength;
import modelos.Carro;
import modelos.Cor;
import modelos.Fabricante;
import modelos.Tipo;

@SuppressWarnings("serial")
public class TelaLogado extends JFrame implements ActionListener {

	private int idConcessionaria;
	private ArrayList<Carro> carros;
	private ArrayList<Carro> carrosFiltrados;
	private int[] idCarros;
	private final String[] titulos = {"Modelo", "Cor", "Ano", "Fabricante", "Tipo", "Kilometragem", "Data Compra", "Preco Compra", "Vendido", "Data Venda", "Preco Venda"};
	private ArrayList<Fabricante> fabricantes;
	private ArrayList<Tipo> tipos;
	private ArrayList<Cor> cores;
	
	private JTextField textFieldPesquisa;
	private JButton buttonPesquisa;
	private JComboBox<String> comboBoxFabricante;
	private JComboBox<String> comboBoxTipo;
	private JComboBox<String> comboBoxVendido;
	private DefaultTableModel conteudoTabela;
	private JTable tabela;
	private JButton buttonAdicionar;
	private JButton buttonVender;
	private JButton buttonEditar;
	private JButton buttonExcluir;
	private JButton buttonResetaFiltros;

	private final Font fonteMedia = new Font("Arial", Font.PLAIN, 17);
	private final Font fonteGrande = new Font("Arial", Font.PLAIN, 20);
	
	public TelaLogado(int idConcessionaria) {
		super("Concesionaria");
		this.idConcessionaria = idConcessionaria;
		carros = CarroDAO.getCarros(idConcessionaria);
		fabricantes = DAOs.getFabricantes();
		tipos = DAOs.getTipos();
		cores = DAOs.getCores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1264, 786);
		setResizable(false);
		setLayout(null);
		
		textFieldPesquisa = new JTextField("Nome do carro");
		textFieldPesquisa.setBounds(5, 5, 290, 40);
		textFieldPesquisa.setFont(fonteMedia);
		textFieldPesquisa.setDocument(new LimitaLength(30));
		add(textFieldPesquisa);
		
		buttonPesquisa = new JButton("Pesquisar");
		buttonPesquisa.setBounds(300, 5, 110, 40);
		buttonPesquisa.setFont(fonteMedia);
		buttonPesquisa.addActionListener(this);
		add(buttonPesquisa);
		
		JLabel labelFabricante = new JLabel("Fabricante:");
		labelFabricante.setBounds(425, 5, 90, 40);
		labelFabricante.setFont(fonteMedia);
		add(labelFabricante);
		
		comboBoxFabricante = new JComboBox<>();
		comboBoxFabricante.setBounds(515, 5, 120, 40);
		comboBoxFabricante.setFont(fonteMedia);
		comboBoxFabricante.addItem("Todos");
		for(Fabricante f : fabricantes) {
			comboBoxFabricante.addItem(f.getNome());
		}
		comboBoxFabricante.addActionListener(this);
		add(comboBoxFabricante);
		
		JLabel labelTipo = new JLabel("Tipo:");
		labelTipo.setBounds(645, 5, 40, 40);
		labelTipo.setFont(fonteMedia);
		add(labelTipo);
		
		comboBoxTipo = new JComboBox<>();
		comboBoxTipo.setBounds(685, 5, 120, 40);
		comboBoxTipo.setFont(fonteMedia);
		comboBoxTipo.addItem("Todos");
		for(Tipo t : tipos) {
			comboBoxTipo.addItem(t.getNome());
		}
		comboBoxTipo.addActionListener(this);
		add(comboBoxTipo);
		
		JLabel labelVendido = new JLabel("Vendido:");
		labelVendido.setBounds(815, 5, 70, 40);
		labelVendido.setFont(fonteMedia);
		add(labelVendido);
		
		comboBoxVendido = new JComboBox<>();
		comboBoxVendido.setBounds(885, 5, 70, 40);
		comboBoxVendido.setFont(fonteMedia);
		comboBoxVendido.addItem("Todos");
		comboBoxVendido.addItem("True");
		comboBoxVendido.addItem("False");
		comboBoxVendido.setSelectedIndex(2);
		comboBoxVendido.addActionListener(this);
		add(comboBoxVendido);
		
		buttonResetaFiltros = new JButton("Resetar Filtros");
		buttonResetaFiltros.setFont(fonteMedia);
		buttonResetaFiltros.setBounds(1093, 5, 150, 40);
		buttonResetaFiltros.addActionListener(this);
		add(buttonResetaFiltros);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 50, 1238, 645);
		conteudoTabela = new DefaultTableModel(null, titulos);
		tabela = new JTable(conteudoTabela) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabela.setDefaultRenderer(Object.class, centerRenderer);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabela);
		add(scrollPane);
		
		buttonAdicionar = new JButton("Adicionar");
		buttonAdicionar.setFont(fonteGrande);
		buttonAdicionar.setBounds(5, 700, 185, 40);
		buttonAdicionar.addActionListener(this);
		add(buttonAdicionar);
		
		buttonVender = new JButton("Vender");
		buttonVender.setFont(fonteGrande);
		buttonVender.setBounds(195, 700, 185, 40);
		buttonVender.addActionListener(this);
		add(buttonVender);
		
		buttonEditar = new JButton("Editar");
		buttonEditar.setFont(fonteGrande);
		buttonEditar.setBounds(868, 700, 185, 40);
		buttonEditar.addActionListener(this);
		add(buttonEditar);
		
		buttonExcluir = new JButton("Excluir");
		buttonExcluir.setFont(fonteGrande);
		buttonExcluir.setBounds(1058, 700, 185, 40);
		buttonExcluir.addActionListener(this);
		add(buttonExcluir);
		
		preencheTabela();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonPesquisa || e.getSource() == comboBoxFabricante || e.getSource() == comboBoxTipo || e.getSource() == comboBoxVendido) {
			preencheTabela();
		}
		else if(e.getSource() == buttonResetaFiltros) {
			textFieldPesquisa.setText("");
			comboBoxFabricante.setSelectedIndex(0);
			comboBoxTipo.setSelectedIndex(0);
			comboBoxVendido.setSelectedIndex(2);
			preencheTabela();
		}
		else if(e.getSource() == buttonAdicionar) {
			new TelaAdicionar(idConcessionaria, fabricantes, tipos, cores, carros, this);
		}
		else if(e.getSource() == buttonVender) {
			int index = tabela.getSelectedRow();
			if(index != -1) {
				if(!carrosFiltrados.get(index).isVendido()) {
					new TelaVender(carrosFiltrados.get(index), this);
				}
				else {
					JOptionPane.showMessageDialog(null, "Carro selecionado ja foi vendido");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Selecione o carro");
			}
		}
		else if(e.getSource() == buttonEditar) {
			int index = tabela.getSelectedRow();
			if(index != -1) {
				new TelaEditar(fabricantes, tipos, cores, this, carrosFiltrados.get(index));
			}
			else {
				JOptionPane.showMessageDialog(null, "Selecione o carro que deseja editar");
			}
		}
		else if(e.getSource() == buttonExcluir) {
			int index = tabela.getSelectedRow();
			if(index != -1) {
				Carro c = carrosFiltrados.get(index);
				int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir:\n" + c.getModelo() + " " + c.getCor() + " de " + c.getAno() + " com kilometragem de " + c.getKilometragem() + "?");
				if(confirma == 0) {
					CarroDAO.exclui(idCarros[index]);
					for(int i = 0; i < carros.size(); i++) {
						if(carros.get(i).getId() == idCarros[index]) {
							carros.remove(i);
						}
					}
					preencheTabela();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Selecione o carro que deseja excluir");
			}
		}
	}
	
	public void preencheTabela() {
		String pesquisa = textFieldPesquisa.getText().toLowerCase();
		String fabrianteSelecionado = (String)comboBoxFabricante.getSelectedItem();
		String tipoSelecionado = (String)comboBoxTipo.getSelectedItem();
		String vendidoSelecionado = (String)comboBoxVendido.getSelectedItem();
		carrosFiltrados = new ArrayList<>();
		for(int i = 0; i < carros.size(); i++) {
			String fabricante = carros.get(i).getFabricante();
			String tipo = carros.get(i).getTipo();
			String vendido = carros.get(i).isVendido() + "";
			if(carros.get(i).getModelo().toLowerCase().contains(pesquisa)) {
				if(fabrianteSelecionado.equals("Todos") || fabricante.equals(fabrianteSelecionado)) {
					if(tipoSelecionado.equals("Todos") || tipo.equals(tipoSelecionado)) {
						if(vendidoSelecionado.equals("Todos") || vendido.equalsIgnoreCase(vendidoSelecionado)) {
							carrosFiltrados.add(carros.get(i));
						}
					}
				}
			}
		}
		int size = carrosFiltrados.size();
		String[][] novoConteudo = new String[size][11];
		idCarros = new int[size];
		for(int i = 0; i < size; i++) {
			idCarros[i] = carrosFiltrados.get(i).getId();
			novoConteudo[i][0] = carrosFiltrados.get(i).getModelo();
			novoConteudo[i][1] = carrosFiltrados.get(i).getCor();
			novoConteudo[i][2] = carrosFiltrados.get(i).getAno() + "";
			novoConteudo[i][3] = carrosFiltrados.get(i).getFabricante();
			novoConteudo[i][4] = carrosFiltrados.get(i).getTipo();
			novoConteudo[i][5] = carrosFiltrados.get(i).getKilometragem() + "";
			novoConteudo[i][6] = carrosFiltrados.get(i).getDataCompra();
			novoConteudo[i][7] = carrosFiltrados.get(i).getPrecoCompra() + "";
			novoConteudo[i][8] = carrosFiltrados.get(i).isVendido() + "";
			if(!carrosFiltrados.get(i).isVendido()) {
				novoConteudo[i][9] = "";
				novoConteudo[i][10] = "";
			}
			else {
				novoConteudo[i][9] = carrosFiltrados.get(i).getDataVenda();
				novoConteudo[i][10] = carrosFiltrados.get(i).getPrecoVenda() + "";
			}
		}
		conteudoTabela.setDataVector(novoConteudo, titulos);
		if(size > 38) {
			tabela.getColumnModel().getColumn(0).setMaxWidth(235);
			tabela.getColumnModel().getColumn(1).setMaxWidth(160);
			tabela.getColumnModel().getColumn(2).setMaxWidth(50);
			tabela.getColumnModel().getColumn(3).setMaxWidth(160);
			tabela.getColumnModel().getColumn(4).setMaxWidth(130);
			tabela.getColumnModel().getColumn(5).setMaxWidth(90);
			tabela.getColumnModel().getColumn(6).setMaxWidth(85);
			tabela.getColumnModel().getColumn(7).setMaxWidth(85);
			tabela.getColumnModel().getColumn(8).setMaxWidth(55);
			tabela.getColumnModel().getColumn(9).setMaxWidth(85);
			tabela.getColumnModel().getColumn(10).setMaxWidth(85);
		}
		else {
			tabela.getColumnModel().getColumn(0).setMaxWidth(237);
			tabela.getColumnModel().getColumn(1).setMaxWidth(161);
			tabela.getColumnModel().getColumn(2).setMaxWidth(51);
			tabela.getColumnModel().getColumn(3).setMaxWidth(161);
			tabela.getColumnModel().getColumn(4).setMaxWidth(131);
			tabela.getColumnModel().getColumn(5).setMaxWidth(91);
			tabela.getColumnModel().getColumn(6).setMaxWidth(87);
			tabela.getColumnModel().getColumn(7).setMaxWidth(87);
			tabela.getColumnModel().getColumn(8).setMaxWidth(57);
			tabela.getColumnModel().getColumn(9).setMaxWidth(86);
			tabela.getColumnModel().getColumn(10).setMaxWidth(86);
		}
	}
}
