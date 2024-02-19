package daos;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import conexao.Conexao;

public class UsuarioDAO {

	public static boolean cadastra(String login, String senha, int idConcessionaria) {
		String query = "INSERT INTO Usuario VALUES ('" + login + "', '" + senha + "', " + idConcessionaria + ")";
		return Conexao.executa(query);
	}
	
	public static boolean edita(String login, String senha, int idConcessionaria) {
		String query = "UPDATE Usuario SET senha = '" + senha + "', idConcessionaria = '" + idConcessionaria + "' WHERE login = '" + login + "'";
		return Conexao.executa(query);
	}
	
	public static boolean exclui(String login) {
		String query = "DELETE FROM Usuario WHERE login = '" + login + "'";
		return Conexao.executa(query);
	}
	
	public static int verificaLogin(String login, String senha) {
		String query = "SELECT idConcessionaria FROM Usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		ResultSet rs = Conexao.consulta(query);
		if(rs != null) {
			try {
				if(rs.next()) {
					return rs.getInt("idConcessionaria");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return -1;	
	}
}
