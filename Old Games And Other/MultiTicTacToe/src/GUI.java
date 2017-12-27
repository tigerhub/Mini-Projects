import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends Applet {
	private Button reset;
    private final int barWidth = 5;
    private GameGrid t;
    private boolean userTurn = true;
    private static final int boardDim = 6;
    private static final int startingHeight = 50;
    
    
    public GUI(){
    	reset = new Button("Reset");
    }
    public Color Blue = new Color (0,0,205);		// color of selected square on board
	public Color Green = new Color (0,255,0);		// color of opponents selected square on board
	public int aWinner = 0;
	
	public void init(){
        setBackground(Color.BLACK);
        add(reset);
        setSize(500, 500);
        addMouseListener(new MouseActions());
        
        t = new TicTacToe(boardDim);
    }
    public boolean action(Event e, Object obj){
    	if (e.target == reset){
    		t = new TicTacToe(boardDim);
    		aWinner = 0;
    		if (userTurn){
    			t.nextTurn();
    			userTurn = !userTurn;
    		}
    		repaint();
    	}
    	return false;
    }
    public void paint(Graphics g){
    	// draws the white lanes on the GameGrid board
        g.setColor(Color.WHITE);
        
        for(int i = 1; i < boardDim; i++) {
        	// vertical bars
        	g.fillRect(((getWidth()-barWidth) * i)/boardDim, 50, barWidth, getHeight()-50);
        	// horizontal bars
        	g.fillRect(0, (getHeight()-50-barWidth)*i/boardDim+50, getWidth(), barWidth);
        }
        
        
        // draws the X's and O's in the appropriate spots
        for(int j = 0; j < boardDim; j++)
            for(int i = 0; i < boardDim; i++)
                switch(t.getValueAt(i, j)){
                	// drawing an X
	                case -1: drawThickLine(g, i*getWidth()/boardDim, j*(getHeight()-50)/boardDim+50, (i+1)*getWidth()/boardDim, (j+1)*(getHeight()-50)/boardDim+50, 5, Blue); 
	                         drawThickLine(g, i*getWidth()/boardDim, (j+1)*(getHeight()-50)/boardDim+50, (i+1)*getWidth()/boardDim, j*(getHeight()-50)/boardDim + 50, 5, Blue);
	                break;
	                
	                // Drawing an O
	                case 1: g.setColor(Green); g.fillOval(i*getWidth()/boardDim, j*(getHeight()-50)/boardDim+50, getWidth()/boardDim, (getHeight()-50)/boardDim); 
	                        g.setColor(Color.BLACK);  g.fillOval(i*getWidth()/boardDim+5, j*(getHeight()-50)/boardDim+55, getWidth()/boardDim-10, (getHeight()-50)/boardDim-10);break;
	              }
    	
        if (aWinner == -1)
        {
        	g.setColor(Blue);
        	g.drawString("X wins!", 10,20);
        }
        else if (aWinner == 1)
        {
        	g.setColor(Green);
        	g.drawString("O wins!", 10,20);
        }
        else if (aWinner == 2)
        {
        	g.setColor(Color.YELLOW);
        	g.drawString("It's a tie!!", 10,20);
        }
    }
    
    
    public void drawThickLine(
    		  Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c) {
    		  // The thick line is in fact a filled polygon
    		  g.setColor(c);
    		  int dX = x2 - x1;
    		  int dY = y2 - y1;
    		  // line length
    		  double lineLength = Math.sqrt(dX * dX + dY * dY);

    		  double scale = (double)(thickness) / (2 * lineLength);

    		  // The x,y increments from an endpoint needed to create a rectangle...
    		  double ddx = -scale * (double)dY;
    		  double ddy = scale * (double)dX;
    		  ddx += (ddx > 0) ? 0.5 : -0.5;
    		  ddy += (ddy > 0) ? 0.5 : -0.5;
    		  int dx = (int)ddx;
    		  int dy = (int)ddy;

    		  // Now we can compute the corner points...
    		  int xPoints[] = new int[4];
    		  int yPoints[] = new int[4];

    		  xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
    		  xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
    		  xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
    		  xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

    		  g.fillPolygon(xPoints, yPoints, 4);
    		 }
    
    //this is where the game is essentially played
    private class MouseActions implements MouseListener {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
        	// Method might return before aWinner updated, so I also get the value here.
        	aWinner = t.whoWon();
        	repaint();
        	if (t.whoWon() != 0) {
        		System.out.println("we have a result: " + t.whoWon());
        		return;
        	}
        	int x = coordRange(e.getX(), getWidth(), 0);
            int y = coordRange(e.getY(), getHeight(), startingHeight);
            if (x == -1 || y == -1 || t.getValueAt(x, y) != 0)
            	return;
            t.setOAt(x, y);
            aWinner = t.whoWon();
            t.nextTurn();
            repaint();
        }
        // this method converts the (x,y) coordinate where mouse is pressed into
        // the corresponding [x][y] position in grid.
        private int coordRange(int en, int dist, int start){
        	en -= start;
        	dist -= start;
        	int n = -1;
        	/*if (0 < en && en < dist/boardDim){
                n = 0;
            } else if (dist/boardDim < en && en < dist*2/boardDim){
                n = 1;
            } else if (dist*2/boardDim < en && en < dist){
                n = 2;
            }*/
        	
        	System.out.println((double)en / (double)dist);
        	return (int)(((double)en / (double)dist) * boardDim);
        	//return n;
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}