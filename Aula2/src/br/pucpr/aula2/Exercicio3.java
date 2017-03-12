package br.pucpr.aula2;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio3 {
	
	//Subtract
	BufferedImage subtract(BufferedImage img1, BufferedImage img2) {
		BufferedImage newImg = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color1 = new Color(img1.getRGB(x, y));
				Color color2 = new Color(img2.getRGB(x, y));
				newImg.setRGB(x, y, subtractColor(color1, color2).getRGB());
			}
		}
		return newImg;
	}
	
	Color subtractColor(Color color1, Color color2) {
		int r1 = color1.getRed();
		int g1 = color1.getGreen();
		int b1 = color1.getBlue();
		
		int r2 = color2.getRed();
		int g2 = color2.getGreen();
		int b2 = color2.getBlue();
		
		return Aula2.saturate(r1 - r2, g1 - g2, b1 - b2);
	}
	
	// Add
	BufferedImage add(BufferedImage img1, BufferedImage img2) {
		BufferedImage newImg = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color1 = new Color(img1.getRGB(x, y));
				Color color2 = new Color(img2.getRGB(x, y));
				newImg.setRGB(x, y, addColor(color1, color2).getRGB());
			}
		}
		return newImg;
	}
	
	Color addColor(Color color1, Color color2) {
		int r1 = color1.getRed();
		int g1 = color1.getGreen();
		int b1 = color1.getBlue();
		
		int r2 = color2.getRed();
		int g2 = color2.getGreen();
		int b2 = color2.getBlue();
		
		return Aula2.saturate(r1 + r2, g1 + g2, b1 + b2);
	}
}
