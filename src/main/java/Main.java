import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;


public class Main extends JFrame{
  private static File[] thumbs;
  JLabel pic;
  Timer tm;
  int x = 0;

  public Main(){
    super("Learning box");
    pic = new JLabel();
    pic.setBounds(0, 0, 300, 200);

    //Call The Function SetImageSize
    SetImageSize(thumbs.length-1);
    //set a timer
    tm = new Timer(1000*90,new ActionListener() { // <- HERE SET TIME

      @Override
      public void actionPerformed(ActionEvent e) {
        SetImageSize(x);
        x += 1;
        if(x >= thumbs.length )
          x = 0;
      }
    });
    add(pic);
    tm.start();
    setLayout(null);
    setSize(315, 240);
    setAlwaysOnTop( true );
    getContentPane().setBackground(Color.decode("#bdb67b"));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void SetImageSize(int i){
    ImageIcon icon = getRandomThumbIcon();
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon newImc = new ImageIcon(newImg);
    pic.setIcon(newImc);
  }

  public static void main(String[] args){
    try {
      thumbs = new File(new File("").getCanonicalFile().toPath().toAbsolutePath().toString()
              + "/src/thumbs").listFiles();
    } catch (IOException e) {
      e.printStackTrace();
    }
    new Main();
  }

  private static ImageIcon getRandomThumbIcon() {
    return new ImageIcon(thumbs[new Random().nextInt(thumbs.length)].getAbsolutePath());
  }
}