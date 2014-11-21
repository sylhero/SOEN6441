package usefulfunctions;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class ShowWaveLog {
	public static int batchCounter = 0;
	public static  JTabbedPane tpane = new JTabbedPane();
	
	
	public static void addWaveTab(String log){
		tpane.addTab("Wave"+batchCounter++, null, new JScrollPane(new TextArea(log)));
		
	}
	public static void showWaveLog(){
		
			  tpane.setPreferredSize( new Dimension( 800, 600 ) );
			JOptionPane.showMessageDialog(null, tpane, "wave log",  
			                                       JOptionPane.YES_NO_OPTION);
		
	}

}
