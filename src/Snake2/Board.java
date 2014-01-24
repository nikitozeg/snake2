package Snake2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.Color;
import java.util.Random;

import static sun.swing.SwingUtilities2.drawString;

public class Board extends JPanel implements ActionListener {
    int size = 10, delay = 150;
    int x[] = new int[900], y[] = new int[900], dots, applex[]=new int[300], appley[]=new int[300];
    boolean left = false, right = false, up = false, down = false;
    Timer timer;
    Image body, apple;

    private JLabel label;

    public Board() {
        addKeyListener(new TAdaptaer());
        ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        body = iid.getImage();
     //   ImageIcon apple = new ImageIcon(this.getClass().getResource("dot.png"));
     //   apple = apple.getImage();
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
        int a= (int) (Math.random()*10);
        int b= (int) (Math.random()*10);
        applex[1]=a;
        appley[1]=b;
        for (int z = 0; z < dots; z++) {
            g.drawImage(body, x[z], y[z], this);
          //  g.drawImage(body, applex[1], appley[1], this);
        }
    }

    public void appleSet(){
         int a= (int) Math.random()*10;
         int b= (int) Math.random()*10;
        applex[1]=a;
        appley[1]=b;


    }
    public void move() {
        if (((x[1]==0)||(y[1]==0))==true) {
            setBackground(Color.black);
            JOptionPane.showMessageDialog(null, "Meeting with the wall");

        }
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
