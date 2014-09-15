package Main;

import gamepanel.GamePanel;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class StartTD {
	private JFrame window;
	public StartTD(){
		window = new JFrame("TD Game");
		window.requestFocus();
		window.setResizable(false);
		window.add(new GamePanel());
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] Args){
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                new StartTD();              
            }
        });
	}

}
