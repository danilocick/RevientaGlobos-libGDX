package com.cick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PantallaJuego extends BaseScreen {

    public PantallaJuego(Main game) { super(game); }
    boolean move_left = false;
    private int contador;
    private int movedor = 0;
    private int alarmaCreadorDeGlobos=3;
    private int contadorDelta=0;
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
        contadorDelta+=delta;
        if (alarmaCreadorDeGlobos < delta){
            System.out.println("hola");
            arrayGlobitos.add(new Globito());
            alarmaCreadorDeGlobos+=3;
        }

        //los utilizamos
        spriteBatch.draw(background, 0, 0, 640, 480);
        for (int i = 0; i < arrayGlobitos.size(); i++) {
            spriteBatch.draw(arrayGlobitos.get(i).textura,arrayGlobitos.get(i).posX,arrayGlobitos.get(i).posY,arrayGlobitos.get(i).size,arrayGlobitos.get(i).size);
        }



        bitmapFont.draw(spriteBatch, "PUNTUACIÓN: "+contador,300, 300);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            contador++;
        }

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
