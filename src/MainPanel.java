import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = 600;
	static final int SCREEN_CENTER_X = SCREEN_HEIGHT / 2;
	static final int SCREEN_CENTER_Y = SCREEN_WIDTH / 2;
	static final int FIELD_SIZE = 50;
	static final Color BUTTON_BACKGROUND_COLOR = Color.white;
	Font defaultFont;
	Board board;
	JButton[][] buttons;
	JButton selectedButton;
	JPanel boardPanel;
	Color focusColor;
	Color selectedColor;

	MainPanel() {
		initVariables();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		board = new Board();
		boardPanel = new JPanel();
		boardPanel.setVisible(true);
		boardPanel.setLayout(null);
		boardPanel.setBackground(Color.gray);
		boardPanel.setPreferredSize(new Dimension(9 * FIELD_SIZE, 9 * FIELD_SIZE));
		// this.addKeyListener(new MyKeyAdapter());
		initbuttons();
		this.add(boardPanel);
	}

	private void initVariables() {
		defaultFont = new Font("Consolas", Font.PLAIN, 20);
		focusColor = new Color(0, 0, 255, 20);
		selectedColor = new Color(0, 255, 0, 100);
	}

	private void initbuttons() {
		buttons = new JButton[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBounds(i * FIELD_SIZE, j * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
				buttons[i][j].setVisible(true);
				buttons[i][j].setBackground(Color.white);
				buttons[i][j].setHorizontalAlignment(JButton.CENTER);
				buttons[i][j].setFont(defaultFont);
				buttons[i][j].setRolloverEnabled(false);
				buttons[i][j].addMouseListener(new ButtonMouseAdapter());
				buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				buttons[i][j].addFocusListener(new ButtonFocusAdapter());
				buttons[i][j].addKeyListener(new ButtonKeyAdapter());
				boardPanel.add(buttons[i][j]);
			}
		}
	}

	public void buttonClicked(ActionEvent e) {
		// selectedButton = (JButton) e.getSource();
		// selectedButton.setBackground(new Color(0, 255, 0, 100));
	}

	public void test() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board.setNum(i, j, j + 1);
			}
		}
		printBoard();
	}

	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			System.out.println(arrayToString(board.getRow(i)));
		}
	}

	public String arrayToString(int[] array) {
		String res = "";
		for (int i = 0; i < array.length; i++) {
			res += array[i] + " ";
		}
		return res;
	}

	public String blockToString(int[] array) {
		String res = "";
		for (int i = 0; i < array.length; i++) {
			if (i % 3 == 0 && i != 0)
				res += "\n";
			res += array[i] + " ";
		}
		return res;
	}

	public class ButtonKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if ((JButton) e.getSource() == selectedButton) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_0:
					System.out.println("0");
					break;
				}
			}

		}
	}

	public class ButtonFocusAdapter extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			selectedButton = (JButton) e.getSource();
			selectedButton.setBackground(selectedColor);
			System.out.println(selectedButton + " gained focus");
		}

		@Override
		public void focusLost(FocusEvent e) {
			selectedButton.setBackground(BUTTON_BACKGROUND_COLOR);
			selectedButton = null;
			System.out.println(selectedButton + " lost focus");
		}
	}

	public class ButtonMouseAdapter extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			JButton hoveredButton = (JButton) e.getSource();
			if (hoveredButton != selectedButton) {
				hoveredButton.setBackground(focusColor);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton hoveredButton = (JButton) e.getSource();
			if (hoveredButton != selectedButton) {
				hoveredButton.setBackground(BUTTON_BACKGROUND_COLOR);
			} else {
				hoveredButton.setBackground(selectedColor);
			}
		}
	}

}
