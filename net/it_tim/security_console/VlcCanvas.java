package net.it_tim.security_console;

import java.awt.Canvas;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

public class VlcCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8870325582708677218L;
	
	public VlcCanvas(String url) {
		super();
		mediaPlayerFactory = new MediaPlayerFactory(new String[] {"--no-video-title-show"});
		mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
		videoSurface = mediaPlayerFactory.newVideoSurface(this);
		mediaPlayer.setVideoSurface(videoSurface);
		this.url = url;
	}
	
	public void playVideo() {
		mediaPlayer.playMedia(url);
	}
	
	public void close() {
		mediaPlayer.release();
        mediaPlayerFactory.release();
	}
	private CanvasVideoSurface videoSurface;
	private EmbeddedMediaPlayer mediaPlayer;
	private MediaPlayerFactory mediaPlayerFactory;
	private String url;
}
