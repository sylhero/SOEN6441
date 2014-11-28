package usefulfunctions;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
/**
 * show wave log class
 * @author yulongsong
 *
 */
public class ShowWaveLog {
	/**
	 * show wave log
	 * @param tpane The JTabbedPane
	 */
	
	public static void showWaveLog(JTabbedPane tpane){
		
			  tpane.setPreferredSize( new Dimension( 800, 600 ) );
			JOptionPane.showMessageDialog(null, tpane, "wave log",  
			                                       JOptionPane.YES_NO_OPTION);
		
	}

}
