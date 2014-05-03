import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Ordenador {
	static int comparacoes = 0;
	static long startTime,stopTime;
	static boolean debug;
	public static void main(String[] args) {
		try{
			//Inicializa variaveis de entrada e saida
			String config = args[0];
			File file = new File(config);
			FileReader fileReader = new FileReader(file);
			Scanner scanner = new Scanner(fileReader);
			String algoritmo = scanner.next();
			debug = scanner.nextBoolean();
			String palavras = scanner.next();
			String saida = scanner.next();
			
			//Cria lista das palavras
			// Caminho Base para concatenar com o nome do arquivo
			String base = file.getAbsolutePath().replace(file.getName(), "");
			scanner = new Scanner(new FileReader(base + palavras));
			int tam = scanner.nextInt();
			String[] listaDePalavras = new String[tam];
			for(int i = 0; i<tam && scanner.hasNext(); i++)
			{
				listaDePalavras[i] = scanner.next();
			}
			
			contaTempo(debug);
			
			//Imprime lista de palavras
			//log(listaDePalavras);
			
			//Seleciona e executa método de ordenação
			if (algoritmo.equals("selection"))
				selectSort(listaDePalavras);
			else if (algoritmo.equals("heap"))
				heapSort(listaDePalavras);
			
			//Tempo
			/* Calcula tempo de execucao
			*  Atencao em modo debug pode ocorrer alteracoes no tempo real de execucao
			*/
			contaTempo(false);
			double tempoDeExecucao = stopTime / (double) 1000000;
			if(!debug)
				System.out.println(comparacoes + " " + tempoDeExecucao);
			
			//Imprime estado final
			//log(listaDePalavras);
			
			FileWriter fw = new FileWriter(saida);
			PrintWriter pw = new PrintWriter(fw);
			//pw.println(tam);
			for(int i = 0; i < tam; i++)
			{
				pw.println(listaDePalavras[i]);
			}
			scanner.close();
			pw.close();
			fw.close();
		}
		catch(ArrayIndexOutOfBoundsException | IOException e)
		{
			e.printStackTrace();
			System.out.println("Erro! Não há arquivo de entrada");
		}
	}
	
	//Debug
	public static void log(String [] listaDePalavras)
	{
		if(debug)
		{
			for(int i = 0; i<listaDePalavras.length; i++)
			{
				System.out.print(i>0?" ":"");
				System.out.print(listaDePalavras[i]);
			}
			System.out.println("");
		}
		
	}
	
	public static void contaTempo(boolean estado) {
		if (estado) {
			startTime = System.nanoTime();
		} else {
			stopTime = System.nanoTime() - startTime;
		}
	}
	
	//SelectionSort
	/*
	Ordenação por metódo SelectSort
	posMenorElemento é método auxiliar
	*/
	public static void selectSort(String[] arranjo)
	{
		for(int i = 0; i<arranjo.length-1; i++)
		{
			log(arranjo);
			int posMenorElemento = i;
			for(int j = i +1; j < arranjo.length; j++)
			{
				comparacoes++;
				if(arranjo[j].compareTo(arranjo[posMenorElemento]) < 0)
				{
					posMenorElemento = j;
				}
			}
			//comparacoes++;
			if(posMenorElemento != i)
			{
				String aux = arranjo[i];
				arranjo[i] = arranjo[posMenorElemento];
				arranjo[posMenorElemento] = aux; 
			}
			
		}
	}
	
	//HeapSort
	public static void heapSort(String[] arranjo)
	{
		log(arranjo);
		constroiHeap(arranjo);
		int n = arranjo.length;
		log(arranjo);
		for(int i = arranjo.length -1; i > 0; i--)
		{
			troca(arranjo, i, 0);
			heapfica(arranjo, 0, --n);
			log(arranjo);
		}
	}
	private static void heapfica(String[] arranjo, int pos, int n)
	{
		int maximo = 2 * pos + 1;
		int direita = maximo + 1;
		if(maximo < n)
		{
			comparacoes++;
			if(direita < n && arranjo[maximo].compareTo(arranjo[direita]) < 0)
			{

				maximo = direita;
			}
			comparacoes++;
			if(arranjo[maximo].compareTo(arranjo[pos]) > 0)
			{
				troca(arranjo, maximo, pos);
				heapfica(arranjo, maximo, n);
			}
		}
	}
	private static void troca(String [] arranjo, int  j, int proxj)
	{
		String aux = arranjo[j];
		arranjo[j] = arranjo[proxj];
		arranjo[proxj] = aux;
	}
	private static void constroiHeap(String[] arranjo)
	{
		for(int i = arranjo.length/2 - 1; i >= 0; i--)
		{
			heapfica(arranjo, i, arranjo.length);
		}
	}
}
