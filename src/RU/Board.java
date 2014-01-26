package RU;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.Color;

import static sun.swing.SwingUtilities2.drawString;

public class Board extends JPanel implements ActionListener {
    int size = 10, delay = 150;
    boolean setter = true;
    int x[] = new int[300], y[] = new int[300], dots = 3, applex[] = new int[300], appley[] = new int[300];
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

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        timer = new Timer(delay, this);
        timer.start();
    }

    public void appleSett(Graphics g) {
        //  super.paint(g);
        g.drawImage(body, 10 * ((int) (Math.random() * 30)), 10 * ((int) (Math.random() * 30)), this);
    }


    public void paint(Graphics g) {
        super.paint(g);
        for (int z = 0; z < dots; z++) {
            g.drawImage(body, x[z], y[z], this);

            if (setter) {
                setter = false;
                appley[0] = 10 * ((int) (Math.random() * 27));
                applex[0] = 10 * ((int) (Math.random() * 27));
            }

            g.drawImage(body, applex[0], appley[0], this);

            //g.drawImage(body, applex[1], appley[1], this);
        }
    }

 /*   public void appleSet() {
        applex[1] = (int) (Math.random() * 300);
        appley[1] = (int) (Math.random() * 300);

    }         */

    public void checkApple() {
        if ((x[0] == applex[0]) && (y[0] == appley[0])) {
            dots++;
            setter = true;
        }
    }

    public void move() {


        if (((x[0] == -10 || x[0] == 300) || (y[0] == -10 || y[0] == 300)) == true) {
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
        checkApple();
        repaint();
    }

    private class TAdaptaer extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
        /*    label = new JLabel();
            panel.add(label, BorderLayout.CENTER);
            label.setFont(new Font("Calibri", Font.PLAIN, 20));
            label.setText(e.getKeyText(e.getKeyCode()));   */
            if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }

}
