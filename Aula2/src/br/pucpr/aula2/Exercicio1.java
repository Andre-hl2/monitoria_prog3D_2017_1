package br.pucpr.aula2;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio1 {
	
	BufferedImage bright(BufferedImage img, float intensity) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		if(intensity < 0){
			intensity = 1 + intensity;
		}
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				
				Color color = new Color(img.getRGB(x, y));
				
				newImg.setRGB(x, y, brightColor(color, intensity).getRGB());
			}
		}
		return newImg;
	}
	
	Color brightColor(Color color, float intensity) {
		
		int r = (int)(color.getRed() * intensity);
		int g = (int)(color.getGreen() * intensity);
		int b = (int)(color.getBlue() * intensity);
		
		return Aula2.saturate(r, g, b);
	}
}
