package Snake2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    int size = 10, delay = 150;
    int x[] = new int[900], y[] = new int[900], dots;
    boolean left = false, right = true, up = false, down = false;
    Timer timer;
    Image body;


    private JLabel label;


    public Board() {
        addKeyListener(new TAdaptaer());
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

    private class TAdaptaer extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
        /*    label = new JLabel();
            panel.add(label, BorderLayout.CENTER);
            label.setFont(new Font("Calibri", Font.PLAIN, 20));
            label.setText(e.getKeyText(e.getKeyCode()));   */
            if ((e.getKeyCode()== KeyEvent.VK_LEFT)) {
                left = true;
                up = false;
                down = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                right = true;
                up = false;
                down = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_UP)) {
                up = true;
                right = false;
                left = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }

}
