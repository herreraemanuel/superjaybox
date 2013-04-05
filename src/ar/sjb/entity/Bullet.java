package ar.sjb.entity;

import org.flixel.*;

public class Bullet extends FlxSprite
{
	private String ImgBullet = "assets/img/bullet.png";
//	private String SndHit = "examples/mode/jump.mp3";
//	private String SndShoot = "examples/mode/shoot.mp3";
	
	
	
	public float speed;
	public String emiter;

	public Bullet()
	{
		super();
		loadGraphic(ImgBullet,true);
		width = 6;
		height = 6;
		offset.x = 1;
		offset.y = 1;

		addAnimation("up",new int[]{0});
		addAnimation("down",new int[]{1});
		addAnimation("left",new int[]{2});
		addAnimation("right",new int[]{3});
		addAnimation("poof",new int[]{4, 5, 6, 7}, 50, false);

		speed = 600;
	}

	@Override
	public void update()
	{
		if(!alive)
		{
			if(finished)
				exists = false;
		}
		else if(touching > NONE)
			kill();
	}

	@Override
	public void kill()
	{
		if(!alive)
			return;
		velocity.x = 0;
		velocity.y = 0;
//		if(onScreen())
//			FlxG.play(SndHit);
		alive = false;
		setSolid(false);
		play("poof");
	}

	public void shoot(FlxPoint Location, int Aim, String emiter)
	{
		this.emiter = emiter;
//		FlxG.play(SndShoot);
		super.reset(Location.x-width/2 ,Location.y-height/2);
		setSolid(true);
		switch(Aim)
		{
			case LEFT:
				play("left");
				velocity.x = -speed;
				break;
			case RIGHT:
				play("right");
				velocity.x = speed;
				break;
			default:
				break;
		}
	}
}