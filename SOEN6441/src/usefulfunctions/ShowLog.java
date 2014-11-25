package usefulfunctions;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * show logs
 * @author yulongsong
 *
 */
public class ShowLog {
	/**
	 * show log
	 * @param log
	 */
	public static void showLog(String log){
		JTextArea textArea = new JTextArea(log);
		System.out.println(log);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		//Font font = new Font("logFont", Font.HANGING_BASELINE , 18);
		//textArea.setFont(font);
		//textArea.setColumns(10);
		scrollPane.setPreferredSize( new Dimension( 1000, 500 ) );
		JOptionPane.showMessageDialog(null, scrollPane, "log",  
		                                       JOptionPane.YES_NO_OPTION);
	}

}
