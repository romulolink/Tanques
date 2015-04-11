package br.com.bilac.tecnojogos;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by romulolink on 09/04/15.
 */
public class Tanque {

    private double x, y;
    private double angulo;
    private double velocidade;
    private Color cor;
    private boolean estaAtivo;

    public Tanque(double x, double y, double angulo, Color cor) {
        this.x = x;
        this.y = y;
        this.angulo = 90 - angulo;
        this.cor = cor;
        this.velocidade = 1;
        this.estaAtivo = false;
    }


    public void aumentaVelocidade() {
        velocidade++;
    }

    public void giraHorario(int a) {
        angulo += a;
    }

    public void giraAntiHorario(int a) {
        angulo -= a;
    }


    public void move() {
        x = x + Math.sin(Math.toRadians(angulo)) * velocidade;
        y = y + Math.cos(Math.toRadians(angulo)) * velocidade;
    }


    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }


    public void draw(Graphics2D g2d) {

        AffineTransform antes = g2d.getTransform();

        AffineTransform at = new AffineTransform();

        at.translate(x, y);
        at.rotate(Math.toRadians(angulo));


        g2d.transform(at);


        g2d.setColor(cor);
        g2d.fillRect(-10, -12, 20, 24);

        for (int e = -12; e <= 8; e += 4) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(-15, e, 5, 4);
            g2d.fillRect(10, e, 5, 4);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(-15, e, 5, 4);
            g2d.drawRect(10, e, 5, 4);

        }

        // Canhão
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(-3, -25, 6, 25);
        g2d.setColor(cor);
        g2d.drawRect(-3, -25, 6, 25);

        // Se o tanque estiver ativo, desenhamos uma margem nele.
        if (estaAtivo) {
            g2d.setColor(new Color(120, 120, 120));
            Stroke linha = g2d.getStroke();
            g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{8}, 0));
            g2d.drawRect(-24, -32, 48, 55);
            g2d.setStroke(linha);
        }
        g2d.setTransform(antes);

    }

    public Shape getRectEnvolvente() {
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        at.rotate(Math.toRadians(angulo));
        Rectangle rect = new Rectangle(-24, -32, 48, 55);
        return at.createTransformedShape(rect);
    }
}
