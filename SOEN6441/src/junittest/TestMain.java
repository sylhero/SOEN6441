package junittest;

import java.awt.EventQueue;

import javax.swing.JFrame;



public class TestMain{
	private JFrame window;
	public TestMain(){
		window = new JFrame("TD Game");
		window.setResizable(false);

		window.add(new Test());
		window.add(new Test());
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public static void main(String[] Args){
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                new TestMain();              
            }
        });
	}


}
