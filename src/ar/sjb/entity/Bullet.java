package ar.sjb.entity;

import org.flixel.*;

public class Bullet extends FlxSprite {
    private String ImgBullet = "assets/img/bullet_scb.png";

    public float speed;

    public Bullet() {
		super();
		loadGraphic(ImgBullet);
		width = 4;
		height = 4;
		offset.x = 1;
		offset.y = 1;
	
		speed = 360;
    }

    @Override
    public void update() {
	if (!alive) {
	    if (finished){
		exists = false;
	    }
	} else if (touching > NONE)
	    kill();
    }

    @Override
    public void kill() {
		if (!alive){
		    return;
		}
		velocity.x = 0;
		velocity.y = 0;
		alive = false;
		setSolid(false);
    }

    public void shoot(FlxPoint Location, int Aim) {
	super.reset(Location.x - width / 2, Location.y - height / 2);
	setSolid(true);
	switch (Aim) {
	case LEFT:
	    velocity.x = -speed;
	    break;
	case RIGHT:
	    velocity.x = speed;
	    break;
	default:
	    break;
	}
    }
}