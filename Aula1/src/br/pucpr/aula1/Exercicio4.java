package br.pucpr.aula1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio4 {
	
	BufferedImage lerp(BufferedImage img1, BufferedImage img2, float percent) {
		BufferedImage newImg = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color1 = new Color(img1.getRGB(x, y));
				Color color2 = new Color(img2.getRGB(x, y));
				newImg.setRGB(x, y, lerpColor(color1, color2, percent).getRGB());
			}
		}
		
		return newImg;
	}
	
	Color lerpColor(Color color1, Color color2, float percent) {
		int r1 = color1.getRed();
		int g1 = color1.getGreen();
		int b1 = color1.getBlue();
		
		int r2 = color2.getRed();
		int g2 = color2.getGreen();
		int b2 = color2.getBlue();
		
		return Aula1.saturate((int)(r1 * (1.0f - percent) + r2 * percent),
						(int)(g1 * (1.0f - percent) + g2 * percent),
						(int)(b1 * (1.0f - percent) + b2 * percent)
					   );
	}
}
