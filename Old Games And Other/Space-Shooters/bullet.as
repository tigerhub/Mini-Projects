package {

	import flash.display.*;
	import flash.events.*
	import flash.geom.ColorTransform;
	
	//import fl.events.ListEvent;

    
	
	public class bullet extends MovieClip {

static public var ang=ship.stuff();
public var X=0;
public var Y=0;
static public var remove=false;
public var counter=0;

		public function bullet() {
			

			ang=ship.stay;
			X=Math.cos(ang) * 5;
			Y=Math.sin(ang) * 5;
			
			
			
			 addEventListener( Event.ENTER_FRAME, onEnterFrame);
			
			
		}




		
		private function onEnterFrame (e:Event)
{
	
	this.x += X;
  this.y += Y;
  
  //alien.obj=re();
  
 for(var k=0;k<RunGame.AliAr.length;k++)
			{
				
  if (this.hitTestObject(RunGame.AliAr[k]))
  
  {
	  
	  RunGame.hp[k]= RunGame.hp[k]-100;
	//RunGame.win=RunGame.win-100;
	
	  if (RunGame.hp[k]<=0)
	  {
		RunGame.hp[k]=0;
		  //trace(RunGame.AliAr[k] + RunGame.hp[k]);
		  
		 RunGame.AliAr[k].gotoAndPlay(2);
		//RunGame.AliAr.splice(k);
		RunGame.dead[k]=4;
		
	  }
	 
	
	
	//hehe nice little way to trick the compiler here =D.
	RunGame.AliAr[k].addChild(this);
	RunGame.AliAr[k].removeChild(this);

 }
 
			}
 //add timer to remove each bullet;
}

public function re()
{
	return this;
}


	}
}