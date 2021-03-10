package com.cick.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaJuego extends BaseScreen {

    public PantallaJuego(Main game) { super(game); }
    private int x = 0;
    private int y = 4;



    SpriteBatch spriteBatch;
    Texture background, ballonRed, ballonGreen, ballonBlue;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        //Cargas los recursos a utilizar
        background = new Texture("background.png");
        ballonRed = new Texture("ballon_red.png");
        ballonGreen = new Texture("ballon_green.png");
        ballonBlue = new Texture("ballon_blue.png");
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        //los utilizamos

        spriteBatch.draw(background, 0, 0, 640, 480);
        spriteBatch.draw(ballonRed, x, y);
        if (x == Gdx.graphics.getWidth()) {
            x = 0;
        } else {
            x++;
        }


        spriteBatch.end();
    }
}
