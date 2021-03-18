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

        // UPDATE
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
        if (alarmaCreadorDeGlobos < contadorDelta) {
            System.out.println("nou globito");
            arrayGlobitos.add(new Globito());

            if (contador >30){
                alarmaCreadorDeGlobos+=0.3F;
            }else if (contador>25){
                alarmaCreadorDeGlobos+=0.40F;
            }else if (contador>20){
                alarmaCreadorDeGlobos+=0.55F;
            }else if (contador>15){
                alarmaCreadorDeGlobos+=0.85F;
            }else {
                alarmaCreadorDeGlobos += 1F;
            }
        }

        //TODO: mejorar la precision
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            int mx = Gdx.input.getX();
            int my = Gdx.graphics.getHeight() - Gdx.input.getY();
            for(Globito globito: arrayGlobitos){
//                if ((globito.posX + globito.size/2) + globito.size/2 >= mx && (globito.posX + globito.size/2) - globito.size/2 <= mx && (globito.posY + globito.size/2) + globito.size/2 >= my && (globito.posY + globito.size/2) + globito.size/2 <= my){
                  if (globito.posX <= mx && globito.posX + globito.size >= mx && globito.posY <= my && globito.posY + globito.size >= my){
                    globito.eliminar = true;
                    if(globito.colorglobo.equals(colorglobo)) {
                        contador++;
                    } else {
                        contador--;
                    }
                }
            }
            arrayGlobitos.removeIf(globito -> globito.eliminar);
        }

        for (Globito globito : arrayGlobitos) {
            if (globito.posY+1 == 640){
                globito.eliminar = true;
            }else {
                if (globito.movedor) {
                    if (globito.contador_movedor == 45){
                        globito.movedor = false;
                        globito.contador_movedor = 0;
                    }
                    globito.posX++;
                    globito.posY+=globito.speed;
                    globito.contador_movedor++;
                }else {
                    if (globito.contador_movedor == 45){
                        globito.movedor = true;
                        globito.contador_movedor = 0;
                    }
                    globito.posX--;
                    globito.posY+=globito.speed;
                    globito.contador_movedor++;
                }
            }
        }
        arrayGlobitos.removeIf(globito -> globito.eliminar);

        // RENDER
        //printamos background
        spriteBatch.draw(background, 0, 0, 640, 480);

        //pintamos los globitos y movemos una posición hacia arriaba y a los lados.
        for(Globito globito:arrayGlobitos){
            spriteBatch.draw(globito.textura,globito.posX,globito.posY,globito.size,globito.size);
        }
        //puntuación y globos a petar
        bitmapFont.draw(spriteBatch, "PUNTUACIÓN: "+contador,250, 300);
        bitmapFont.draw(spriteBatch, ""+colorglobo.toUpperCase(),12, 465);

        spriteBatch.end();
    }
}
