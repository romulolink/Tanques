package br.com.bilac.tecnojogos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tanque {

    private int x, y;
    private double angulo;
    private double velocidade;
    private Color cor;
    private boolean estaAtivo;
    private int raio = 25;
    private ArrayList missil;
    private boolean podeAtirar;
    private boolean destruido;
    private BufferedImage explosao;
    private Animation explosaoAnimacao;

    public Tanque(int x, int y, double angulo, Color cor) {
        this.x = x;
        this.y = y;
        this.angulo = 90 - angulo;
        this.cor = cor;
        this.velocidade = 5;
        this.estaAtivo = false;
        this.podeAtirar = false;
        this.destruido = false;
        missil = new ArrayList();
        try {
            explosao = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\Tanques\\src\\br\\com\\bilac\\tecnojogos\\explosion2-sprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        explosaoAnimacao = new Animation(explosao, 31, 30, 45, 14, false, raio / 2, raio / 2, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public void aumentaVelocidade() {
        velocidade++;
    }

    public void giraHorario(int a) {
        angulo -= a * velocidade;
    }

    public void giraAntiHorario(int a) {
        angulo += a * velocidade;
    }

    public void moverFrente() {

        x = (int) (x + (Math.sin(Math.toRadians(angulo))) * velocidade);
        y = (int) (y - (Math.cos(Math.toRadians(angulo))) * velocidade);

    }

    /*
    Inverso de ir para frente
     */
    public void moverTras() {

        x = (int) (x - (Math.sin(Math.toRadians(angulo))) * velocidade);
        y = (int) (y + (Math.cos(Math.toRadians(angulo))) * velocidade);

    }


    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public boolean getEstaAtivo() {
        return estaAtivo;
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

        // Canhao
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(-3, -25, 6, 25);
        g2d.setColor(cor);
        g2d.drawRect(-3, -25, 6, 25);

        // Se o tanque estiver ativo, desenhamos uma margem nele.
        if (estaAtivo) {
            g2d.setColor(new Color(0, 0, 255));
            Stroke linha = g2d.getStroke();
            g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{8}, 0));
            g2d.drawOval(-25, -25, raio * 2, raio * 2);
            g2d.setStroke(linha);
        } else {
            g2d.setColor(new Color(255, 0, 0));
            Stroke linha = g2d.getStroke();
            g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{8}, 0));
            g2d.drawOval(-25, -25, raio * 2, raio * 2);
            g2d.setStroke(linha);
        }

        if (destruido) {
            explosaoAnimacao.Draw(g2d);
            Sound.EXPLOSION.play();
        }
        g2d.setTransform(antes);
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public Shape getRectEnvolvente() {
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        at.rotate(Math.toRadians(angulo));
        Rectangle rect = new Rectangle(-24, -32, 48, 55);
        return at.createTransformedShape(rect);
    }

    public ArrayList getMissil() {
        return missil;
    }

    public void atirar() {
        missil.add(new Missil(x + raio, y + raio / 2));
    }

    public void setPodeAtirar(boolean podeAtirar) {
        this.podeAtirar = podeAtirar;
    }

    public boolean getPodeAtirar() {
        return podeAtirar;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), getRaio(), getRaio());
    }
}
