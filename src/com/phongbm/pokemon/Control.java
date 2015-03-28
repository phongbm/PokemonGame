package com.phongbm.pokemon;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Control extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GamePlay gamePlay;
	private JLabel lbBackground, lbFind, lbChange, lbDestroy, lbPause,
			lbNewGame, lbStartGame, lbMenu, lbBack, lbBackgroundPause;

	public Control(GamePlay gamePlay) {
		this.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);
		this.setLayout(null);
		this.gamePlay = gamePlay;
		initializeComponents();
		setEventComponents();
	}

	private void initializeComponents() {
		lbBackground = new JLabel();
		lbBackground.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);
		this.add(lbBackground);

		lbFind = new JLabel();
		lbFind.setBounds(650, 10, 50, 50);
		lbFind.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgFind1.png")));
		lbBackground.add(lbFind);

		lbChange = new JLabel();
		lbChange.setBounds(700, 10, 50, 50);
		lbChange.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgChange1.png")));
		lbBackground.add(lbChange);

		lbDestroy = new JLabel();
		lbDestroy.setBounds(750, 10, 50, 50);
		lbDestroy.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgDestroy1.png")));
		lbBackground.add(lbDestroy);

		lbPause = new JLabel();
		lbPause.setBounds(800, 10, 50, 50);
		lbPause.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgPause1.png")));
		lbBackground.add(lbPause);

		lbMenu = new JLabel();
		lbMenu.setBounds(20, 270, 50, 50);
		Image imgMenu = new ImageIcon(getClass().getResource(
				"/images/menu1.png")).getImage();
		imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbMenu.setIcon(new ImageIcon(imgMenu));
		lbBackground.add(lbMenu);

		lbNewGame = new JLabel();
		lbNewGame.setBounds(20, 320, 50, 50);
		imgMenu = new ImageIcon(getClass().getResource("/images/newGame1.png"))
				.getImage();
		imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbNewGame.setIcon(new ImageIcon(imgMenu));
		lbBackground.add(lbNewGame);

		lbStartGame = new JLabel();
		lbStartGame.setBounds(20, 370, 50, 50);
		imgMenu = new ImageIcon(getClass()
				.getResource("/images/startGame1.png")).getImage();
		imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbStartGame.setIcon(new ImageIcon(imgMenu));
		lbBackground.add(lbStartGame);

		lbBack = new JLabel();
		lbBack.setBounds(20, 420, 50, 50);
		imgMenu = new ImageIcon(getClass().getResource("/images/imgBack1.png"))
				.getImage();
		imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lbBack.setIcon(new ImageIcon(imgMenu));
		lbBackground.add(lbBack);

		lbBackgroundPause = new JLabel();
		lbBackgroundPause.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);
		Image imgBackgroundPause = new ImageIcon(getClass().getResource(
				"/images/imgBackgroundPause.png")).getImage();
		imgBackgroundPause = imgBackgroundPause.getScaledInstance(GUI.W_FRAME,
				GUI.H_FRAME, Image.SCALE_SMOOTH);
		lbBackgroundPause.setIcon(new ImageIcon(imgBackgroundPause));
		this.add(lbBackgroundPause);
		lbBackgroundPause.setVisible(false);
	}

	private void setEventComponents() {
		lbFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (gamePlay.isEndGame()) {
					return;
				}
				if (gamePlay.getCountFind() > 0) {
					gamePlay.setFind(true);
					if (gamePlay.getScores() > 10) {
						gamePlay.setScores(gamePlay.getScores() - 10);
					} else {
						gamePlay.setScores(0);
					}
					gamePlay.setCountFind(gamePlay.getCountFind() - 1);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbFind.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgFind2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbFind.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgFind1.png")));
			}
		});

		lbChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (gamePlay.isEndGame()) {
					return;
				}
				if (gamePlay.getCountChange() > 0) {
					gamePlay.getPokemonManager().shufflePokemon();
					if (gamePlay.getScores() > 10) {
						gamePlay.setScores(gamePlay.getScores() - 10);
					} else {
						gamePlay.setScores(0);
					}
					gamePlay.setCountChange(gamePlay.getCountChange() - 1);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbChange.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgChange2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbChange.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgChange1.png")));
			}
		});

		lbDestroy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (gamePlay.isEndGame()) {
					return;
				}
				if (gamePlay.getCountDestroy() > 0) {
					gamePlay.setDestroy(true);
					if (gamePlay.getScores() > 10) {
						gamePlay.setScores(gamePlay.getScores() - 10);
					} else {
						gamePlay.setScores(0);
					}
					gamePlay.setCountDestroy(gamePlay.getCountDestroy() - 1);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbDestroy.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgDestroy2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbDestroy.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgDestroy1.png")));
			}
		});

		lbPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (gamePlay.isEndGame()) {
					return;
				}
				gamePlay.setPausing(true);
				lbBackground.setVisible(false);
				lbBackgroundPause.setVisible(true);
				gamePlay.getSoundLibrary().playPause();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbPause.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgPause2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbPause.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgPause1.png")));
			}
		});

		lbBackgroundPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbBackgroundPause.setVisible(false);
				lbBackground.setVisible(true);
				gamePlay.setPausing(false);
			}
		});

		lbMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/menu2.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbMenu.setIcon(new ImageIcon(imgMenu));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/menu1.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbMenu.setIcon(new ImageIcon(imgMenu));
			}
		});

		lbNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				gamePlay.setEndGame(true);
				gamePlay.newGame();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/newGame2.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbNewGame.setIcon(new ImageIcon(imgMenu));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/newGame1.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbNewGame.setIcon(new ImageIcon(imgMenu));
			}
		});

		lbStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!gamePlay.isEndGame() || gamePlay.isLoseGame()) {
					return;
				}
				gamePlay.getSoundLibrary().playPikaWho();
				gamePlay.startGame();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/startGame2.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbStartGame.setIcon(new ImageIcon(imgMenu));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/startGame1.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbStartGame.setIcon(new ImageIcon(imgMenu));
			}
		});

		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/imgBack2.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbBack.setIcon(new ImageIcon(imgMenu));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgMenu = new ImageIcon(getClass().getResource(
						"/images/imgBack1.png")).getImage();
				imgMenu = imgMenu.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lbBack.setIcon(new ImageIcon(imgMenu));
			}
		});
	}

	public JLabel getLbMenu() {
		return lbMenu;
	}

	public JLabel getLbBack() {
		return lbBack;
	}

}