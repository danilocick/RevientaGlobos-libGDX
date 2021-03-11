package com.cick.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Globito {
    static Texture ballonBlue = new Texture("ballon_blue.png");
    static Texture ballonGreen = new Texture("ballon_blue.png");
    static Texture ballonRed = new Texture("ballon_blue.png");

    public int size;
    Texture textura;
    int posX;
    int posY;
    int speed;

    public Globito() {
        Random random = new Random();

        int color= random.nextInt(3);
        if (color == 0){
            this.textura = ballonBlue;
        }else if (color==1){
            this.textura = ballonGreen;
        }else {
            this.textura = ballonRed ;
        }

        this.posX = random.nextInt(100);
        this.posY = random.nextInt(100)+1;
        this.speed = random.nextInt(10)+1;
        this.size =  random.nextInt(400)+1;
    }

}
