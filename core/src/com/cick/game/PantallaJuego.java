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

    private boolean paletilla = true;

    private int contador;
    private int movedor = 0;
    private Random random = new Random();
    private float alarmaCreadorDeGlobos= 2.5F;
    private float alarmaCambioColor = 6F;
    private float contadorDelta=0;

    private BitmapFont bitmapFont;
    List<Globito> arrayGlobitos = new ArrayList<>();

    SpriteBatch spriteBatch;
    Texture background, ballonRed, ballonGreen, ballonBlue, paleta, abc;

    private String colorTexto;
    private String palabra;
    private Color colorLetras;

    private static DecimalFormat df = new DecimalFormat("0");
    private float vida = 35;

    Sound sound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));

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
        paleta = new Texture("paleta.png");
        abc = new Texture("abc.png");
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        System.out.println(delta);
        contadorDelta+= delta;
        vida-= delta;

        // UPDATE COLOR AND TEXT
        if (alarmaCambioColor<contadorDelta){
            setPalete();
            setColorToText();
            alarmaCambioColor+=6F;
        }

        //CREADOR DE UN NUEVO GLOBO
        if (alarmaCreadorDeGlobos < contadorDelta) {
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

        //CHECK IF IS PRESSED ONE BALL
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            int mx = Gdx.input.getX();
            int my = Gdx.graphics.getHeight() - Gdx.input.getY();
            for(Globito globito: arrayGlobitos){
                if (globito.posX <= mx && globito.posX + globito.size >= mx && globito.posY <= my && globito.posY + globito.size >= my){
                    globito.eliminar = true;
                    if (paletilla){
                        if(globito.colorglobo.equals(colorTexto)) {
                            sound.play(1.0f);
                            contador++;
                        } else {
                            sound.play(1.0f);
                            contador--;
                        }
                    }else {
                        if(globito.colorglobo.equals(palabra)) {
                            sound.play(1.0f);
                            contador++;
                        } else {
                            sound.play(1.0f);
                            contador--;
                        }
                    }
                    break;
                }
            }
            arrayGlobitos.removeIf(globito -> globito.eliminar);
        }

        //MOVER GLOBITO i eliminar necesarios
        for (Globito globito : arrayGlobitos) {
            globito.move(globito);
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
        PrintTxt();

        if (vida <= 0.5){
            Gdx.input.setInputProcessor(null);
            setScreen(new PantallaGameOver(game, contador));
        }
        System.out.println(vida);
        spriteBatch.end();

    }

    private void PrintTxt() {
        bitmapFont.setColor(new Color(0,0,0,1));
        bitmapFont.draw(spriteBatch, "PUNTUACIÓN: "+contador,490, 450);

        if (vida < 5){
            bitmapFont.setColor(new Color(255,0,0,1));
        }else {
            bitmapFont.setColor(new Color(255,255,255,1));
        }
        bitmapFont.draw(spriteBatch, "SEGUNDOS: "+df.format(vida),250, 450);

        if (colorLetras == null){
            setColorToText();
        }
        if (palabra == null){
            setPalabra();
        }

        bitmapFont.setColor(colorLetras);
        bitmapFont.draw(spriteBatch, ""+ palabra.toUpperCase(),15, 450);
        if (paletilla) {
            spriteBatch.draw(paleta, 15, 380, 45, 45);
        }else {
            spriteBatch.draw(abc, 15, 380, 45, 45);
        }
    }

    private void setColorToText() {
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

    private void setPalete() {
        int letras = random.nextInt(2);
        if (letras==0){
            paletilla = true;
        }else {
            paletilla = false;
        }
        setPalabra();
    }

    private void setPalabra() {
        int x = random.nextInt(3);
        if (x == 0){
            palabra = "blue";
        }else if (x == 1){
            palabra = "green";
        }else{
            palabra = "red";
        }
    }
}
