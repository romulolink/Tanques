package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by User on 23/04/2015.
 */
public class Missil {
    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;

    private final int BOARD_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int BOARD_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final int MISSILE_SPEED = 100;

    public Missil(int x, int y) {
        ImageIcon ii =
                new ImageIcon(this.getClass().getResource("missil.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += MISSILE_SPEED;
        y += MISSILE_SPEED;
        if (x > BOARD_WIDTH)
            visible = false;
        if (y > BOARD_HEIGHT)
            visible = false;
    }
}
