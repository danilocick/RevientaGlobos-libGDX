package com.cick.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Globito {
    static Texture ballonBlue = new Texture("ballon_blue.png");
    static Texture ballonGreen = new Texture("ballon_green.png");
    static Texture ballonRed = new Texture("ballon_red.png");

    public int size;
    Texture textura;
    int posX;
    int posY;
    int speed;

    public Globito() {
        Random random = new Random();

        int color = random.nextInt(3);
        System.out.println(color);
        if (color == 0){
            this.textura = ballonBlue;
        }else if (color==1){
            this.textura = ballonGreen;
        }else {
            this.textura = ballonRed ;
        }

        this.posX = random.nextInt(450)+10;
        this.posY = random.nextInt(340)+1;
        this.speed = random.nextInt(10)+1;
        this.size =  random.nextInt(50)+50;
    }

    public Globito(Texture textura, int posX, int posY, int size, int speed) {
        this.size = size;
        this.textura = textura;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }
}
