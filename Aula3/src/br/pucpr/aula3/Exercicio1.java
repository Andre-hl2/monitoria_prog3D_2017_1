package br.pucpr.aula3;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio1 {
	BufferedImage convolve(BufferedImage img, float[][] kernel) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color[][] colors = {
						{Aula3.getRGB(img, x-1, y-1), Aula3.getRGB(img, x, y-1), Aula3.getRGB(img, x+1, y-1)},
						{Aula3.getRGB(img, x-1, y), Aula3.getRGB(img, x, y), Aula3.getRGB(img, x+1, y)},
						{Aula3.getRGB(img, x-1, y+1), Aula3.getRGB(img, x, y+1), Aula3.getRGB(img, x+1, y+1)}
				};
				newImg.setRGB(x, y, convolveColor(colors, kernel).getRGB());
			}
		}
		return newImg;
	}
	
	Color convolveColor(Color[][] colors, float[][] kernel) {
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int i=0; i<colors.length; i++){
			for(int j=0;j < colors[i].length; j++) {
				r += colors[i][j].getRed() * kernel[i][j];
				g += colors[i][j].getGreen() * kernel[i][j];
				b += colors[i][j].getBlue() * kernel[i][j];
			}
		}
		
		return Aula3.saturate(r, g, b);
	}
	
	
}
