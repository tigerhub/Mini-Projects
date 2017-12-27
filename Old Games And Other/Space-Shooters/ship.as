package {

	import flash.display.*;
	import flash.events.*;
	import flash.ui.*;
	import flash.events.KeyboardEvent;
	import flash.geom.ColorTransform;
	//import fl.events.ListEvent;

    
	
	public class ship extends MovieClip {

public var rightArrow:Boolean;
	public var leftArrow:Boolean;
	public var upArrow:Boolean;
	public var downArrow:Boolean;
static public var stay= 0;
static public var angle=0;
static public var shoot=false;
public var shootAr=new Array(2);

//public var bullet1;

public var gun=new ((new gun1()).constructor);
 		
		public function ship() {
			
			addChild(gun);
			addEventListener(Event.ADDED_TO_STAGE, init);
	}

	private function init(e:Event=null):void {

stage.addEventListener(MouseEvent.MOUSE_DOWN, mouseHandler);
			stage.addEventListener(MouseEvent.MOUSE_MOVE, mouseUpHandler2);
			stage.addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler3);
			//stage.addEventListener(KeyboardEvent.KEY_DOWN, keyPressed);
		//stage.addEventListener(KeyboardEvent.KEY_UP, keyReleased);

function mouseHandler(evt:MouseEvent):void {
			
			
			ship.stay= stuff();
			ship.shoot=true;
			

}


function mouseUpHandler3(evt:MouseEvent):void {
			
			
			ship.stay= stuff();
			ship.shoot=false;
			

}
function mouseUpHandler2(evt:MouseEvent):void {
	//instead of gun.x/.y; set it to the .x/.y of the center pt of the ship; 
	
			  var dx:Number = mouseX+120;
  var dy:Number = mouseY+20;
    var rads:Number = Math.atan2( dy, dx);
	var r2d = (180 / Math.PI);
			
  gun.rotation = (rads * r2d);
 
    ship.angle=rads;
			
		}
		

	}
static public function stuff()

{
	return ship.angle;
}

public function REturnerR()
{
	return rightArrow;
}

public function REturnerL()
{
	return leftArrow;
}

public function REturnerU()
{
	return upArrow;
}

public function REturnerD()
{
	return downArrow;
}


	}
}