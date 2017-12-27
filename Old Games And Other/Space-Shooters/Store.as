package {

	import flash.display.*;
	import flash.events.*;
	import flash.geom.ColorTransform;
	//import lower;

	public class Store extends MovieClip {

		//public var Alph:aplhy= new aplhy;
		static public var Open:buy=new buy ;
		//public var prev:live=new live  ;
		static public var Spend:BuyBt= new BuyBt;
		
		//public var green=0;
		//public var blue=0;


		//public var Red=0;
		//public var Calar:lower=new lower  ;


		public function Store() {

			this.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
			
			//addChild(Open);

		}

		function mouseDownHandler(evt:MouseEvent):void {
			
			stage.addChild(Open);
			Open.addChild(Spend);
			Open.addChild(RunGame.yourShip);
			RunGame.yourShip.scaleX=RunGame.yourShip.scaleY=.5;
			RunGame.yourShip.x=325;
			RunGame.yourShip.y=125;
			
			Spend.x= 300;
			Spend.y= 310;
			Spend.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler2);
		}

function mouseDownHandler2(evt:MouseEvent):void {
			
			//add exit path here!!!
			stage.removeChild(Open);
			RunGame.yourShip.x=390;
			RunGame.yourShip.y=150;
			RunGame.yourShip.scaleY=.8;
			RunGame.yourShip.scaleX=.8;

			stage.addChild(RunGame.yourShip);
		}


	}

}