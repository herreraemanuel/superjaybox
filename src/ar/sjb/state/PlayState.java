package ar.sjb.state;

import java.util.ArrayList;

import org.flixel.FlxEmitter;
import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxPoint;
import org.flixel.FlxState;
import org.flixel.FlxTilemap;
import org.flixel.FlxU;
import org.flixel.event.IFlxCollision;

import ar.sjb.entity.Bullet;
import ar.sjb.entity.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObject;
import com.badlogic.gdx.graphics.g2d.tiled.TiledObjectGroup;
import com.badlogic.gdx.utils.Array;

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
	public Array<FlxPoint> spawsPoint;
	
	private FlxGroup player_list;
	private FlxGroup bullets;
	
	private String ImgGibs = "assets/img/gibs.png";

	private FlxEmitter _littleGibs;
	
	
	
	public PlayState(String mapName) {
		this.map = TiledLoader.createMap(Gdx.files.internal("assets/maps/" + mapName +".tmx"));
	}

	@Override
	public void create() {
		FlxG.setBgColor(FlxU.makeColor(76, 76, 76));
		
		spawsPoint = new Array<FlxPoint>();
		
		level = new FlxTilemap();
		level.loadMap(FlxTilemap.tiledmapToCSV(map, 0), "assets/maps/tiles.png", 8, 8, FlxTilemap.OFF, 1);
		
		add(level);
		
		for(TiledObjectGroup group : map.objectGroups)
		{
			if(group.name.equals("player_spawn")){
				
				for(TiledObject object : group.objects)
				{
					spawsPoint.add(new FlxPoint(object.x, object.y));
					
				}
			}
		}
		
		
		FlxG.log("Width : " + FlxG.width + " | Height" + FlxG.height  );
		
		_littleGibs = new FlxEmitter();
		_littleGibs.setXSpeed(-150,150);
		_littleGibs.setYSpeed(-200,0);
		_littleGibs.setRotation(-720,-720);
		_littleGibs.gravity = 350;
		_littleGibs.bounce = 0.5f;
		_littleGibs.makeParticles(ImgGibs,100,10,true,0.5f);

		player_list = new FlxGroup();
		bullets = new FlxGroup();

		Player p1 = new Player(0, 0, null, bullets, _littleGibs, "player1", spawsPoint);
		player_list.add(p1);
		
		Player p2 = new Player(0, 0, null, bullets, _littleGibs, "player2", spawsPoint);
		player_list.add(p2);
		
		

		add(player_list);
		
		add(bullets);
		
		add(_littleGibs);
		

		

	}
	
	@Override
	public void update() {
		
		FlxG.collide(level, player_list);
		FlxG.collide(level, bullets);
		FlxG.collide(level, _littleGibs);
		FlxG.collide(bullets, player_list, bulletPlayer );
		
		
		if(FlxG.keys.R){
			FlxG.switchState(new PlayState("lvl1"));
		}
		
		super.update();
	}
	
	IFlxCollision bulletPlayer = new IFlxCollision() {
		
		@Override
		public void callback(FlxObject Object1, FlxObject Object2) {
			Bullet bullet = (Bullet) Object1;
			Player player = (Player) Object2;
			if(!player.name.equals(bullet.emiter)){
				player.kill();
			}else {
				return;
			}
			System.out.println("collision");
			
		}
	};
	
	
	

}
