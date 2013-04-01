package ar.sjb;

import org.flixel.FlxG;
import org.flixel.FlxGame;

import ar.sjb.state.MenuState;
import ar.sjb.state.PlayState;

public class SJB extends FlxGame{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final int GAME_ZOOM = 3;

	public SJB() {
		super(GAME_WIDTH / GAME_ZOOM, GAME_HEIGHT / GAME_ZOOM, MenuState.class, GAME_ZOOM);
		FlxG.debug = true;

	}

}
