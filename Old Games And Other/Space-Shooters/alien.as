package {

	import flash.display.*;
	import flash.events.*;
	import flash.geom.ColorTransform;
	//import lower;

	public class alien extends MovieClip {


var counter=0;
		public function alien() {

//this.x=this.x+ (Math.floor(Math.random()*5));
//this.y=this.y+ (Math.floor(Math.random()*5));

			addEventListener(Event.ADDED_TO_STAGE, init);

		}

		private function init(e:Event=null):void {
			
			//addChild();
addEventListener(Event.ENTER_FRAME, everyFrame);


		}
		
		
		
		function everyFrame(event:Event):void {
			if (this.x!=RunGame.yourShip.x)
			{
				var dx=RunGame.yourShip.x-this.x;
				var dy=RunGame.yourShip.y-this.y;
				
				 var ang:Number = Math.atan2( dy, dx);
				 
				 this.x=this.x + Math.cos(ang)*.25;
				  this.y=this.y + Math.sin(ang)*.25;
			}
			
			
			
			
		}
	}
}