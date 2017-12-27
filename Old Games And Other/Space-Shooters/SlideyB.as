package {

	import flash.display.*;
	import flash.events.*
	import flash.geom.Rectangle;
	import flash.geom.ColorTransform;
	import flash.filters.GlowFilter; 
	//import lower;

	public class SlideyB extends MovieClip {


		var rectangle3:Rectangle=new Rectangle(0,0,370,0);
		public var green=0;
		public var blue=0;
		private var col:sliderR=new sliderR  ;
		
		public var glow:GlowFilter = new GlowFilter(0x33CCFF);
		
		public var Red=0;
		//public var Calar:lower=new lower  ;


		public function SlideyB() {
glow.strength=3;
glow.alpha=.5;
glow.blurX=glow.blurX=15;
col.filters = [glow];

			addChild(col);
			col.width=40;
col.height=25;
			col.x=180;
			blue=col.x;

			col.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
			col.addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
			
			
		}

		function mouseDownHandler(evt:MouseEvent):void {
			blue=col.x/360;
				glow.strength=col.x/30;
col.filters = [glow];
			col.startDrag(false, rectangle3);
			
		}

		function mouseUpHandler(evt:MouseEvent):void {
			
			//ALSO NEED TO INCLUDE THE REAL MAX COLORS (full green, red, blue)!!!
			blue=col.x/360;
				glow.strength=col.x/30;
col.filters = [glow];
			col.stopDrag();
			
			//next up is using a class that has arrays!!! FUN =D!
			//lower.lowy.transform.colorTransform=new ColorTransform(Red,green,blue,1,1,0,1,0);
		//trace(lower.lowy.transform.colorTransform);

		}
		
public function SendB()
		{
			return blue;
		}
		
 public function SetB()
		{
			blue=col.x/360;
		}

	}

}