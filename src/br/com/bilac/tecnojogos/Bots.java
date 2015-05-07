package br.com.bilac.tecnojogos;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by romulorlo on 07/05/2015.
 */
public class Bots implements Runnable {

    private HashSet<Tanque> tanques;


    public Bots(HashSet<Tanque> tanques) {
        this.tanques = tanques;
    }




    public static int randInt(int min, int max){

        Random rand = new Random();


        int randomNum = rand.nextInt((max - min) + 1) + min;


        return randomNum;
    }

    // Thread roddando em Loop
    @Override
    public void run() {


        for (Tanque t : tanques) {
            if (t.getEstaAtivo() == false){
                t.moverFrente();
            }
        }

        // Para cada tanque escolher uma direção e seguir
        for (Tanque t : tanques) {

            if (!t.getEstaAtivo()){

                // Para cada 10 passos espera 100ms para mudar de direção
                for (int i =0;i<10;i++){

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t.moverFrente();
                }


               switch (randInt(1,4)){
                   case 1:
                       t.giraHorario(90);
                       break;
                   case 2:
                       t.giraAntiHorario(90);
                       break;
                   case 3:
                       t.giraHorario(45);
                       break;
                   case 4:
                       t.giraAntiHorario(45);
                       break;
               }

                // para cada virada espera um tempo
                /*try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            }


        }
    }
}
