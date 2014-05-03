import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagem {

	protected int largura;
	protected int altura;
	
	private int [] imagem;
	private int cor;

	private static int valorInteiro(int r, int g, int b){

		return (r << 16) + (g << 8) + b;
	}

	protected static int min(int a, int b){

		if(a < b) return a;

		return b;
	}

	protected static int max(int a, int b){

		if(a > b) return a;

		return b;
	}

	protected static double max(double a, double b){

		if(a > b) return a;

		return b;
	}

	public Imagem(int l, int a, int r, int g, int b){

		largura = l;
		altura = a;
		imagem = new int[altura * largura];
		cor = valorInteiro(r, g, b);

		for(int i = 0; i < imagem.length; i++) imagem[i] = cor;
	}

	public void salva(String nomeArquivo){

		BufferedImage img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, largura, altura, imagem, 0, largura);

		try{
			ImageIO.write(img, "png", new File(nomeArquivo));	
		}
		catch(Exception e){
	
			System.out.println("Erro salvando imagem.");
			e.printStackTrace();
		}
	}

	public void setCor(int r, int g, int b){

		cor = valorInteiro(r, g, b);
	}

	public void setPixel(int x, int y){

		if(x >= 0 && x < largura && y >= 0 && y < altura){
		
			imagem[y * largura + x] = cor;
		}
	}

	public void setPixel(double x, double y){

		int ix = (int) Math.round(x);
		int iy = (int) Math.round(y);
	
		setPixel(ix, iy);
	}

	public int getPixel(int x, int y){

		return imagem[y * largura + x];
	}

	public void reta(double x1, double y1, double x2, double y2){


		reta(	(int) Math.round(x1),	(int) Math.round(y1),
			(int) Math.round(x2),	(int) Math.round(y2)	);	
	}

	public void reta(int x1, int y1, int x2, int y2){

		int deltaX = Math.abs(x2 - x1);
		int deltaY = Math.abs(y2 - y1);

		int a, b, c, d;

		if(deltaX > deltaY){

			a = min(x1, x2);
			b = (a == x1) ? y1 : y2;

			c = max(x1, x2);
			d = (c == x1) ? y1 : y2;	
		}
		else{

			a = min(y1, y2);
			b = (a == y1) ? x1 : x2;

			c = max(y1, y2);
			d = (c == y1) ? x1 : x2;
		}

		int deltaAC = (c - a);
		int deltaBD = (d - b);

		for(int p = a; p <= c; p++){

			double alpha = ((double)(p - a)) / deltaAC;
			double q = alpha * deltaBD + b;

			if(deltaX > deltaY) setPixel(p, q);
			else setPixel(q, p); 
		}
	}
}
