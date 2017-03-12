package br.pucpr.aula3;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Aula3 {

	public static String path = "/Users/andredossantos/Documents/monitoria_prog3D_2017_1/img";
	public static String resultPath = "/Users/andredossantos/Documents/monitoria_prog3D_2017_1/Aula3/result";
	
	// Kernels
	float[][] mediaFilter = {
			{ 1.0f/9.0f, 1.0f/9.0f,1.0f/9.0f},
			{ 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
			{ 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f}
	};
	
	float [][] sharpSuavization = {
			{ 1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f},
			{ 2.0f/16.0f, 4.0f/16.0f, 2.0f/16.0f},
			{ 1.0f/16.0f, 2.0f/16.0f, 1.0f/16.0f}
	};
	
	float[][] crossSuavization = {
			{0, 1.0f/5.0f, 0},
			{1.0f/5.0f, 1.0f/5.0f, 1.0f/5.0f},
			{0, 1.0f/5.0f, 0}
	};
	
	public static void main(String[] args) throws IOException {
		new Aula3().executar(1);
	}
	
	
	
	void executar(int exercicio) throws IOException {
		
		BufferedImage img = ImageIO.read(new File(path, "cor/turtle.jpg"));
		
		switch(exercicio) {
		case 1:
			BufferedImage newImg = convolve(img, mediaFilter);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_media.png"));
			
			newImg = convolve(img, sharpSuavization);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_sharp.png"));
			
			newImg = convolve(img, crossSuavization);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_cross.png"));
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	
	BufferedImage convolve(BufferedImage img, float[][] kernel) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color[][] colors = {
						{getRGB(img, x-1, y-1), getRGB(img, x, y-1), getRGB(img, x+1, y-1)},
						{getRGB(img, x-1, y), getRGB(img, x, y), getRGB(img, x+1, y)},
						{getRGB(img, x-1, y+1), getRGB(img, x, y+1), getRGB(img, x+1, y+1)}
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
		
		return saturate(r, g, b);
	}
	
	//Helper methods
	static Color getRGB(BufferedImage img, int x, int y) {
		
		if(x < 0 || y < 0 || x >= img.getWidth() || y >= img.getHeight())
			return new Color(0,0,0);
		
		return new Color(img.getRGB(x, y));
	}
	
	static Color saturate(int r, int g, int b) {
		return new Color(
					clamp(r, 0, 255),
					clamp(g, 0, 255),
					clamp(b, 0, 255)
				);
	}
	
	static Color saturate(int r, int g, int b, int a) {
		return new Color(
					clamp(r, 0, 255),
					clamp(g, 0, 255),
					clamp(b, 0, 255),
					clamp(a, 0, 255)
				);
	}
	
	static int clamp(int value, int min, int max) {
		return value > max ? max : value < min ? min : value;
	}
}
