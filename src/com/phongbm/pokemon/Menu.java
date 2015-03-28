package com.phongbm.pokemon;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbBackground, lbPlayGame, lbHighScore, lbSettings, lbHelp,
			lbSound, lbInfo, lbButton1, lbButton2, lbExit;

	public Menu() {
		this.setLayout(null);
		this.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);

		initializeComponent();
		setEventMouse();
	}

	private void initializeComponent() {
		lbBackground = new JLabel();
		lbBackground.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);
		Image imgBackground = new ImageIcon(getClass().getResource(
				"/images/imgBackgroundMain.png")).getImage();
		imgBackground = imgBackground.getScaledInstance(
				lbBackground.getWidth(), lbBackground.getHeight() - 30,
				Image.SCALE_SMOOTH);
		lbBackground.setIcon(new ImageIcon(imgBackground));
		this.add(lbBackground);

		lbPlayGame = new JLabel();
		lbPlayGame.setBounds(350, 380, 130, 50);
		Image imgPlayGame = new ImageIcon(getClass().getResource(
				"/images/imgPlayGame1.png")).getImage();
		imgPlayGame = imgPlayGame.getScaledInstance(lbPlayGame.getWidth(),
				lbPlayGame.getHeight(), Image.SCALE_SMOOTH);
		lbPlayGame.setIcon(new ImageIcon(imgPlayGame));

		lbHighScore = new JLabel();
		lbHighScore.setBounds(350, 440, 132, 52);
		Image imgHighScore = new ImageIcon(getClass().getResource(
				"/images/imgHighScore1.png")).getImage();
		imgHighScore = imgHighScore.getScaledInstance(lbHighScore.getWidth(),
				lbHighScore.getHeight(), Image.SCALE_SMOOTH);
		lbHighScore.setIcon(new ImageIcon(imgHighScore));

		lbSettings = new JLabel();
		lbSettings.setBounds(900, 150, 50, 150);
		Image imgSettings = new ImageIcon(getClass().getResource(
				"/images/imgSettings1.png")).getImage();
		imgSettings = imgSettings.getScaledInstance(lbSettings.getWidth(),
				lbSettings.getHeight(), Image.SCALE_SMOOTH);
		lbSettings.setIcon(new ImageIcon(imgSettings));

		lbHelp = new JLabel();
		lbHelp.setBounds(0, 50, 50, 49);
		Image imgHelp = new ImageIcon(getClass().getResource(
				"/images/imgHelp1.png")).getImage();
		imgHelp = imgHelp.getScaledInstance(lbHelp.getWidth(),
				lbHelp.getHeight(), Image.SCALE_SMOOTH);
		lbHelp.setIcon(new ImageIcon(imgHelp));

		lbSound = new JLabel();
		lbSound.setBounds(0, 0, 50, 49);
		Image imgSound = new ImageIcon(getClass().getResource(
				"/images/imgSound1.png")).getImage();
		imgSound = imgSound.getScaledInstance(lbSound.getWidth(),
				lbSound.getHeight(), Image.SCALE_SMOOTH);
		lbSound.setIcon(new ImageIcon(imgSound));

		lbSettings.add(lbSound);
		lbSettings.add(lbHelp);

		lbInfo = new JLabel();
		lbInfo.setBounds(350, 500, 58, 62);
		lbInfo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(
				"/images/imgInfo1.png")).getImage()));

		lbButton1 = new JLabel();
		lbButton1.setBounds(420, 500, 58, 62);
		lbButton1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(
				"/images/button1.png")).getImage()));

		lbButton2 = new JLabel();
		lbButton2.setBounds(490, 500, 58, 62);
		lbButton2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(
				"/images/button2.png")).getImage()));

		lbExit = new JLabel();
		lbExit.setBounds(560, 500, 58, 62);
		lbExit.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(
				"/images/imgExit1.png")).getImage()));

		lbBackground.add(lbPlayGame);
		lbBackground.add(lbHighScore);
		lbBackground.add(lbSettings);
		lbBackground.add(lbInfo);
		lbBackground.add(lbButton1);
		lbBackground.add(lbButton2);
		lbBackground.add(lbExit);
	}

	private void setEventMouse() {
		lbPlayGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgPlayGame = new ImageIcon(getClass().getResource(
						"/images/imgPlayGame2.png")).getImage();
				imgPlayGame = imgPlayGame.getScaledInstance(
						lbPlayGame.getWidth(), lbPlayGame.getHeight(),
						Image.SCALE_SMOOTH);
				lbPlayGame.setIcon(new ImageIcon(imgPlayGame));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgPlayGame = new ImageIcon(getClass().getResource(
						"/images/imgPlayGame1.png")).getImage();
				imgPlayGame = imgPlayGame.getScaledInstance(
						lbPlayGame.getWidth(), lbPlayGame.getHeight(),
						Image.SCALE_SMOOTH);
				lbPlayGame.setIcon(new ImageIcon(imgPlayGame));
			}
		});

		lbHighScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgHighScore = new ImageIcon(getClass().getResource(
						"/images/imgHighScore2.png")).getImage();
				imgHighScore = imgHighScore.getScaledInstance(
						lbHighScore.getWidth(), lbHighScore.getHeight(),
						Image.SCALE_SMOOTH);
				lbHighScore.setIcon(new ImageIcon(imgHighScore));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				Image imgHighScore = new ImageIcon(getClass().getResource(
						"/images/imgHighScore1.png")).getImage();
				imgHighScore = imgHighScore.getScaledInstance(
						lbHighScore.getWidth(), lbHighScore.getHeight(),
						Image.SCALE_SMOOTH);
				lbHighScore.setIcon(new ImageIcon(imgHighScore));
			}
		});

		lbSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				Image imgSettings = new ImageIcon(getClass().getResource(
						"/images/imgSettings2.png")).getImage();
				imgSettings = imgSettings.getScaledInstance(
						lbSettings.getWidth(), lbSettings.getHeight(),
						Image.SCALE_SMOOTH);
				lbSettings.setIcon(new ImageIcon(imgSettings));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				super.mouseEntered(e);
				Image imgSettings = new ImageIcon(getClass().getResource(
						"/images/imgSettings1.png")).getImage();
				imgSettings = imgSettings.getScaledInstance(
						lbSettings.getWidth(), lbSettings.getHeight(),
						Image.SCALE_SMOOTH);
				lbSettings.setIcon(new ImageIcon(imgSettings));
			}
		});

		lbInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbInfo.setIcon(new ImageIcon(new ImageIcon(getClass()
						.getResource("/images/imgInfo2.png")).getImage()));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbInfo.setIcon(new ImageIcon(new ImageIcon(getClass()
						.getResource("/images/imgInfo1.png")).getImage()));
			}
		});

		lbExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbExit.setIcon(new ImageIcon(new ImageIcon(getClass()
						.getResource("/images/imgExit2.png")).getImage()));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbExit.setIcon(new ImageIcon(new ImageIcon(getClass()
						.getResource("/images/imgExit1.png")).getImage()));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.exit(0);
			}
		});

	}

	public JLabel getLbPlayGame() {
		return lbPlayGame;
	}

	public JLabel getLbHighScore() {
		return lbHighScore;
	}

	public JLabel getLbHelp() {
		return lbHelp;
	}

	public void setLbHelp(JLabel lbHelp) {
		this.lbHelp = lbHelp;
	}

	public JLabel getLbSound() {
		return lbSound;
	}

	public void setLbSound(JLabel lbSound) {
		this.lbSound = lbSound;
	}

	public JLabel getLbInfo() {
		return lbInfo;
	}

	public void setLbInfo(JLabel lbInfo) {
		this.lbInfo = lbInfo;
	}

}