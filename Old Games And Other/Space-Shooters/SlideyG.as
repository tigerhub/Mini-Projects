package {

	import flash.display.*;
	import flash.events.*
	import flash.geom.Rectangle;
	import flash.geom.ColorTransform;
import flash.filters.GlowFilter; 


	public class SlideyG extends MovieClip {


		var rectangle2:Rectangle=new Rectangle(0,0,370,0);
		public var green=0;
		public var blue=0;
		private var col:sliderR=new sliderR  ;
		
		//For all color codes found, replace the # with 0x!
		public var glow:GlowFilter = new GlowFilter(0x00FF00);

		//public function GlowFilter(color:uint = 0xFF0000, alpha:Number = 1.0, 
		//blurX:Number = 6.0, blurY:Number = 6.0, strength:Number = 2, quality:int = 1, 
		//inner:Boolean = false, knockout:Boolean = false)
		
		public var Red=0;
		//public var Calar:lower=new lower  ;


		public function SlideyG() {

glow.strength=3;
glow.alpha=.5;
glow.blurX=glow.blurX=15;

col.filters = [glow];
			addChild(col);
col.width=40;
col.height=25;
			col.x=180;
			green=col.x;
			
			

			col.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
			col.addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
			
			
		}

		function mouseDownHandler(evt:MouseEvent):void {
			green=col.x/360;
			glow.strength=col.x/30;
col.filters = [glow];
			col.startDrag(false, rectangle2);
			
		}

		function mouseUpHandler(evt:MouseEvent):void {
			
			//ALSO NEED TO INCLUDE THE REAL MAX COLORS (full green, red, blue)!!!
			green=col.x/360;
			glow.strength=col.x/30;
			col.filters = [glow];
			col.stopDrag();
			
			//next up is using a class that has arrays!!! FUN =D!
			//lower.lowy.transform.colorTransform=new ColorTransform(Red,green,blue,1,1,0,1,0);
		//trace(lower.lowy.transform.colorTransform);

		}
		
public function SendG()
		{
			return green;
		}
		
		public function SetG()
		{
			green=col.x/360;
		}

	}

}