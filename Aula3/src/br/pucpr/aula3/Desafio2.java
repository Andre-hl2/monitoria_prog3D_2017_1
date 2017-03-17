package br.pucpr.aula3;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Desafio2 {

	float[][] mediaFilter = {
			{ 1.0f/9.0f, 1.0f/9.0f,1.0f/9.0f},
			{ 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f},
			{ 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f}
	};
	
	BufferedImage bloom(BufferedImage img) {
		BufferedImage selected = brighPixels(img);
		return smooth(selected, 1);
//		BufferedImage res1 = add(img, smooth(selected, 5));
//		BufferedImage res2 = add(res1, smooth(selected, 11));
//		BufferedImage res3 = add(res2, smooth(selected, 21));
//		BufferedImage res4 = add(res3, smooth(selected, 41));
		
	}
	
	
	// Select Pixels
	BufferedImage brighPixels(BufferedImage img) {
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color = new Color(img.getRGB(x, y));
				float[] hsv = new float[3];
				Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsv);
				if(hsv[2] > 0.8f) {
					newImg.setRGB(x, y, color.getRGB());
				} else {
					newImg.setRGB(x, y, new Color(0,0,0,0).getRGB());
				}
			}
		}
		return newImg;
	}
	
	// Smooth
	BufferedImage smooth(BufferedImage img, int potency) {
		for(int i=0; i < potency; i++) {
			img = applysmooth(img);
		}
		return img;
	}
	
	//TODO: Make it work with the alpha channel
	BufferedImage applysmooth(BufferedImage img) {
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				Color[][] colors = {
						{Aula3.getRGB(img, x-1, y-1), Aula3.getRGB(img, x, y-1), Aula3.getRGB(img, x+1, y-1)},
						{Aula3.getRGB(img, x-1, y), Aula3.getRGB(img, x, y), Aula3.getRGB(img, x+1, y)},
						{Aula3.getRGB(img, x-1, y+1), Aula3.getRGB(img, x, y+1), Aula3.getRGB(img, x+1, y+1)}
				};
				
				int r = 0;
				int g = 0;
				int b = 0;
				int a = 0;
				
				for(int i=0; i<colors.length; i++){
					for(int j=0;j < colors[i].length; j++) {
						r += colors[i][j].getRed() * mediaFilter[i][j];
						g += colors[i][j].getGreen() * mediaFilter[i][j];
						b += colors[i][j].getBlue() * mediaFilter[i][j];
						a += colors[i][j].getAlpha() * mediaFilter[i][j];
					}
				}
				newImg.setRGB(x, y, new Color(r, g, b, a).getRGB());
			}
		}
		return newImg;
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
		
		return Aula3.saturate(r1 + r2, g1 + g2, b1 + b2);
	}
	
}
