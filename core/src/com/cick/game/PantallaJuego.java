package com.cick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PantallaJuego extends BaseScreen {

    public PantallaJuego(Main game) { super(game); }

    boolean move_left = false;
    private int contador;
    private int movedor = 0;
    private Random random = new Random();
    private float alarmaCreadorDeGlobos= 2.5F;
    private float alarmaCambioColor = 6F;
    private float contadorDelta=0;
    private String colorglobo = "green";
    private BitmapFont bitmapFont;
    List<Globito> arrayGlobitos = new ArrayList<>();



    SpriteBatch spriteBatch;
    Texture background, ballonRed, ballonGreen, ballonBlue;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        bitmapFont = new BitmapFont();

        //Cargas los recursos a utilizar
        background = new Texture("background.png");
        ballonRed = new Texture("ballon_red.png");
        ballonGreen = new Texture("ballon_green.png");
        ballonBlue = new Texture("ballon_blue.png");
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        contadorDelta+= delta;

        if (alarmaCambioColor<contadorDelta){
            int color = random.nextInt(3);
            if (color == 0){
                colorglobo = "blue";
            }else if (color==1){
                colorglobo = "green";
            }else {
                colorglobo = "red";
            }
            alarmaCambioColor+=6F;
        }

        //CREAR UN NUEVO GLOBO
        if (alarmaCreadorDeGlobos < contadorDelta){
            System.out.println("nou globito");
            arrayGlobitos.add(new Globito());
            if (contador >= 12){
                alarmaCreadorDeGlobos+=0.5F;
            }else if (contador>=30){
                alarmaCreadorDeGlobos+=0.25F;
            }else if (contador>=33){
                alarmaCreadorDeGlobos+=0.1F;
            }else {
                alarmaCreadorDeGlobos+=0.75F;
            }
        }

        //TODO: mejorar la precision
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            for (int i = 0; i < arrayGlobitos.size(); i++) {
                if (arrayGlobitos.get(i).posX+50 >= Gdx.input.getX() && arrayGlobitos.get(i).posX-50 <= Gdx.input.getX() && arrayGlobitos.get(i).posY+50 >= (Gdx.graphics.getHeight()-Gdx.input.getY()) && arrayGlobitos.get(i).posY-50 <= (Gdx.graphics.getHeight()-Gdx.input.getY()) ){
                    arrayGlobitos.remove(i);
//                    if(arrayGlobitos.get(i).colorglobo.equals(colorglobo)) {
//                        contador++;
//                    }else contador--;
                }
            }
        }

        //printamos background
        spriteBatch.draw(background, 0, 0, 640, 480);

        //pintamos los globitos y movemos una posición hacia arriaba y a los lados.
        for (int i = 0; i < arrayGlobitos.size(); i++) {
            spriteBatch.draw(arrayGlobitos.get(i).textura,arrayGlobitos.get(i).posX,arrayGlobitos.get(i).posY,arrayGlobitos.get(i).size,arrayGlobitos.get(i).size);
            if (arrayGlobitos.get(i).posY+1 == 640){
                arrayGlobitos.remove(i);
            }else {
                Globito e;
                if (arrayGlobitos.get(i).movedor == true ) {
                    if (arrayGlobitos.get(i).contador_movedor == 45){
                        arrayGlobitos.get(i).movedor = false;
                        arrayGlobitos.get(i).contador_movedor = 0;
                    }
                    e = new Globito(arrayGlobitos.get(i).textura,arrayGlobitos.get(i).posX+1,arrayGlobitos.get(i).posY+1,arrayGlobitos.get(i).size,arrayGlobitos.get(i).speed,arrayGlobitos.get(i).movedor, arrayGlobitos.get(i).contador_movedor+1);
                }else {
                    if (arrayGlobitos.get(i).contador_movedor == 45){
                        arrayGlobitos.get(i).movedor = true;
                        arrayGlobitos.get(i).contador_movedor = 0;
                    }
                    e = new Globito(arrayGlobitos.get(i).textura,arrayGlobitos.get(i).posX-1,arrayGlobitos.get(i).posY+1,arrayGlobitos.get(i).size,arrayGlobitos.get(i).speed,arrayGlobitos.get(i).movedor,arrayGlobitos.get(i).contador_movedor+1);
                }

                arrayGlobitos.set(i, e);

            }
        }




        //puntuación y globos a petar
        bitmapFont.draw(spriteBatch, "PUNTUACIÓN: "+contador,250, 300);
        bitmapFont.draw(spriteBatch, ""+colorglobo.toUpperCase(),10, 450);

        //MOVER BOLA
//        if (x == Gdx.graphics.getWidth()) {
//            x = 0;
//        }
//        else {
//            if (move_left){
//                movedor++;
//                y++;
//                if (movedor == 45){
//                    move_left=false;
//                    movedor=0;
//                }
//
//            }
//            else {
//                movedor++;
//                y--;
//                if (movedor == 45){
//                    move_left=true;
//                    movedor=0;
//                }
//            }
//            x++;
//        }
        spriteBatch.end();
    }
}
