package usefulfunctions;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class ShowWaveLog {
	
	public static void showWaveLog(JTabbedPane tpane){
		
			  tpane.setPreferredSize( new Dimension( 800, 600 ) );
			JOptionPane.showMessageDialog(null, tpane, "wave log",  
			                                       JOptionPane.YES_NO_OPTION);
		
	}

}
