README

EP1 IAA - Turma 94
Aluno: Joao Pedro Nardari dos Santos
Numero USP - 8623865


** Para melhor visualiza��o deste arquivo README 
(Sete quebra de linha autom�tica para o seu visualizador/editor padr�o de texto)

*****************************************
INDICE:
	1. CLASSES PERTENCENTES AO EXERCICIO PROGRAMA
		1.1 DRAW.JAVA -> IMAGEM.JAVA
		1.2 READER.JAVA
	
	2. COMO COMPILAR E EXECUTAR o EXERCICIO PROGRAMA

*****************************************

1. CLASSES PERTENCENTES AO EXERCICIO PROGRAMA

Este exerc�cio programa 1 da mat�ria Introdu��o a An�lise de Algoritmos
possui 3 Classes: a classe Draw.java herdada da j� passada classe Imagem.java e a classe Reader.java

1.1 DRAW.JAVA -> IMAGEM.JAVA

A classe de nome Draw.java, como j� dita anteriormente, herda 	caracteristicas da classe Imagem.java. Possuindo um atributo
diferente de sua classe pai, o atributo initialColor com seus 	respectivos metodos getInitialColor() e setInitialColoyr(int initialColor)
utilizados para reconhecimento da cor do pixel inicial no 	preenchimento de cor em determinada �rea do desenho. Este atributo � 	utilizado
pelo m�todo paintArea(int x,int y) que tem como fun��o setar a cor dos pixels de maneira recursiva caso a cor inicial seja a encontrada no 	atributo initialColor.
Al�m destes j� expostos anteriormente, temos o m�todo kochCurve(int 	x1,int y1,int x2, int y2, int l) o qual tem como fun��o
desenhar a curva de koch quadrada entre dois pontos recursivamente at� alcan�ar o limiar. 

1.2 READER.JAVA

A classe de nome READER.JAVA, tem como fun��o a leitura dos parametros (args), leitura do .txt e sua identifica��o de comandos.
Possui como atributos est�ticos as constantes com o nome dos comandos 	para respectiva chamada de metodo. Tendo o m�todo 
identifyCommand(String[] lineParams)  que conforme a constante e o 	n�mero de parametros passados no .txt chama determinado m�todo da classe Draw.java. Como
os parametros passados s�o de maioria do tipo inteiro, foi criado um 	m�todo auxiliar convertParam(String param) para converter
as strings de parametro em inteiro.

Por ser a classe principal do exerc�cio programa possui o m�todo main, o qual recebe os parametros de caminho do .txt e o nome
do arquivo de imagem que ser� gerado (recomendassse .png). Neste � 	executado um BufferedReader para leitura do .txt que ap�s a execu��o
de todos os comandos � fechado e a imagem � salva, sendo esta 	localizada na raiz em que est� os c�digos fonte do exercicio programa.

*************************************************

2. COMO COMPILAR E EXECUTAR O EXERCICIO PROGRAMA

Para compilar o exerc�cio programa execute os comando seguintes comandos no terminal de seu sistema operacional, 
para certifica��o de que todos .java estar�o compilados para execu��o:
	javac Imagem.java
	javac Draw.java
	javac Reader.java

Para realizar a execu��o via terminal:

java -Xss100m Reader [CAMINHO DO .TXT] [NOME PARA GERAR].png

** � de extrema importancia n�o esquecer do parametro -Xss100m, este tem como fun��o reservar 100m para a pilha de execu��o. 
E � necess�rio por a recursividade do exerc�cio programa alcan�ar altas profundidades durante sua execu��o!

Ap�s a execuca��o e caso voc� tenha passado corretamente os parametros a imagem ser� gerada com o nome especificado na raiz 
(pasta localizada os fontes do exerc�cio programa)

************************************************

