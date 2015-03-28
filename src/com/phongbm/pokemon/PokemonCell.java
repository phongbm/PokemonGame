package com.phongbm.pokemon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PokemonCell {
	public static final int SIZE = 45;
	public static Image imageBG1 = new ImageIcon(
			PokemonCell.class.getResource("/images/squares/square_0.png"))
			.getImage();
	public static Image imageBG2 = new ImageIcon(
			PokemonCell.class.getResource("/images/squares/square_"
					+ (1 + (new Random()).nextInt(9)) + ".png")).getImage();
	Image background = imageBG2;

	private int x, y;
	private Image image;
	private boolean isShow;

	public PokemonCell() {
	}

	public PokemonCell(int x, int y, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
		isShow = true;
	}

	public boolean isContain(int x, int y) {
		if (this.x <= x && x <= this.x + SIZE && this.y <= y
				&& y <= this.y + SIZE)
			return true;
		return false;
	}

	public void drawPokemonCell(Graphics2D g2D) {
		if (isShow) {
			g2D.drawImage(background, x, y, SIZE, SIZE, null);
			g2D.drawImage(image, x, y, SIZE, SIZE, null);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

}