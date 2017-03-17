package br.pucpr.aula3;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio2 {

	BufferedImage changeHSV(BufferedImage img, float[] hsvMultiplier) {
		
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
			
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				Color color = new Color(img.getRGB(x, y));
				float[] hsv = new float[3];
				Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsv);
				
				hsv[0] *= hsvMultiplier[0];
				hsv[1] *= hsvMultiplier[1];
				hsv[2] *= hsvMultiplier[2];
				
				newImg.setRGB(x, y, Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]));
			}
		}
			
		return newImg;	
	}
}
