
public class Board {

	private int[][] fields;
	public int size = 9;

	Board() {
		fields = new int[size][size];
		fillField();
	}
	
	private void fillField() {
		for(int i = 0; i < fields.length; i++) {
			for(int j = 0; j < fields[0].length; j++) {
				fields[i][j] = 0;
			}
		}
	}

	public int getNum(int row, int col) {
		return fields[row][col];
	}

	public int[] getRow(int row) {
		return fields[row];
	}

	public int[] getCol(int col) {
		int[] res = new int[9];

		for (int i = 0; i < fields[0].length; i++) {
			res[i] = fields[i][col];
		}

		return res;
	}
	
	public int[][] getFields(){
		return fields;
	}
	
	
	
	public int[] getBlock(int x, int y) {
		int[] res = new int[9];
		int index = 0;
		for(int i = x*3; i < (x+1)*3; i++) {
			for(int j = y*3; j < (y+1)*3; j++) {
				res[index] = fields[i][j];
				index++;
			}
		}
		return res;
	}
	
	public void setNum(int row, int col, int val) {
		if(val >= 0 && val <=9)
		fields[row][col] = val;
	}
	
	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(fields[i][j] + " ");
			}
			System.out.println();
		}
	}
}
