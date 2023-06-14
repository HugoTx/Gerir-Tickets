package TrabalhoFinalV1;

import java.io.Serializable;
import java.time.LocalDate;

public class Ticket implements Serializable {
	static final long serialVersionUID = 0;
	static String ANSI_RESET = "\u001b[0m";
	static String ANSI_GREEN = "\u001b[32m";
	static String ANSI_RED = "\u001b[31m";
	static String ANSI_BLUE = "\u001b[34m";

	//DECLARA��O DE VARI�VEIS
	static int ref = 0;
	int numTicket;
	private String comentarios;
	private String dataAtualizacao;
	private String estado;
	private String dataRegisto;
	private String dataPrevista;
	private String descricao;
	Cliente cliente;

	//GETTERS & SETTERS
	public static int getRef() {
		return ref;
	}

	public static void setRef(int ref) {
		Ticket.ref = ref;
	}

	public int getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(int numTicket) {
		this.numTicket = numTicket;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	//CONSTRUTOR
	public Ticket() {
		//Sempre que se criar um novo Ticket vai-lhe dar um novo numTicket autom�ticamente
		ref++; //ref � apenas o contador
		numTicket = ref; //numTicket recebe o valor da ref (j� incrementado)
		System.out.println("Vai criar o ticket com o n�mero " + numTicket);
	}

	//TOSTRING
	@Override
	public String toString() {

		if (dataAtualizacao == null && comentarios == null) {
			return ANSI_GREEN +  "Ticket n�mero: " + numTicket +
					", data de Registo = " + dataRegisto +
					" ,data de Entrega = " + dataPrevista +
					", estado= " + estado + ", Pre�o: " + String.format("%.2f", cliente.getPreco()) + "Cliente " +
					cliente + "\n" + ANSI_RESET;
		} else if (dataAtualizacao == null) {
			return ANSI_GREEN + "Ticket n�mero " + numTicket +
					", data de Registo = " + dataRegisto +
					" ,data de Entrega = " + dataPrevista +
					", estado= " + estado + ", Pre�o: " + String.format("%.2f", cliente.getPreco()) +
					"�, coment�rios = " + comentarios + "\n" +
					"Cliente: " + cliente + "\n" + ANSI_RESET;

		} else if (comentarios == null) {
			return ANSI_GREEN + "Ticket n�mero " + numTicket +
					", data de Registo = " + dataRegisto +
					" ,Data mudada em " + dataAtualizacao +
					" ,data de Entrega = " + dataPrevista +
					", estado = " + estado + ", Pre�o: " + String.format("%.2f", cliente.getPreco()) + "\n" +
					"Cliente: " + cliente + "\n" + ANSI_RESET;

		} else if (estado.equalsIgnoreCase("Fechado") || estado.equalsIgnoreCase("Entregue ao Cliente")) {
			return ANSI_GREEN + "Ticket n�mero " + numTicket +
					", data de Registo = " + dataRegisto +
					" ,Data de entrega " + dataAtualizacao +
					" coment�rios " + comentarios +
					", estado= " + estado +   ", Pre�o: " + String.format("%.2f", cliente.getPreco()) +"\n" +
					"Cliente: " + cliente + "\n" + ANSI_RESET;
		} else {
			return ANSI_GREEN + "Ticket n�mero " + numTicket +
					", data de Registo = " + dataRegisto +
					", Data actualizada " + dataAtualizacao +
					", data de Entrega Prevista = " + dataPrevista +
					", estado= " + estado +  ", Pre�o: " + String.format("%.2f", cliente.getPreco()) +
					"\n, comentarios = " + comentarios + "\n" +
					"Cliente: " + cliente +"\n" + ANSI_RESET;
		}
	}
}