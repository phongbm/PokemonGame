package com.phongbm.pokemon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScorePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TopHighScore topHighScore = new TopHighScore();
	private JLabel lbBG;

	public HighScorePanel() {
		this.setLayout(null);
		this.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);

		lbBG = new JLabel();
		lbBG.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);
		Image imgBackground = new ImageIcon(getClass().getResource(
				"/images/bgHighScore.png")).getImage();
		imgBackground = imgBackground.getScaledInstance(lbBG.getWidth(),
				lbBG.getHeight() - 30, Image.SCALE_SMOOTH);
		lbBG.setIcon(new ImageIcon(imgBackground));
		this.add(lbBG);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		RenderingHints renderingHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderingHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHints(renderingHints);
		g2D.setColor(Color.RED);
		g2D.setFont(new Font("Tahoma", Font.BOLD, 60));
		g2D.drawString("Điểm Cao Nhất", 300, 100);

		g2D.setColor(Color.BLUE);
		g2D.setFont(new Font("Tahoma", Font.BOLD, 30));
		int y = 250;
		for (int i = 0; i < topHighScore.getTopHightScore().size(); i++) {
			g2D.drawString(topHighScore.getTopHightScore().get(i).getName(),
					150, y);
			g2D.drawString(
					String.valueOf(topHighScore.getTopHightScore().get(i)
							.getScores() + "   Điểm"), 500, y);
			y += 50;
		}
	}

	public JLabel getLbBG() {
		return lbBG;
	}

	public TopHighScore getTopHighScore() {
		return topHighScore;
	}

}