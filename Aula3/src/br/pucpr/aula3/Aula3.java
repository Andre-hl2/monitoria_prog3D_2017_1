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
		new Aula3().executar(4);
	}
	
	
	
	void executar(int exercicio) throws IOException {
		
		BufferedImage img = ImageIO.read(new File(path, "cor/turtle.jpg"));
		BufferedImage bloomImg = ImageIO.read(new File(path, "cor/metroid2.jpg"));
		
		switch(exercicio) {
		case 1:
			BufferedImage newImg = new Exercicio1().convolve(img, mediaFilter);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_media.png"));
			
			newImg = new Exercicio1().convolve(img, sharpSuavization);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_sharp.png"));
			
			newImg = new Exercicio1().convolve(img, crossSuavization);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio1_cross.png"));
			break;
		case 2:
			newImg = new Exercicio2().changeHSV(img, new float[] { 1.0f, 2.0f, 1.0f } );
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio2.png"));
			
			break;
		case 3:
			newImg = new Exercicio3().erode(img );
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio3_erode.png"));
			
			newImg = new Exercicio3().dilate(img );
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio3_dilate.png"));
			
			newImg = new Exercicio3().open(img, 3);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio3_open.png"));
			
			newImg = new Exercicio3().close(img, 3);
			ImageIO.write(newImg, "png", new File(resultPath, "exercicio3_close.png"));
			
			break;
		case 4:
			newImg = new Desafio2().bloom(bloomImg );
			ImageIO.write(newImg, "png", new File(resultPath, "desafio2.png"));
			
			break;
		}
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
