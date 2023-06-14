package TrabalhoFinalV1;

import java.io.*;
import java.util.ArrayList;

import static TrabalhoFinalV1.Run.clientes;
import static TrabalhoFinalV1.Run.tickets;

public class Backup {
	static final long serialVersionUID = 0;
	public static void saveCustomers(ArrayList<Cliente> clientes, String nomeFicheiro) {
		File f = new File(nomeFicheiro);
		try {
			f.createNewFile();//cria novo ficheiro
			ObjectOutputStream ficheiro = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));
			ficheiro.writeObject(clientes);
			System.out.println("Backup de clientes executado com sucesso..." + f.getAbsolutePath());
			ficheiro.flush();
			ficheiro.close();
		} catch (IOException e) {
			e.printStackTrace();//Se a operação der erro mostra o erro...
		}
	}
	public static ArrayList<Cliente> readCustomers(String nomeFicheiro) {
		File ficheiro = new File(nomeFicheiro);
		if (!ficheiro.exists())
			ficheiro = new File(nomeFicheiro);
		else {
			ObjectInputStream f;
			try {
				f = new ObjectInputStream(new FileInputStream(nomeFicheiro));
				clientes = (ArrayList<Cliente>) f.readObject();
				System.out.println("Restauro de dados feito com sucesso.");
				return clientes;
			} catch (IOException e) {
				System.out.println("Caminho do Ficheiro: "+ficheiro.getAbsolutePath());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void saveTickets(ArrayList<Ticket> tickets, String nomeFicheiroTicket) {
		File t = new File(nomeFicheiroTicket);
		try {
			t.createNewFile();//cria novo ficheiro
			ObjectOutputStream ficheiro = new ObjectOutputStream(new FileOutputStream(nomeFicheiroTicket));
			ficheiro.writeObject(tickets);
			System.out.println("Backup de tickets executado com sucesso..." + t.getAbsolutePath());
			ficheiro.flush();
			ficheiro.close();
		} catch (IOException e) {
			e.printStackTrace();//Se a operação der erro mostra o erro...
		}
	}
	public static ArrayList<Ticket> readTickets(String nomeFicheiroTicket) {
		File ficheiro = new File(nomeFicheiroTicket);
		if (!ficheiro.exists())
			ficheiro = new File(nomeFicheiroTicket);
		else {
			ObjectInputStream t;
			try {
				t = new ObjectInputStream(new FileInputStream(nomeFicheiroTicket));
				tickets = (ArrayList<Ticket>) t.readObject();
				System.out.println("Restauro de dados feito com sucesso.");
				return tickets;
			} catch (IOException e) {
				System.out.println("Caminho do Ficheiro: "+ficheiro.getAbsolutePath());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
