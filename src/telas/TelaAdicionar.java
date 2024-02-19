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
public class TelaAdicionar extends JDialog implements ActionListener {

	private int idConcessionaria;
	private ArrayList<Carro> carros;
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
	private JButton buttonConfirma;
	
	private final Font fonteGrande = new Font("Arial", Font.PLAIN, 25);
	private final Font fonteMedia = new Font("Arial", Font.PLAIN, 20);
	private final Font fontePequena = new Font("Arial", Font.PLAIN, 15);
	
	public TelaAdicionar(int idConcessionaria, ArrayList<Fabricante> fabricantes, ArrayList<Tipo> tipos, ArrayList<Cor> cores, ArrayList<Carro> carros, TelaLogado telaLogado) {
		this.idConcessionaria = idConcessionaria;
		this.carros = carros;
		this.telaLogado = telaLogado;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(356, 569);
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
		add(textFieldModelo);
		
		comboBoxCor = new JComboBox<String>();
		comboBoxCor.setFont(fonteGrande);
		comboBoxCor.setBounds(20, 70, 300, 40);
		for(Cor c : cores) {
			comboBoxCor.addItem(c.getNome());
		}
		add(comboBoxCor);
		
		comboBoxFabricante = new JComboBox<String>();
		comboBoxFabricante.setFont(fonteGrande);
		comboBoxFabricante.setBounds(20, 120, 300, 40);
		for(Fabricante f : fabricantes) {
			comboBoxFabricante.addItem(f.getNome());
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
		add(textFieldAno);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setFont(fonteGrande);
		comboBoxTipo.setBounds(20, 230, 300, 40);
		for(Tipo t : tipos) {
			comboBoxTipo.addItem(t.getNome());
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
		add(textFieldKilometragem);
		
		JLabel labelDataCompra = new JLabel("Data Compra");
		labelDataCompra.setFont(fontePequena);
		labelDataCompra.setBounds(20, 335, 300, 15);
		add(labelDataCompra);
		
		comboBoxCompraAno = new JComboBox<String>();
		comboBoxCompraAno.setFont(fonteMedia);
		comboBoxCompraAno.setBounds(20, 350, 75, 40);
		for(int i = 2020; i <= 2030; i++) {
			comboBoxCompraAno.addItem(i + "");
		}
		comboBoxCompraAno.addActionListener(this);
		add(comboBoxCompraAno);
		
		comboBoxCompraMes = new JComboBox<String>();
		comboBoxCompraMes.setFont(fonteMedia);
		comboBoxCompraMes.setBounds(105, 350, 50, 40);
		for(int i = 1; i <= 12; i++) {
			if(i < 10) {
				comboBoxCompraMes.addItem("0" + i);
			}
			else {
				comboBoxCompraMes.addItem(i + "");
			}
		}
		comboBoxCompraMes.addActionListener(this);
		add(comboBoxCompraMes);
		
		comboBoxCompraDia = new JComboBox<String>();
		comboBoxCompraDia.setFont(fonteMedia);
		comboBoxCompraDia.setBounds(165, 350, 50, 40);
		preencheComboBoxDia(2020, 1);
		add(comboBoxCompraDia);
		
		JLabel labelPrecoCompra = new JLabel("Preco Compra");
		labelPrecoCompra.setFont(fontePequena);
		labelPrecoCompra.setBounds(20, 395, 300, 15);
		add(labelPrecoCompra);
		
		textFieldPrecoCompra = new JTextField();
		textFieldPrecoCompra.setFont(fonteGrande);
		textFieldPrecoCompra.setBounds(20, 410, 300, 40);
		textFieldPrecoCompra.setDocument(new LimitaSomenteDouble());
		add(textFieldPrecoCompra);
		
		buttonConfirma = new JButton("Confirmar");
		buttonConfirma.setFont(fonteGrande);
		buttonConfirma.setBounds(20, 460, 300, 50);
		buttonConfirma.addActionListener(this);
		add(buttonConfirma);
		
		setVisible(true);
	}
	
	private void preencheComboBoxDia(int ano, int mes) {
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
				comboBoxCompraDia.addItem("0" + i);
			}
			else {
				comboBoxCompraDia.addItem(i + "");
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
							String valorString = textFieldPrecoCompra.getText();
							if(!valorString.isBlank()) {
								if(validaValor(valorString)) {
									Double valor = Double.parseDouble(valorString);
									int id = CarroDAO.cria(idConcessionaria, modelo, cor, ano, fabricante, tipo, kilometragem, dataCompra, valor);
									Carro c = new Carro(id, modelo, (String)comboBoxCor.getSelectedItem(), ano, (String)comboBoxFabricante.getSelectedItem(), (String)comboBoxTipo.getSelectedItem(), kilometragem, dataCompra, valor, false, null, null);
									carros.add(c);
									telaLogado.preencheTabela();
									JOptionPane.showMessageDialog(null, "Carro adicionado com sucesso");
									dispose();
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
			int ano = Integer.parseInt((String)comboBoxCompraAno.getSelectedItem());
			int mes = Integer.parseInt((String)comboBoxCompraMes.getSelectedItem());
			preencheComboBoxDia(ano, mes);
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
