package Project;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.*;

public class GameRandom extends Thread{

	static short victories;
	static short defeats;

	public static void main(String[] args) throws IOException {
		
		String path = "points.txt";	
		File arqu = new File(path);

		if (!arqu.exists()){
			try (PrintWriter pw = new PrintWriter(arqu)) {
				arqu.createNewFile();
				pw.print("0;0;Developed by R1TKILL");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	    Scanner input = new Scanner(in);
		Random random = new Random();

	    try {
			cls();
			out.print("\n\n\n\n\t\t\t\t  Welcome to Game \n\t\t\t       ***Random Number!!***\n\n\n\t");
			out.print("\n\tDeveloped by R1TKILL.\t\t\t\tVersion: 1.0.8");
			out.print("\n\n\n\t 1 - Play \n\t 2 - Records \n\t 3 - Reset \n\t 4 - Exit\n\n\n escolha: ");
			int choice = input.nextInt();

			if(choice == 1)
			{
				Game(random, input);
				write(arqu);
				GameRandom.main(null);
			}
			else if(choice == 2)
			{
				try {
					cls();
					String contents = read(arqu);
					out.println("\n\n\n\n\n\n\n\n\t\tVitórias: " + contents.split(";")[0]);
					out.println("\n\t\tDerrotas: " + contents.split(";")[1]);
					out.println("\n\t\tPressione >> Enter << para voltar ao Menu Principal. \n");
					in.read();
					GameRandom.main(null);
				}
				catch (ArrayIndexOutOfBoundsException aioobe){
				cls();
				out.println("Leitor não conseguiu ler do arquivo!!! \n\n Erro do tipo: " + aioobe.getMessage());
				in.read();
				exit(0);
			}
			}
			else if(choice == 3){
				cls();
				out.print("\n\n\n\n\n\n\n\n\tTem certeza que deseja resetar o placar[s/n]?: ");
				char confirm = input.next().charAt(0);
				if(confirm == 's' || confirm == 'S'){
					arqu.delete();
					arqu.createNewFile();
					try {
						FileWriter fwrite = new FileWriter(arqu, true);
						PrintWriter pwrite = new PrintWriter(fwrite);
						pwrite.print(0 + ";" + 0 + ";" + "Developed by RITKILL" + "\n");
						fwrite.close();
						pwrite.close();
					} catch (Exception e) {
						cls();
						out.println("\n\n\t\t\tErro ao Resetar o arquivo!!!");
						out.println("\n\t\t\tErro do tipo: " + e.getMessage());
						in.read();
						exit(0);
					}
					GameRandom.main(null);
				}else{
					GameRandom.main(null);
				}
			}
			else if(choice == 4)
			{
				input.close();
				cls();
					try {
						out.println("\n\n\n\n\n\n\n\n\n\n\t\t\t  Fechando Aplicação em 3");
						sleep(1000);
						cls();
						out.println("\n\n\n\n\n\n\n\n\n\n\t\t\t  Fechando Aplicação em 2");
						sleep(1000);
						cls();
						out.println("\n\n\n\n\n\n\n\n\n\n\t\t\t  Fechando Aplicação em 1");
						sleep(1000);
						cls();
					}
					catch (InterruptedException ie){
						out.println("\n\n\n\n\n\n\n\n\n\t\t  Erro Fechando Aplicação!!!");
						exit(0);
					}
				exit(0);
			}
			else {
				cls();
				GameRandom.main(null);
			}
	    }
	    catch(IOException e) {
			cls();
	        out.println("Ocorreu um Erro do tipo: " + e.getMessage());
			in.read();
			out.println("Pressione >> Enter << para continuar.");
			exit(0);
	    }
	}


	public static void Game(Random random, Scanner input) throws IOException {
		
		short value_random = (short) random.nextLong();
		int lifes = 20;
		boolean attempt = false;
		int chosen_value;

		cls();
		while(!attempt)
		{
			out.print("\n\n\n\n\n\t\tInsira um valor: ");
			chosen_value = input.nextInt();

			if(chosen_value == value_random)
			{
				cls();
				out.println("\n\n\n\n\n\n\n\n\n\t\t\t   Parabéns Você Acertou!!!");
				in.read();
				victories++;
				attempt = true;
			}
			else if (chosen_value > value_random)
			{
				if(lifes == 1) {
					cls();
					out.println("\n\n\n\n\n\n\n\n\n\t\t\t   Que pena você Perdeu.");
					in.read();
					defeats++;
					break;
				}

				cls();
				out.println("\n\n\n\tO valor " + chosen_value + " é maior do que o numero escolhido.\n");
				--lifes;
				out.println("\tagora lhe resta apenas " + lifes + " vidas.");
			}
			else
			{
				if(lifes == 1) {
					cls();
					out.println("\n\n\n\n\n\n\n\n\n\t\t\t   Que pena você Perdeu.");
					in.read();
					defeats++;
					break;
				}

				cls();
				out.println("\n\n\n\tO valor " + chosen_value + " é menor do que o numero escolhido.\n");
				--lifes;
				out.println("\tagora lhe resta apenas " + lifes + " vidas.");
			}
		}
	}

	public static void write(File file) throws IOException {
		try {
			List<String> list = Files.readAllLines(file.toPath());
			file.delete();
			file.createNewFile();
			try {
				FileWriter fwrite = new FileWriter(file, true);
				PrintWriter pwrite = new PrintWriter(fwrite);
				int concat1 = (victories + Integer.parseInt(list.get(0).split(";")[0]));
				int concat2 = (defeats + Integer.parseInt(list.get(0).split(";")[1]));
				out.println();
				pwrite.print(concat1 + ";" + concat2 + ";" + "Developed by RITKILL" + "\n");
				victories = 0;
				defeats = 0;
				fwrite.close();
				pwrite.close();
			} catch (Exception e) {
				cls();
				out.println("\n\n\t\t\tErro ao Gravar arquivo!!!");
				out.println("\n\t\t\tErro do tipo: " + e.getMessage());
				in.read();
				exit(0);
			}
		}
		catch (FileNotFoundException fnfe){
			cls();
			out.println("\n\n\t\t\tArquivo não existe!!!");
			in.read();
			exit(0);
		}
		catch (NumberFormatException nfe){
			cls();
			out.println("\n\n\t\t\tProblemas na conversão do arquivo txt para o formato numerico!!");
			out.println("\n\t\t\tErro do tipo: " + nfe.getMessage());
			in.read();
			exit(0);
		}
	}

	public static String read(File arqu) throws IOException {
		String contents = " ";
		try {
			FileReader fread = new FileReader(arqu);
			BufferedReader bread = new BufferedReader(fread);
			String line = " ";
			try {
				line = bread.readLine();
				while (line != null){
					contents += line;
					line = bread.readLine();
				}
			}
			catch (Exception e) {
				cls();
				bread.close();
				out.println("\n\n\n\n\n\n\t\tErro ao ler o arquivo!!! \n\n Erro do tipo: " + e.getMessage());
				in.read();
				exit(0);
			}
			fread.close();
			bread.close();
		}
		catch (FileNotFoundException fnfe){
			cls();
			out.println("\n\n\n\n\n\n\n\t\tArquivo não existe!!! \n\n Erro do tipo: " + fnfe.getMessage());
			in.read();
			exit(0);
		}
		return contents;
	}

	public static void cls() throws IOException{
		try
	    {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				out.print("\033\143");		
	    }
	    catch (final Exception e)
	    {
	    	cls();
	        out.println("\n\n\n\n\n\n\n\t\tErro ao tentar limpar.\n\n");
	        in.read();
	        exit(0);
	    }
	}
}
