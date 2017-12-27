package {

	import flash.display.*;
	import flash.events.*
	
	import flash.geom.ColorTransform;
	//import lower;

	public class buy extends MovieClip {
		
		
	public  var Red:Slidey=new Slidey;
	public  var Green:SlideyG=new SlideyG;
	public  var Blue:SlideyB=new SlideyB;
	//public var Alph:aplhy= new aplhy;
		
		//public var green=0;
		//public var blue=0;
		
		
		//public var Red=0;
		//public var Calar:lower=new lower  ;


		public function buy() {

			addChild(Red);
			addChild(Green);
			addChild(Blue);
			
			
			Red.y=100;
			
			Blue.y=150;
			Green.y=200;
			
			Red.width=Blue.width=Green.width=250;
			Red.height=Blue.height=Green.height=30;
			
			
			
			this.addEventListener(MouseEvent.MOUSE_MOVE, mouseUpHandler);
			
		}


		function mouseUpHandler(evt:MouseEvent):void {
			Red.SetR();
			Green.SetG();
			Blue.SetB();
			
			RunGame.Red=Red.SendR();
			RunGame.Green=Green.SendG();
			RunGame.Blue=Blue.SendB();
			
			
			RunGame.yourShip.transform.colorTransform=new ColorTransform(Red.SendR(),Green.SendG(),Blue.SendB(),1,1,0,1,0);
			
			//trace(Red);
		}
		
		

	}

}