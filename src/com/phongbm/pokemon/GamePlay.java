package com.phongbm.pokemon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int MAX_LEVEL = 13;

	private CompareKey clicked = new CompareKey();
	private CompareKey moved = new CompareKey();
	private int indexMoved = -1;
	private PokemonManager pokemonManager;
	private SoundLibrary soundLibrary = new SoundLibrary();
	private Control control;
	private Graphics2D g2D;
	private Image imageBackground;
	private int xGrid, yGrid, xMouse, yMouse, index1 = -1, index2 = -1,
			maxTime, timeOut, progressBarTimeX, indexClicked = -1, countTime,
			scores, countChange, countFind, countDestroy;
	private Point point1 = null, point2 = null;
	private boolean isEndGame, isPausing, find, destroy, winGame, loseGame;
	private Thread thread;
	private int level;
	private List<Point> pairs;
	private TopHighScore topHighScore = new TopHighScore();

	public GamePlay() {
		this.setLayout(null);
		this.addMouseListener(mouseClick);
		this.addMouseMotionListener(mouseMove);
		control = new Control(GamePlay.this);
		this.add(control);
		pairs = new ArrayList<Point>();
		isEndGame = true;
		isPausing = false;
	}

	public void newGame() {
		imageBackground = new ImageIcon(getClass().getResource(
				"/images/backgrounds/bg" + (1 + (new Random()).nextInt(12))
						+ ".png")).getImage();
		pokemonManager = new PokemonManager();
		pokemonManager.initializePokemon();
		maxTime = 300000;
		timeOut = maxTime;
		progressBarTimeX = 310;
		countTime = 0;
		countChange = 5;
		countFind = 5;
		countDestroy = 5;
		find = false;
		destroy = false;
		loseGame = false;
		winGame = false;
		if (isEndGame) {
			scores = 0;
			level = 1;
		}
		repaint();
	}

	public void startGame() {
		if (isEndGame) {
			thread = new Thread(GamePlay.this);
			thread.start();
			isEndGame = false;
		}
	}

	private MouseAdapter mouseMove = new MouseAdapter() {
		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
			super.mouseMoved(e);
			moved.setKey(e.getX(), e.getY());
			if (indexMoved >= 0 && indexMoved != indexClicked)
				pokemonManager.returnBGClicked(indexMoved);
			indexMoved = pokemonManager.findEvent(moved);
			if (indexMoved == -1)
				return;
			pokemonManager.changeBGClicked(indexMoved);
		};
	};

	private MouseAdapter mouseClick = new MouseAdapter() {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			super.mouseClicked(e);
			if (isEndGame) {
				return;
			}
			xMouse = e.getX();
			yMouse = e.getY();
			if (xMouse < PokemonManager.PADDING_LEFT || xMouse > 895
					|| yMouse < PokemonManager.PADDING_TOP || yMouse > 515) {
				return;
			}
			xGrid = 1 + (yMouse - PokemonManager.PADDING_TOP)
					/ PokemonManager.SIZE;
			yGrid = 1 + (xMouse - PokemonManager.PADDING_LEFT)
					/ PokemonManager.SIZE;
			int index = (yGrid - 1) + (xGrid - 1) * PokemonManager.COLUMN;
			if (!pokemonManager.getPokemonCells().get(index).isShow()) {
				return;
			}
			find = false;
			soundLibrary.playSelect();
			clicked.setKey(e.getX(), e.getY());
			if (indexClicked >= 0)
				pokemonManager.returnBGClicked(indexClicked);
			indexClicked = pokemonManager.findEvent(clicked);
			if (indexClicked == -1)
				return;
			pokemonManager.changeBGClicked(indexClicked);
			pokemon();
		}
	};

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2D = (Graphics2D) g;
		RenderingHints renderingHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderingHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHints(renderingHints);
		drawBackground(g2D);
		drawLevelAndScores(g2D);
		pokemonManager.drawAllPokemon(g2D);
		drawProgressBarTime(g2D);
		drawNumberItems(g2D);

		if (find) {
			drawFindItems(g2D);
		}
		if (pokemonManager.isOk()) {
			drawLineBoom(g2D);
			pokemonManager.setOk(false);
		}
		if (destroy) {
			drawDestroyPokemon();
		}
		if (pokemonManager.findPairs() == null) {
			pokemonManager.shufflePokemon();
		}
		if (winGame) {
			g2D.drawImage(
					new ImageIcon(getClass().getResource("/images/winGame.png"))
							.getImage(), 0, 0, GUI.W_FRAME, GUI.H_FRAME, null);
			return;
		}
		if (loseGame) {
			g2D.drawImage(
					new ImageIcon(getClass()
							.getResource("/images/loseGame.png")).getImage(),
					(GUI.W_FRAME - 353) / 2, (GUI.H_FRAME - 197) / 2, 353, 197,
					null);
			g2D.drawString(String.valueOf(scores), 400, 300);
		}
	}

	private void drawDestroyPokemon() {
		// List<Point> pairs = pokemonManager.findPairs();
		// if (pairs == null) {
		// return;
		// }
		// for (int i = 0; i < pairs.size(); i++) {
		// int x = pairs.get(i).x;
		// int y = pairs.get(i).y;
		// int index = (y - 1) + (x - 1) * PokemonManager.COLUMN;
		// pokemonManager.getGrid()[x][y] = 0;
		// pokemonManager.getPokemonAlgorithm().getGrid()[x][y] = 0;
		// pokemonManager.getPokemonCells().get(index).setShow(false);
		// }
		pairs = convertIndexToLocation(pairs);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/imgBoom.png"))
						.getImage(), pairs.get(0).x, pairs.get(0).y, null);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/imgBoom.png"))
						.getImage(), pairs.get(1).x, pairs.get(1).y, null);
		pokemonManager.setItems(pokemonManager.getItems() - 1);
		soundLibrary.playOk();
		destroy = false;
	}

	private void drawNumberItems(Graphics2D g2D) {
		g2D.setColor(Color.YELLOW);
		g2D.fillOval(685, 45, 20, 20);
		g2D.fillOval(735, 45, 20, 20);
		g2D.fillOval(785, 45, 20, 20);
		g2D.setColor(Color.RED);
		g2D.setFont(new Font("Tahoma", Font.BOLD, 15));
		g2D.drawString(String.valueOf(countFind), 690, 60);
		g2D.drawString(String.valueOf(countChange), 740, 60);
		g2D.drawString(String.valueOf(countDestroy), 790, 60);
	}

	private void drawFindItems(Graphics2D g2D) {
		List<Point> pairs = pokemonManager.findPairs();
		if (pairs != null) {
			pairs = convertIndexToLocation(pairs);
			for (int i = 0; i < pairs.size(); i++) {
				g2D.drawImage(
						new ImageIcon(getClass().getResource(
								"/images/imgFind3.png")).getImage(),
						pairs.get(i).x, pairs.get(i).y, null);
			}
		}
	}

	private void drawLevelAndScores(Graphics2D g2D) {
		g2D.setColor(Color.BLUE);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/stageLevel.png"))
						.getImage(), 100, 10, null);
		g2D.setFont(new Font("Tahoma", Font.BOLD, 45));
		g2D.drawString(String.valueOf(level), 170, 50);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/stageScore.png"))
						.getImage(), 250, 10, null);
		g2D.setFont(new Font("Tahoma", Font.BOLD, 25));
		g2D.drawString(String.valueOf(scores), 350, 45);
	}

	private void drawProgressBarTime(Graphics2D g2D) {
		g2D.drawImage(
				new ImageIcon(getClass().getResource(
						"/images/imgTimeBackground.png")).getImage(), 550, 70,
				null);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/imgTime.png"))
						.getImage(), 550, 70, 550 + progressBarTimeX, 140, 0,
				0, progressBarTimeX, 70, null);
	}

	private void drawLineBoom(Graphics2D g2D) {
		g2D.setStroke(new BasicStroke(5));
		g2D.setColor(Color.RED);
		List<Point> points = pokemonManager.getPoints();
		points = convertIndexToLocation(points);
		for (int i = 0; i < points.size() - 1; i++) {
			g2D.drawLine(points.get(i).x + 25, points.get(i).y + 25,
					points.get(i + 1).x + 25, points.get(i + 1).y + 25);
		}
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/imgBoom.png"))
						.getImage(), points.get(0).x, points.get(0).y, null);
		g2D.drawImage(
				new ImageIcon(getClass().getResource("/images/imgBoom.png"))
						.getImage(), points.get(points.size() - 1).x, points
						.get(points.size() - 1).y, null);
	}

	private void drawBackground(Graphics2D g2D) {
		g2D.drawImage(imageBackground, 0, 0, GUI.W_FRAME, GUI.H_FRAME, null);
	}

	private void pokemon() {
		if (point1 == null) {
			point1 = new Point(xGrid, yGrid);
			index1 = (yGrid - 1) + (xGrid - 1) * PokemonManager.COLUMN;
		} else {
			if (point2 == null) {
				point2 = new Point(xGrid, yGrid);
				index2 = (yGrid - 1) + (xGrid - 1) * PokemonManager.COLUMN;
			}
		}
		if (point1 != null && point2 != null
				&& pokemonManager.pokemon(point1, point2)) {
			soundLibrary.playOk();
			pokemonManager.getPokemonCells().get(index1).setShow(false);
			pokemonManager.getPokemonCells().get(index2).setShow(false);
			point1 = null;
			point2 = null;
			index1 = -1;
			index2 = -1;
			scores += 10;
		} else {
			if (point1 != null && point2 != null
					&& !pokemonManager.pokemon(point1, point2)) {
				pokemonManager.returnBGClicked(indexClicked);
				soundLibrary.playWrong();
				point1 = null;
				point2 = null;
			}
		}
		switch (level) {
		case 1:
			break;
		case 2:
			pokemonManager.moveLeft(1, PokemonManager.COLUMN);
			break;
		case 3:
			pokemonManager.moveRight(PokemonManager.COLUMN, 1);
			break;
		case 4:
			pokemonManager.moveUp(1, PokemonManager.ROW);
			break;
		case 5:
			pokemonManager.moveDown(PokemonManager.ROW, 1);
			break;
		case 6:
			pokemonManager.moveLeftRight();
			break;
		case 7:
			pokemonManager.moveUpDown();
			break;
		case 8:
			pokemonManager.moveLeftRightAmong();
			break;
		case 9:
			pokemonManager.moveUpDownAmong();
			break;
		case 10:
			pokemonManager.moveRandom1();
			break;
		case 11:
			pokemonManager.moveRandom2();
			break;
		case 12:
			pokemonManager.moveRandom3();
			break;
		case 13:
			pokemonManager.moveRandom4();
			break;
		}
	}

	private List<Point> convertIndexToLocation(List<Point> list) {
		for (int i = 0; i < list.size(); i++) {
			int xx = list.get(i).x;
			int yy = list.get(i).y;
			list.get(i).x = PokemonManager.PADDING_LEFT + (yy - 1)
					* PokemonManager.SIZE;
			list.get(i).y = PokemonManager.PADDING_TOP + (xx - 1)
					* PokemonManager.SIZE;
		}
		return list;
	}

	public SoundLibrary getSoundLibrary() {
		return soundLibrary;
	}

	@Override
	public void run() {
		while (!isEndGame) {
			if (!isPausing) {
				repaint();
				if (countTime % 10 == 0 && progressBarTimeX > 10)
					progressBarTimeX -= 1;
				countTime++;
				timeOut -= 100;
				if (destroy) {
					pairs = pokemonManager.findPairs();
					if (pairs == null) {
						return;
					}
					for (int i = 0; i < pairs.size(); i++) {
						int x = pairs.get(i).x;
						int y = pairs.get(i).y;
						int index = (y - 1) + (x - 1) * PokemonManager.COLUMN;
						pokemonManager.getGrid()[x][y] = 0;
						pokemonManager.getPokemonAlgorithm().getGrid()[x][y] = 0;
						pokemonManager.getPokemonCells().get(index)
								.setShow(false);
					}
				}
				if (pokemonManager.getItems() == 0) {
					upgradeLevel();
				}
				if (timeOut == 0) {
					loseGame();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void upgradeLevel() {
		if (level < MAX_LEVEL) {
			level++;
			scores += (maxTime - countTime) / 1000;
			newGame();
		} else {
			winGame();
		}
	}

	private void winGame() {
		isEndGame = true;
		winGame = true;
		control.setVisible(false);
		winGame();
		repaint();
		soundLibrary.playPikaWin();
	}

	private void loseGame() {
		isEndGame = true;
		loseGame = true;
		repaint();
		soundLibrary.playPikaLose();
		addTopHighScore();
	}

	private void addTopHighScore() {
		topHighScore.readData();
		if (!topHighScore.isFull()
				|| (topHighScore.isFull() && scores >= topHighScore
						.getTopHightScore().get(topHighScore.getLength() - 1)
						.getScores())) {
			String name = JOptionPane.showInputDialog("Name:");
			if (topHighScore.isFull()) {
			} else {
				topHighScore.getTopHightScore().add(new HighScore());
				topHighScore.setLength(topHighScore.getLength() + 1);
			}
			topHighScore.getTopHightScore().set(topHighScore.getLength() - 1,
					new HighScore(name, scores));
			topHighScore.swap();
			topHighScore.writeData();
		}
	}

	public void setEndGame(boolean isEndGame) {
		this.isEndGame = isEndGame;
	}

	public boolean isEndGame() {
		return isEndGame;
	}

	public int getCountChange() {
		return countChange;
	}

	public void setCountChange(int countChange) {
		this.countChange = countChange;
	}

	public PokemonManager getPokemonManager() {
		return pokemonManager;
	}

	public void setPokemonManager(PokemonManager pokemonManager) {
		this.pokemonManager = pokemonManager;
	}

	public boolean isPausing() {
		return isPausing;
	}

	public void setPausing(boolean isPausing) {
		this.isPausing = isPausing;
	}

	public JLabel getLbBack() {
		return control.getLbBack();
	}

	public boolean isLoseGame() {
		return loseGame;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public boolean isFind() {
		return find;
	}

	public void setFind(boolean find) {
		this.find = find;
	}

	public int getCountFind() {
		return countFind;
	}

	public void setCountFind(int countFind) {
		this.countFind = countFind;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public int getCountDestroy() {
		return countDestroy;
	}

	public void setCountDestroy(int countDestroy) {
		this.countDestroy = countDestroy;
	}

}