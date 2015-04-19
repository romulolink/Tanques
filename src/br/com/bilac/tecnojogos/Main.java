package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Arena arena = new Arena((int) screenSize.getWidth(), (int) screenSize.getHeight());
        arena.adicionaTanque(new Tanque(100, 200, 0, Color.BLUE));
        arena.adicionaTanque(new Tanque(200, 200, 45, Color.RED));
        arena.adicionaTanque(new Tanque(470, 360, 90, Color.GREEN));
        arena.adicionaTanque(new Tanque(450, 50, 157, Color.YELLOW));
        JFrame f = new JFrame("Tanques");
        f.getContentPane().add(arena);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
