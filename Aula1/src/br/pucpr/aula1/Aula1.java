package br.pucpr.aula1;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Aula1 {
	
	public static String path = "/Users/andredossantos/Documents/eclipseWorkspace/Aula1/img/";
	
	public static void main(String[] args) throws IOException {
		new Aula1().executar(5);
	}
	
	void executar(int exercicio) throws IOException
	{
		BufferedImage img = ImageIO.read(new File(path, "turtle.jpg"));
		
		BufferedImage img1 = ImageIO.read(new File(path, "errosB1.png"));
		BufferedImage img2 = ImageIO.read(new File(path, "errosB2.png"));
		
		switch(exercicio){
		case 1:
			BufferedImage newImg = bright(img, 1.5f);
			ImageIO.write(newImg, "png", new File(path, "exercicio1.png"));
			break;
		case 2:
			newImg = grayscale(img, 0);
			ImageIO.write(newImg, "png", new File(path, "exercicio2_0.png"));
			
			newImg = grayscale(img, 1);
			ImageIO.write(newImg, "png", new File(path, "exercicio2_1.png"));
			
			newImg = grayscale(img, 2);
			ImageIO.write(newImg, "png", new File(path, "exercicio2_2.png"));
			break;
		case 3:
			newImg = threshold(img, 300);
			ImageIO.write(newImg, "png", new File(path, "exercicio2_3.png"));
			break;
		case 4:
			newImg = subtract(img2, img1);
			ImageIO.write(newImg, "png", new File(path, "exercicio3_1.png"));
		case 5:
			newImg = add(img1, img2);
			ImageIO.write(newImg, "png", new File(path, "exercicio3_1.png"));
		}
	}
	
	//Exercício 1
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
		
		return saturate(r, g, b);
	}

	// Exercicio 2
	
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
		
		if(r+g+b > value) {
			return new Color(255, 255, 255);
		} else {
			return new Color(0,0,0);
		}
	}
	
	// Exercicio 3
	
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
		
		return saturate(r1 - r2, g1 - g2, b1 - b2);
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
		
		return saturate(r1 + r2, g1 + g2, b1 + b2);
	}
	
	// Helper methods
	Color saturate(int r, int g, int b) {
		return new Color(
					clamp(r, 0, 255),
					clamp(g, 0, 255),
					clamp(b, 0, 255)
				);
	}
	
	int clamp(int value, int min, int max) {
		return value > max ? max : value < min ? min : value;
	}
}
