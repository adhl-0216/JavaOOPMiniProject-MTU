package GUIs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JPanel;

public class designStyles extends javax.swing.JFrame {
    Image img = Toolkit.getDefaultToolkit().getImage("maxresdefault.jpg");
    public designStyles() throws IOException {
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });
        pack();
        setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        new designStyles();
    }
}
