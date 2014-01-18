package Snake2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    int size = 10, delay = 150;
    int x[] = new int[900], y[] = new int[900], dots;
    boolean left = false, right = true, up = false, down = false;
    Timer timer;
    Image body;

    public Board() {
        addKeyListener(new TAdapter());
        ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        body = iid.getImage();
        Start();
        setFocusable(true);
    }

    public void Start() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int z = 0; z < dots; z++) {
            g.drawImage(body, x[z], y[z], this);
        }
    }

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (left) {
            x[0] -= size;
        }
        if (right) {
            x[0] += size;
        }
        if (up) {
            y[0] -= size;
        }
        if (down) {
            y[0] += size;
        }
    }

    public void actionPerformed(ActionEvent e) {
        move();
       repaint();
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }

}
