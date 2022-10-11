
package database2019;


import java.awt.*;
import javax.swing.*;

public class Background extends JPanel {
    
    private final Image img;
    public Background() {
          this.img = new ImageIcon(getClass().
          getResource("/img/Sfondo.jpg")).getImage(); 
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((Image) img, 0, 0, this);
    }
}