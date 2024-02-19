package daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.Conexao;
import modelos.Carro;
import modelos.Cor;
import modelos.Fabricante;
import modelos.Tipo;

public class CarroDAO {

	public static int cria(int concessionaria, String modelo, int cor, int ano, int fabricante, int tipo, int kilometragem, String dataCompra, Double precoCompra) {
		String query = "INSERT INTO Carro VALUES (0, " + concessionaria + ", '" + modelo + "', " + cor + ", " + ano + ", " + fabricante + ", " + tipo + ", " + kilometragem + ", '" + dataCompra + "', " + precoCompra + ", false, null, null);";
		return Conexao.executaRetornandoID(query);
	}
	
	public static boolean exclui(int id) {
		String query = "DELETE FROM Carro WHERE id = " + id;
		return Conexao.executa(query);
	}
	
	public static boolean edita(int id, String modelo, int cor, int ano, int fabricante, int tipo, int kilometragem, String dataCompra, Double precoCompra, boolean vendido, String dataVenda, Double precoVenda) {
		String query;
		if(vendido) {
			query = "UPDATE Carro SET modelo = '" + modelo + "', cor = " + cor + ", ano = " + ano + ", fabricante = " + fabricante + ", tipo = " + tipo + ", kilometragem = " + kilometragem + ", dataCompra = '" + dataCompra + "', precoCompra = " + precoCompra + ", vendido = " + vendido + ", dataVenda = '" + dataVenda + "', precoVenda = " + precoVenda + " WHERE id = " + id;
		}
		else {
			query = "UPDATE Carro SET modelo = '" + modelo + "', cor = " + cor + ", ano = " + ano + ", fabricante = " + fabricante + ", tipo = " + tipo + ", kilometragem = " + kilometragem + ", dataCompra = '" + dataCompra + "', precoCompra = " + precoCompra + ", vendido = false, dataVenda = null, precoVenda = null WHERE id = " + id;
		}
		return Conexao.executa(query);
	}
	
	public static boolean vende(int id, String dataVenda, Double precoVenda) {
		String query = "UPDATE Carro SET vendido = true, dataVenda = '" + dataVenda + "', precoVenda = " + precoVenda + " WHERE id = " + id;
		return Conexao.executa(query);
	}
	
	public static ArrayList<Carro> getCarros(int concessionaria) {
		ArrayList<Cor> cores = DAOs.getCores();
		ArrayList<Fabricante> fabricantes = DAOs.getFabricantes();
		ArrayList<Tipo> tipos = DAOs.getTipos();
		ArrayList<Carro> carros = new ArrayList<>();
		String query = "SELECT * FROM Carro WHERE idConcessionaria = " + concessionaria;
		ResultSet rs = Conexao.consulta(query);
		if(rs != null) {
			try {
				while(rs.next()) {
					String cor = "";
					int id = rs.getInt("cor");
					for(int i = 0; i < cores.size(); i++) {
						if(cores.get(i).getId() == id) {
							cor = cores.get(i).getNome();
						}
					}
					String fabricante = "";
					id = rs.getInt("fabricante");
					for(int i = 0; i < fabricantes.size(); i++) {
						if(fabricantes.get(i).getId() == id) {
							fabricante = fabricantes.get(i).getNome();
						}
					}
					String tipo = "";
					id = rs.getInt("tipo");
					for(int i = 0; i < tipos.size(); i++) {
						if(tipos.get(i).getId() == id) {
							tipo = tipos.get(i).getNome();
						}
					}
					Carro c = new Carro(rs.getInt("id"), rs.getString("modelo"), cor, rs.getInt("ano"), fabricante, tipo, rs.getInt("kilometragem"), rs.getString("dataCompra"), rs.getDouble("precoCompra"), rs.getBoolean("vendido"), rs.getString("dataVenda"), rs.getDouble("precoVenda"));
					carros.add(c);
				}
				return carros;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return null;
	}
}
