package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import daos.CarroDAO;
import filtros.LimitaLength;
import filtros.LimitaSomenteDouble;
import filtros.LimitaSomenteInteger;
import modelos.Carro;
import modelos.Cor;
import modelos.Fabricante;
import modelos.Tipo;

@SuppressWarnings("serial")
public class TelaEditar extends JDialog implements ActionListener {

	private Carro carro;
	private TelaLogado telaLogado;
	
	private JTextField textFieldModelo;
	private JComboBox<String> comboBoxCor;
	private JComboBox<String> comboBoxFabricante;
	private JTextField textFieldAno;
	private JComboBox<String> comboBoxTipo;
	private JTextField textFieldKilometragem;
	private JComboBox<String> comboBoxCompraAno;
	private JComboBox<String> comboBoxCompraMes;
	private JComboBox<String> comboBoxCompraDia;
	private JTextField textFieldPrecoCompra;
	private JComboBox<String> comboBoxVendido;
	private JComboBox<String> comboBoxVendaAno;
	private JComboBox<String> comboBoxVendaMes;
	private JComboBox<String> comboBoxVendaDia;
	private JTextField textFieldPrecoVenda;
	private JButton buttonConfirma;
	
	private final Font fonteGrande = new Font("Arial", Font.PLAIN, 25);
	private final Font fonteMedia = new Font("Arial", Font.PLAIN, 20);
	private final Font fontePequena = new Font("Arial", Font.PLAIN, 15);
	
