package Engine;

import java.util.ArrayList;

import Exception.PanicException;
import Visual.PersonnagesVisual;
import Visual.Plateau;
import Visual.RobotVisual;
import Visual.Terrain;

public class Robots implements Vivante {

	Automates behavior;
	int x, y;
	int equipe;
	int nbcoups = 0;
	int PV = 3;
	Plateau plateau;
	RobotVisual visuel;
	Personnages personnage;

	/**
	 * cree un robot et le place dans l'équipe e
	 * 
	 * @param e
	 *            l'equipe dans laquelle ajouter le robot
	 * @require e == 0 || e == 1
	 */
	public Robots(Plateau plateau, Personnages personnage, int e, RobotVisual visuel) {
		this.plateau = plateau;
		if (e == 0) {
			x = Terrain.getTuileY() / 2;
			y = 1;
		} else if (e == 1) {
			x = Terrain.getTuileY() / 2;
			y = Terrain.getTuileX() - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates();
		this.visuel = visuel;
		this.personnage = personnage;
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
			x = Terrain.getTuileY() / 2;
			y = 1;
		} else if (e == 1) {
			x = Terrain.getTuileY() / 2;
			y = Terrain.getTuileX() - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates(a);
	}

	public String toString() {
		return "R(" + x + "," + y + ") : " + behavior.toString();
	}

	@Override
	public void mouvement(PointCardinal p) {
		plateau.toString();
		switch (p) {
		case NORD:
			if (y > 0 && !(plateau.unsafeGet(x, y - 1) instanceof Vivante)) {
				if (plateau.unsafeGet(x, y - 1) instanceof Operateurs)
					((Operateurs) plateau.unsafeGet(x, y - 1)).stock(personnage);
				y--;
				plateau.move(x, y + 1, x, y);
				visuel.Haut();
				visuel.requestFocus();
			}
			break;
		case SUD:
			if (y < plateau.nbLignes() - 1 && !(plateau.unsafeGet(x, y + 1) instanceof Vivante)) {
				if (plateau.unsafeGet(x, y + 1) instanceof Operateurs)
					((Operateurs) plateau.unsafeGet(x, y + 1)).stock(personnage);
				y++;
				plateau.move(x, y - 1, x, y);
				visuel.Bas();
				visuel.requestFocus();
			}
			break;
		case EST:
			if (x < plateau.nbColonnes() - 1 && !(plateau.unsafeGet(x + 1, y) instanceof Vivante)) {
				if (plateau.unsafeGet(x + 1, y) instanceof Operateurs)
					((Operateurs) plateau.unsafeGet(x + 1, y)).stock(personnage);
				x++;
				plateau.move(x - 1, y, x, y);
				visuel.Droite();
				visuel.requestFocus();
			}
			break;
		case OUEST:
			if (x > 0 && !(plateau.unsafeGet(x - 1, y) instanceof Vivante)) {
				if (plateau.unsafeGet(x - 1, y) instanceof Operateurs)
					((Operateurs) plateau.unsafeGet(x - 1, y)).stock(personnage);
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
	 * @return la direction de l'ennemi adjacent, ou null s'il n'y a pas
	 *         d'ennemi adjacent
	 */
	public PointCardinal ennemiAdjacent() {
		PointCardinal retour = null;
		if (plateau.unsafeGet(x + 1, y) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (plateau.unsafeGet(x - 1, y) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (plateau.unsafeGet(x, y + 1) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (plateau.unsafeGet(x, y - 1) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x, y - 1)))
			retour = PointCardinal.NORD;

		return retour;
	}

	/**
	 * Si un joueur est place sur une case adjacente, ressort la position de ce
	 * joueur par rapport au robot
	 * 
	 * @return la direction du joueur adjacent, ou null s'il n'y a pas de joueur
	 *         adjacent
	 */
	public PointCardinal allieAdjacent() {
		PointCardinal retour = null;
		if (plateau.unsafeGet(x + 1, y) instanceof Personnages && memeEquipe((Personnages) plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (plateau.unsafeGet(x - 1, y) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (plateau.unsafeGet(x, y + 1) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (plateau.unsafeGet(x, y - 1) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x, y - 1)))
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
		switch (ennemiAdjacent()) {
		case NORD:
			((Vivante) plateau.unsafeGet(x, y - 1)).isHit(nbHits);
			break;
		case SUD:
			((Vivante) plateau.unsafeGet(x, y + 1)).isHit(nbHits);
			break;
		case EST:
			((Vivante) plateau.unsafeGet(x + 1, y)).isHit(nbHits);
			break;
		case OUEST:
			((Vivante) plateau.unsafeGet(x - 1, y)).isHit(nbHits);
			break;
		default:
		}
	}

	public void boom() {
		if (plateau.unsafeGet(x + 1, y) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x + 1, y)))
			((Vivante) plateau.unsafeGet(x + 1, y)).isHit(PV);
		if (plateau.unsafeGet(x - 1, y) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x - 1, y)))
			((Vivante) plateau.unsafeGet(x - 1, y)).isHit(PV);
		if (plateau.unsafeGet(x, y + 1) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x, y + 1)))
			((Vivante) plateau.unsafeGet(x, y + 1)).isHit(PV);
		if (plateau.unsafeGet(x, y - 1) instanceof Vivante && !memeEquipe((Vivante) plateau.unsafeGet(x, y - 1)))
			((Vivante) plateau.unsafeGet(x, y - 1)).isHit(PV);
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
		Entite caze;
		int destX = x;
		int destY = y;
		// Recherche des coordonnees de l'ennemi
		for (int i = 0; i < plateau.nbLignes(); i++)
			for (int j = 0; j < plateau.nbColonnes(); i++) {
				caze = plateau.unsafeGet(i, j);
				if (caze instanceof Vivante) {
					if (!memeEquipe((Vivante) caze) && ((Math.abs(x - caze.getX())
							+ Math.abs(y - caze.getY()) < (Math.abs(y - destX) + Math.abs(y - destY))))) {
						destX = caze.getX();
						destY = caze.getY();
					}

				}
			}

		// Recherche des nbMov premiers pas du plus cours chemin vers l'ennemi
		RechercheChemin trajet = new RechercheChemin(plateau, y, x, destY, destX);
		ArrayList<PointCardinal> mvmt = new ArrayList<PointCardinal>();
		mvmt = trajet.xPas(nbMov);
		for (int i = 0; i < mvmt.size(); i++)
			if (mvmt.get(i) != null)
				mouvement(mvmt.get(i));
	}

	/**
	 * Fais avancer le robot de nbMov pas vers le joueur associé au robot
	 * 
	 * @param nbMov
	 *            le nombre de pas du robot
	 */
	public void versJoueur(int nbMov) {
		// TODO : Algorithme de recherche de l'allié + plus court chemin

		// Recherche des coordonnees de l'allie
		Entite caze;
		int destX = -1;
		int destY = -1;
		// Recherche des coordonnees de l'ennemi
		for (int i = 0; destX < 0 && i < plateau.nbLignes(); i++)
			for (int j = 0; destX < 0 && j < plateau.nbColonnes(); i++) {
				caze = plateau.unsafeGet(i, j);
				if (caze instanceof Personnages && memeEquipe((Personnages) caze)) {
					destX = caze.getX();
					destY = caze.getY();
				}
			}

		// Recherche des nbMov premiers pas du plus cours chemin vers l'allie
		RechercheChemin trajet = new RechercheChemin(plateau, y, x, destY, destX);
		ArrayList<PointCardinal> mvmt = new ArrayList<PointCardinal>();
		mvmt = trajet.xPas(nbMov);
		for (int i = 0; i < mvmt.size(); i++)
			if (mvmt.get(i) != null)
				mouvement(mvmt.get(i));
	}
	
	/**
	 * Accesseur de visuel associe au robot
	 * 
	 * @return
	 */
	public RobotVisual getVisual() {
		return visuel;
	}
	
	public void step(){
		behavior.execute(this);
	}
}
