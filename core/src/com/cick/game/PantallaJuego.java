package com.cick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PantallaJuego extends BaseScreen {

    private Random random = new Random();
    private Hood hood = new Hood(new Texture("paleta.png"), new Texture("abc.png"));

    private int movedor = 0;
    private float alarmaCreadorDeGlobos= 2.5F;
    private float alarmaCambioColor = 6F;
    private float gameTime =0;

    private BitmapFont bitmapFont;
    private SpriteBatch spriteBatch;
    private ArrayList<Globito> arrayGlobitos = new ArrayList<>();
    private Texture background, ballonRed, ballonGreen, ballonBlue;

    private static DecimalFormat df = new DecimalFormat("0");
    private float vida = 35;

    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));

    public PantallaJuego(Main game) { super(game); }

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

        gameTime += delta;
        vida-= delta;

        // UPDATE COLOR AND TEXT
        if (gameTime > alarmaCambioColor){
            hood.setPalete();
            hood.setColorToText();
            alarmaCambioColor+=6F;
        }

        //CREADOR DE UN NUEVO GLOBO
        crearNuevoGlobo();

        //CHECK IF IS PRESSED ONE BALL
        comprobarColision();

        //MOVER GLOBITO i eliminar necesarios
        moverGlobitosyEliminarlos();

        // RENDER
        render();

        comprovarSiQuedaVida();
        spriteBatch.end();

    }

    private void render() {
        //printamos background
        spriteBatch.draw(background, 0, 0, 640, 480);

        //pintamos los globitos
        for(Globito globito:arrayGlobitos){
            spriteBatch.draw(globito.textura,globito.posX,globito.posY,globito.size,globito.size);
        }

        //puntuación y globos a petar
        PrintTxt();
    }

    private void moverGlobitosyEliminarlos() {
        for (Globito globito : arrayGlobitos) {
            globito.move(gameTime);
        }
        arrayGlobitos.removeIf(globito -> globito.eliminar);
    }

    private void comprovarSiQuedaVida() {
        if (vida <= 0.5){
            Gdx.input.setInputProcessor(null);
            setScreen(new PantallaGameOver(game, hood.contador));
        }
    }

    private void crearNuevoGlobo() {
        if (alarmaCreadorDeGlobos < gameTime) {
            arrayGlobitos.add(new Globito());

            if (hood.contador >30){
                alarmaCreadorDeGlobos+=0.3F;
            }else if (hood.contador>25){
                alarmaCreadorDeGlobos+=0.40F;
            }else if (hood.contador>20){
                alarmaCreadorDeGlobos+=0.55F;
            }else if (hood.contador>15){
                alarmaCreadorDeGlobos+=0.85F;
            }else {
                alarmaCreadorDeGlobos += 1F;
            }
        }
    }

    private void comprobarColision() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            int mx = Gdx.input.getX();
            int my = Gdx.graphics.getHeight() - Gdx.input.getY();
            for(Globito globito: arrayGlobitos){
                if (globito.posX <= mx && globito.posX + globito.size >= mx && globito.posY <= my && globito.posY + globito.size >= my){
                    globito.eliminar = true;
                    if (hood.paletilla){
                        if(globito.colorglobo.equals(hood.colorTexto)) {
                            sound.play(1.0f);
                            hood.contador++;
                        } else {
                            sound.play(1.0f);
                            hood.contador--;
                        }
                    }else {
                        if(globito.colorglobo.equals(hood.palabra)) {
                            sound.play(1.0f);
                            hood.contador++;
                        } else {
                            sound.play(1.0f);
                            hood.contador--;
                        }
                    }
                    break;
                }
            }
            arrayGlobitos.removeIf(globito -> globito.eliminar);
        }
    }

    private void PrintTxt() {
        bitmapFont.setColor(new Color(0,0,0,1));
        bitmapFont.draw(spriteBatch, "PUNTUACIÓN: "+hood.contador,490, 450);

        if (vida < 5){
            bitmapFont.setColor(new Color(255,0,0,1));
        }else {
            bitmapFont.setColor(new Color(255,255,255,1));
        }
        bitmapFont.draw(spriteBatch, "SEGUNDOS: "+df.format(vida),250, 450);

        if (hood.colorLetras == null){
            hood.setColorToText();
        }
        if (hood.palabra == null){
            hood.setPalabra();
        }

        bitmapFont.setColor(hood.colorLetras);
        bitmapFont.draw(spriteBatch, ""+ hood.palabra.toUpperCase(),15, 450);
        if (hood.paletilla) {
            spriteBatch.draw(hood.paleta, 15, 380, 45, 45);
        }else {
            spriteBatch.draw(hood.abc, 15, 380, 45, 45);
        }
    }
}
