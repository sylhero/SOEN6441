package usefulfunctions;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * this class is for loading image from the file
 * @author yulongsong
 *
 */
public class LoadImage {
	/**
	 * This method load the image
	 * @param path This the image's path.
	 * @return The image which need to be used.
	 */
	public static Image loadImage(String path){
		Image temp = null;
		try {
			temp = ImageIO.read(
				LoadImage.class.getResourceAsStream(path)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(temp==null){
			System.out.println("null image");
		}
		return temp;
	}
	/**
	 * this method load the image icon
	 * @param path The path of image icon.
	 * @return The image icon.
	 */
	public static ImageIcon loadImageIcon(String path){
		ImageIcon temp = null;
		try{
			temp = new ImageIcon(LoadImage.class.getResource(
	                 path));
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
		
	}

}
