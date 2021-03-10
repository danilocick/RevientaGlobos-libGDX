package com.cick.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BaseButton extends ImageButton{

    public BaseButton(String imageStyleUp, String imageStyleOver, int posX, int posY, int sizeWitdh, int sizeHeigth, InputListener inputListener) {
        super(new ImageButton.ImageButtonStyle());

        getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture(imageStyleUp)));
        getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture(imageStyleOver)));

        setPosition(posX, posY);
        setSize(sizeWitdh, sizeHeigth);
        addListener(inputListener);

    }
}
