package ru;

/**
 *
 @author is Ivanov Nikita
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class Board.
 * <p/>
 * {@value} neednewAppleFirst if true - need to put new apple on Board.
 *
 * @value neednewAppleSecond if true - need to put a second apple on Board.
 */

public class Board extends JPanel implements ActionListener {
    final private int windowSize = 300;
    final private int dotsSize = 10;
    private int dots = 3;
    private boolean neednewAppleFirst = true;
    private boolean neednewAppleSecond = true;
    private int[] x = new int[windowSize];
    private int[] y = new int[windowSize];
    private int applex;
    private int appley;
    private int applexx;
    private int appleyy;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private Image body, appleImg;

    /**
     * MEthod for drawing a Board.
     *
     * @param
     */

    public Board() {
        addKeyListener(new TAdapter());
        ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        body = iid.getImage();
        appleImg = iid.getImage();
        start();
        setFocusable(true);
    }

    void start() {
        final int startPosOfSnake = 50;
        for (int z = 0; z < dots; z++) {
            x[z] = startPosOfSnake - z * dotsSize;
            y[z] = startPosOfSnake;
        }
        final int delay = 150;
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    public void drawApple(Graphics g) {
        if (neednewAppleFirst) {
            appley = dotsSize * ((int) (Math.random() * 27));
            applex = dotsSize * ((int) (Math.random() * 27));
            neednewAppleFirst = false;
        }
        g.drawImage(appleImg, applex, appley, this);

        if (neednewAppleSecond) {
            applexx = dotsSize * ((int) (Math.random() * 27));
            appleyy = dotsSize * ((int) (Math.random() * 27));
            neednewAppleSecond = false;
        }
        g.drawImage(appleImg, applexx, appleyy, this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int z = 0; z < dots; z++) {
            g.drawImage(body, x[z], y[z], this);
        }
        drawApple(g);
    }

    void checkApple() {
        if ((x[0] == applex) && (y[0] == appley)) {
            dots++;
            neednewAppleFirst = true;
        }

        if ((x[0] == applexx) && (y[0] == appleyy)) {
            dots++;
            neednewAppleSecond = true;
        }
    }

    void move() {
        if (((x[0] == -10 || x[0] == windowSize) || (y[0] == -10 || y[0] == windowSize))) {
            setBackground(Color.black);
            JOptionPane.showMessageDialog(null, "Meeting with the wall");
            System.exit(0);
        }
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (left) {
            int size = dotsSize;
            x[0] -= size;
        }
        if (right) {
            int size = dotsSize;
            x[0] += size;
        }
        if (up) {
            int size = dotsSize;
            y[0] -= size;
        }
        if (down) {
            int size = dotsSize;
            y[0] += size;
        }
    }

    public void actionPerformed(ActionEvent e) {
        move();
        checkApple();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

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
