/**
 * Main Game class
 */

package com.aaa.pingpong;

import com.aaa.pingpong.screens.GameScreen;
import com.aaa.pingpong.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class PingPongGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen());
		
	}
	
}
