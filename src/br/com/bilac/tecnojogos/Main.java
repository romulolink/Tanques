package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;

public class Main {

    public Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Arena arena = new Arena((int) screenSize.getWidth(), (int) screenSize.getHeight());
        arena.adicionaTanque(new Tanque(100, 200, 90, Color.BLUE));
        arena.adicionaTanque(new Tanque(200, 200, 180, Color.RED));
        arena.adicionaTanque(new Tanque(470, 360, 0, Color.GREEN));
        arena.adicionaTanque(new Tanque(450, 50, 270, Color.YELLOW));
        JFrame f = new JFrame("Tanques");
        f.getContentPane().add(arena);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }
}
