import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Buscador {
	
	static int comparacoes = 0;
	static long startTime,stopTime;
	static boolean debug;

	public static void main(String[] args) {
		
		try{
			//Inicializa variaveis de entrada e saida
			String config = args[0];
			// Le o arquivo conforme o caminho passado
			File file = new File(config);
			FileReader fileReader = new FileReader(file);
			Scanner scanner = new Scanner(fileReader);
			String algoritmo = scanner.next();
			String palavras = scanner.next();
			String saida = scanner.next();
			
			//Cria lista das palavras
			// Caminho Base para concatenar com o nome do arquivo
			String base = file.getAbsolutePath().replace(file.getName(), "");
			Scanner scannerWordList = new Scanner(new FileReader(base + palavras));
			
			int tam = scannerWordList.nextInt();
			String[] listaDePalavras = new String[tam];
			for(int i = 0; i<tam && scannerWordList.hasNext(); i++)
			{
				listaDePalavras[i] = scannerWordList.next();
			}
			
			/*Cria Matriz Busca para as palavras que serao buscadas
			*Utilizado matriz para guardar os resultados
			*	COLUNAS:
			*	0 - Palavra para buscar
			*	1 - Resultado - SIM ou NAO
			*	2 - Numero de comparacoes
			*	3 - Tempo gasto
			*/
			tam = scanner.nextInt();
			String[][] listaDePalavrasBusca = new String[tam][4];
			for(int i = 0; i<tam && scanner.hasNext(); i++)
			{
				listaDePalavrasBusca[i][0] = scanner.next();
			}
			
			//Seleciona e executa método de ordenação
			if (algoritmo.equals("seq"))
				buscaSeq(listaDePalavras,listaDePalavrasBusca);
			else if (algoritmo.equals("bin"))
				buscaBin(listaDePalavras,listaDePalavrasBusca);
			else if (algoritmo.equals("hash1"))
				preparaHash(listaDePalavras,listaDePalavrasBusca,1);
			else if (algoritmo.equals("hash2"))
				preparaHash(listaDePalavras,listaDePalavrasBusca,2);
			
			// Saida
			FileWriter fw = new FileWriter(saida);
			PrintWriter pw = new PrintWriter(fw);
			for(int i = 0; i < tam; i++)
			{
				pw.println(listaDePalavrasBusca[i][0] + " " +
						listaDePalavrasBusca[i][1] + " " +
						listaDePalavrasBusca[i][2] + " " +
						listaDePalavrasBusca[i][3]);
			}
			pw.close();
			fw.close();
			scanner.close();
			scannerWordList.close();
		}
		catch(ArrayIndexOutOfBoundsException | IOException e)
		{
			System.out.println("Erro! Não há arquivo de entrada");
		}
	}
		
	public static void heapSort(String[] arranjo){
		constroiHeap(arranjo);
		int n = arranjo.length;

		for(int i = arranjo.length -1; i > 0; i--)
		{
			troca(arranjo, i, 0);
			heapfica(arranjo, 0, --n);
		}
	}
	
	private static void heapfica(String[] arranjo, int pos, int n)
	{
		int maximo;
		int direita = 2 * pos + 2;
		int esquerda = 2 * pos + 1;

		comparacoes++;
		if((esquerda < n) && arranjo[esquerda].compareTo(arranjo[pos]) > 0)
		{
			
			maximo = esquerda;
		}
		else
		{
			maximo = pos;
		}
		comparacoes++;
		if(direita < n && arranjo[direita].compareTo(arranjo[maximo]) > 0){
			maximo = direita;
		}
		if(maximo != pos)
		{
			troca(arranjo, pos, maximo);
			heapfica(arranjo, maximo, n);
		}
	}
	
	private static void troca(String [] arranjo, int  j, int proxj){
		String aux = arranjo[j];
		arranjo[j] = arranjo[proxj];
		arranjo[proxj] = aux;
	}
	
	private static void constroiHeap(String[] arranjo){
		for(int i = arranjo.length/2 - 1; i >= 0; i--)
		{
			heapfica(arranjo, i, arranjo.length);
		}
	}
	
	// Metodo para auxiliar conversao Int para String
	public static String giveStr(int value) {
		return String.valueOf(value);
	}
	
	// Metodo para auxiliar conversao Long para String
	public static String giveStr(double value) {
		return String.valueOf(value);
	}
	
	/* Busca sequencial 
	 * com calculo do tempo e iteracoes
	 */
	public static void buscaSeq(String[] lista, String[][] palavrasBusca) {
		int tamanhoBuscas = palavrasBusca.length;
		for (int i = 0; i < tamanhoBuscas;i++) {
			// Seleciona palavra a ser buscada
			String palavra = palavrasBusca[i][0];
			int tamanhoLista = lista.length;
			
			// Inicia contagem de tempo
			contaTempo(true);
			int numComparacoes = 0;
			String encontrado = "NAO";
			for (int z = 0;z < tamanhoLista;z++) {
				
				numComparacoes++;
				if (palavra.compareTo(lista[z]) == 0) {
					// Palavra encontrada
					encontrado = "SIM";
					break;
				}
				
			}
			
			/* Calcula tempo de execucao
			*  Atencao em modo debug pode ocorrer alteracoes no tempo real de execucao
			*/
			contaTempo(false);
			double tempoDeExecucao = stopTime / (double) 1000000;
			
			//Preenche Linha da matriz
			palavrasBusca[i][1] = encontrado;
			palavrasBusca[i][2] = giveStr(numComparacoes);
			palavrasBusca[i][3] = giveStr(tempoDeExecucao);
		}
	}
	
	// Metodo para contar o tempo de execucao
	public static void contaTempo(boolean estado) {
		if (estado) {
			startTime = System.nanoTime();
		} else {
			stopTime = System.nanoTime() - startTime;
		}
	}
	
	/* Busca binaria 
	 * com calculo do tempo e iteracoes
	 */
	public static void buscaBin(String[] lista, String[][] palavrasBusca) {
		heapSort(lista);
		int tamanhoBuscas = palavrasBusca.length;	
		for(int i=0;i<tamanhoBuscas;i++){
			String word = palavrasBusca[i][0];
			int tamList = lista.length;	
			contaTempo(true);
			String achou = "NAO";
			int numComp = 0;
			
			int ini=0;
			int fim = tamList-1;
			int meio = (ini+fim)/2;
			boolean find=false;
							
			while(find==false && fim>=ini){
				numComp++;	
				if(word.compareTo(lista[meio]) == 0){
						achou = "SIM";
						find=true;
						break;
					}			
					else if(word.compareTo(lista[meio])<0){
						fim=meio-1;
					}
					else if(word.compareTo(lista[meio])>0){
						ini=meio+1;
					}
					meio=(ini+fim)/2;
			}
			
			contaTempo(false);
			double tempoDeExecucao = stopTime / (double) 1000000;			
			
			palavrasBusca[i][1] = achou;
			palavrasBusca[i][2] = giveStr(numComp);
			palavrasBusca[i][3] = giveStr(tempoDeExecucao);
		}
	}
	
	/**
	 *  @param numberHash -> 1 - Hash1 // 2 - Hash2
	 **/
	public static void preparaHash(String[] lista, String[][] palavrasBusca, int numberHash) {
		// Cria vetor principal
		int slotsSize = lista.length / 10;
		
		@SuppressWarnings("unchecked")
		ArrayList<String>[] hashTable = new ArrayList[slotsSize];
		
		// Popula
		for (String palavra : lista) {
			int calc = calculaHash(palavra, numberHash, slotsSize);
			
			if (hashTable[calc] == null) {
				// Nova
				ArrayList<String> newList = new ArrayList<String>();
				newList.add(palavra);
				
				hashTable[calc] = newList;
			} else {
				// Conflito
				ArrayList<String> oldList = hashTable[calc];
				oldList.add(palavra);
			}
		}
		
		// Procura palavras
		int tamanhoBuscas = palavrasBusca.length;
		for (int i = 0; i < tamanhoBuscas;i++) {
			// Seleciona palavra a ser buscada
			String palavra = palavrasBusca[i][0];
			
			// Inicia contagem de tempo
			contaTempo(true);
			int numComparacoes = 0;
			String achou = "NAO";
			
			// Calcula Hash
			int hash = calculaHash(palavra, numberHash, slotsSize);
			
			if (hashTable[hash] != null) {
				
				for (String p : (ArrayList<String>) hashTable[hash]) {
					numComparacoes++;
					
					if (p.compareTo(palavra) == 0) {
						// Achou
						achou = "SIM";
						break;
					}
				}
			}
			
			contaTempo(false);
			double tempoDeExecucao = stopTime / (double) 1000000;			
			
			palavrasBusca[i][1] = achou;
			palavrasBusca[i][2] = giveStr(numComparacoes);
			palavrasBusca[i][3] = giveStr(tempoDeExecucao);
		}
	
	}
	
	public static int calculaHash(String palavra, int numberHash, int size) {
		switch (numberHash) {
		case 1:
			/* HASH 1 */
			// Get chars
			int p0 = 0;
			int p1 = 0;
			int p2 = 0;
			
			try {
				p0 = (int) palavra.charAt(0);
				p1 = (int) palavra.charAt(1);
				p2 = (int) palavra.charAt(2);
			} catch (Exception e) {}
			
			/* Calc Hash1 
			(p0 * 2^16 + p1 * 2^8 + p2) % n/10 */
			int calc = (p0 * (int) Math.pow(2, 16) + p1 * (int) Math.pow(2, 8) + p2) % size;
			
			return calc;
		case 2:
			/* HASH 2 */
			// Get chars
			int h2p0 = 0;
			int h2p1 = 0;
			int h2p2 = 0;
			int h2p3 = 0;
			int h2p4 = 0;
			
			try {
				h2p0 = (int) palavra.charAt(0);
				h2p1 = (int) palavra.charAt(1);
				h2p2 = (int) palavra.charAt(2);
				h2p3 = (int) palavra.charAt(3);
				h2p4 = (int) palavra.charAt(4);
			} catch (Exception e) {}
			
			/* Calc Hash2 
			(p0 * 2^16 + p1 * 2^12 + p2 * 2^8 + p3 * 2^4 + p4) % n/10 */
			int calch2 = (h2p0 * (int) Math.pow(2, 16) + h2p1 * (int) Math.pow(2, 12) + h2p2 * (int) Math.pow(2, 8) + h2p3 * (int) Math.pow(2, 4) + h2p4) % size;
			return calch2;
		}
		
		return -1;
	}
}
