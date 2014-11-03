package junittest;

import static org.junit.Assert.*;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import usefulfunctions.LoadImage;

/**
 * This is the test class for LoadImage class in the usefulfunctions package.
 * It tests the method LoadImage and LoadImageIcon
 * @author Xunrong Xia
 *
 */

public class LoadImageTest {
	private static LoadImage loadImage = new LoadImage();
	private static Image test;
	private static ImageIcon testIcon;
	/**
	 * To initialize the variables to be null.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = null;
		testIcon = null;
	}

	/**
	 * To test the LoadImage method. 
	 * The initial value of the variable is null, after the tested method be called, the variable is not null.
	 */
	@Test
	public void testLoadImage() {
		test = loadImage.loadImage("/images/arrowtower.png");
		assertNotNull(test);
	}

	/**
	 * To test the LoadImageIcon method.
	 * The initial value of the variable is null, after the tested method be called, the variable is not null.
	 */
	@Test
	public void testLoadImageIcon() {
		testIcon = loadImage.loadImageIcon("/images/img293.png");
		assertNotNull(testIcon);
	}

}
