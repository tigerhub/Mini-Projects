package {

	import flash.display.*;
	import flash.events.*;
	import flash.ui.*;
	import flash.events.KeyboardEvent;
	import flash.geom.ColorTransform;
	//import fl.managers.FocusManager;
	import flash.display.InteractiveObject;
	

	public class RunGame extends MovieClip {
		
		public var rightArrow:Boolean;
	public var leftArrow:Boolean;
	public var upArrow:Boolean;
	public var downArrow:Boolean;
	static public var win=false;
		static public var attachedObj:ship = new ship();
		static public var attachedObj2:ship = new ship();
		static public var yourShip= new yoShip();
		static public var ET;
		static public var AliAr;
		static public var hp;
		static public var dead;
		public var shootAr=new Array(2);
		
		public var speed=3;
		static public var Red=.5;
	 static public var Green=.5;
	static public var Blue=.5;

		public function RunGame() {

			
			addEventListener(Event.ADDED_TO_STAGE, init);

		}
		private function init(e:Event=null):void {


//attachedObj.transform.colorTransform=new ColorTransform(RunGame.Red,RunGame.Green,RunGame.Blue,1,1,0,1,0);
yourShip.transform.colorTransform=new ColorTransform(RunGame.Red,RunGame.Green,RunGame.Blue,1,1,0,1,0);
			
			var AliAr=new Array(5);
			var hp=new Array();
			var dead=new Array();
			RunGame.dead=dead;
			RunGame.hp=hp;
			RunGame.AliAr=AliAr;
			
			//RunGame.hp.length=RunGame.AliAr.length;
			var ET=new alien();
			//RunGame.ET=ET;
			//RunGame.ET.x=200;
			
			for(var i=0;i<RunGame.AliAr.length;i++)
			{
				RunGame.dead[i]=false;
				RunGame.hp[i]=20000;
			RunGame.AliAr[i]=new (ET.constructor);
			//trace(RunGame.AliAr[i].x);
			RunGame.AliAr[i].x=75*i;
			stage.addChild(RunGame.AliAr[i]);
			}
			stage.removeChild(RunGame.AliAr[0]);
			RunGame.yourShip.x=390;
			RunGame.yourShip.y=150;
			RunGame.yourShip.scaleY=.8;
			RunGame.yourShip.scaleX=.8;
		//stage.addChild(RunGame.ET);
			addChild(yourShip);
			
			yourShip.addChild(attachedObj);
			attachedObj.x=0;
			attachedObj2.x=240;
			RunGame.yourShip.addChild (attachedObj2);
			
			stage.addEventListener(KeyboardEvent.KEY_DOWN, keyPressed);
		stage.addEventListener(KeyboardEvent.KEY_UP, keyReleased);
			addEventListener(Event.ENTER_FRAME, everyFrame);
			
			
		
		
		function keyPressed(event:KeyboardEvent):void {
			if (event.keyCode==Keyboard.RIGHT) {
				rightArrow=true;
				
			} else if (event.keyCode==Keyboard.LEFT) {
				leftArrow=true;
				
			} else if (event.keyCode==Keyboard.UP) {
				upArrow=true;
				
			} else if (event.keyCode==Keyboard.DOWN) {
				downArrow=true;
				
			}
		}
		function keyReleased(event:KeyboardEvent):void {
			if (event.keyCode==Keyboard.RIGHT) {
				rightArrow=false;
			} else if (event.keyCode==Keyboard.LEFT) {
				leftArrow=false;
			} else if (event.keyCode==Keyboard.UP) {
				upArrow=false;
			} else if (event.keyCode==Keyboard.DOWN) {
				downArrow=false;
			}
		}

	

		function everyFrame(event:Event):void {
			
			
			if(ship.shoot)
			{
			
			var bullet1=new bullet();
			
			shootAr[0]=new (bullet1.constructor);
			shootAr[1]=new (bullet1.constructor);
			
			for(var i=0;i<shootAr.length;i++)
			
			{
				shootAr[i].x=RunGame.yourShip.x+(185*i);
				shootAr[i].y=RunGame.yourShip.y-20;
				stage.addChild(shootAr[i]);
			}
			}
			
			var count=RunGame.dead.every(isNumeric);
			if (count==true)
			{
				
				RunGame.win=true;
			}
			
			
			if (rightArrow) {
				attachedObj.gotoAndStop(2);
				yourShip.x+=speed;
			} 
			if (leftArrow) {
				attachedObj.gotoAndStop(2);
				yourShip.x-=speed;
			} 
			if (upArrow) {
				attachedObj.gotoAndStop(2);
				yourShip.y-=speed;
			} 
			
			if (downArrow) {
				attachedObj.gotoAndStop(2);
				yourShip.y+=speed;
			} 
			else
			{
				yourShip.gotoAndStop(1);
			}
		}
}
		

static public function reAlien()

{
	return RunGame.ET;
}

public function isNumeric(element:*, index:int, arr:Array):Boolean {
            return (element is Number);
        }

		}
}

