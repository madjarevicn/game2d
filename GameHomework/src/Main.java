import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.ImageViewer;
import rafgfxlib.Util;

public class Main {

	public static void main(String[] args) {
		BufferedImage imgMadji = Util.loadImage("src/resources/madji.jpg");
		WritableRaster target = Util.createRaster(imgMadji.getWidth()/2, imgMadji.getHeight()/2, false);
		
		target = ScaleImage.scale(imgMadji, imgMadji.getWidth()/2, imgMadji.getHeight()/2).getRaster();
		ImageViewer.showImageWindow(Util.rasterToImage(target), "RAF Racunarska Grafika");
	}
}
