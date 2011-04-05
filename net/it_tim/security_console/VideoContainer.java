package net.it_tim.security_console;

import java.util.ArrayList;
import net.it_tim.security_console.VideoExeptions.*;

public class VideoContainer {

	public VideoContainer() {
		videoContainer = new ArrayList<VlcCanvas>();
	}

	public void createCanvas(String url, String description) {
		VlcCanvas playerObject = new VlcCanvas(url);
		playerObject.setSize(320, 240);
		videoContainer.add(playerObject);
	}

	public void hideAll() {
		for (VlcCanvas canvasList: videoContainer) {
			canvasList.setVisible(false);
		}
	}

	public void showAll() {
		for (VlcCanvas canvasList: videoContainer) {
			canvasList.setVisible(true);
		}
	}

	public void playAll() throws CantPlayException {
		for (VlcCanvas canvasList: videoContainer)
			canvasList.playVideo();
	}
	
	public VlcCanvas getCanvas(int index) {
		return videoContainer.get(index);
	}

	private ArrayList<VlcCanvas> videoContainer;
}