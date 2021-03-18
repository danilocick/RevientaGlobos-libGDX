package com.cick.game;

import com.badlogic.gdx.graphics.Texture;
import com.sun.management.internal.VMOptionCompositeData;

import java.util.Random;

public class Globito {
    static Texture ballonBlue = new Texture("ballon_blue.png");
    static Texture ballonGreen = new Texture("ballon_green.png");
    static Texture ballonRed = new Texture("ballon_red.png");

    public Texture textura;
    public String colorglobo;
    public int posX;
    public int posY;
    public int size;
    public int speed;
    public boolean movedor;
    public int contador_movedor;
    public boolean eliminar;

    public Globito() {
        Random random = new Random();

        int color = random.nextInt(3);
        System.out.println(color);
        if (color == 0){
            this.textura = ballonBlue;
            colorglobo = "blue";
        }else if (color==1){
            this.textura = ballonGreen;
            colorglobo = "green";
        }else {
            this.textura = ballonRed ;
            colorglobo = "red";
        }

        this.posX = random.nextInt(450)+10;
        this.posY = random.nextInt(340)+1;
        this.speed = random.nextInt(2)+1;
        this.size =  random.nextInt(50)+50;
        if (size % 2 ==0){
            movedor = true;
        }else movedor = false;

        contador_movedor =0;
    }
}
