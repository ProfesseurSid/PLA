package Engine;

import Exception.PanicException;
import Visual.Plateau;

public class Robots implements Vivante {

	Automates behavior;
	int x, y;
	int equipe;
	int nbcoups = 0;
	int PV = 3;

	/**
	 * crée un robot et le place dans l'équipe e
	 * 
	 * @param e
	 */
	public Robots(int e) {
		if (e == 0) {
			x = Plateau.nblignes / 2;
			y = 1;
		} else if (e == 1) {
			x = Plateau.nblignes / 2;
			y = Plateau.nbcolonnes - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates();
	}

	/**
	 * Crée un robot de comportement a, et le place dans l'équipe e
	 * 
	 * @param e
	 * @param a
	 */
	public Robots(int e, Automates a) {
		if (e == 0) {
			x = Plateau.nblignes / 2;
			y = 1;
		} else if (e == 1) {
			x = Plateau.nblignes / 2;
			y = Plateau.nbcolonnes - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates(a);
	}

	public String toString() {
		return "R(" + x + "," + y + ") : " + behavior.toString();
	}

	@Override
	public void detruire() {
		// TODO Auto-generated method stub

	}

	@Override
	public void apparaitre() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouvement(PointCardinal p, int nb) {
		// TODO Auto-generated method stub

	}

	/**
	 * Simple getter de coordonees
	 */
	public int getX() {
		return x;
	}

	/**
	 * Simple getter de coordonees
	 */
	public int getY() {
		return y;
	}

	/**
	 * Récupere le numero d'equipe de ce robot
	 */
	public int getEquipe() {
		return equipe;
	}

	/**
	 * booleen indiquant si l'element en cours et l'element vivante donne sont
	 * de la meme equipe
	 */
	public boolean memeEquipe(Vivante v) {
		return equipe == v.getEquipe();
	}

	/**
	 * Si un ennemi est place sur une case adjacente, ressort la position de cet
	 * ennemi par rapport au robot
	 * 
	 * @return la direction de l'ennemi adjacent, ou null s'il n'y a pas d'ennemi adjacent
	 */
	public PointCardinal ennemiAdjacent() {
		PointCardinal retour = null;
		if (Plateau.unsafeGet(x + 1, y) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (Plateau.unsafeGet(x - 1, y) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (Plateau.unsafeGet(x, y + 1) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (Plateau.unsafeGet(x, y - 1) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x, y - 1)))
			retour = PointCardinal.NORD;

		return retour;
	}
	
	/**
	 * Si un joueur est place sur une case adjacente, ressort la position de ce
	 * joueur par rapport au robot
	 * 
	 * @return la direction du joueur adjacent, ou null s'il n'y a pas de joueur adjacent
	 */
	public PointCardinal allieAdjacent() {
		PointCardinal retour = null;
		if (Plateau.unsafeGet(x + 1, y) instanceof Personnages && memeEquipe((Personnages) Plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (Plateau.unsafeGet(x - 1, y) instanceof Personnages && memeEquipe((Personnages) Plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (Plateau.unsafeGet(x, y + 1) instanceof Personnages && memeEquipe((Personnages) Plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (Plateau.unsafeGet(x, y - 1) instanceof Personnages && memeEquipe((Personnages) Plateau.unsafeGet(x, y - 1)))
			retour = PointCardinal.NORD;

		return retour;
	}

	/**
	 * Si un ennemi est localise dans une case adjacente, le frappe
	 * 
	 * @param nbHits
	 *            le nombre de coups a donner
	 */
	public void hit(int nbHits) {
		// if (Plateau.unsafeGet(x + 1, y) instanceof Vivante &&
		// !memeEquipe((Vivante) Plateau.unsafeGet(x + 1, y)))
		// ((Vivante) Plateau.unsafeGet(x + 1, y)).isHit(nbHits);
		// else if (Plateau.unsafeGet(x - 1, y) instanceof Vivante &&
		// !memeEquipe((Vivante) Plateau.unsafeGet(x - 1, y)))
		// ((Vivante) Plateau.unsafeGet(x - 1, y)).isHit(nbHits);
		// else if (Plateau.unsafeGet(x, y + 1) instanceof Vivante &&
		// !memeEquipe((Vivante) Plateau.unsafeGet(x, y + 1)))
		// ((Vivante) Plateau.unsafeGet(x, y + 1)).isHit(nbHits);
		// else if (Plateau.unsafeGet(x, y - 1) instanceof Vivante &&
		// !memeEquipe((Vivante) Plateau.unsafeGet(x, y - 1)))
		// ((Vivante) Plateau.unsafeGet(x, y - 1)).isHit(nbHits);
		switch (ennemiAdjacent()) {
		case NORD:
			((Vivante) Plateau.unsafeGet(x, y - 1)).isHit(nbHits);
			break;
		case SUD:
			((Vivante) Plateau.unsafeGet(x, y + 1)).isHit(nbHits);
			break;
		case EST:
			((Vivante) Plateau.unsafeGet(x + 1, y)).isHit(nbHits);
			break;
		case OUEST:
			((Vivante) Plateau.unsafeGet(x - 1, y)).isHit(nbHits);
			break;
		default:
		}
	}

	public void boom() {
		if (Plateau.unsafeGet(x + 1, y) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x + 1, y)))
			((Vivante) Plateau.unsafeGet(x + 1, y)).isHit(PV);
		if (Plateau.unsafeGet(x - 1, y) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x - 1, y)))
			((Vivante) Plateau.unsafeGet(x - 1, y)).isHit(PV);
		if (Plateau.unsafeGet(x, y + 1) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x, y + 1)))
			((Vivante) Plateau.unsafeGet(x, y + 1)).isHit(PV);
		if (Plateau.unsafeGet(x, y - 1) instanceof Vivante && !memeEquipe((Vivante) Plateau.unsafeGet(x, y - 1)))
			((Vivante) Plateau.unsafeGet(x, y - 1)).isHit(PV);
	}

	/**
	 * On reçoit nbHits coup(s) d'un adversaire
	 * 
	 * @param nbHits
	 *            le nombre de coups reçus
	 */
	public void isHit(int nbHits) {
		nbcoups++;
	}

	/**
	 * public on se protege de nbHits coups
	 * 
	 * @param nbHits
	 *            le nombre de coups dont on se protege
	 */
	public void protect(int nbHits) {
		nbcoups--;
	}

	/**
	 * Fais avancer le robot de nbMov pas vers l'ennemi le plus proche
	 * 
	 * @param nbMov
	 *            le nombre de pas du robot
	 */
	public void versEnnemi(int nbMov) {
		// TODO : Algorithme de recherche de l'ennemi le plus proche + plus
		// court chemin
	}

	/**
	 * Fais avancer le robot de nbMov pas vers le joueur associé au robot
	 * 
	 * @param nbMov
	 *            le nombre de pas du robot
	 */
	public void versJoueur(int nbMov) {
		// TODO : Algorithme de recherche de l'allié + plus court chemin
	}
}
