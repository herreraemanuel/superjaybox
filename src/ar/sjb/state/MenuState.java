package ar.sjb.state;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;

public class MenuState extends FlxState {

	@Override
	public void create() {

		FlxText t;
		t = new FlxText(0,FlxG.height/2-10,FlxG.width,"Super Jay Box");
		t.setSize(16);
		t.setAlignment("center") ;
		add(t);
		t = new FlxText(FlxG.width/2-50,FlxG.height-20,100,"click to play");
		t.setAlignment("center") ;
		add(t);

	}
	
	@Override
	public void update() {
		super.update();

		if(FlxG.mouse.justPressed())
		{
			FlxG.switchState(new PlayState("lvl1"));
		}
	}

}
