package com.phongbm.pokemon;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int W_SCREEN = Toolkit.getDefaultToolkit()
			.getScreenSize().width;
	public static final int H_SCREEN = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public static final int W_FRAME = 1000;
	public static final int H_FRAME = 600;

	private GamePlay gamePlay;
	private Menu menu;
	private Help help;
	private HighScorePanel highScorePanel;
	private SoundLibrary soundLibrary = new SoundLibrary();
	private int countClickedSound = 0;
	private JLabel lbBGInfo;

	public GUI() {
		super("Pokemon Game");
		initializeFrame();
		setEventMouse();
	}

	private void initializeFrame() {
		this.setBounds((W_SCREEN - W_FRAME) / 2, (H_SCREEN - H_FRAME) / 2,
				W_FRAME, H_FRAME);
		this.setLayout(new CardLayout());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new ImageIcon(getClass().getResource("/images/icon.png"))
				.getImage();
		icon = icon.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		this.setIconImage(new ImageIcon(icon).getImage());

		soundLibrary.setSound(true);
		gamePlay = new GamePlay();
		menu = new Menu();
		help = new Help();
		highScorePanel = new HighScorePanel();

		lbBGInfo = new JLabel();
		lbBGInfo.setBounds(0, -15, W_FRAME, H_FRAME);
		Image bg = new ImageIcon(getClass().getResource(
				"/images/backgroundInfo.png")).getImage();
		bg = bg.getScaledInstance(lbBGInfo.getWidth(),
				lbBGInfo.getHeight() - 30, Image.SCALE_SMOOTH);
		lbBGInfo.setIcon(new ImageIcon(bg));
		this.add(lbBGInfo);
		lbBGInfo.setVisible(false);

		this.add(menu);
		menu.setVisible(true);
	}

	private void setEventMouse() {
		menu.getLbPlayGame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				menu.setVisible(false);
				GUI.this.add(gamePlay);
				gamePlay.setVisible(true);
				gamePlay.newGame();
			}
		});

		menu.getLbHelp().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				menu.setVisible(false);
				GUI.this.add(help);
				help.setVisible(true);
			}
		});

		help.getLbBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				help.setVisible(false);
				menu.setVisible(true);
			}
		});

		menu.getLbSound().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				countClickedSound++;
				if (countClickedSound % 2 == 0) {
					gamePlay.getSoundLibrary().setSound(true);
					Image imgSound = new ImageIcon(getClass().getResource(
							"/images/imgSound1.png")).getImage();
					imgSound = imgSound.getScaledInstance(menu.getLbSound()
							.getWidth(), menu.getLbSound().getHeight(),
							Image.SCALE_SMOOTH);
					menu.getLbSound().setIcon(new ImageIcon(imgSound));
				} else {
					gamePlay.getSoundLibrary().setSound(false);
					Image imgSound = new ImageIcon(getClass().getResource(
							"/images/imgSound2.png")).getImage();
					imgSound = imgSound.getScaledInstance(menu.getLbSound()
							.getWidth(), menu.getLbSound().getHeight(),
							Image.SCALE_SMOOTH);
					menu.getLbSound().setIcon(new ImageIcon(imgSound));
				}
			}
		});

		gamePlay.getLbBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				gamePlay.setEndGame(true);
				gamePlay.setVisible(false);
				menu.setVisible(true);
			}
		});

		menu.getLbInfo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				menu.setVisible(false);
				lbBGInfo.setVisible(true);
			}
		});

		lbBGInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				lbBGInfo.setVisible(false);
				menu.setVisible(true);
			}
		});

		menu.getLbHighScore().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				menu.setVisible(false);
				GUI.this.add(highScorePanel);
				highScorePanel.setVisible(true);
				highScorePanel.getTopHighScore().readData();
			}
		});

		highScorePanel.getLbBG().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				highScorePanel.setVisible(false);
				menu.setVisible(true);
			}
		});

	}

}