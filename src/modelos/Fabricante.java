package modelos;

public class Fabricante {

	private int id;
	private String nome;
	
	public Fabricante(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
