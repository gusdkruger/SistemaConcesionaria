package daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.Conexao;
import modelos.Cor;
import modelos.Fabricante;
import modelos.Tipo;

public class DAOs {

	public static ArrayList<Cor> getCores() {
		ArrayList<Cor> cores = new ArrayList<>();
		String query = "SELECT * FROM Cor";
		ResultSet rs = Conexao.consulta(query);
		if(rs != null) {
			try {
				while(rs.next()) {
					Cor c = new Cor(rs.getInt("id"), rs.getString("Nome"));
					cores.add(c);
				}
				return cores;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return null;
	}

	public static ArrayList<Fabricante> getFabricantes() {
		ArrayList<Fabricante> fabricantes = new ArrayList<>();
		String query = "SELECT * FROM Fabricante";
		ResultSet rs = Conexao.consulta(query);
		if(rs != null) {
			try {
				while(rs.next()) {
					Fabricante c = new Fabricante(rs.getInt("id"), rs.getString("Nome"));
					fabricantes.add(c);
				}
				return fabricantes;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return null;
	}
	
	public static ArrayList<Tipo> getTipos() {
		ArrayList<Tipo> tipos = new ArrayList<>();
		String query = "SELECT * FROM Tipo";
		ResultSet rs = Conexao.consulta(query);
		if(rs != null) {
			try {
				while(rs.next()) {
					Tipo c = new Tipo(rs.getInt("id"), rs.getString("Nome"));
					tipos.add(c);
				}
				return tipos;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return null;
	}
}
