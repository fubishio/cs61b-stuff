public class Piece{
	private boolean isFire2;
	private Board b;
	private int x;
	private int y;
	private String Type;
	private boolean didCapture;
	
	
	
	public Piece(boolean isFire1, Board b1, int x1, int y1, String Type1){
		isFire2 = isFire1; 
		b = b1;
		x = x1;
		y = y1;
		Type = Type1;
		
	}
		
	public int side(){
		if (isFire()){
			return 0;
		} else {
			return 1;
		}
	}
	
	public boolean isFire(){
		if(this.isFire2){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isKing(){
		if (this.Type == "king" || this.Type == "bombking" || this.Type == "shieldking"){
			return true;
		} else {
			return false;
		} 
	}
	
	public boolean isBomb(){
		if (this.Type == "bombking" || this.Type == "bomb"){
			return true;
		} else {
			return false;
		} 
	}
	
	public boolean isShield(){
		if (this.Type == "shield" || this.Type == "shieldking"){
			return true;
		} else {
			return false;
		} 
	}
	
	public void move(int x, int y){
			boolean capturing = Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 2;
			if (capturing && this.x > x && this.y > y){
				b.remove(x+1, y+1);
				hasCaptured();
			} else if (capturing && this.x < x && this.y > y){
				b.remove(x-1, y+1);
				hasCaptured();
			} else if (capturing && this.x > x && this.y < y){
				b.remove(x+1, y-1);
				hasCaptured();
			} else if (capturing && this.x < x && this.y < y){
				b.remove(x-1, y-1);
				hasCaptured();
			}
			b.remove(this.x, this.y);
			this.x = x;
			this.y = y;
			b.place(this, x, y);
			crowning();
			explosion();
		}
	
	private void crowning() {
		if (isFire()){
			if (this.y == 7) {
				if (isBomb()){
					this.Type = "bombking";
				} else if (isShield()) {
					this.Type = "shieldking";
				} else {
					this.Type = "king";
				}
			}
		} else if (isFire() == false) {
			if (this.y == 0) {
				if (isBomb()){
					this.Type = "bombking";
				} else if (isShield()) {
					this.Type = "shieldking";
				} else {
					this.Type = "king";
				}
			}
		}
	}
	
	private void explosion(){
		if (isBomb()){
			if (didCapture){
				if (this.x + 1 <= 7 && this.y + 1 <= 7){ 
					if (b.pieceAt(this.x + 1, this.y + 1)== null){}
					else {if (b.pieceAt(this.x + 1, this.y + 1).isShield()){}
					else {b.remove(x+1, y+1);}}}
				if (this.x + 1 <= 7  && this.y - 1 >= 0){
					if (b.pieceAt(this.x + 1, this.y - 1)== null){}
					else {if (b.pieceAt(this.x + 1, this.y - 1).isShield()){}
					else {b.remove(x+1, y-1);}}}
				if (this.x - 1 >= 0 && this.y + 1 <= 7) {
					if (b.pieceAt(this.x - 1, this.y + 1)== null){}
					else {if (b.pieceAt(this.x - 1, this.y + 1).isShield()){}
					else {b.remove(x-1, y+1);}}}
				if (this.x - 1 >= 0 && this.y - 1 >= 0){
					if (b.pieceAt(this.x - 1, this.y - 1)== null){}
					else {if (b.pieceAt(this.x - 1, this.y - 1).isShield()){}
					else {b.remove(x-1, y-1);}}}
			b.remove(x, y);
			}			
		} else {			
		}
	}
	
	public boolean hasCaptured(){
		if (didCapture == false) {
			didCapture = true;
			return false;
		} else {
			return didCapture;
		}
	}
	
	public void doneCapturing(){
		didCapture = false;
	}
	
	
}
