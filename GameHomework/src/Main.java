import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.ImageViewer;
import rafgfxlib.Util;

public class Main {

	public static void main(String[] args) {
		BufferedImage imgMadji = Util.loadImage("src/resources/madji.jpg");
		WritableRaster target = Util.createRaster(imgMadji.getWidth()/2, imgMadji.getHeight()/2, false);
		
		target = ImageMethods.scale(imgMadji, imgMadji.getWidth()/4, imgMadji.getHeight()/4).getRaster();
		
		BufferedImage compressed = Util.rasterToImage(target);		
		ImageViewer.showImageWindow(ImageMethods.reflect(compressed), "Madji");
	}
}
