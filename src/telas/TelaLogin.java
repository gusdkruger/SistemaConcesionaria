package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daos.UsuarioDAO;
import filtros.LimitaLength;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame implements ActionListener {
	
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JButton buttonLogin;
	
	private final Font fonteGrande = new Font("Arial", Font.PLAIN, 25);
	private final Font fontePequena = new Font("Arial", Font.PLAIN, 15);
	
	public TelaLogin() {
		super("Concessionaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(426, 354);
		setResizable(false);
		setLayout(null);
		
		JLabel labelUsuario = new JLabel("Login");
		labelUsuario.setFont(fontePequena);
		labelUsuario.setBounds(80, 23, 250, 15);
		add(labelUsuario);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(fonteGrande);
		textFieldLogin.setBounds(80, 40, 250, 50);
		textFieldLogin.setDocument(new LimitaLength(20));
		add(textFieldLogin);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setFont(fontePequena);
		labelSenha.setBounds(80, 113, 250, 15);
		add(labelSenha);
		
		passwordField = new JPasswordField();
		passwordField.setFont(fonteGrande);
		passwordField.setBounds(80, 130, 250, 50);
		passwordField.setDocument(new LimitaLength(20));
		add(passwordField);
		
		buttonLogin = new JButton("Logar");
		buttonLogin.setFont(fonteGrande);
		buttonLogin.setBounds(80, 220, 250, 50);
		buttonLogin.addActionListener(this);
		add(buttonLogin);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String login = textFieldLogin.getText();
		@SuppressWarnings("deprecation")
		String senha = passwordField.getText();
		if(!login.isBlank() && !senha.isBlank()) {
			int idConcessionaria = UsuarioDAO.verificaLogin(login, senha);
			if(idConcessionaria != -1) {
				new TelaLogado(idConcessionaria);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuario ou senha invalidos");
				passwordField.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Preencha os campos");
		}
	}
}
