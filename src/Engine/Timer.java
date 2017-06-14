package Engine;

import java.util.Date;
import Exception.PanicException;
import Visual.*;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Implemente un timer afin d'executer certaines actions pour chaque laps de
 * temps donne
 * 
 * @author CHANET Zoran
 */
public class Timer extends AnimationTimer {
	long lastTime = new Date().getTime();
	long lastTime_op = new Date().getTime();
	long lastTime_op2 = new Date().getTime();
	OperateursVisual visuel;
	ImageView op;
	int indX;
	int indY;
	Terrain t;

	public Timer(Terrain t) {
		this.t = t;
	}

	/**
	 * a l'appel du timer, on execute cette fonction
	 */
	public void handle(long now) {
		Robots rob1, rob2;
		long date = new Date().getTime();
		int rand;
		/* Si la derniere action a ete effectuee il y a plus de 500ms */
		/* Avancee d'un pas dans l'algo des robots */
		if (date - lastTime > 500) {
			// t.getPlateau().toString();
			/* On execute un pas de chacun des robots existants */
			for (int i = 1; i < 4; i++) {
				rob1 = t.getpersonnage1().getRobot(i);
				rob2 = t.getpersonnage2().getRobot(i);
				if (rob1 != null)
					rob1.step();
				if (rob2 != null)
					rob2.step();
				if (rob1 != null)
					System.out.println("Coups recus R1P1 : " + rob1.nbCoupsRecus);
			}

			if (!t.getpersonnage1().estEnVie() && !t.getpersonnage2().estEnVie()) {
				Test.EndGame(0);
			} else if (!t.getpersonnage1().estEnVie()) {
				// TODO PLAYER 2 WINS HAHAHA
				Test.EndGame(2);
			} else if (!t.getpersonnage2().estEnVie()) {
				// TODO PLAYER 1 WINS HAHAHA
				Test.EndGame(1);
			} else
				for (int i = 1; i < 4; i++) {
					rob1 = t.getpersonnage1().getRobot(i);
					rob2 = t.getpersonnage2().getRobot(i);
					if (rob1 != null) {
						System.out.println("ROB1PV : " + rob1.getHealth());
						if (!rob1.estEnVie()) {
							t.getpersonnage1().removeRobot(i);
							rob1.getVisual().remove();
							t.getPlateau().remove(rob1.getX(), rob1.getY(), rob1);
						}
					}
					if (rob2 != null) {
						System.out.println("ROB2PV : " + rob2.getHealth());
						if (!rob2.estEnVie()) {
							t.getpersonnage2().removeRobot(i);
							rob2.getVisual().remove();
							t.getPlateau().remove(rob2.getX(), rob2.getY(), rob2);
						}
					}
				}
			System.out.println("Vie P1 : " + t.getpersonnage1().getHealth());
			System.out.println("Vie P2 : " + t.getpersonnage2().getHealth());

			t.getpersonnage1().updateHealthBar();
			t.getpersonnage2().updateHealthBar();

			rob1 = t.getpersonnage1().getRobot(1);
			if (rob1 != null) {
				System.out.println("Vie R1P1 : " + t.getpersonnage1().getRobot(1).PV);
			}
			lastTime = date;
		}

		/* Si la derniere action a ete effectuee il y a plus de 15s */
		/* Apparition des operateurs rares */
		if (date - lastTime_op > 15000) {
			rand = (int) (Math.random() * 4);
			// rand = 0;
			switch (rand) {
			case 0:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/hit.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Hit hit = new Hit(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 1:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/kam.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Kamikaze kamikaze = new Kamikaze(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 2:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/other.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Others others = new Others(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 3:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/protect.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Protect protect = new Protect(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			default:
				throw new PanicException("Random sur operateur : nombre non gere");
			}
			lastTime_op = date;
		}

		/* Si la derniere action a ete effectuee il y a plus de 5s */
		/* Apparition des operateurs frequents */
		if (date - lastTime_op2 > 5000) {
			rand = (int) (Math.random() * 8);
			// rand = 1;
			switch (rand) {
			case 0:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/af.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				AccoladeF accoladeF = new AccoladeF(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 1:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/ao.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				AccoladeO accoladeO = new AccoladeO(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 2:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/barre.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Barre barre = new Barre(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 3:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/dp.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				DeuxPoints deuxPoints = new DeuxPoints(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 4:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/pv.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				PointVirgule pointVirgule = new PointVirgule(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 5:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/pref.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Preference preference = new Preference(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 6:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/rapport.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Rapport rapport = new Rapport(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			case 7:
				op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/star.png")));
				random();
				visuel = new OperateursVisual(this.indX, this.indY, op, t.getPlateau());
				Star star = new Star(t, this.indX, this.indY, t.getPlateau(), visuel);
				blink(visuel, op);
				break;
			default:
				throw new PanicException("Random sur operateur : nombre non gere");
			}
			lastTime_op2 = date;
		}
	}

	public void blink(OperateursVisual visuel, ImageView image) {
		Timeline blinker = visuel.Blinker(image);
		FadeTransition fader = visuel.Fader(image);
		// SequentialTransition blinkThenFade = new SequentialTransition(image,
		// blinker, fader);
		// blinkThenFade.play();
		blinker.play();
	}

	public void random() {
		indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
		indY = (int) (Math.random() * (Terrain.getTuileY() - 1));
		while ((t.getPlateau()).verification(indX, indY) == 1) {
			indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
			indY = (int) (Math.random() * (Terrain.getTuileY() - 1));
		}
	}

	public void setTime(long time) {
		lastTime = time;
		lastTime_op = time;
		lastTime_op2 = time;
	}
}
