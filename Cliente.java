package TrabalhoFinalV1;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
	static final long serialVersionUID = 0;

	//DECLARAÇÃO DE VARIÁVEIS
	protected Integer nif;
	protected Float maoObra;
	protected Float pecas;
	protected String nome;
	protected String morada;
	static Integer num = 0;
	protected Integer numCliente;
	protected Float preco;
	protected Integer telef;
	protected String email;

	protected Integer tipoCliente;


	public Integer getTelef() {
		return telef;
	}

	public void setTelef(Integer telef) {
		this.telef = telef;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//GETTERS & SETTERS
	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Integer getNif() {
		return nif;
	}

	public void setNif(Integer nif) {
		this.nif = nif;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public Float getPreco() {
		return preco;
	}

	public abstract void setPreco(Float maoObra, Float precoPeca);


	//CONSTRUTOR
	public Cliente() {
		//Sempre que se criar um novo Cliente vai-lhe dar um novo numCliente automaticamente
		num++; //num é apenas o contador
		this.numCliente = num; // //numClient recebe o valor da ref (já incrementado)
	}

	@Override
	public String toString() {
		final String ANSI_RESET = "\u001b[0m";
		final String ANSI_GREEN = "\u001b[32m";
		if (preco == null) {
			return
					ANSI_GREEN + "Cliente número: " + numCliente +
							", Nome: " + nome +
							", nif=" + nif +
							", telemóvel: " + telef +
							", email: " + email +
							", morada: " + morada +
							", tipo de cliente: " + tipoCliente +
							'.' + ANSI_RESET;
		} else {
			return
					ANSI_GREEN + "Cliente número: " + numCliente +
							", Nome: " + nome +
							", nif=" + nif +
							", telemóvel: " + telef +
							", email: " + email +
							", morada: " + morada +
							", tipo de cliente: " + tipoCliente +
							", preço: " + preco +
							"€" +
							'.' + ANSI_RESET;
		}
	}
}
