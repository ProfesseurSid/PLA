package Visual;

import java.applet.Applet;

public class Fps extends Applet implements Runnable {
	public Thread animation;

	private long framerate;

	public void init() {
		animation = new Thread(this);

		framerate = 1000 / 60;
	}

	/*
	 * public void start(){ animation.start(); }
	 */

	public void stop() {
		animation = null;
	}

	public void run() {
		long frameStart;
		long frameCount = 0;
		long elapsedTime;
		long totalElapsedTime = 0;
		long reportedFramerate;
		Thread t = Thread.currentThread();
		while (t == animation) {
			frameStart = System.currentTimeMillis();
			repaint();
			elapsedTime = System.currentTimeMillis() - frameStart;
			try {
				if (elapsedTime < framerate) {
					Thread.sleep(framerate - elapsedTime);
				} else {
					Thread.sleep(5);
				}
			} catch (InterruptedException e) {
				break;
			}
			++frameCount;
			totalElapsedTime += (System.currentTimeMillis() - frameStart);
			if (totalElapsedTime > 1000) {
				reportedFramerate = (long) ((double) frameCount / (double) totalElapsedTime * 1000.0);
				System.out.println("fps:" + reportedFramerate);
				frameCount = 0;
				totalElapsedTime = 0;
			}

		}
	}

}
