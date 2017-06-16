package Engine;

import Exception.PanicException;
import Visual.OperateursVisual;
import Visual.Plateau;
import Visual.RobotVisual;
import javafx.scene.Parent;

public class CibleMouvante implements Vivante {

	int x, y;
	int equipe = 2;
	int PV = 10;
	RobotVisual visuel;
	Plateau plateau;
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Parent getVisual() {
		return visuel;
	}

	@Override
	public void mouvement(PointCardinal p) {
		switch (p) {
		case NORD:
			if (y > 0 && !(plateau.unsafeGet(x, y - 1) instanceof Vivante)) {
				if (plateau.unsafeGet(x, y - 1) instanceof Operateurs)
					((OperateursVisual) plateau.unsafeGet(x, y - 1).getVisual()).remove();
				y--;
				plateau.move(x, y + 1, x, y);
				visuel.Haut();
				visuel.requestFocus();
			}
			break;
		case SUD:
			if (y < plateau.nbLignes() - 1 && !(plateau.unsafeGet(x, y + 1) instanceof Vivante)) {
				if (plateau.unsafeGet(x, y + 1) instanceof Operateurs)
					((OperateursVisual) plateau.unsafeGet(x, y - 1).getVisual()).remove();
				y++;
				plateau.move(x, y - 1, x, y);
				visuel.Bas();
				visuel.requestFocus();
			}
			break;
		case EST:
			if (x < plateau.nbColonnes() - 1 && !(plateau.unsafeGet(x + 1, y) instanceof Vivante)) {
				if (plateau.unsafeGet(x + 1, y) instanceof Operateurs)
					((OperateursVisual) plateau.unsafeGet(x, y - 1).getVisual()).remove();
				x++;
				plateau.move(x - 1, y, x, y);
				visuel.Droite();
				visuel.requestFocus();
			}
			break;
		case OUEST:
			if (x > 0 && !(plateau.unsafeGet(x - 1, y) instanceof Vivante)) {
				if (plateau.unsafeGet(x - 1, y) instanceof Operateurs)
					((OperateursVisual) plateau.unsafeGet(x, y - 1).getVisual()).remove();
				x--;
				plateau.move(x + 1, y, x, y);
				visuel.Gauche();
				visuel.requestFocus();
			}
			break;
		default:
			throw new PanicException("Deplacement Personnage : Point Cardinal incorrect.");
		}
	}

	@Override
	public int getEquipe() {
		return equipe;
	}

	@Override
	public boolean memeEquipe(Vivante ent) {
		return ent.getEquipe() == equipe;
	}

	@Override
	public void isHit() {
		PV--;
	}

	@Override
	public boolean estEnVie() {
		if(PV < 0)
			PV = 10;
		return true;
	}

}
