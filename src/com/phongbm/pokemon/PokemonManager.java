package com.phongbm.pokemon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class PokemonManager {
	public static final int PADDING_LEFT = 100;
	public static final int PADDING_TOP = 120;
	public static final int ROW = 8;
	public static final int COLUMN = 16;
	public static final int SIZE = PokemonCell.SIZE + 5;
	private List<PokemonCell> pokemonCells;
	private PokemonAlgorithm pokemonAlgorithm;
	private int[][] grid;
	private boolean isPair, isOk = false;
	private int items;
	private List<Point> points;

	public PokemonManager() {
		pokemonCells = new ArrayList<PokemonCell>();
		pokemonAlgorithm = new PokemonAlgorithm(ROW + 2, COLUMN + 2);
		grid = new int[ROW + 2][COLUMN + 2];
		grid = pokemonAlgorithm.getGrid();
		points = new ArrayList<Point>();
		points = pokemonAlgorithm.getPoints();
		items = ROW * COLUMN / 2;
	}

	public void initializePokemon() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				initializePokemonCell(PADDING_LEFT + j * SIZE, PADDING_TOP + i
						* SIZE, grid[i + 1][j + 1]);
			}
		}
	}

	private void initializePokemonCell(int x, int y, int indexImage) {
		Image image = new ImageIcon(getClass().getResource(
				"/images/icons/icon" + indexImage + ".png")).getImage();
		pokemonCells.add(new PokemonCell(x, y, image));
	}

	public void shufflePokemon() {
		List<Integer> indexs = new ArrayList<Integer>();
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (grid[i + 1][j + 1] != 0) {
					indexs.add(grid[i + 1][j + 1]);
				}
			}
		}
		Collections.shuffle(indexs);
		int k = 0, h = 0;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (grid[i + 1][j + 1] != 0) {
					grid[i + 1][j + 1] = indexs.get(k);
					Image icon = new ImageIcon(getClass().getResource(
							"/images/icons/icon" + indexs.get(k) + ".png"))
							.getImage();
					pokemonCells.get(h).setImage(icon);
					k++;
				}
				h++;
			}
		}
		pokemonAlgorithm.setGrid(grid);
	}

	public List<Point> findPairs() {
		List<Point> pairs = new ArrayList<Point>();
		for (int i = 1; i <= ROW; i++) {
			for (int j = 1; j <= COLUMN; j++) {
				if (grid[i][j] != 0) {
					for (int m = i; m <= ROW; m++) {
						for (int n = 1; n <= COLUMN; n++) {
							if (grid[m][n] != 0) {
								if (grid[i][j] == grid[m][n]
										&& pokemonAlgorithm.checkTwoPoint(
												new Point(i, j),
												new Point(m, n))) {
									pairs.add(new Point(i, j));
									pairs.add(new Point(m, n));
									return pairs;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public void drawAllPokemon(Graphics2D g2D) {
		for (PokemonCell pokemonCell : pokemonCells) {
			pokemonCell.drawPokemonCell(g2D);
		}
	}

	public int findEvent(CompareKey key) {
		for (int i = 0; i < pokemonCells.size(); i++) {
			if (pokemonCells.get(i).isContain(key.x, key.y))
				return i;
		}
		return -1;
	}

	public void changeBGClicked(int index) {
		if (index < 0)
			return;
		pokemonCells.get(index).setBackground(PokemonCell.imageBG1);
	}

	public void returnBGClicked(int index) {
		if (index < 0)
			return;
		pokemonCells.get(index).setBackground(PokemonCell.imageBG2);
	}

	public boolean pokemon(Point point1, Point point2) {
		isPair = pokemonAlgorithm.checkTwoPoint(point1, point2);
		if (isPair) {
			pokemonAlgorithm.getGrid()[point1.x][point1.y] = 0;
			pokemonAlgorithm.getGrid()[point2.x][point2.y] = 0;
			grid[point1.x][point1.y] = 0;
			grid[point2.x][point2.y] = 0;
			isPair = false;
			items = items - 1;
			isOk = true;
			return true;
		} else {
		}
		return false;
	}

	private void setImages() {
		pokemonAlgorithm.setGrid(grid);
		int k = 0;
		for (int i = 1; i <= ROW; i++) {
			for (int j = 1; j <= COLUMN; j++) {
				if (grid[i][j] != 0) {
					pokemonCells.get(k)
							.setImage(
									new ImageIcon(getClass().getResource(
											"/images/icons/icon" + grid[i][j]
													+ ".png")).getImage());
					pokemonCells.get(k).setShow(true);
				} else {
					pokemonCells.get(k).setImage(null);
					pokemonCells.get(k).setShow(false);
				}
				k++;
			}
		}
	}

	public void moveLeft(int y1, int y2) {
		for (int i = 1; i <= ROW; i++) {
			for (int j = y1; j <= y2; j++) {
				if (grid[i][j] == 0) {
					for (int k = j + 1; k <= y2; k++) {
						if (grid[i][k] != 0) {
							grid[i][j] = grid[i][k];
							grid[i][k] = 0;
							break;
						}
					}
				}
			}
		}
		setImages();
	}

	public void moveRight(int y1, int y2) {
		for (int i = 1; i <= ROW; i++) {
			for (int j = y1; j >= y2; j--) {
				if (grid[i][j] == 0) {
					for (int k = j - 1; k >= y2; k--) {
						if (grid[i][k] != 0) {
							grid[i][j] = grid[i][k];
							grid[i][k] = 0;
							break;
						}
					}
				}
			}
		}
		setImages();
	}

	public void moveUp(int x1, int x2) {
		for (int j = 1; j <= COLUMN; j++) {
			for (int i = x1; i <= x2; i++) {
				if (grid[i][j] == 0) {
					for (int k = i + 1; k <= x2; k++) {
						if (grid[k][j] != 0) {
							grid[i][j] = grid[k][j];
							grid[k][j] = 0;
							break;
						}
					}
				}
			}
		}
		setImages();
	}

	public void moveDown(int x1, int x2) {
		for (int j = 1; j <= COLUMN; j++) {
			for (int i = x1; i >= x2; i--) {
				if (grid[i][j] == 0) {
					for (int k = i - 1; k >= x2; k--) {
						if (grid[k][j] != 0) {
							grid[i][j] = grid[k][j];
							grid[k][j] = 0;
							break;
						}
					}
				}
			}
		}
		setImages();
	}

	public void moveLeftRight() {
		moveLeft(1, COLUMN / 2);
		moveRight(COLUMN, COLUMN / 2 + 1);
	}

	public void moveUpDown() {
		moveUp(1, ROW / 2);
		moveDown(ROW, ROW / 2 + 1);
	}

	public void moveLeftRightAmong() {
		moveLeft(COLUMN / 2 + 1, COLUMN);
		moveRight(COLUMN / 2, 1);
	}

	public void moveUpDownAmong() {
		moveUp(ROW / 2 + 1, ROW);
		moveDown(ROW / 2, 1);
	}

	public void moveRandom1() {
		if ((new Random()).nextInt(2) == 0) {
			moveUp(1, ROW);
			moveLeft(1, COLUMN);
		} else {
			moveLeft(1, COLUMN);
			moveUp(1, ROW);
		}
	}

	public void moveRandom2() {
		if ((new Random()).nextInt(2) == 0) {
			moveUp(1, ROW);
			moveRight(COLUMN, 1);
		} else {
			moveRight(COLUMN, 1);
			moveUp(1, ROW);
		}
	}

	public void moveRandom3() {
		if ((new Random()).nextInt(2) == 0) {
			moveDown(ROW, 1);
			moveRight(COLUMN, 1);
		} else {
			moveRight(COLUMN, 1);
			moveDown(ROW, 1);
		}
	}

	public void moveRandom4() {
		if ((new Random()).nextInt(2) == 0) {
			moveDown(ROW, 1);
			moveLeft(1, COLUMN);
		} else {
			moveLeft(1, COLUMN);
			moveDown(ROW, 1);
		}
	}

	public List<PokemonCell> getPokemonCells() {
		return pokemonCells;
	}

	public List<Point> getPoints() {
		return points;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public PokemonAlgorithm getPokemonAlgorithm() {
		return pokemonAlgorithm;
	}

	public void setPokemonAlgorithm(PokemonAlgorithm pokemonAlgorithm) {
		this.pokemonAlgorithm = pokemonAlgorithm;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

}