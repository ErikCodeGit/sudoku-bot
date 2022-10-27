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
	static final Color LABEL_DEFAULT_COLOR = Color.white;
	Font defaultFont;
	Board board;
	JLabel[][] labels;
	JLabel selectedLabel;
	JPanel boardPanel;
	JPanel[][] blocks;
	Color hoverColor;
	Color selectedColor;

	MainPanel() {
		initVariables();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		board = new Board();
		boardPanel = new JPanel();
		boardPanel.setVisible(true);
		boardPanel.setLayout(null);
		boardPanel.setFocusable(true);
		boardPanel.setPreferredSize(new Dimension(9 * FIELD_SIZE, 9 * FIELD_SIZE));
		this.addKeyListener(new PanelKeyAdapter());
		initlabels();
		this.add(boardPanel);
	}

	private void initVariables() {
		defaultFont = new Font("Consolas", Font.PLAIN, 20);
		hoverColor = new Color(220, 220, 255);
		selectedColor = new Color(155, 255, 155);
	}

	private void initlabels() {
		labels = new JLabel[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setBounds(i * FIELD_SIZE, j * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
				labels[i][j].setVisible(true);
				labels[i][j].setOpaque(true);
				labels[i][j].setBackground(Color.white);
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				labels[i][j].setFont(defaultFont);
				labels[i][j].addMouseListener(new LabelMouseAdapter());
				labels[i][j].setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
				boardPanel.add(labels[i][j]);
			}
		}
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

	public class PanelKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (selectedLabel != null) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_NUMPAD0:
					selectedLabel.setText("0");
					break;
				case KeyEvent.VK_NUMPAD1:
					selectedLabel.setText("1");
					break;
				case KeyEvent.VK_NUMPAD2:
					selectedLabel.setText("2");
					break;
				case KeyEvent.VK_NUMPAD3:
					selectedLabel.setText("3");
					break;
				case KeyEvent.VK_NUMPAD4:
					selectedLabel.setText("4");
					break;
				case KeyEvent.VK_NUMPAD5:
					selectedLabel.setText("5");
					break;
				case KeyEvent.VK_NUMPAD6:
					selectedLabel.setText("6");
					break;
				case KeyEvent.VK_NUMPAD7:
					selectedLabel.setText("7");
					break;
				case KeyEvent.VK_NUMPAD8:
					selectedLabel.setText("8");
					break;
				case KeyEvent.VK_NUMPAD9:
					selectedLabel.setText("9");
					break;
				}
			}
		}
	}

	public class LabelFocusAdapter extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			selectedLabel = (JLabel) e.getSource();
			selectedLabel.setBackground(selectedColor);
		}

		@Override
		public void focusLost(FocusEvent e) {
			selectedLabel.setBackground(LABEL_DEFAULT_COLOR);
			selectedLabel = null;
		}
	}

	public class LabelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel hoveredLabel = (JLabel) e.getSource();
			if (hoveredLabel != selectedLabel) {
				hoveredLabel.setBackground(hoverColor);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel hoveredLabel = (JLabel) e.getSource();
			if (hoveredLabel != selectedLabel) {
				hoveredLabel.setBackground(LABEL_DEFAULT_COLOR);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JLabel clickedLabel = (JLabel) e.getSource();
			if (selectedLabel != clickedLabel) { // if not the same label as before
				if (selectedLabel != null) { // if not first label
					selectedLabel.setBackground(LABEL_DEFAULT_COLOR);
				}
				selectedLabel = clickedLabel;
				selectedLabel.setBackground(selectedColor);
			}
		}
	}

}
