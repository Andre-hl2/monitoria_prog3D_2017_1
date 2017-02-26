package br.pucpr.aula1;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Aula1 {
	
	// Atenção, alterar esses caminhos ao trocar a maquina rodando esse código.
	public static String path = "/Users/andredossantos/Documents/monitoria_prog3D_2017_1/Aula1/img";
	
	public static void main(String[] args) throws IOException {
		new Aula1().executar(7);
	}
	
	/**
	 * Cada um dos exercícios e sub-exercícios da apresentação estão como um case dessa função
	 */
	void executar(int exercicio) throws IOException
	{
		BufferedImage img = ImageIO.read(new File(path, "cor/turtle.jpg"));
		
		BufferedImage img1 = ImageIO.read(new File(path, "pb/errosB1.png"));
		BufferedImage img2 = ImageIO.read(new File(path, "pb/errosB2.png"));
		
		BufferedImage lerp1 = ImageIO.read(new File(path, "cor/mario.jpg"));
		BufferedImage lerp2 = ImageIO.read(new File(path, "cor/sonic.jpg"));
		
		switch(exercicio){
		case 1:
			BufferedImage newImg = new Exercicio1().bright(img, 1.5f);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio1.png"));
			break;
		case 2:
			newImg = new Exercicio2().grayscale(img, 0);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio2_0.png"));
			
			newImg = new Exercicio2().grayscale(img, 1);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio2_1.png"));
			
			newImg = new Exercicio2().grayscale(img, 2);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio2_2.png"));
			break;
		case 3:
			newImg = new Exercicio2().threshold(img, 300);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio2_3.png"));
			break;
		case 4:
			newImg = new Exercicio3().subtract(img2, img1);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio3_1.png"));
		case 5:
			newImg = new Exercicio3().add(img1, img2);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio3_2.png"));
		case 6:
			newImg = new Exercicio4().lerp(lerp1, lerp2, 0.25f);
			ImageIO.write(newImg, "png", new File(path, "result/exercicio4.png"));
		case 7:
			newImg = new Exercicio5().multiply(img, new float[] { 0.5f, 1, 0.5f});
			ImageIO.write(newImg, "png", new File(path, "result/exercicio5.png"));
		}
	}

	//Helper methods
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
