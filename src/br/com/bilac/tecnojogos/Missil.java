package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by User on 23/04/2015.
 */
public class Missil {
    private double x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private double direcaoX, direcaoY;
    private int raio = 5;

    private final int BOARD_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int BOARD_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final int MISSILE_SPEED = 50;

    public Missil(double x, double y, double direcaoX, double direcaoY) {
        ImageIcon ii =
                new ImageIcon(this.getClass().getResource("Imagens/missil.png"));

        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.direcaoX = direcaoX;
        this.direcaoY = direcaoY;
        this.x = x;
        this.y = y;
    }

    public double getDirecaoX() {
        return direcaoX;
    }

    public void setDirecaoX(double direcaoX) {
        this.direcaoX = direcaoX;
    }

    public double getDirecaoY() {
        return direcaoY;
    }

    public void setDirecaoY(double direcaoY) {
        this.direcaoY = direcaoY;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public int getRaio(){
        return this.raio;
    }
    public void move() {


            x = x + direcaoX * MISSILE_SPEED;
            y = y - direcaoY * MISSILE_SPEED;

            if (x > BOARD_WIDTH)
                visible = false;
            if (y > BOARD_HEIGHT)
                visible = false;




    }
}
