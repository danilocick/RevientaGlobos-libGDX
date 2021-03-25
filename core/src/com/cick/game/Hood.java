package com.cick.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Hood {
    public boolean paletilla = true;
    public Texture paleta, abc;
    public String palabra;
    public int contador;;

    private Random random = new Random();
    public Color colorLetras;
    public String colorTexto;

    public Hood(Texture paleta, Texture abc) {
        this.paleta = paleta;
        this.abc = abc;
    }

    public void setPalabra() {
        int x = random.nextInt(3);
        if (x == 0){
            palabra = "blue";
        }else if (x == 1){
            palabra = "green";
        }else{
            palabra = "red";
        }
    }

    public void setPalete() {
        int letras = random.nextInt(2);
        if (letras==0){
            paletilla = true;
        }else {
            paletilla = false;
        }
        setPalabra();
    }

    public void setColorToText() {
        int color = random.nextInt(3);
        if (color == 0){
            colorLetras = new Color(0, 0, 255f,1);
            colorTexto = "blue";
        }else if (color==1){
            colorLetras = new Color(0, 255, 0, 1);
            colorTexto = "green";
        }else {
            colorLetras = new Color(255, 0, 0, 1);
            colorTexto = "red";
        }
    }

}
