package Snake2;

import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setTitle("Snake");

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Snake();
    }
}
