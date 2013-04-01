package ar.sjb.state;

import java.util.ArrayList;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import org.flixel.FlxU;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;




import ar.sjb.entity.BulletMode;
import ar.sjb.entity.Player;

public class PlayState extends FlxState {

	/**
	 * Referencia al archivo de Tiled
	 */
	public TiledMap map;
	
	/**
	 * El mapa de collision/vista
	 */
	public FlxTilemap level;
	
	
	/**
	 * Puntos de inicio de los jugadores
	 */
	public ArrayList<FlxPoint> spawsPoint;
	
	private FlxSprite floor;
	private FlxGroup player_list;
	private FlxGroup bullets;
	
	
	
	public PlayState(String mapName) {
		this.map = TiledLoader.createMap(Gdx.files.internal("assets/maps/" + mapName +".tmx"));
	}

	@Override
	public void create() {
		
		level = new FlxTilemap();
		level.loadMap(FlxTilemap.tiledmapToCSV(map, 0), "assets/maps/tiles.png", 8, 8, FlxTilemap.OFF, 1);
		
		add(level);
		
		FlxG.setBgColor(FlxU.makeColor(76, 76, 76));
		
		FlxG.log("Width : " + FlxG.width + " | Height" + FlxG.height  );

		player_list = new FlxGroup();
		bullets = new FlxGroup();

		Player p = new Player(20, 20, null, bullets);
		player_list.add(p);
		
		add(player_list);
		
		add(bullets);

	}
	
	@Override
	public void update() {
		
		FlxG.collide(level, player_list);
		FlxG.collide(level, bullets);
		
		if(FlxG.keys.R){
			FlxG.switchState(new PlayState("lvl1"));
		}
		
		super.update();
	}

}
