package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;


public class Arena extends JComponent implements MouseListener, ActionListener {
    private int w, h;
    private HashSet<Tanque> tanques;
    private Timer timer;
    private Tanque t_player;
    private boolean gameover = false;

    public Arena(int w, int h) {
        this.w = w;
        this.h = h;
        tanques = new HashSet<Tanque>();
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (Tanque t : tanques) {
                    if (t.getEstaAtivo()) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_LEFT:
                                t.giraHorario(3);
                                repaint();
                                break;
                            case KeyEvent.VK_RIGHT:
                                t.giraAntiHorario(3);
                                repaint();
                                break;
                            case KeyEvent.VK_UP:
                                t.moverFrente();
                                    if (checarColisao())
                                        mostrarMensagem();
                                    else
                                        repaint();
                                break;
                            case KeyEvent.VK_DOWN:
                                t.moverTras();
                                repaint();
                                break;
                            case KeyEvent.VK_SPACE:
                                t.aumentaVelocidade();
                                break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (Tanque t : tanques) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && t.getEstaAtivo()) {
                        //  t.setVelocidade(0);
                    }
                }
            }
        };
        addMouseListener(this);
        addKeyListener(listener);
        setFocusable(true);
        timer = new Timer(500, this);
        timer.start();
    }

    public void gameEnd(){
        gameover = true;
    }


    public void mostrarMensagem(){

        JOptionPane.showMessageDialog(this, "Colisão detectada!","Tanques",JOptionPane.INFORMATION_MESSAGE);

    }


    public void adicionaTanque(Tanque t) {
        tanques.add(t);
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(245, 245, 255));
        g2d.fillRect(0, 0, w, h);
        g2d.setColor(new Color(220, 220, 220));
        for (int _w = 0; _w <= w; _w += 20) g2d.drawLine(_w, 0, _w, h);
        for (int _h = 0; _h <= h; _h += 20) g2d.drawLine(0, _h, w, _h);

        for (Tanque t : tanques) t.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Tanque t : tanques) t.setEstaAtivo(false);
        for (Tanque t : tanques) {
            boolean clicado = t.getRectEnvolvente().contains(e.getX(), e.getY());
            if (clicado) {
                t.setEstaAtivo(true);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Tanque t : tanques) {
            if (t.getX() < 0 || t.getY() < 0)
                t.setVelocidade(1);
            if (t.getX() > getWidth() - 50 || t.getY() > getHeight() - 50)
                t.setVelocidade(-1);
          //  t.move();
            repaint();
        }

    }


    public boolean checarColisao(){

       double distance;
        // Identifica o tanque do jogador
        for (Tanque t : tanques){
            if (t.getEstaAtivo()){
                t_player = t;
            }
        }
        // Verifica colisão com qualquer dos tanques na arena
        for (Tanque t: tanques){

            // Seleciona apenas inimigos
            if (t.getEstaAtivo() == false){


               distance = Math.pow(t_player.getY() - t.getY() , 2)
                        + Math.pow(t_player.getX() - t.getX(), 2);

                distance = Math.sqrt(distance);

                // verifica distancia entre os raios
                if (distance <= t_player.getRaio() + t.getRaio()) {
                    return true;
                }

            }

        }

        return false;
    }


    /*public boolean colisao(){
        for (Tanque t : tanques) {
            if(t.getBounds().intersects(getBounds())){
                return true;
            }
        }
        return false;
    }*/
}
