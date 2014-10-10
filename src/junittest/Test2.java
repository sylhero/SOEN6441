package junittest;



import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Test2 extends JFrame {
  public Test2() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose a file");
    this.getContentPane().add(fileChooser);
    fileChooser.setVisible(true);
  }

  public static void main(String[] args) {
    JFrame frame = new Test2();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.pack();
    frame.setVisible(true);
  }
}