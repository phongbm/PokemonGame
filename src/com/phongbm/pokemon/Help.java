package com.phongbm.pokemon;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Help extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbHelp, lbBack;

	public Help() {
		this.setLayout(null);
		this.setBounds(0, 0, GUI.W_FRAME, GUI.H_FRAME);

		initializeComponent();
	}

	private void initializeComponent() {
		lbHelp = new JLabel();
		lbHelp.setBounds(0, -15, GUI.W_FRAME, GUI.H_FRAME);
		Image imgBackground = new ImageIcon(getClass().getResource(
				"/images/imgHelpPanel.png")).getImage();
		imgBackground = imgBackground.getScaledInstance(lbHelp.getWidth(),
				lbHelp.getHeight() -30, Image.SCALE_SMOOTH);
		lbHelp.setIcon(new ImageIcon(imgBackground));

		lbBack = new JLabel();
		lbBack.setBounds(900, 500, 79, 72);
		lbBack.setIcon(new ImageIcon(getClass().getResource(
				"/images/imgBack1.png")));
		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				lbBack.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgBack2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				lbBack.setIcon(new ImageIcon(getClass().getResource(
						"/images/imgBack1.png")));
			}
		});

		this.add(lbHelp);
		lbHelp.add(lbBack);
	}

	public JLabel getLbBack() {
		return lbBack;
	}

}