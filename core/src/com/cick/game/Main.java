package com.cick.game;

import com.badlogic.gdx.Game;

public class Main extends Game {

	@Override
	public void create(){
		setScreen(new PantallaJuego(this));
	}
	@Override
	public void render () {
		super.render();
	}
}
