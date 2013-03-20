package ar.sjb.entity;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxSprite;

import com.badlogic.gdx.controllers.Controller;

public class Player extends FlxSprite {
    protected String ImgSpaceman = "assets/img/create.png";

    protected int _jumpPower;
    protected int _aim;
    protected float _restart;
    protected Controller controller;

    private FlxGroup bullets;
    
    
    

    // This is the player object class. Most of the comments I would put in here
    // would be near duplicates of the Enemy class, so if you're confused at all
    // I'd recommend checking that out for some ideas!
    public Player(int X, int Y, Controller controller, FlxGroup bullets) {
	super(X, Y);
	loadGraphic(ImgSpaceman, true, true, 10, 10);
	_restart = 0;

	// bounding box tweaks
	// width = 6;
	// height = 7;
	offset.x = 1;
	offset.y = 1;

	// basic player physics
	int runSpeed = 80;
	drag.x = runSpeed * 8;
	acceleration.y = 420;
	_jumpPower = 200;
	maxVelocity.x = runSpeed;
	maxVelocity.y = _jumpPower;

	// animations
	addAnimation("idle", new int[] { 0, 1, 2, 3, 4 }, 12);
	addAnimation("run", new int[] { 5, 6, 7, 8, 9 }, 12);
	addAnimation("jump", new int[] { 10, 11, 12 }, 12);

	this.controller = controller;
	this.bullets = bullets;
    }

    @Override
    public void destroy() {
	super.destroy();
    }

    @Override
    public void update() {
	if (controller != null) {
	    
	    // MOVEMENT GAMEPAD
		
		//TODO GAMEPAD
		
//	    acceleration.x = 0;
//	    if (controller.getAxis(DefaultValue.AXIS_H) == -1) {
//		moveLeft();
//	    } 
//	    else if (controller.getAxis(DefaultValue.AXIS_H) == 1) {
//		moveRight();
//	    }
//	    
//	    if (controller.getButton(DefaultValue.BTO_JUMP))  {
//		jump();
//	    }
//	    
//	    if(controller.getButton(DefaultValue.BTO_SHOOT)){
//		shoot();
//	    }
	    
	} else {

	    // MOVEMENT KEY
	    acceleration.x = 0;
	    if (FlxG.keys.LEFT) {
		moveLeft();
	    }
	    else if (FlxG.keys.RIGHT) {
		moveRight();
	    }
	    
	    if (FlxG.keys.justPressed("X")) {
		jump();
	    }
	    
	    if(FlxG.keys.justPressed("C")){
		shoot();
	    }
	    
	}

	// ANIMATION
	if (velocity.y != 0) {
	    play("jump");
	} else if (velocity.x == 0) {
	    play("idle");
	} else {
	    play("run");
	}
    }

    /**
     * Mover al jugador a la izq
     */
    public void moveLeft() {
	setFacing(LEFT);
	acceleration.x -= drag.x;
	_aim = LEFT;
    }

    /**
     * Mover al jugador a la derecha
     */
    public void moveRight() {
	setFacing(RIGHT);
	acceleration.x += drag.x;
	_aim = RIGHT;
    }

    /**
     * Saltar el jugador
     */
    public void jump() {
	if ((int) velocity.y == 0 ) {
	    velocity.y = -_jumpPower;
	}
    }
    
    public void shoot(){
	getMidpoint(_point);
	((Bullet) bullets.recycle(Bullet.class)).shoot(_point,_aim);
    }
    

    @Override
    public void kill() {
	if (!alive){
	    return;
	}
	setSolid(false);
	super.kill();
	exists = true;
	visible = false;
	velocity.make();
	acceleration.make();
	FlxG.camera.shake(0.005f, 0.35f);
	FlxG.camera.flash(0xffd8eba2, 0.35f);
    }

}
