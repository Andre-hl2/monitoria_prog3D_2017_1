package br.pucpr.aula1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio2 {
	// Grayscale
	BufferedImage grayscale(BufferedImage img, int type) {
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color = new Color(img.getRGB(x, y));
				newImg.setRGB(x, y, grayscaleColor(color, type).getRGB());
			}
		}
		
		return newImg;
	}
	
	Color grayscaleColor(Color color, int type) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		switch(type) {
		case 0:
			return new Color(r, r, r);
		case 1:
			int gray = (r + g + b)/3;
			return new Color(gray, gray, gray);
		case 2:
			gray = (int)(r * 0.3f + g * 0.59f + b * 0.11f);
			return new Color(gray, gray, gray);
		}
		
		return color;
	}
	
	// Threshold
	BufferedImage threshold(BufferedImage img, int value) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color = new Color(img.getRGB(x, y));
				newImg.setRGB(x, y, thresholdColor(color, value).getRGB());
			}
		}
		
		return newImg;
	}
	
	Color thresholdColor(Color color, int value) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		if ((r + g + b)/3 > value) {
			return new Color(255, 255, 255);
		} else {
			return new Color(0,0,0);
		}
	}
}
