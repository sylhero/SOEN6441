package usefulfunctions;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowLog {
	public static void showLog(String log){
		JTextArea textArea = new JTextArea(log);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize( new Dimension( 700, 500 ) );
		JOptionPane.showMessageDialog(null, scrollPane, "log",  
		                                       JOptionPane.YES_NO_OPTION);
	}

}
