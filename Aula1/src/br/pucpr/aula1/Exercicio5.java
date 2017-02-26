package br.pucpr.aula1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Exercicio5 {

	BufferedImage multiply(BufferedImage src, float[] color) {
		BufferedImage newImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < newImg.getHeight(); y++) {
			for(int x = 0; x < newImg.getWidth(); x++) {
				float[] srcColor = getRGBf(src, x, y);
				
				setRGBf(newImg, x, y, new float[] {
						srcColor[0] * color[0],
						srcColor[1] * color[1],
						srcColor[2] * color[2]
				});
			}
		}
		
		return newImg;
	}
	
	
	float[] getRGBf(BufferedImage img, int x, int y) {
		Color color = new Color(img.getRGB(x, y));
		return new float[] {
				color.getRed()/255.0f,
				color.getGreen()/255.0f,
				color.getBlue()/255.0f
				};
	}
	
	float[] getRGBAf(BufferedImage img, int x, int y) {
		Color color = new Color(img.getRGB(x, y));
		return new float[] {
				color.getRed()/255.0f,
				color.getGreen()/255.0f,
				color.getBlue()/255.0f,
				color.getAlpha()/255.0f
				};
	}
	
	void setRGBf(BufferedImage img, int x, int y, float[] color) {
		Color newColor = Aula1.saturate(
					(int)(color[0] * 255),
					(int)(color[1] * 255),
					(int)(color[2] * 255)
				);
		img.setRGB(x, y, newColor.getRGB());
	}
	
	void setRGBAf(BufferedImage img, int x, int y, float[] color) {
		Color newColor = Aula1.saturate(
					(int)(color[0] * 255),
					(int)(color[1] * 255),
					(int)(color[2] * 255), 
					(int)(color[3] * 255)
				);
		img.setRGB(x, y, newColor.getRGB());
	}
}
