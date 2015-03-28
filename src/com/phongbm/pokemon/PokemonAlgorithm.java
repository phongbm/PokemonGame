package com.phongbm.pokemon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonAlgorithm {
	private static final int NUMBER_IMAGES = 25;
	private static final int MAX_IMAGE = 25;

	private int row, column;
	private int notBarrier;
	private int[][] grid;
	private List<Point> points;

	public PokemonAlgorithm(int row, int column) {
		this.row = row;
		this.column = column;
		notBarrier = 0;

		points = new ArrayList<Point>();
		initializeGrid();
	}

	private void initializeGrid() {
		grid = new int[row][column];
		for (int i = 0; i < column; i++) {
			grid[0][i] = 0;
			grid[row - 1][i] = 0;
		}
		for (int i = 0; i < row; i++) {
			grid[i][0] = 0;
			grid[i][column - 1] = 0;
		}
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < column - 1; j++) {
				points.add(new Point(i, j));
			}
		}
		Random random = new Random();
		int[] indexIcons = new int[NUMBER_IMAGES + 1];
		int items = 0;
		do {
			int indexIcon = 1 + random.nextInt(NUMBER_IMAGES);
			if (indexIcons[indexIcon] < MAX_IMAGE) {
				indexIcons[indexIcon] += 2;
				for (int j = 0; j < 2; j++) {
					try {
						int size = points.size();
						int pointIndex = random.nextInt(size);
						grid[points.get(pointIndex).x][points.get(pointIndex).y] = indexIcon;
						points.remove(pointIndex);
					} catch (Exception e) {
					}
				}
				items++;
			}
		} while (items < row * column / 2);
	}

	private boolean checkLineX(int x, int y1, int y2) {
		int min = Math.min(y1, y2);
		int max = Math.max(y1, y2);
		for (int y = min + 1; y < max; y++) {
			if (grid[x][y] > notBarrier) {
				return false;
			}
		}
		points.clear();
		points.add(new Point(x, y1));
		points.add(new Point(x, y2));
		return true;
	}

	private boolean checkLineY(int y, int x1, int x2) {
		int min = Math.min(x1, x2);
		int max = Math.max(x1, x2);
		for (int x = min + 1; x < max; x++) {
			if (grid[x][y] > notBarrier) {
				return false;
			}
		}
		points.clear();
		points.add(new Point(x1, y));
		points.add(new Point(x2, y));
		return true;
	}

	private int checkRectX(Point point1, Point point2) {
		Point pointMinY = point1, pointMaxY = point2;
		if (point1.y > point2.y) {
			pointMinY = point2;
			pointMaxY = point1;
		}
		for (int y = pointMinY.y; y <= pointMaxY.y; y++) {
			if ((y > pointMinY.y) && (grid[pointMinY.x][y] != notBarrier)) {
				return -1;
			}
			if ((grid[pointMaxY.x][y] == notBarrier || y == pointMaxY.y)
					&& checkLineY(y, pointMinY.x, pointMaxY.x)
					&& checkLineX(pointMaxY.x, y, pointMaxY.y)) {
				points.clear();
				points.add(new Point(point1.x, point1.y));
				points.add(new Point(point1.x, y));
				points.add(new Point(point2.x, y));
				points.add(new Point(point2.x, point2.y));
				return y;
			}
		}
		return -1;
	}

	private int checkRectY(Point point1, Point point2) {

		Point pointMinX = point1, pointMaxX = point2;
		if (point1.x > point2.x) {
			pointMinX = point2;
			pointMaxX = point1;
		}
		for (int x = pointMinX.x; x <= pointMaxX.x; x++) {
			if (x > pointMinX.x && grid[x][pointMinX.y] != notBarrier) {
				return -1;
			}
			if ((grid[x][pointMaxX.y] == notBarrier || x == pointMaxX.x)
					&& checkLineX(x, pointMinX.y, pointMaxX.y)
					&& checkLineY(pointMaxX.y, x, pointMaxX.x)) {
				points.clear();
				points.add(new Point(point1.x, point1.y));
				points.add(new Point(x, point1.y));
				points.add(new Point(x, point2.y));
				points.add(new Point(point2.x, point2.y));
				return x;
			}
		}
		return -1;
	}

	private int checkMoreLineX(Point point1, Point point2, int dir) {
		Point pointMinY = point1, pointMaxY = point2;
		if (point1.y > point2.y) {
			pointMinY = point2;
			pointMaxY = point1;
		}
		int y = pointMaxY.y + dir;
		int row = pointMinY.x;
		int columnFinish = pointMaxY.y;
		if (dir == -1) {
			columnFinish = pointMinY.y;
			y = pointMinY.y + dir;
			row = pointMaxY.x;
		}
		if ((grid[row][columnFinish] == notBarrier || pointMinY.y == pointMaxY.y)
				&& checkLineX(row, pointMinY.y, pointMaxY.y)) {
			while (grid[pointMinY.x][y] == notBarrier
					&& grid[pointMaxY.x][y] == notBarrier) {
				if (checkLineY(y, pointMinY.x, pointMaxY.x)) {
					points.clear();
					points.add(new Point(point1.x, point1.y));
					points.add(new Point(point1.x, y));
					points.add(new Point(point2.x, y));
					points.add(new Point(point2.x, point2.y));
					return y;
				}
				y += dir;
			}
		}
		return -1;
	}

	private int checkMoreLineY(Point point1, Point point2, int dir) {
		Point pointMinX = point1, pointMaxX = point2;
		if (point1.x > point2.x) {
			pointMinX = point2;
			pointMaxX = point1;
		}
		int x = pointMaxX.x + dir;
		int column = pointMinX.y;
		int rowFinish = pointMaxX.x;
		if (dir == -1) {
			rowFinish = pointMinX.x;
			x = pointMinX.x + dir;
			column = pointMaxX.y;
		}
		if ((grid[rowFinish][column] == notBarrier || pointMinX.x == pointMaxX.x)
				&& checkLineY(column, pointMinX.x, pointMaxX.x)) {
			while (grid[x][pointMinX.y] == notBarrier
					&& grid[x][pointMaxX.y] == notBarrier) {
				if (checkLineX(x, pointMinX.y, pointMaxX.y)) {
					points.clear();
					points.add(new Point(point1.x, point1.y));
					points.add(new Point(x, point1.y));
					points.add(new Point(x, point2.y));
					points.add(new Point(point2.x, point2.y));
					return x;
				}
				x += dir;
			}
		}
		return -1;
	}

	public boolean checkTwoPoint(Point point1, Point point2) {
		if (!point1.equals(point2)
				&& grid[point1.x][point1.y] == grid[point2.x][point2.y]) {
			if (point1.x == point2.x) {
				if (checkLineX(point1.x, point1.y, point2.y)) {
					return true;
				}
			}
			if (point1.y == point2.y) {
				if (checkLineY(point1.y, point1.x, point2.x)) {
					return true;
				}
			}
			if (checkRectX(point1, point2) != -1) {
				return true;
			}
			if (checkRectY(point1, point2) != -1) {
				return true;
			}
			if (checkMoreLineX(point1, point2, 1) != -1) {
				return true;
			}
			if (checkMoreLineX(point1, point2, -1) != -1) {
				return true;
			}
			if (checkMoreLineY(point1, point2, 1) != -1) {
				return true;
			}
			if (checkMoreLineY(point1, point2, -1) != -1) {
				return true;
			}
		}
		return false;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public List<Point> getPoints() {
		return points;
	}

}