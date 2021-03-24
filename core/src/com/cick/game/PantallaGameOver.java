package com.cick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PantallaGameOver extends BaseScreen{

    private BitmapFont bitmapFont;
    private SpriteBatch spriteBatch;
    private Texture background;
    private Stage stage;
    private int contador;

    public PantallaGameOver(Main game, int contador) {
        super(game);
        this.contador = contador;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        bitmapFont = new BitmapFont();

        //Cargas los recursos a utilizar
        background = new Texture("gameover.jpg");
        // Boton START
        BaseButton buttonStart = new BaseButton("button_start_up.png","button_start_over.png",280,200,24*3,10*3, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.setInputProcessor(null);
                setScreen(new PantallaJuego(game));
                return true;
            }
        });

        // Boton QUIT
        BaseButton buttonQuit = new BaseButton("button_quit_up.png","button_quit_over.png",280,160,24*3,10*3, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                System.exit(0);
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage = new Stage());
        stage.addActor(buttonStart);
        stage.addActor(buttonQuit);
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //printamos background
        stage.act();
        stage.draw();
        printTxt();

        spriteBatch.end();
    }

    public void printTxt() {
        bitmapFont.setColor(new Color(0,0,0,1));
        bitmapFont.draw(spriteBatch, "PUNTUACIÓN TOTAL : "+contador,240, 380);

        bitmapFont.draw(spriteBatch, "GAME OVER",280, 300);

    }
}
