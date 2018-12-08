package recursive;

import java.awt.Graphics;

import javax.swing.JApplet;

public class SnowFlake extends JApplet {

	public void makeSnowFlake(Graphics g, int x, int y, int scale, int level) {
		for (int a = 0; a < 360; a += 40) {
			double rad = a * Math.PI / 180;
			int x2 = (int) (x + Math.cos(rad) * scale);
			int y2 = (int) (y + Math.sin(rad) * scale);
			g.drawLine(x, y, x2, y2);

			if (scale > 0) {
				makeSnowFlake(g, x2, y2, scale / 3, level - 1);
			}
		}
	}
	
	public void paint(Graphics g) {
		makeSnowFlake(g, 200, 200, 120, 5);
	}
}
