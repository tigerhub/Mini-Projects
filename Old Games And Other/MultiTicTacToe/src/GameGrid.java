
public interface GameGrid {
	public void nextTurn();
	public void setXAt(int x, int y);
	public void setOAt(int x, int y);
	public int getValueAt(int x, int y);
	public int whoWon();
}
