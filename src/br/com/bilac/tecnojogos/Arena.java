package br.com.bilac.tecnojogos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Arena extends JComponent implements MouseListener, ActionListener, Runnable {
    private int w, h;
    private HashSet<Tanque> tanques;
    private Timer timer;
    private Tanque t_player;
    private boolean ingame;
    private boolean gameover = false;
    private Animation animation;
    private Missil missil;
    private int i;
    private boolean isUpPressed,isDownPressed,isLeftPressed,isRightPressed,isSpacePressed,atirar;
    private final ExecutorService pool = Executors.newFixedThreadPool(3);
    private Colisoes col;


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

                                switch (e.getKeyCode()) {

                                    case KeyEvent.VK_LEFT:
                                        isLeftPressed = true;
                                        break;
                                    case KeyEvent.VK_RIGHT:
                                        isRightPressed = true;
                                        break;
                                    case KeyEvent.VK_UP:
                                        isUpPressed = true;
                                        break;
                                    case KeyEvent.VK_DOWN:
                                        isDownPressed = true;
                                        break;
                                    case KeyEvent.VK_SPACE:
                                        isSpacePressed = true;
                                        break;
                                    case KeyEvent.VK_W:
                                        atirar = true;
                                        break;
                                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                for (Tanque t : tanques) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && t.getEstaAtivo()) {
                        //  t.setVelocidade(0);
                    }
                }


                switch (e.getKeyCode()) {


                    case  KeyEvent.VK_UP: isUpPressed =  false ;  break ;
                    case  KeyEvent.VK_DOWN: isDownPressed =  false ;  break ;
                    case  KeyEvent.VK_LEFT: isLeftPressed =  false ;  break ;
                    case  KeyEvent.VK_RIGHT: isRightPressed = false ; break ;
                    case  KeyEvent.VK_SPACE: isSpacePressed = false ; break ;
                    case  KeyEvent.VK_W: atirar = false ; break ;

                }


            }
        };

        addMouseListener(this);
        addKeyListener(listener);
        setFocusable(true);
        new  Thread(this).start();

        ingame = true;
        //Sound.BACKGROUND_MUSIC.loop();
    }

    public boolean hasKeyPressed(){

        return isUpPressed || isDownPressed || isLeftPressed || isRightPressed || atirar;

    }

    // Game loop
    public  void  run() {

        while (true){

        // Thread para checar colisões
        try{
            pool.execute(col = new Colisoes(tanques, t_player));
        }catch (Exception e){

            e.printStackTrace();
            pool.shutdown();
        }


            try  {

                if (hasKeyPressed()){

                    for (Tanque t : tanques) {

                        if (t.getEstaAtivo()) {

                            if (isUpPressed){

                                t.moverFrente();

                            }
                            if (isLeftPressed) {
                                t.giraHorario(3);
                            }
                            if(isRightPressed){
                                t.giraAntiHorario(3);

                            }
                            if (isDownPressed) {
                                t.moverTras();
                            }

                            if (isSpacePressed){
                                t.aumentaVelocidade();
                            }

                            if (atirar){
                                // adiciona bala
                                t.setPodeAtirar(true);
                                t.atirar();

                            }

                            repaint();

                        }
                    }

                }


                Thread.sleep(100);
            }  catch (Exception exc) {
                exc.printStackTrace();
                break ;
            }

            // Executa o tiro de forma contínua e checa colisões
            // Para aparecer o tiro é setado o visible para true

            for (Tanque t : tanques) {

              //  System.out.printf("\njogo rodando " + i++);
                // Apenas o tanque ativo está atirando o tempo inteiro no modo invisivel
                if (t.getEstaAtivo()) {
                    // deslocamento da bala

                    tanqueAtirar(t);
                    repaint();
                }

            }

        } // end While

    }


    public void gameEnd(){
        gameover = true;
    }


    public void mostrarMensagem(){

        JOptionPane.showMessageDialog(this, "Colisão detectada!","Tanques",JOptionPane.INFORMATION_MESSAGE);

    }


    public void adicionaTanqueBot(Tanque t) {
        tanques.add(t);
    }

    public void adicionaTanquePlayer(Tanque t){
        t.setEstaAtivo(true);
        t_player = t;
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

        for (Tanque t : tanques) {
            t.draw(g2d);

            if (ingame) {
                ArrayList ms = t.getMissil();

                for (int i = 0; i < ms.size(); i++) {
                    Missil m = (Missil) ms.get(i);
                    g2d.drawImage(m.getImage(), (int)m.getX(), (int)m.getY(), this);
                }
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Tanque t : tanques) t.setEstaAtivo(false);
        for (Tanque t : tanques) {
            boolean clicado = t.getRectEnvolvente().contains(e.getX(), e.getY());
            if (clicado) {
                t.setEstaAtivo(true);
                t_player = t;
                //Sound.BATTLE_BEGINS.play();
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


           if (!t.getEstaAtivo()){
               t.moverFrente();
               repaint();

           }


          //  t.atirar();
          //  Sound.BULLET_SHOT.play();


         /*
            if (t.getRaio() < w || t.getRaio() < h) {
                t.setVelocidade(-1);
                t.moverFrente();
            }
            if (t.getRaio() > w - 50 || t.getRaio() > h - 50) {
                t.setVelocidade(1);
                t.moverTras();
            }*/



       }

    }

    public void tanqueAtirar(Tanque t) {
        if (ingame) {
            ArrayList ms = t.getMissil();
            // Executa quantos mísseis tiver
            for (int i = 0; i < ms.size(); i++) {
                Missil m = (Missil) ms.get(i);

                    m.move();
                   // Sound.BULLET_SHOT.play();
                if (((Missil) ms.get(i)).visible == false)
                    ms.remove(i);
            }


        }
    }


    // Checar colisão entre Missil e Tanques
    public boolean checarColisao() {

        double distance;
        // Identifica o tanque do jogador
        /*for (Tanque t : tanques) {
            if (t.getEstaAtivo()) {
                t_player = t;
            }
        }*/
        // Verifica colisão com qualquer dos tanques na arena
        for (Tanque t : tanques) {

            // Seleciona apenas inimigos
            if (!t.getEstaAtivo()) {

                // Calcula Hipotenusa
                distance = Math.pow(t_player.getY() - t.getY(), 2) + Math.pow(t_player.getX() - t.getX(), 2);
                distance = Math.sqrt(distance);

                // Para cada missil de player
                //for (int j=0;j<t_player.getMissil().size();j--){
                //}
                // verifica distancia entre os raios do player com os robos
                if (distance <= t_player.getRaio() + t.getRaio()) {
                    //Sound.FIRST_BLOOD.play();
                    t.setDestruido(true);
                    return true;
                }

               /* if (t.getBounds().intersects(t.getBounds()) || missil.getBounds().intersects(t_player.getBounds()))
                    return true;*/

            }
        }

        return false;
    }
}
