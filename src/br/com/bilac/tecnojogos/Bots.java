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


        // Para cada tanque escolher uma direção e seguir
        for (Tanque t : tanques) {

            if (!t.getEstaAtivo()){

               for (int i=0; i < 5; i++)
                    t.moverFrente();

               switch (randInt(1,3)){
                   case 1:
                       t.giraHorario(90);
                       break;
                   case 2:
                       t.giraAntiHorario(90);
                       break;
                   default:
                       t.moverFrente();

               }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }
    }
}