	public TelaEditar(ArrayList<Fabricante> fabricantes, ArrayList<Tipo> tipos, ArrayList<Cor> cores, TelaLogado telaLogado, Carro carro) {
		this.carro = carro;
		this.telaLogado = telaLogado;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(356, 759);
		setResizable(false);
		setLayout(null);
		setModal(true);
		
		JLabel labelModelo = new JLabel("Modelo");
		labelModelo.setFont(fontePequena);
		labelModelo.setBounds(20, 5, 300, 15);
		add(labelModelo);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setFont(fonteGrande);
		textFieldModelo.setBounds(20, 20, 300, 40);
		textFieldModelo.setDocument(new LimitaLength(30));
		textFieldModelo.setText(carro.getModelo());
		add(textFieldModelo);
		
		comboBoxCor = new JComboBox<String>();
		comboBoxCor.setFont(fonteGrande);
		comboBoxCor.setBounds(20, 70, 300, 40);
		for(int i = 0; i < cores.size(); i++) {
			String cor = cores.get(i).getNome();
			comboBoxCor.addItem(cor);
			if(cor.equals(carro.getCor())) {
				comboBoxCor.setSelectedIndex(i);
			}
		}
		add(comboBoxCor);
		
		comboBoxFabricante = new JComboBox<String>();
		comboBoxFabricante.setFont(fonteGrande);
		comboBoxFabricante.setBounds(20, 120, 300, 40);
		for(int i = 0; i < fabricantes.size(); i++) {
			String fabricante = fabricantes.get(i).getNome();
			comboBoxFabricante.addItem(fabricante);
			if(fabricante.equals(carro.getFabricante())) {
				comboBoxFabricante.setSelectedIndex(i);
			}
		}
		add(comboBoxFabricante);
		
		JLabel labelAno = new JLabel("Ano");
		labelAno.setFont(fontePequena);
		labelAno.setBounds(20, 165, 300, 15);
		add(labelAno);
		
		textFieldAno = new JTextField();
		textFieldAno.setFont(fonteGrande);
		textFieldAno.setBounds(20, 180, 300, 40);
		textFieldAno.setDocument(new LimitaSomenteInteger(4));
		textFieldAno.setText(carro.getAno() + "");
		add(textFieldAno);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setFont(fonteGrande);
		comboBoxTipo.setBounds(20, 230, 300, 40);
		for(int i = 0; i < tipos.size(); i++) {
			String tipo = tipos.get(i).getNome();
			comboBoxTipo.addItem(tipo);
			if(tipo.equals(carro.getTipo())) {
				comboBoxTipo.setSelectedIndex(i);
			}
		}
		add(comboBoxTipo);
		
		JLabel labelKilometragem = new JLabel("Kilometragem");
		labelKilometragem.setFont(fontePequena);
		labelKilometragem.setBounds(20, 275, 300, 15);
		add(labelKilometragem);
		
		textFieldKilometragem = new JTextField();
		textFieldKilometragem.setFont(fonteGrande);
		textFieldKilometragem.setBounds(20, 290, 300, 40);
		textFieldKilometragem.setDocument(new LimitaSomenteInteger(7));
		textFieldKilometragem.setText(carro.getKilometragem() + "");
		add(textFieldKilometragem);
		
		JLabel labelDataCompra = new JLabel("Data Compra");
		labelDataCompra.setFont(fontePequena);
		labelDataCompra.setBounds(20, 335, 300, 15);
		add(labelDataCompra);

		comboBoxCompraAno = new JComboBox<String>();
		comboBoxCompraAno.setFont(fonteMedia);
		comboBoxCompraAno.setBounds(20, 350, 75, 40);
		for(int i = 0; i < 11; i++) {
			comboBoxCompraAno.addItem((i + 2020) + "");
			String anoCompra = carro.getDataCompra().substring(0, 4);
			if(anoCompra.equals((i + 2020) + "")) {
				comboBoxCompraAno.setSelectedIndex(i);
			}
		}
		comboBoxCompraAno.addActionListener(this);
		add(comboBoxCompraAno);
		
		comboBoxCompraMes = new JComboBox<String>();
		comboBoxCompraMes.setFont(fonteMedia);
		comboBoxCompraMes.setBounds(105, 350, 50, 40);
		for(int i = 0; i < 12; i++) {
			String mes;
			if(i < 9) {
				mes = "0" + (i + 1);
				comboBoxCompraMes.addItem(mes);
			}
			else {
				mes = (i + 1) + "";
				comboBoxCompraMes.addItem(mes);
			}
			String mesCompra = carro.getDataCompra().substring(5, 7);
			if(mesCompra.equals(mes)) {
				comboBoxCompraMes.setSelectedIndex(i);
			}
		}
		comboBoxCompraMes.addActionListener(this);
		add(comboBoxCompraMes);
		
		comboBoxCompraDia = new JComboBox<String>();
		comboBoxCompraDia.setFont(fonteMedia);
		comboBoxCompraDia.setBounds(165, 350, 50, 40);
		preencheComboBoxDia(comboBoxCompraAno, comboBoxCompraMes, comboBoxCompraDia);
		String dia = carro.getDataCompra().substring(8);
		for(int i = 0; i < comboBoxCompraDia.getItemCount(); i++) {
			if(dia.equals(comboBoxCompraDia.getItemAt(i))) {
				comboBoxCompraDia.setSelectedIndex(i);
			}
		}
		add(comboBoxCompraDia);
		
		JLabel labelPrecoCompra = new JLabel("Preco Compra");
		labelPrecoCompra.setFont(fontePequena);
		labelPrecoCompra.setBounds(20, 395, 300, 15);
		add(labelPrecoCompra);
		
		textFieldPrecoCompra = new JTextField();
		textFieldPrecoCompra.setFont(fonteGrande);
		textFieldPrecoCompra.setBounds(20, 410, 300, 40);
		textFieldPrecoCompra.setDocument(new LimitaSomenteDouble());
		textFieldPrecoCompra.setText(carro.getPrecoCompra() + "");
		add(textFieldPrecoCompra);
		
		JLabel labelVendido = new JLabel("Vendido");
		labelVendido.setFont(fontePequena);
		labelVendido.setBounds(20, 455, 300, 15);
		add(labelVendido);
		
		comboBoxVendido = new JComboBox<>();
		comboBoxVendido.setFont(fonteMedia);
		comboBoxVendido.setBounds(20, 470, 75, 40);
		comboBoxVendido.addItem("True");
		comboBoxVendido.addItem("False");
		comboBoxVendido.addActionListener(this);
		add(comboBoxVendido);
		
		JLabel labelDataVenda = new JLabel("Data Venda");
		labelDataVenda.setFont(fontePequena);
		labelDataVenda.setBounds(20, 515, 300, 15);
		add(labelDataVenda);
		
		comboBoxVendaAno = new JComboBox<String>();
		comboBoxVendaAno.setFont(fonteMedia);
		comboBoxVendaAno.setBounds(20, 530, 75, 40);
		for(int i = 2020; i <= 2030; i++) {
			comboBoxVendaAno.addItem(i + "");
		}
		comboBoxVendaAno.addActionListener(this);
		add(comboBoxVendaAno);
		
		comboBoxVendaMes = new JComboBox<String>();
		comboBoxVendaMes.setFont(fonteMedia);
		comboBoxVendaMes.setBounds(105, 530, 50, 40);
		for(int i = 1; i <= 12; i++) {
			if(i < 10) {
				comboBoxVendaMes.addItem("0" + i);
			}
			else {
				comboBoxVendaMes.addItem(i + "");
			}
		}
		comboBoxVendaMes.addActionListener(this);
		add(comboBoxVendaMes);
		
		comboBoxVendaDia = new JComboBox<String>();
		comboBoxVendaDia.setFont(fonteMedia);
		comboBoxVendaDia.setBounds(165, 530, 50, 40);
		preencheComboBoxDia(comboBoxVendaAno, comboBoxVendaMes, comboBoxVendaDia);
		add(comboBoxVendaDia);
		
		JLabel labelPrecoVenda = new JLabel("Preco Venda");
		labelPrecoVenda.setFont(fontePequena);
		labelPrecoVenda.setBounds(20, 575, 300, 15);
		add(labelPrecoVenda);
		
		textFieldPrecoVenda = new JTextField(carro.getPrecoCompra() + "");
		textFieldPrecoVenda.setFont(fonteGrande);
		textFieldPrecoVenda.setBounds(20, 590, 300, 40);
		textFieldPrecoVenda.setDocument(new LimitaSomenteDouble());
		add(textFieldPrecoVenda);

		if(carro.isVendido()) {
			comboBoxVendido.setSelectedIndex(0);
			String anoVenda = carro.getDataVenda().substring(0, 4);
			for(int i = 0; i < comboBoxVendaAno.getItemCount(); i++) {
				if(anoVenda.equals(comboBoxVendaAno.getItemAt(i))) {
					comboBoxVendaAno.setSelectedIndex(i);
				}
			}
			String mesVenda = carro.getDataVenda().substring(5, 7);
			for(int i = 0; i < comboBoxVendaMes.getItemCount(); i++) {
				if(mesVenda.equals(comboBoxVendaMes.getItemAt(i))) {
					comboBoxVendaMes.setSelectedIndex(i);
				}
			}
			String diaVenda = carro.getDataVenda().substring(8);
			for(int i = 0; i < comboBoxVendaDia.getItemCount(); i++) {
				if(diaVenda.equals(comboBoxVendaDia.getItemAt(i))) {
					comboBoxVendaDia.setSelectedIndex(i);
				}
			}
			textFieldPrecoVenda.setText(carro.getPrecoVenda() + "");
		}
		else {
			comboBoxVendido.setSelectedIndex(1);
			comboBoxVendaAno.setEnabled(false);
			comboBoxVendaMes.setEnabled(false);
			comboBoxVendaDia.setEnabled(false);
			textFieldPrecoVenda.setEnabled(false);
		}
		
		buttonConfirma = new JButton("Salvar");
		buttonConfirma.setFont(fonteGrande);
		buttonConfirma.setBounds(20, 650, 300, 50);
		buttonConfirma.addActionListener(this);
		add(buttonConfirma);
		
		setVisible(true);
	}
	
