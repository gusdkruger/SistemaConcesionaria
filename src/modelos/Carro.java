package modelos;

public class Carro {

	private int id;
	private String modelo;
	private String cor;
	private int ano;
	private String fabricante;
	private String tipo;
	private int kilometragem;
	private String dataCompra;
	private Double precoCompra;
	private boolean vendido;
	private String dataVenda;
	private Double precoVenda;
	
	public Carro(int id, String modelo, String cor, int ano, String fabricante, String tipo, int kilometragem,
			String dataCompra, Double precoCompra, boolean vendido, String dataVenda, Double precoVenda) {
		this.id = id;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.fabricante = fabricante;
		this.tipo = tipo;
		this.kilometragem = kilometragem;
		this.dataCompra = dataCompra;
		this.precoCompra = precoCompra;
		this.vendido = vendido;
		this.dataVenda = dataVenda;
		this.precoVenda = precoVenda;
	}

	public int getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCor() {
		return cor;
	}

	public int getAno() {
		return ano;
	}

	public String getFabricante() {
		return fabricante;
	}

	public String getTipo() {
		return tipo;
	}

	public int getKilometragem() {
		return kilometragem;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public Double getPrecoCompra() {
		return precoCompra;
	}

	public boolean isVendido() {
		return vendido;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setKilometragem(int kilometragem) {
		this.kilometragem = kilometragem;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
}
