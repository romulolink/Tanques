package br.com.bilac.tecnojogos;

import java.util.HashSet;
import java.util.Random;

public class BotsMovimento implements Runnable{

    private HashSet<Tanque> tanques;
    private volatile boolean running = true;


    public void terminate() {
        running = false;
    }

    public BotsMovimento(HashSet<Tanque> tanques) {
        this.tanques = tanques;
    }

    @Override
    public void run() {

        while (running){



        }
    }


    public static int randInt(int min, int max){

        Random rand = new Random();


        int randomNum = rand.nextInt((max - min) + 1) + min;


        return randomNum;
    }

}