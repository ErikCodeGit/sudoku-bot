
public class Bot {

	int[][] board;
	final static int SIZE = 9;

	Bot(Board board) {
		this.board = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board.getNum(i, j);
			}
		}
	}

	public Board solve() {
		solveBoard(board);
		printBoard();
		Board solvedBoard = new Board();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				solvedBoard.setNum(i, j, board[i][j]);
			}
		}
		return solvedBoard;
	}

	private static boolean isNumInRow(int[][] board, int num, int row) {
		for (int i = 0; i < SIZE; i++) {
			if (board[row][i] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumInCol(int[][] board, int num, int col) {
		for (int i = 0; i < SIZE; i++) {
			if (board[i][col] == num) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumInBlock(int[][] board, int num, int row, int col) {
		int blockRow = row - row % 3;
		int blockCol = col - col % 3;

		for (int i = blockRow; i < blockRow + 3; i++) {
			for (int j = blockCol; j < blockCol + 3; j++) {
				if (board[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
		return (!isNumInRow(board, num, row) && !isNumInCol(board, num, col) && !isNumInBlock(board, num, row, col));
	}

	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (board[row][col] == 0) {
					for (int attempt = 1; attempt <= SIZE; attempt++) {
						if (isValidPlacement(board, attempt, row, col)) {
							board[row][col] = attempt;
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private void printBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
