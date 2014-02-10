package ru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {
    final private int windowSize = 300;
    final private int dotsSize = 10;
    private int dots = 3;
    private boolean neednewAppleFirst = true;
    private boolean neednewAppleSecond = true;
    private boolean isNeedSearchAppleZone = true;
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
    int position;

    public Board() {
        addKeyListener(new TAdapter());
        ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        body = iid.getImage();
        ImageIcon iidd = new ImageIcon(this.getClass().getResource("apple.png"));
        appleImg = iidd.getImage();
        start();
        setFocusable(true);
        setBackground(Color.BLACK);

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

    public void checkApple(int xx, int yy) {
        applex = xx;
        appley = yy;
        if (isNeedSearchAppleZone) {
            if (((x[0] + 20 == applex) & (y[0] + 10 == appley)) |
                    ((x[0] + 20 == applex) & (y[0] + 20 == appley)) |
                    ((x[0] + 20 == applex) & (y[0] == appley)) |
                    ((x[0] + 20 == applex) & (y[0] - 10 == appley)) |
                    ((x[0] + 20 == applex) & (y[0] - 20 == appley)) |
                    ((x[0] + 10 == applex) & (y[0] + 20 == appley)) |
                    ((x[0] + 10 == applex) & (y[0] - 20 == appley)) |
                    ((x[0] == applex) & (y[0] + 20 == appley)) |
                    ((x[0] == applex) & (y[0] - 20 == appley)) |
                    ((x[0] - 10 == applex) & (y[0] + 20 == appley)) |
                    ((x[0] - 10 == applex) & (y[0] - 20 == appley)) |
                    ((x[0] - 20 == applex) & (y[0] + 20 == appley)) |
                    ((x[0] - 20 == applex) & (y[0] + 10 == appley)) |
                    ((x[0] - 20 == applex) & (y[0] == appley)) |
                    ((x[0] - 20 == applex) & (y[0] - 10 == appley)) |
                    ((x[0] - 20 == applex) & (y[0] - 20 == appley))
                    ) {
                isNeedSearchAppleZone = false;
                appleEscaping();
            }
        }
    }                                   //l


    void checkApple() {
        checkApple(applex, appley);


        if ((x[0] == applex) & (y[0] == appley)) {
            dots++;
            neednewAppleFirst = true;
            isNeedSearchAppleZone = true;
        }
    }

    public void appleEscaping() {
        position = ((int) (Math.random() * 9));
        switch (position) {
            case 0:
                repaint();
                break;
            case 1:
                appley -= 10;
                repaint();
                break;
            case 2:
                appley += 10;
                repaint();
                break;
            case 3:
                applex -= 10;
                repaint();                                           //d
                break;
            case 4:
                applex += 10;
                repaint();
                break;
            case 5:
                applex += 10;
                appley += 10;
                repaint();
                break;
            case 6:
                applex += 10;
                appley -= 10;
                repaint();
                break;
            case 7:
                applex -= 10;
                appley += 10;
                repaint();
                break;
            case 8:
                applex -= 10;
                appley -= 10;
                repaint();
                break;
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
        if (!isNeedSearchAppleZone) {
            appleEscaping();
            checkApple();
        }
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
