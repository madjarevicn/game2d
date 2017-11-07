import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.Util;


//Metoda koja skalira unetu sliku na drukciji format tj. dimenzije
public class ImageMethods {

	public static BufferedImage reflect(BufferedImage img) {
		
		WritableRaster source = img.getRaster();
		
		int newH = source.getHeight() + (source.getHeight())/4;
		WritableRaster ret = Util.createRaster(source.getWidth(),source.getHeight()+newH,false);
		
		int [] rgb = new int[3];
		
		for(int i=0;i<source.getWidth();i++) {
			for(int j=0;j<source.getHeight();j++) {
				
				source.getPixel(i, j, rgb);
				ret.setPixel(i, j, rgb);
			}
		}
		for(int y = 0; y < source.getHeight()/4; y++)
		{
			// Izracunavamo 0 - 1 odnos trenutne Y koordinate i visine produzetka
			double fy = y / (double)source.getHeight()/4;
			
			for(int x = 0; x < source.getWidth(); x++)
			{
				// Citamo odgovarajuci piksel iz izvorne slike (-y za vertikalnu refleksiju)
				source.getPixel(x, source.getHeight() - 1 - y, rgb);
				
				// Jednostavnom formulom zatamnjujemo piksele refleksije, od 0.5x na vrhu
				// refleksije, sve do nule na dnu (jer fy ide od 0 do 1, od vrha prema dnu)
				rgb[0] *= (1.0 - fy) * 0.5;
				rgb[1] *= (1.0 - fy) * 0.5;
				rgb[2] *= (1.0 - fy) * 0.5;
				
				// Upisujemo izracunati piksel u podrucje ispod kopirane slike
				ret.setPixel(x, source.getHeight() + y, rgb);
			}
		}
		
		return Util.rasterToImage(ret);

	}
	public static BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) {

	    int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	    BufferedImage ret = img;
	    BufferedImage scratchImage = null;
	    Graphics2D g2 = null;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    int prevW = w;
	    int prevH = h;

	    do {
	        if (w > targetWidth) {
	            w /= 2;
	            w = (w < targetWidth) ? targetWidth : w;
	        }

	        if (h > targetHeight) {
	            h /= 2;
	            h = (h < targetHeight) ? targetHeight : h;
	        }

	        if (scratchImage == null) {
	            scratchImage = new BufferedImage(w, h, type);
	            g2 = scratchImage.createGraphics();
	        }

	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

	        prevW = w;
	        prevH = h;
	        ret = scratchImage;
	    } while (w != targetWidth || h != targetHeight);

	    if (g2 != null) {
	        g2.dispose();
	    }

	    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	        g2 = scratchImage.createGraphics();
	        g2.drawImage(ret, 0, 0, null);
	        g2.dispose();
	        ret = scratchImage;
	    }

	    return ret;

	}
}
