package com.phongbm.pokemon;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundLibrary {

	public static AudioClip SELECT;
	public static AudioClip WRONG;
	public static AudioClip OK;
	public static AudioClip PIKAWIN;
	public static AudioClip PIKAWHO;
	public static AudioClip PIKALOSE;
	public static AudioClip PAUSE;

	public void setSound(boolean logic) {
		if (logic) {
			SELECT = Applet.newAudioClip(getClass().getResource(
					"/sounds/select.wav"));
			WRONG = Applet.newAudioClip(getClass().getResource(
					"/sounds/wrong.wav"));
			OK = Applet.newAudioClip(getClass().getResource("/sounds/ok.wav"));
			PIKAWIN = Applet.newAudioClip(getClass().getResource(
					"/sounds/pikaWin.wav"));
			PIKAWHO = Applet.newAudioClip(getClass().getResource(
					"/sounds/pikaWho.wav"));
			PIKALOSE = Applet.newAudioClip(getClass().getResource(
					"/sounds/pikaLose.wav"));
			PAUSE = Applet.newAudioClip(getClass().getResource(
					"/sounds/pause.wav"));
		} else {
			SELECT = null;
			WRONG = null;
			OK = null;
			PIKAWIN = null;
			PIKAWHO = null;
			PIKALOSE = null;
			PAUSE = null;
		}
	}

	public void playSelect() {
		if (SELECT != null) {
			SELECT.play();
		}
	}

	public void playWrong() {
		if (WRONG != null) {
			WRONG.play();
		}
	}

	public void playOk() {
		if (OK != null) {
			OK.play();
		}
	}

	public void playPikaWin() {
		if (PIKAWIN != null) {
			PIKAWIN.play();
		}
	}

	public void playPikaWho() {
		if (PIKAWHO != null) {
			PIKAWHO.play();
		}
	}

	public void playPikaLose() {
		if (PIKALOSE != null) {
			PIKALOSE.play();
		}
	}

	public void playPause() {
		if (PAUSE != null) {
			PAUSE.play();
		}
	}

}