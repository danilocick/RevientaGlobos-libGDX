package com.cick.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Globito {
    static Texture ballonBlue = new Texture("ballon_blue.png");
    static Texture ballonGreen = new Texture("ballon_green.png");
    static Texture ballonRed = new Texture("ballon_red.png");

    public Texture textura;
    public String colorglobo;
    public int posX;
    public float posY;
    public int size;
    public float speed;
    public boolean direccion;
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
        this.posY = 0;
        this.speed = 0.12F + random.nextFloat() * (2 - 0.08F);
        this.size =  random.nextInt(25)+50;
        if (size % 2 ==0){
            direccion = true;
        }else direccion = false;

        contador_movedor =0;
    }

    public void move(Globito globito) {
        if (globito.posY+ globito.speed == 640){
            globito.eliminar = true;
        }else {
            if (globito.direccion) { //mover izquierda
                if (globito.contador_movedor == 45){
                    globito.direccion = false;
                    globito.contador_movedor = 0;
                }
                globito.posX++;
                globito.posY+=globito.speed;
                globito.contador_movedor++;

            }
            else { //mover derecha
                if (globito.contador_movedor == 45){
                    globito.direccion = true;
                    globito.contador_movedor = 0;
                }
                globito.posX--;
                globito.posY+=globito.speed;
                globito.contador_movedor++;
            }
        }
    }
}
