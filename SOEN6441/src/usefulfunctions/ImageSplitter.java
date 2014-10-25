package usefulfunctions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
/**
 * this class is for splitting the image 
 * @author Kun Wang
 *
 */
public class ImageSplitter {
    public static void main(String[] args) throws IOException {

        File file = new File("tiles.png"); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file

        int rows = 97; //You could decide the values for rows and cols variables
        int cols = 8;
        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols; // determines the chunk's width and height
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        System.out.println("Splitting done");

        //writing mini images into image files
        for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "png", new File("img" + i + ".png"));
        }
        System.out.println("Mini images created");
    }
}
