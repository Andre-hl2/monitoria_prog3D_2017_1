package br.pucpr.aula3;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio3 {

	// Open and Close Methods
	BufferedImage open(BufferedImage img, int potency) {
		for(int i = 0; i < potency; i++) {
			img = dilate(img);
		}
		return img;
	}
	
	BufferedImage close(BufferedImage img, int potency) {
		for(int i = 0; i < potency; i++) {
			img = erode(img);
		}
		return img;
	}
	
	
	// Erode and Dilate Methods
	BufferedImage erode(BufferedImage img) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color[][] colors = {
						{Aula3.getRGB(img, x-1, y-1), Aula3.getRGB(img, x, y-1), Aula3.getRGB(img, x+1, y-1)},
						{Aula3.getRGB(img, x-1, y), Aula3.getRGB(img, x, y), Aula3.getRGB(img, x+1, y)},
						{Aula3.getRGB(img, x-1, y+1), Aula3.getRGB(img, x, y+1), Aula3.getRGB(img, x+1, y+1)}
				};
				
				newImg.setRGB(x, y, erodeColor(colors).getRGB());
			}
		}
		return newImg;
	}
	
BufferedImage dilate(BufferedImage img) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color[][] colors = {
						{Aula3.getRGB(img, x-1, y-1), Aula3.getRGB(img, x, y-1), Aula3.getRGB(img, x+1, y-1)},
						{Aula3.getRGB(img, x-1, y), Aula3.getRGB(img, x, y), Aula3.getRGB(img, x+1, y)},
						{Aula3.getRGB(img, x-1, y+1), Aula3.getRGB(img, x, y+1), Aula3.getRGB(img, x+1, y+1)}
				};
				
				newImg.setRGB(x, y, dilateColor(colors).getRGB());
			}
		}
		return newImg;
	}
	
	Color erodeColor(Color[][] colors) {
		Color small = new Color(255,255,255);
		for(int i=0;i < 3; i++){
			for(int j=0;j < 3; j++) {
				int smallSum = small.getRed() + small.getGreen() + small.getBlue();
				int actualSum = colors[i][j].getRed() + colors[i][j].getGreen() + colors[i][j].getBlue();
				if(actualSum < smallSum) {
					small = colors[i][j];
				}
			}
		}
		return small;
	}
	
	Color dilateColor(Color[][] colors) {
		Color bigger = new Color(0,0,0);
		for(int i=0;i < 3; i++){
			for(int j=0;j < 3; j++) {
				int biggerSum = bigger.getRed() + bigger.getGreen() + bigger.getBlue();
				int actualSum = colors[i][j].getRed() + colors[i][j].getGreen() + colors[i][j].getBlue();
				if(actualSum > biggerSum) {
					bigger = colors[i][j];
				}
			}
		}
		return bigger;
	}
	
}
