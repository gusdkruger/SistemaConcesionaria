package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import daos.CarroDAO;
import filtros.LimitaSomenteDouble;
import modelos.Carro;

@SuppressWarnings("serial")
public class TelaVender extends JDialog implements ActionListener {

	private Carro carro;
	private TelaLogado telaLogado;
	
	private JComboBox<String> comboBoxVendaAno;
	private JComboBox<String> comboBoxVendaMes;
	private JComboBox<String> comboBoxVendaDia;
	private JTextField textFieldPrecoVenda;
	private JButton buttonConfirma;
	
	private final Font fonteGrande = new Font("Arial", Font.PLAIN, 25);
	private final Font fonteMedia = new Font("Arial", Font.PLAIN, 20);
	private final Font fontePequena = new Font("Arial", Font.PLAIN, 15);
	
	public TelaVender(Carro carro, TelaLogado telaLogado) {
		this.carro = carro;
		this.telaLogado = telaLogado;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(356, 249);
		setResizable(false);
		setLayout(null);
		setModal(true);

		JLabel labelDataVenda = new JLabel("Data Venda");
		labelDataVenda.setFont(fontePequena);
		labelDataVenda.setBounds(20, 5, 300, 15);
		add(labelDataVenda);
		
		comboBoxVendaAno = new JComboBox<String>();
		comboBoxVendaAno.setFont(fonteMedia);
		comboBoxVendaAno.setBounds(20, 20, 75, 40);
		for(int i = 2020; i <= 2030; i++) {
			comboBoxVendaAno.addItem(i + "");
		}
		comboBoxVendaAno.addActionListener(this);
		add(comboBoxVendaAno);
		
		comboBoxVendaMes = new JComboBox<String>();
		comboBoxVendaMes.setFont(fonteMedia);
		comboBoxVendaMes.setBounds(105, 20, 50, 40);
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
		comboBoxVendaDia.setBounds(165, 20, 50, 40);
		preencheComboBoxDia(2020, 1);
		add(comboBoxVendaDia);
		
		JLabel labelPrecoVenda = new JLabel("Preco");
		labelPrecoVenda.setFont(fontePequena);
		labelPrecoVenda.setBounds(20, 65, 300, 15);
		add(labelPrecoVenda);
		
		textFieldPrecoVenda = new JTextField();
		textFieldPrecoVenda.setFont(fonteGrande);
		textFieldPrecoVenda.setBounds(20, 80, 300, 40);
		textFieldPrecoVenda.setDocument(new LimitaSomenteDouble());
		add(textFieldPrecoVenda);
		
		buttonConfirma = new JButton("Confirmar");
		buttonConfirma.setFont(fonteGrande);
		buttonConfirma.setBounds(20, 140, 300, 50);
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
				comboBoxVendaDia.addItem("0" + i);
			}
			else {
				comboBoxVendaDia.addItem(i + "");
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonConfirma) {
			String dataVenda = comboBoxVendaAno.getSelectedItem() + "-" + comboBoxVendaMes.getSelectedItem() + "-" + comboBoxVendaDia.getSelectedItem();
			String valorString = textFieldPrecoVenda.getText();
			if(!valorString.isBlank()) {
				if(validaValor(valorString)) {
					Double valor = Double.parseDouble(valorString);
					CarroDAO.vende(carro.getId(), dataVenda, valor);
					carro.setVendido(true);
					carro.setDataVenda(dataVenda);
					carro.setPrecoVenda(valor);
					telaLogado.preencheTabela();
					JOptionPane.showMessageDialog(null, "Carro atualizado com sucesso");
					dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Preencha o valor do carro");
			}
		}
		else if(e.getSource() == comboBoxVendaAno || e.getSource() == comboBoxVendaMes) {
			comboBoxVendaDia.removeAllItems();
			int ano = Integer.parseInt((String)comboBoxVendaAno.getSelectedItem());
			int mes = Integer.parseInt((String)comboBoxVendaMes.getSelectedItem());
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
