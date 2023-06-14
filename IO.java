package TrabalhoFinalV1;

import java.util.Scanner;

public class IO {
	static final long serialVersionUID = 0;
		public static int getInt() {
			Scanner in = new Scanner(System.in);
			try
			{
				return Integer.parseInt(in.nextLine());
			}
			catch (Exception e)
			{
				System.out.println("Erro: Foi assumido valor zero...");
				return 0;
			}
		}
		public static float getFloat() {
			Scanner in = new Scanner(System.in);
			try
			{
				return Float.parseFloat(in.nextLine());
			}
			catch (Exception e)
			{
				System.out.println("Erro: Foi assumido valor zero...");
				return 0f;
			}
		}
		public static String getString() {
			Scanner in = new Scanner(System.in);
			try
			{
				return  in.nextLine();
			}
			catch (Exception e)
			{
				System.out.println("Erro: Foi assumido valor string vazia...");
				return "";
			}
		}
	}

