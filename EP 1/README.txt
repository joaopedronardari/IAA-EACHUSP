README

EP1 IAA - Turma 94
Aluno: Joao Pedro Nardari dos Santos
Numero USP - 8623865


** Para melhor visualização deste arquivo README 
(Sete quebra de linha automática para o seu visualizador/editor padrão de texto)

*****************************************
INDICE:
	1. CLASSES PERTENCENTES AO EXERCICIO PROGRAMA
		1.1 DRAW.JAVA -> IMAGEM.JAVA
		1.2 READER.JAVA
	
	2. COMO COMPILAR E EXECUTAR o EXERCICIO PROGRAMA

*****************************************

1. CLASSES PERTENCENTES AO EXERCICIO PROGRAMA

Este exercício programa 1 da matéria Introdução a Análise de Algoritmos
possui 3 Classes: a classe Draw.java herdada da já passada classe Imagem.java e a classe Reader.java

1.1 DRAW.JAVA -> IMAGEM.JAVA

A classe de nome Draw.java, como já dita anteriormente, herda 	caracteristicas da classe Imagem.java. Possuindo um atributo
diferente de sua classe pai, o atributo initialColor com seus 	respectivos metodos getInitialColor() e setInitialColoyr(int initialColor)
utilizados para reconhecimento da cor do pixel inicial no 	preenchimento de cor em determinada área do desenho. Este atributo é 	utilizado
pelo método paintArea(int x,int y) que tem como função setar a cor dos pixels de maneira recursiva caso a cor inicial seja a encontrada no 	atributo initialColor.
Além destes já expostos anteriormente, temos o método kochCurve(int 	x1,int y1,int x2, int y2, int l) o qual tem como função
desenhar a curva de koch quadrada entre dois pontos recursivamente até alcançar o limiar. 

1.2 READER.JAVA

A classe de nome READER.JAVA, tem como função a leitura dos parametros (args), leitura do .txt e sua identificação de comandos.
Possui como atributos estáticos as constantes com o nome dos comandos 	para respectiva chamada de metodo. Tendo o método 
identifyCommand(String[] lineParams)  que conforme a constante e o 	número de parametros passados no .txt chama determinado método da classe Draw.java. Como
os parametros passados são de maioria do tipo inteiro, foi criado um 	método auxiliar convertParam(String param) para converter
as strings de parametro em inteiro.

Por ser a classe principal do exercício programa possui o método main, o qual recebe os parametros de caminho do .txt e o nome
do arquivo de imagem que será gerado (recomendassse .png). Neste é 	executado um BufferedReader para leitura do .txt que após a execução
de todos os comandos é fechado e a imagem é salva, sendo esta 	localizada na raiz em que está os códigos fonte do exercicio programa.

*************************************************

2. COMO COMPILAR E EXECUTAR O EXERCICIO PROGRAMA

Para compilar o exercício programa execute os comando seguintes comandos no terminal de seu sistema operacional, 
para certificação de que todos .java estarão compilados para execução:
	javac Imagem.java
	javac Draw.java
	javac Reader.java

Para realizar a execução via terminal:

java -Xss100m Reader [CAMINHO DO .TXT] [NOME PARA GERAR].png

** É de extrema importancia não esquecer do parametro -Xss100m, este tem como função reservar 100m para a pilha de execução. 
E é necessário por a recursividade do exercício programa alcançar altas profundidades durante sua execução!

Após a execucação e caso você tenha passado corretamente os parametros a imagem será gerada com o nome especificado na raiz 
(pasta localizada os fontes do exercício programa)

************************************************

