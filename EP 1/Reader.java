/* EP
 * Student Joao Pedro Nardari dos Santos
 * USP Number 8623865 - Class 94
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	// Constants
		static String SET_COLOR = "SET_COLOR";
		static String SET_PIXEL = "SET_PIXEL";
		static String RETA = "RETA";
		static String CURVA_KOCH = "CURVA_KOCH";
		static String PREENCHE_REGIAO = "PREENCHE_REGIAO";
		
		static Draw image;
		
		public static void identifyCommand(String[] lineParams) {
			switch (lineParams.length - 1) {
			case 2:
				if (lineParams[0].equals(SET_PIXEL)) {
					image.setPixel(
							convertParam(lineParams[1]), 
							convertParam(lineParams[2]));
				} else if (lineParams[0].equals(PREENCHE_REGIAO)) {
					image.setInitialColor(image.getPixel(convertParam(lineParams[1]),convertParam(lineParams[2])));
					image.paintArea(
							convertParam(lineParams[1]), 
							convertParam(lineParams[2]));
				}
				break;
			case 3:
				image.setCor(
						convertParam(lineParams[1]), 
						convertParam(lineParams[2]), 
						convertParam(lineParams[3]));
				break;
			case 4:
				if (lineParams[0].equals(RETA)) {
				image.reta(
						convertParam(lineParams[1]), 
						convertParam(lineParams[2]), 
						convertParam(lineParams[3]), 
						convertParam(lineParams[4]));
				} else {
					image = new Draw(
							convertParam(lineParams[0]), 
							convertParam(lineParams[1]), 
							convertParam(lineParams[2]), 
							convertParam(lineParams[3]), 
							convertParam(lineParams[4])); 
				}
				break;
			case 5:
				image.kochCurve(
							convertParam(lineParams[1]), 
							convertParam(lineParams[2]), 
							convertParam(lineParams[3]), 
							convertParam(lineParams[4]), 
							convertParam(lineParams[5]));
				break;
			}
		}
		
		public static int convertParam(String param) {
			return Integer.parseInt(param);
		}
		
		public static void main(String[] args) {
			if (args.length > 1) {
				FileReader comandsFile;
				try {
					// Load txt file
					comandsFile = new FileReader(args[0]);
					BufferedReader reader = new BufferedReader(comandsFile);
					
					// Read first line of comands file
					String line = reader.readLine();
					// while has comands read line
					while (line != null) {
						String[] lineParams = line.split(" ");
						identifyCommand(lineParams);
						line = reader.readLine();
					}
					
					reader.close();
					image.salva(args[1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
}
