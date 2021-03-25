package com.cick.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

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
    public float speedY, speedX, accX;
    public float alarmaCambioDireccion;
    public boolean eliminar;

    Random random = new Random();
    private float leftLimit = -0.87F;
    private float rightLimit = 0.87F;

    public Globito() {
        int color = random.nextInt(3);

        if (color == 0){
            textura = ballonBlue;
            colorglobo = "blue";
        }else if (color==1){
            textura = ballonGreen;
            colorglobo = "green";
        }else {
            textura = ballonRed ;
            colorglobo = "red";
        }

        posX = random.nextInt(450)+10;

        speedY = 0.12F + random.nextFloat() * (2 - 0.08F);
        setRandomSpeedX();
        setRandomAccX();
        size =  random.nextInt(25)+50;

    }

    public void move(float gameTime) {
        if (posY+ speedY > 640){
            eliminar = true;
        }else {
            if (gameTime > alarmaCambioDireccion){
                setRandomSpeedX();
                setRandomAccX();
                alarmaCambioDireccion = gameTime + 1;
            }
            posY+=speedY;
            posX+=speedX;
        }

        speedX += accX;
    }

    void setRandomSpeedX(){
        speedX = MathUtils.random(rightLimit, leftLimit);
    }

    void setRandomAccX(){
        accX = MathUtils.random(-0.05f, 0.05f);
    }
}