	private void preencheComboBoxDia(JComboBox<String> comboBoxAno, JComboBox<String> comboBoxMes, JComboBox<String> comboBoxDia) {
		int ano = Integer.parseInt((String)comboBoxAno.getSelectedItem());
		int mes = Integer.parseInt((String)comboBoxMes.getSelectedItem());
		int dias;
		if(mes == 2) {
			if((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
				dias = 29;
			}
			else {
				dias = 28;
			}
		}
		else if(mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			dias = 30;
		}
		else {
			dias = 31;
		}
		for(int i = 1; i <= dias; i++) {
			if(i < 10) {
				comboBoxDia.addItem("0" + i);
			}
			else {
				comboBoxDia.addItem(i + "");
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonConfirma) {
			String modelo = textFieldModelo.getText();
			if(!modelo.isBlank()) {
				int cor = comboBoxCor.getSelectedIndex() + 1;
				int fabricante = comboBoxFabricante.getSelectedIndex() + 1;
				String textAno = textFieldAno.getText();
				if(!textAno.isEmpty()) {
					int ano = Integer.parseInt(textAno);
					if(ano > 1899) {
						int tipo = comboBoxTipo.getSelectedIndex() + 1;
						String textKilometragem = textFieldKilometragem.getText();
						if(!textKilometragem.isEmpty()) {
							int kilometragem = Integer.parseInt(textKilometragem);
							String dataCompra = comboBoxCompraAno.getSelectedItem() + "-" + comboBoxCompraMes.getSelectedItem() + "-" + comboBoxCompraDia.getSelectedItem();
							String valorCompraString = textFieldPrecoCompra.getText();
							if(!valorCompraString.isBlank()) {
								if(validaValor(valorCompraString)) {
									Double valorCompra = Double.parseDouble(valorCompraString);
									Boolean vendido = true;
									if(comboBoxVendido.getSelectedIndex() == 1) {
										vendido = false;
									}
									if(!vendido) {
										CarroDAO.edita(carro.getId(), modelo, cor, ano, fabricante, tipo, kilometragem, dataCompra, valorCompra, false, null, null);
										carro.setModelo(modelo);
										carro.setCor((String)comboBoxCor.getSelectedItem());
										carro.setAno(ano);
										carro.setFabricante((String)comboBoxFabricante.getSelectedItem());
										carro.setTipo((String)comboBoxTipo.getSelectedItem());
										carro.setKilometragem(kilometragem);
										carro.setDataCompra(dataCompra);
										carro.setPrecoCompra(valorCompra);
										carro.setVendido(false);
										carro.setDataVenda(null);
										carro.setPrecoVenda(null);
										telaLogado.preencheTabela();
										JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso");
										dispose();
									}
									else {
										String dataVenda = comboBoxVendaAno.getSelectedItem() + "-" + comboBoxVendaMes.getSelectedItem() + "-" + comboBoxVendaDia.getSelectedItem();
										String valorVendaString = textFieldPrecoVenda.getText();
										if(!valorVendaString.isBlank()) {
											if(validaValor(valorCompraString)) {
												Double valorVenda = Double.parseDouble(valorVendaString);
												CarroDAO.edita(carro.getId(), modelo, cor, ano, fabricante, tipo, kilometragem, dataCompra, valorCompra, true, dataVenda, valorVenda);
												carro.setModelo(modelo);
												carro.setCor((String)comboBoxCor.getSelectedItem());
												carro.setAno(ano);
												carro.setFabricante((String)comboBoxFabricante.getSelectedItem());
												carro.setTipo((String)comboBoxTipo.getSelectedItem());
												carro.setKilometragem(kilometragem);
												carro.setDataCompra(dataCompra);
												carro.setPrecoCompra(valorCompra);
												carro.setVendido(true);
												carro.setDataVenda(dataVenda);
												carro.setPrecoVenda(valorVenda);
												telaLogado.preencheTabela();
												JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso");
												dispose();
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "Preencha o preco de venda do carro");
										}
									}
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Preencha o preco de compra do carro");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Preencha a kilometragem do carro");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Ano minimo: 1900");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Preencha o ano do carro");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Preencha o modelo do carro");
			}
		}
		else if(e.getSource() == comboBoxCompraAno || e.getSource() == comboBoxCompraMes) {
			comboBoxCompraDia.removeAllItems();
			preencheComboBoxDia(comboBoxCompraAno, comboBoxCompraMes, comboBoxCompraDia);
		}
		else if(e.getSource() == comboBoxVendido) {
			if(comboBoxVendido.getSelectedIndex() == 0) {
				comboBoxVendaAno.setEnabled(true);
				comboBoxVendaMes.setEnabled(true);
				comboBoxVendaDia.setEnabled(true);
				textFieldPrecoVenda.setEnabled(true);
			}
			else {
				comboBoxVendaAno.setEnabled(false);
				comboBoxVendaMes.setEnabled(false);
				comboBoxVendaDia.setEnabled(false);
				textFieldPrecoVenda.setEnabled(false);
			}	
		}
		else if(e.getSource() == comboBoxVendaAno || e.getSource() == comboBoxVendaMes) {
			comboBoxVendaDia.removeAllItems();
			preencheComboBoxDia(comboBoxVendaAno, comboBoxVendaMes, comboBoxVendaDia);
		}
	}
	
	private boolean validaValor(String valor) {
		try {
			Double d = Double.parseDouble(valor);
			if(d >= 1000000000) {
				JOptionPane.showMessageDialog(null, "Valor maximo: 999999999.99");
				return false;
			}
			else if(BigDecimal.valueOf(d).scale() > 2) {
				JOptionPane.showMessageDialog(null, "Maximo de 2 numeros depois da virgula ou ponto");
				return false;
			}
			return true;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Valor so pode conter um , ou .");
			return false;
		}
	}
}
