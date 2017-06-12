package Visual;

import java.util.Date;

import Engine.Robots;
import javafx.animation.AnimationTimer;

/**
 * Implemente un timer afin d'executer certaines actions pour chaque laps de
 * temps donne
 * 
 * @author CHANET Zoran
 *
 */
public class Timer extends AnimationTimer {
	long lastTime = new Date().getTime();

	/**
	 * a l'appel du timer, on execute cette fonction
	 */
	public void handle(long now) {
		Robots rob1, rob2;
		long date = new Date().getTime();

		/* Si la derniere action a ete effectuee il y a plus de 500ms */
		if (date - lastTime > 500) {
			/* On execute un pas de chacun des robots existants */
			for (int i = 1; i < 4; i++) {
				rob1 = Terrain.personnage1.getRobot(i);
				rob2 = Terrain.personnage2.getRobot(i);
				if (rob1 != null)
					rob1.step();
				if (rob2 != null)
					rob2.step();
			}
			// TODO test de estenvie
			if (!Terrain.personnage1.estEnVie() && !Terrain.personnage1.estEnVie()) {
				// DRAW GAME
			} else if (!Terrain.personnage1.estEnVie()) {
				// PLAYER 2 WINS HAHAHA
			} else if (!Terrain.personnage2.estEnVie()) {
				// PLAYER 1 WINS HAHAHA
			} else
				for (int i = 1; i < 4; i++) {
					rob1 = Terrain.personnage1.getRobot(i);
					rob2 = Terrain.personnage2.getRobot(i);
					if (rob1 != null) {
						System.out.println("ROB1PV : " + rob1.getHealth());
						if (!rob1.estEnVie()) {
							Terrain.personnage1.removeRobot(i);
							rob1.getVisual().remove();
							Terrain.plateau.remove(rob1.getX(), rob1.getY(), rob1);
						}
					}
					if (rob2 != null) {
						System.out.println("ROB2PV : " + rob2.getHealth());
						if (!rob2.estEnVie()) {
							Terrain.personnage2.removeRobot(i);
							rob2.getVisual().remove();
							Terrain.plateau.remove(rob2.getX(), rob2.getY(), rob2);
						}
					}
				}
			System.out.println("Vie P1 : " + Terrain.personnage1.getHealth());
			System.out.println("Vie P2 : " + Terrain.personnage2.getHealth());
			lastTime = date;
		}
	}
}
