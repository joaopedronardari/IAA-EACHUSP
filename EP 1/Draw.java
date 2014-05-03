/* EP
 * Student Joao Pedro Nardari dos Santos
 * USP Number 8623865 - Class 94
 */

public class Draw extends Imagem {
	
	private int initialColor;
	
	public Draw(int l, int a, int r, int g, int b) {
		super(l, a, r, g, b);
	}

	public void kochCurve(int x1,int y1,int x2,int y2,int l) {
		int distance;
		
		if (y1 == y2) {
			distance = Math.abs(x2 - x1) + 1;
			if (x2 - x1 > 0) {
				// horizontal > 0
				if (distance >= l) {
					int pointDistance = distance/4;
					// Calls new distances
					kochCurve(x1, y1, (x1+pointDistance), y1, l);
					kochCurve((x1+pointDistance), y1, (x1+pointDistance), (y1-pointDistance), l);
					kochCurve((x1+pointDistance), (y1-pointDistance), (x1+2*pointDistance), (y1-pointDistance), l);
					kochCurve((x1+2*pointDistance), (y1-pointDistance), (x1+2*pointDistance), (y1), l);
					kochCurve((x1+2*pointDistance), (y1), (x1+2*pointDistance), (y1+pointDistance), l);
					kochCurve((x1+2*pointDistance), (y1+pointDistance), (x1+3*pointDistance), (y1+pointDistance), l);
					kochCurve((x1+3*pointDistance), (y1+pointDistance), (x1+3*pointDistance), (y1), l);
					kochCurve((x1+3*pointDistance),y1,x2,y2, l);
				} else {
					// draw
					reta(x1,y1,x2,y2);
					return;
				}
			} else {
				// horizontal < 0
				if (distance >= l) {
					int pointDistance = distance/4;
					// Calls new distances
					kochCurve(x1, y1, (x1-pointDistance), y1, l);
					kochCurve((x1-pointDistance), y1, (x1-pointDistance), (y1+pointDistance), l);
					kochCurve((x1-pointDistance), (y1+pointDistance), (x1-2*pointDistance), (y1+pointDistance), l);
					kochCurve((x1-2*pointDistance), (y1+pointDistance), (x1-2*pointDistance), (y1), l);
					kochCurve((x1-2*pointDistance), (y1), (x1-2*pointDistance), (y1-pointDistance), l);
					kochCurve((x1-2*pointDistance), (y1-pointDistance), (x1-3*pointDistance), (y1-pointDistance), l);
					kochCurve((x1-3*pointDistance), (y1-pointDistance), (x1-3*pointDistance), (y1), l);
					kochCurve(x1-3*(pointDistance),y1,x2,y2, l);
				} else {
					// draw
					reta(x1,y1,x2,y2);
					return;
				}
			}
		} else {
			distance = Math.abs(y2 - y1) + 1;
			if (y2 - y1 < 0) {
				// vertical < 0
				if (distance >= l) {
					int pointDistance = distance/4;
					// Calls new distances
					kochCurve(x1, y1, (x1), (y1-pointDistance), l);
					kochCurve((x1), (y1-pointDistance), (x1-pointDistance), (y1-pointDistance), l);
					kochCurve((x1-pointDistance), (y1-pointDistance), (x1-pointDistance), (y1-2*pointDistance), l);
					kochCurve((x1-pointDistance), (y1-2*pointDistance), (x1), (y1-2*pointDistance), l);
					kochCurve((x1), (y1-2*pointDistance), (x1+pointDistance), (y1-2*pointDistance), l);
					kochCurve((x1+pointDistance), (y1-2*pointDistance), (x1+pointDistance), (y1-3*pointDistance), l);
					kochCurve((x1+pointDistance), (y1-3*pointDistance), (x1), (y1-3*pointDistance), l);
					kochCurve((x1), (y1-3*pointDistance),x2,y2, l);
				} else {
					reta(x1,y1,x2,y2);
					return;
				}
			} else {
				// vertical > 0!
				if (distance >= l) {
					int pointDistance = distance/4;
					kochCurve(x1, y1, x1, (y1+pointDistance), l);
					kochCurve(x1, (y1+pointDistance), (x1+pointDistance), (y1+pointDistance), l);
					kochCurve((x1+pointDistance), (y1+pointDistance), (x1+pointDistance), (y1+2*pointDistance), l);
					kochCurve((x1+pointDistance), (y1+2*pointDistance), x1, (y1+2*pointDistance), l);
					kochCurve(x1, (y1+2*pointDistance), (x1-pointDistance), (y1+2*pointDistance), l);
					kochCurve((x1-pointDistance), (y1+2*pointDistance), (x1-pointDistance), (y1+3*pointDistance), l);
					kochCurve((x1-pointDistance), (y1+3*pointDistance), x1, (y1+3*pointDistance), l);
					kochCurve(x1, (y1+3*pointDistance),x2,y2, l);
				} else {
					// draw
					reta(x1,y1,x2,y2);
					return;
				}
			}
		}
	}
	
	public void paintArea(int x,int y) {
		if (x> -1 && y > -1 && x< largura && y < altura && getPixel(x, y) == initialColor) {
			setPixel(x, y);
			paintArea(x+1,y);
			paintArea(x-1,y);
			paintArea(x,y+1);
			paintArea(x,y-1);
		}
	}	
	
	public int getInitialColor() {
		return initialColor;
	}
	
	public void setInitialColor(int initialColor) {
		this.initialColor = initialColor;
	}
}
