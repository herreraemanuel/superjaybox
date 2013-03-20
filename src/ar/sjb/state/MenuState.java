package ar.sjb.state;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxU;

import ar.sjb.entity.Player;

public class MenuState extends FlxState {

	private FlxSprite floor;
	private FlxGroup player_list;
	private FlxGroup bullets;

	@Override
	public void create() {
		
		FlxG.setBgColor(FlxU.makeColor(76, 76, 76));

		player_list = new FlxGroup();
		bullets = new FlxGroup();

		int init = 40;


		Player p = new Player(20, 20, null, bullets);
		player_list.add(p);
		
		add(player_list);
		
		add(bullets);
		
		floor = new FlxSprite(0, 0);
		floor.makeGraphic(FlxG.width, 20, FlxU.makeColor(255, 0, 0));
		floor.y = FlxG.height - (floor.height / 2);
		floor.setSolid(true);
		floor.immovable = true;
		add(floor);
	}
	
	@Override
	public void update() {
		
		FlxG.collide(floor, player_list);
		
		super.update();
	}

}
