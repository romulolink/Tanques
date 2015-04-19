package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;


public class Arena extends JComponent implements MouseListener, ActionListener {

    private int w, h;
    private HashSet<Tanque> tanques;
    private Timer timer;

    public Arena(int w, int h) {
        this.w = w;
        this.h = h;
        tanques = new HashSet<Tanque>();
        addMouseListener(this);
        KeyListener kbListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (Tanque t : tanques) {
                    if (t.isEstaAtivo()) {
                        switch (e.getKeyCode()) {
                            case 37:
                                t.giraHorario(90);
                                repaint();
                                break;
                            case 38:
                                t.giraHorario(90);
                                repaint();
                            case 39:
                                t.giraHorario(90);
                                repaint();
                            case 40:
                                t.giraHorario(90);
                                repaint();
                                break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        addKeyListener(kbListener);
        setFocusable(true);
        timer = new Timer(500, this);
        timer.start();
        Sound.BACKGROUND_MUSIC.loop();
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
                Sound.BATTLE_BEGINS.play();
                break;
            }
        }
        repaint();
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
            t.move();
            repaint();
        }
    }
}
