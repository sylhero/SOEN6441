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
	
	@Before
	public void setUp() throws Exception {
		test = null;
		testIcon = null;
	}

	@Test
	public void testLoadImage() {
		test = loadImage.loadImage("/images/arrowtower.png");
		assertNotNull(test);
		//fail("Not yet implemented");
	}

	@Test
	public void testLoadImageIcon() {
		testIcon = loadImage.loadImageIcon("/images/img293.png");
		assertNotNull(testIcon);
		//fail("Not yet implemented");
	}

}
