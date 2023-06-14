package TrabalhoFinalV1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Run {
	static final long serialVersionUID = 0;
	static String ANSI_RESET = "\u001b[0m";
	static String ANSI_GREEN = "\u001b[32m";
	static String ANSI_RED = "\u001b[31m";
	static String ANSI_BLUE = "\u001b[34m";


	static ArrayList<Ticket> tickets = new ArrayList<>();
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static ArrayList<Ticket> historico = new ArrayList<>();

	static LocalDate data = LocalDate.now();
	static LocalDate entregaPrevista = data.plus(30, ChronoUnit.DAYS);
	final private static String fileCli = "myClientes.by";
	final private static String fileTic = "myTickets.by";
	public static void main(String[] args) {
		clientes = Backup.readCustomers(fileCli);
		if (clientes == null)
			clientes = new ArrayList<>(); //criar um novo objeto ARRAYLIST
		else {
			Cliente.num = clientes.get(clientes.size() - 1).getNumCliente();
		}
		tickets = Backup.readTickets(fileTic);
		if (tickets == null)
			tickets = new ArrayList<>();
		else {
			Ticket.ref = tickets.get(tickets.size() - 1).getNumTicket();
		}
		int op = 0;
		do {
			op = menuCliente();
			switch (op) {
				case 0: //Sair do programa e efetuar backup
					System.out.println("Sair");
					Backup.saveCustomers(clientes, fileCli);
					Backup.saveTickets(tickets, fileTic);
					break;
				case 1: //ADICIONAR CLIENTES
					addCliente();
					break;
				case 2: //LISTAR TODOS OS CLIENTES
					if (clientes.size() == 0)
						System.out.println(ANSI_RED + "N�o existem clientes" + ANSI_RESET);
					else
						listaClientes();
					break;
				case 3: //ALTERAR CLIENTES
					if (clientes.size() == 0) {
						System.out.println(ANSI_RED + "N�o existem clientes registados" + ANSI_RESET);
					} else {
						alterarDados();
					}
					break;
				case 4: //ELIMINAR CLIENTES
					if (clientes.size() == 0)
						System.out.println(ANSI_RED + "N�o existem clientes registados" + ANSI_RESET);
					else
						eliminarDados();
					break;
				case 5: //TUDO TICKETS
					if (clientes.size() == 0)
						System.out.println(ANSI_RED + "N�o pode criar Ticket pois n�o tem nenhum cliente registado" + ANSI_RESET);
					else {
						menuTickets();
						switchTickets();
					}
					break;
				default:
					System.out.println(ANSI_RED + "Op��o inv�lida" + ANSI_RESET);
					break;
			}
		} while (op != 0);
	}
//===============Tickets==========================
	private static void switchTickets() {
		int op2;
		System.out.print("Introduza a opc�o : ");
		op2 = IO.getInt();
		System.out.println("=======================================================");
		switch (op2) {
			case 1:
				addTicket();
				break;
			case 2:
				if (tickets.size() == 0)
					System.out.println(ANSI_RED + "N�o existem tickets" + ANSI_RESET);
				else
					listaTickets();
				break;
			case 3:
				if (tickets.size() == 0) {
					System.out.println(ANSI_RED + "N�o existem Tickets registados" + ANSI_RESET);
				} else {
					alteraTicket();
				}
				break;
			case 4:
				if (tickets.size() == 0)
					System.out.println(ANSI_RED + "N�o existem tickets registados" + ANSI_RESET);
				eliminarTicket();
				break;
		}
	}
	private static void listaTickets() {
		String estado;
		String cliente;
		int nCliente;
		int dadosTicket;
		int nif;
		int verficaNum = 0;
		do {
			System.out.println("1 - Estado | 2 - Nome de cliente | 3 - N�mero de Cliente | 4 - NIF");
			System.out.println("5- Entre datas | 6 - Data prevista ultrapassada | 7- Todos os Tickets");
			System.out.print("Introduza a op��o que pretende : ");
			dadosTicket = IO.getInt();
			switch (dadosTicket) {
				case 1: //Procura por Estado
					System.out.print("Insira o estado do Ticket : ");
					estado = IO.getString();
					for (Ticket t : tickets)
						if (t.getEstado().equalsIgnoreCase(estado)) {
							System.out.println(t);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o h� registo nesse estado" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 2: //Procura por NOME de cliente
					System.out.print("Insira o NOME que pretende procurar : ");
					cliente = IO.getString();
					for (Ticket t : tickets)
						if (t.getCliente().getNome().equalsIgnoreCase(cliente)) {
							System.out.println(t);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o h� registos desse cliente" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 3: //Procura por N�mero de Cliente
					System.out.print("Insira o N�mero de Cliente que pretende procurar : ");
					nCliente = IO.getInt();
					for (int i = 0; i < tickets.size(); i++)
						if (tickets.get(i).getCliente().getNumCliente() == nCliente) {
							System.out.println(tickets.get(i)); //IMPRIME cliente na posicao I (for) do array completo
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o existe esse n�mero de cliente" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 4: // Procura por Nif
					System.out.print("Insira o NIF que pretende procurar : ");
					nif = IO.getInt();
					for (Ticket t : tickets)
						if (t.getCliente().getNif() == nif) {
							System.out.println(t);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o h� registos desse Nif" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 5: // entre datas

				/*

				GregorianCalendar agora = new GregorianCalendar();
				String anoUtilizador = String.format("%tY", agora );
				System.out.println("Introduza o ano: ");
				Integer ano = IO.getInt();
				System.out.println("Introduza o m�s: ");
				Integer mes = IO.getInt();
				System.out.println("Introduza o dia: ");
				Integer dia = IO.getInt();
				LocalDate data1 = LocalDate.of(ano, mes, dia);
				System.out.println("Introduza o ano: ");
				Integer ano1 = IO.getInt();
				System.out.println("Introduza o m�s: ");
				Integer mes1 = IO.getInt();
				System.out.println("Introduza o dia: ");
				Integer dia1 = IO.getInt();
				LocalDate data2 = LocalDate.of(ano1, mes1, dia1);
				for(Ticket t : tickets)

				 */


					break;
				case 6: // data prevista ultrapassada pesquisar tickets em que a data prevista j� ultrapasssou os 30dias
					long difEmDias = ChronoUnit.DAYS.between(data, entregaPrevista);
					for (Ticket t : tickets)
						if (difEmDias > 30) {
							System.out.println(t);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o existem tickets expirados" + ANSI_RESET);
						verficaNum = 0;
					}
					break;

				case 7: // LISTAR TODOS OS TICKETS
					for (Ticket t : tickets)
						System.out.println(t);
					verficaNum = 1;
					break;
				default:
					System.out.println(ANSI_RED + "Op��o inv�lida!" + ANSI_RESET);
					break;
			}
		} while (verficaNum == 0);
	}
	private static void eliminarTicket() {
		System.out.print("Numero do Ticket : ");
		int numTicket = IO.getInt();
		for (int i = 0; i < tickets.size(); i++)
			if (tickets.get(i).getEstado().equalsIgnoreCase("Registado")) {
				if (tickets.get(i).getNumTicket() == numTicket) {
					tickets.remove(i);
					System.out.println("=======================================================");
					System.out.println(ANSI_GREEN + "Ticket eliminado com sucesso" + ANSI_RESET);
					System.out.println("=======================================================");
				} else //se nao houver um ticket com esse numeroTicket
					System.out.println("=======================================================");
				System.out.println(ANSI_RED + "N�o existe esse Ticket" + ANSI_RESET);
				System.out.println("=======================================================");
			} else
				System.out.println(ANSI_RED + "N�o pode eliminar este Ticket" + ANSI_RESET);
	}
	private static void alteraTicket() {
		Final finalCliente = new Final();
		Revendedor revendedorCliente = new Revendedor();
		int numTicket;
		String newcomment;
		float precoPeca, precoMaoObra;
		estadosTicket();
		System.out.print("Insira o n�mero de Ticket a atualizar: ");
		numTicket = IO.getInt();
		for (Ticket t : tickets) {
			if (t.getNumTicket() == numTicket) {
				System.out.print("Introduza o novo estado : ");
				System.out.println("Registado | Or�amentado | Invi�vel | Em curso ");
				System.out.println("Aguarda pe�as | Aguarda Levantamento | Entregue");
				String novoEstado = IO.getString();
				System.out.println("=======================================================");
				tickets.get(numTicket - 1).setEstado(novoEstado);
				tickets.get(numTicket - 1).setDataAtualizacao(String.valueOf(data));
				if (novoEstado.equalsIgnoreCase("Or�amentado") || novoEstado.equalsIgnoreCase("Invi�vel")
						|| novoEstado.equalsIgnoreCase("Em curso") || novoEstado.equalsIgnoreCase("Aguarda pe�as")
						|| novoEstado.equalsIgnoreCase("Aguarda levantamento")
						|| novoEstado.equalsIgnoreCase("Entregue")) {

					if (novoEstado.equalsIgnoreCase("Or�amentado")) {
						System.out.println("O seu ticket foi mudado para o estado de Or�amentado");
						if (tickets.get(numTicket - 1).getCliente().getTipoCliente() == 1) {
							System.out.print("Introduza o preco das pe�as : ");
							precoPeca = IO.getFloat();
							System.out.print("Introduza o preco da m�o de obra : ");
							precoMaoObra = IO.getFloat();
							finalCliente.setPreco(precoPeca, precoMaoObra);
							System.out.println("Pre�o: " + String.format("%.2f", finalCliente.getPreco()) + "�");

						} else if (tickets.get(numTicket - 1).getCliente().getTipoCliente() == 2) {
							System.out.print("Introduza o preco das pe�as : ");
							precoPeca = IO.getFloat();
							System.out.print("Introduza o preco da m�o de obra : ");
							precoMaoObra = IO.getFloat();
							revendedorCliente.setPreco(precoPeca, precoMaoObra);
							System.out.println("Pre�o: " + String.format("%.2f", revendedorCliente.getPreco()) + "�");
						}

					} else if (novoEstado.equalsIgnoreCase("Invi�vel"))
						System.out.println("O seu ticket foi mudado para o estado de Invi�vel");

					else if (novoEstado.equalsIgnoreCase("Em curso"))
						System.out.println("O seu ticket foi mudado para o estado de Em Curso");

					else if (novoEstado.equalsIgnoreCase("Aguarda Pe�as"))
						System.out.println("O seu ticket foi mudado para o estado de Aguarda Pe�as");

					else if (novoEstado.equalsIgnoreCase("Aguarda Levantamento")) {
						System.out.println("O seu ticket foi mudado para o estado de Pronto (Aguarda Levantamento do Cliente)");
						if (tickets.get(numTicket - 1).getCliente().getTipoCliente() == 1) {
							System.out.print("Introduza o preco das pe�as : ");
							precoPeca = IO.getFloat();
							System.out.print("Introduza o preco da m�o de obra : ");
							precoMaoObra = IO.getFloat();
							finalCliente.setPreco(precoPeca, precoMaoObra);
							System.out.println("Pre�o: " + String.format("%.2f", finalCliente.getPreco()) + "�");

						} else if (tickets.get(numTicket - 1).getCliente().getTipoCliente() == 2) {
							System.out.print("Introduza o preco das pe�as : ");
							precoPeca = IO.getFloat();
							System.out.print("Introduza o preco da m�o de obra : ");
							precoMaoObra = IO.getFloat();
							revendedorCliente.setPreco(precoPeca, precoMaoObra);
							System.out.println("Pre�o: " + String.format("%.2f", revendedorCliente.getPreco()) + "�");
						}

					} else if (novoEstado.equalsIgnoreCase("Entregue") || novoEstado.equalsIgnoreCase("Fechado"))
						System.out.println("O seu ticket foi mudado para o estado de Fechado (Entregue ao Cliente)");

					System.out.print("Insira o(s) coment�rio(s) que pretende : ");
					newcomment = IO.getString();
					tickets.get(numTicket - 1).setComentarios(newcomment);
					System.out.println("=======================================================");
					System.out.println(ANSI_GREEN + "Ticket alterado com sucesso" + ANSI_RESET);
					System.out.println("=======================================================");
				} else
					System.out.println(ANSI_RED + "Estado n�o existe" + ANSI_RESET);
			}
			/*Ticket novo = new Ticket();
			novo.setComentarios(t.getComentarios());
			novo.setRef(t.getRef());
			novo.setNumTicket(t.getNumTicket());
			novo.setDataPrevista(t.getDataPrevista());
			novo.setDataAtualizacao(t.getDataAtualizacao());
			novo.setEstado(t.getEstado());
			novo.setDataRegisto(t.getDataRegisto());
			novo.setDescricao(t.getDescricao());
			historico.add(novo);
			System.out.println(historico);
			 */
		}
	}
	private static void estadosTicket() {
		System.out.println("===========================================");
		System.out.println("== ESTADOS POSS�VEIS NUM PEDIDO (TICKET) ==");
		System.out.println("Registado | Or�amentado |  Invi�vel |  Em Curso");
		System.out.println("Aguarda Pe�as |  Aguarda Levantamento do Cliente");
		System.out.println("Fechado / Entregue ao Cliente");
		System.out.println("=======================================================");
	}
	private static void menuTickets() {
		System.out.println(ANSI_BLUE + "===============MENU TICKETS============================" + ANSI_RESET);
		System.out.println("1 - Criar Ticket | 2 - Listar Tickets");
		System.out.println("3 - Alterar estado | 4 - Eliminar Ticket");
		System.out.println(ANSI_BLUE + "=======================================================" + ANSI_RESET);
	}
	private static void addTicket() {
		Ticket t1 = new Ticket();
		String comments;
		t1.setEstado("Registado");
		System.out.print("insira comentarios : ");
		comments = IO.getString();
		t1.setComentarios(comments);
		t1.setDataRegisto(String.valueOf(data));
		t1.setDataPrevista(String.valueOf(entregaPrevista));
		System.out.println("Criou o ticket com o n�mero " + t1.getNumTicket());
		int nCli, verficaNum = 0;
		do {
			System.out.print("Insira o n�mero do cliente: ");
			nCli = IO.getInt();
			for (Cliente x : clientes)
				if (nCli == x.getNumCliente()) {
					t1.setCliente(x);
					verficaNum = 1;
				}
			if (verficaNum == 0)
				System.out.println(ANSI_RED + "Esse n�mero de cliente n�o existe. Por favor volte a inserir" + ANSI_RESET);
		} while (verficaNum == 0);
		tickets.add(t1);
		historico.add(t1);
		System.out.println("=======================================================");
	}
//==========CLIENTES=======================
	private static void eliminarDados() {
		int verifica1 = 0;
		System.out.println("Que cliente quer eliminar");
		System.out.print("Fa�a a pesquisa pelo NIF: ");
		int elimina = IO.getInt();
		int ticketEncontrado = 0;
		for (Ticket t : tickets)
			if (t.getCliente().getNif() == elimina)
				ticketEncontrado = 1;
		if (ticketEncontrado == 0) {
			for (int i = 0; i < clientes.size(); i++)
				if (clientes.get(i).getNif() == elimina) {
					System.out.println(clientes.get(i));
					clientes.remove(i);
					System.out.println(ANSI_GREEN + "Cliente eliminado com sucesso!" + ANSI_RESET);
					verifica1 = 1;
				}
			if (verifica1 == 0)
				System.out.println(ANSI_RED + "N�o foi encontrado nenhum cliente com esse NIF!" + ANSI_RESET);
		} else
			System.out.println(ANSI_RED + "N�o podes eliminar esse cliente porque j� tem um ticket associado!" + ANSI_RESET);
	}
	private static void alterarDados() {
		int verficaNum = 0;
		do {
			System.out.print("Que n�mero de cliente quer alterar: ");
			int op = IO.getInt();
			for (Cliente x : clientes)
				if (x.getNumCliente() == op) {
					verficaNum = 1;
					System.out.println("=======================================================");
					System.out.println(x);
					System.out.println("=======================================================");
					System.out.println("1 - Nome | 2 - Nif | 3 - Morada | 4 - Telem�vel | 5 - Email | 0 - Menu principal");
					System.out.print("Insira a op��o: ");
					int op2 = IO.getInt();
					switch (op2) {
						case 1:
							System.out.println("=======================================================");
							System.out.println("Nome de cliente: " + x.getNome());
							System.out.print("Novo nome: ");
							String novoNome = IO.getString();
							x.setNome(novoNome);
							System.out.println(ANSI_GREEN + "Nome do cliente alterado com sucesso" + ANSI_RESET);
							break;
						case 2:
							System.out.println("=======================================================");
							System.out.println("Nif: " + x.getNif());
							System.out.print("Novo Nif: ");
							int novoNif = IO.getInt();
							x.setNif(novoNif);
							System.out.println(ANSI_GREEN + "Nif do cliente alterado com sucesso" + ANSI_RESET);
							break;
						case 3:
							System.out.println("=======================================================");
							System.out.println("Morada: " + x.getMorada());
							System.out.print("Nova morada: ");
							String novaMorada = IO.getString();
							x.setMorada(novaMorada);
							System.out.println(ANSI_GREEN + "Morada do cliente alterado com sucesso" + ANSI_RESET);
							break;
						case 4: //telemovel
							System.out.println("=======================================================");
							System.out.println("Telem�vel: " + x.getTelef());
							System.out.print("Novo Telem�vel: ");
							int novoTele = IO.getInt();
							x.setTelef(novoTele);
							System.out.println(ANSI_GREEN + "Telem�vel do cliente alterado com sucesso" + ANSI_RESET);
							break;
						case 5: // email
							System.out.println("=======================================================");
							System.out.println("Email: " + x.getEmail());
							System.out.print("Novo email: ");
							String novoEmail = IO.getString();
							x.setEmail(novoEmail);
							System.out.println(ANSI_GREEN + "Email do cliente alterado com sucesso" + ANSI_RESET);
							break;
						default:
							break;
					}
				}
			if (verficaNum == 0) {
				System.out.println(ANSI_RED + "N�o existe cliente com esse n�mero" + ANSI_RESET);
				verficaNum = 0;
			}
		} while (verficaNum == 0);
	}
	private static void listaClientes() {
		int dadoscliente; //para entrar no 2� switch para procurar dados do cliente
		int nif; //nif para procurar o cliente
		String nome; //nome para procurar o cliente
		int numCliente, verficaNum = 0; //numCliente para procurar o cliente
		do {
			System.out.println(" 1 - NIF | 2 - Nome | 3 - N�mero de Cliente | 4 - Listar todos os clientes ");
			System.out.print("Introduza a op��o que pretende : ");
			dadoscliente = IO.getInt();
			switch (dadoscliente) {
				case 1: //Procura por NIF
					System.out.print("Insira o NIF que pretende procurar : ");
					nif = IO.getInt();
					for (Cliente c : clientes)
						if (c.getNif() == nif) {
							System.out.println(c);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o h� registo desse NIF" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 2: //Procura por NOME
					System.out.print("Insira o NOME que pretende procurar : ");
					nome = IO.getString();
					for (Cliente c : clientes)
						if (c.getNome().equalsIgnoreCase(nome)) {
							System.out.println(c);
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o registo desse nome de cliente" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 3: //Procura por N�mero de Cliente
					System.out.print("Insira o N�mero de Cliente que pretende procurar : ");
					numCliente = IO.getInt();
					for (int i = 0; i < clientes.size(); i++)
						if (clientes.get(i).getNumCliente() == numCliente) {
							System.out.println(clientes.get(i)); //IMPRIME cliente na posicao I (for) do array completo
							verficaNum = 1;
						}
					if (verficaNum == 0) {
						System.out.println(ANSI_RED + "N�o ha registo desse n�mero de cliente" + ANSI_RESET);
						verficaNum = 0;
					}
					break;
				case 4: // LISTAR TODOS OS CLIENTES
					for (Cliente c : clientes)
						System.out.println(c);
					verficaNum = 1;
					break;
				default:
					System.out.println(ANSI_RED + "Op��o inv�lida!" + ANSI_RESET);
					break;
			}
		} while (verficaNum == 0);
	}
	private static void addCliente() {
		System.out.println("1 - Cliente Final | 2 - Cliente Revendedor");
		System.out.println("============================================");
		System.out.print("Insira a sua op��o : ");
		int tipoCliente = IO.getInt();
		if (tipoCliente == 1) {
			Final clienteFinal = new Final();
			System.out.print("Nome: ");
			clienteFinal.setNome(IO.getString());
			System.out.print("Nif: ");
			clienteFinal.setNif(IO.getInt());
			System.out.print("Telem�vel: ");
			clienteFinal.setTelef(IO.getInt());
			System.out.print("email: ");
			clienteFinal.setEmail(IO.getString());
			System.out.print("Morada: ");
			clienteFinal.setMorada(IO.getString());
			clientes.add(clienteFinal);
			System.out.println(ANSI_GREEN + "Cliente adicionado com sucesso" + ANSI_RESET);
		} else if (tipoCliente == 2) {
			Revendedor clienteRevendedor = new Revendedor();
			System.out.print("Nome Cliente Revendedor: ");
			clienteRevendedor.setNome(IO.getString());
			System.out.print("Nif: ");
			clienteRevendedor.setNif(IO.getInt());
			System.out.print("Telem�vel: ");
			clienteRevendedor.setTelef(IO.getInt());
			System.out.print("email: ");
			clienteRevendedor.setEmail(IO.getString());
			System.out.print("Morada: ");
			clienteRevendedor.setMorada(IO.getString());
			clientes.add(clienteRevendedor);
			System.out.println(ANSI_GREEN + "Cliente adicionado com sucesso" + ANSI_RESET);
		}
	}
	private static int menuCliente() {
		System.out.println(ANSI_BLUE + "=================== M E N U =========================" + ANSI_RESET);
		System.out.println("1 - Criar Cliente ");
		System.out.println("2 - Mostrar cliente por par�metro");
		System.out.println("3 - Alterar clientes");
		System.out.println("4 - Apagar Cliente");
		System.out.println("5 - Menu Tickets");
		System.out.println("0 - Sair");
		System.out.println(ANSI_BLUE + "=======================================================" + ANSI_RESET);
		System.out.print("Insira a op��o: ");
		int op = IO.getInt();
		return op;
	}
}

