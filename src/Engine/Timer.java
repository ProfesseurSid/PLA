package Engine;

import java.util.Date;

import Visual.Terrain;
import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {
	long lastTime = new Date().getTime();

	public void handle(long now) {
		Robots rob1, rob2;
		long date = new Date().getTime();
		if (date - lastTime > 500) {
			for (int i = 1; i < 4; i++) {
				rob1 = Terrain.personnage1.getRobot(i);
				rob2 = Terrain.personnage2.getRobot(i);
				if (rob1 != null)
					rob1.step();
				if (rob2 != null)
					rob1.step();
			}
			lastTime = date;
		}
	}
}
