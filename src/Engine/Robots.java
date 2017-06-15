package Engine;

import java.util.ArrayList;

import Exception.PanicException;
import Parsing.ParseException;
import Visual.Plateau;
import Visual.RobotVisual;

public class Robots implements Vivante {

	Automate behavior;
	int x, y;
	int equipe;
	int nbcoups = 0;
	int PV = 3;
	public int nbCoupsRecus = 0;
	Plateau plateau;
	RobotVisual visuel;
	Personnages personnage;

	/**
	 * DO NOT USE THIS CONSTRUCTOR
	 */
	public Robots() {

	}

	/**
	 * cree un robot et le place dans l'équipe e
	 * 
	 * @param plateau
	 *            le plateau dans lequel le robot evolue
	 * @param personnage
	 *            le personnage associe au robot
	 * @param e
	 *            l'equipe dans laquelle ajouter le robot
	 * @param visuel
	 *            le "robot graphique" associe au robot
	 * @require e == 0 || e == 1
	 */
	public Robots(Plateau plateau, Personnages personnage, int e, RobotVisual visuel) {
		this.plateau = plateau;
		if (e == 0) {
			x = 1;
			y = plateau.nbLignes() / 2;
		} else if (e == 1) {
			x = plateau.nbColonnes() - 2;
			y = plateau.nbLignes() / 2;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		this.visuel = visuel;
		this.personnage = personnage;
		behavior = new Automate();
		plateau.put(x, y, this);
	}

	/**
	 * cree un robot et le place dans l'équipe e
	 * 
	 * @param plateau
	 *            le plateau dans lequel le robot evolue
	 * @param personnage
	 *            le personnage associe au robot
	 * @param e
	 *            l'equipe dans laquelle ajouter le robot
	 * @param visuel
	 *            le "robot graphique" associe au robot
	 * @param behave
	 *            la chaine de caracteres decrivant l'automate du robot
	 * @require e == 0 || e == 1
	 */
	public Robots(Plateau plateau, Personnages personnage, int e, RobotVisual visuel, String behave) throws ParseException{
		this.plateau = plateau;
		if (e == 0) {
			x = 1;
			y = plateau.nbLignes() / 2;
		} else if (e == 1) {
			x = plateau.nbColonnes() - 2;
			y = plateau.nbLignes() / 2;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		this.visuel = visuel;
		this.personnage = personnage;
		behavior = new Automate(behave);
		plateau.put(x, y, this);
	}

	public String toString() {
		return "R(" + x + "," + y + ") : " + behavior.toString();
	}

	@Override
	/**
	 * Deplace le robot d'une case dans la direction du point cardinal p
	 * 
	 * @param p
	 *            le point cardinal donnant la direction du mouvement
	 */
	public void mouvement(PointCardinal p) {
		// plateau.toString();
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
		if (x < plateau.nbColonnes() && plateau.unsafeGet(x + 1, y) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (x > 0 && plateau.unsafeGet(x - 1, y) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (y < plateau.nbLignes() && plateau.unsafeGet(x, y + 1) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (y > 0 && plateau.unsafeGet(x, y - 1) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x, y - 1)))
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
		if (x < plateau.nbColonnes() && plateau.unsafeGet(x + 1, y) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x + 1, y)))
			retour = PointCardinal.EST;
		else if (x > 0 && plateau.unsafeGet(x - 1, y) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x - 1, y)))
			retour = PointCardinal.OUEST;
		else if (y < plateau.nbLignes() && plateau.unsafeGet(x, y + 1) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x, y + 1)))
			retour = PointCardinal.SUD;
		else if (y > 0 && plateau.unsafeGet(x, y - 1) instanceof Personnages
				&& memeEquipe((Personnages) plateau.unsafeGet(x, y - 1)))
			retour = PointCardinal.NORD;

		return retour;
	}

	/**
	 * Si un ennemi est localise dans une case adjacente, le frappe
	 */
	public void hit() {
		switch (ennemiAdjacent()) {
		case NORD:
			((Vivante) plateau.unsafeGet(x, y - 1)).isHit();
			break;
		case SUD:
			((Vivante) plateau.unsafeGet(x, y + 1)).isHit();
			break;
		case EST:
			((Vivante) plateau.unsafeGet(x + 1, y)).isHit();
			break;
		case OUEST:
			((Vivante) plateau.unsafeGet(x - 1, y)).isHit();
			break;
		default:
		}
	}

	/**
	 * Le robot explose (meurt), en infligeant autour de lui dans les quatre
	 * directions des degats egaux a ses points de vie actuels
	 */
	public void boom() {
		nbCoupsRecus = PV;
		if (x < plateau.nbColonnes() && plateau.unsafeGet(x + 1, y) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x + 1, y)))
			for (int i = 0; i < PV; i++)
				((Vivante) plateau.unsafeGet(x + 1, y)).isHit();
		if (x > 0 && plateau.unsafeGet(x - 1, y) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x - 1, y)))
			for (int i = 0; i < PV; i++)
				((Vivante) plateau.unsafeGet(x - 1, y)).isHit();
		if (y < plateau.nbLignes() && plateau.unsafeGet(x, y + 1) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x, y + 1)))
			for (int i = 0; i < PV; i++)
				((Vivante) plateau.unsafeGet(x, y + 1)).isHit();
		if (y > 0 && plateau.unsafeGet(x, y - 1) instanceof Vivante
				&& !memeEquipe((Vivante) plateau.unsafeGet(x, y - 1)))
			for (int i = 0; i < PV; i++)
				((Vivante) plateau.unsafeGet(x, y - 1)).isHit();
	}

	/**
	 * On reçoit nbHits coup(s) d'un adversaire
	 * 
	 * @param nbHits
	 *            le nombre de coups reçus
	 */
	public void isHit() {
		nbCoupsRecus++;
	}

	/**
	 * public on se protege de nbHits coups
	 * 
	 * @param nbHits
	 *            le nombre de coups dont on se protege
	 */
	public void protect(int nbHits) {
		nbCoupsRecus--;
	}

	/**
	 * Fais avancer le robot de nbMov pas vers l'ennemi le plus proche
	 * 
	 * @param nbMov
	 *            le nombre de pas du robot
	 */
	public void versEnnemi(int nbMov) {
		Entite caze;
		int destX = 3 * plateau.nbColonnes();
		int destY = 3 * plateau.nbLignes();
		// Recherche des coordonnees de l'ennemi
		for (int i = 0; i < plateau.nbColonnes(); i++)
			for (int j = 0; j < plateau.nbLignes(); j++) {
				caze = plateau.unsafeGet(i, j);
				if (caze instanceof Vivante) {
					if (!memeEquipe((Vivante) caze) && ((Math.abs(x - caze.getX())
							+ Math.abs(y - caze.getY()) < (Math.abs(x - destX) + Math.abs(y - destY))))) {
						destX = i;
						destY = j;
						// System.out.println("JE PASSE LAAAAA " + destX + " " +
						// destY);
					}
				}
			}
		// System.out.println("Cherche : " + destX + " " + destY);
		// Recherche des nbMov premiers pas du plus cours chemin vers l'ennemi
		RechercheChemin trajet = new RechercheChemin(plateau, x, y, destX, destY);
		ArrayList<PointCardinal> mvmt = new ArrayList<PointCardinal>();
		mvmt = trajet.xPas(nbMov);
		// System.out.println("LENGTH : " + mvmt.size());
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
		// Recherche des coordonnees de l'allie
		Entite caze;
		int destX = -1;
		int destY = -1;
		// Recherche des coordonnees de l'ennemi
		for (int i = 0; destX < 0 && i < plateau.nbColonnes(); i++)
			for (int j = 0; destX < 0 && j < plateau.nbLignes(); j++) {
				caze = plateau.unsafeGet(i, j);
				if (caze instanceof Personnages && memeEquipe((Personnages) caze)) {
					destX = caze.getX();
					destY = caze.getY();
				}
			}

		// Recherche des nbMov premiers pas du plus cours chemin vers l'allie
		RechercheChemin trajet = new RechercheChemin(plateau, x, y, destX, destY);
		ArrayList<PointCardinal> mvmt = new ArrayList<PointCardinal>();
		mvmt = trajet.xPas(nbMov);
		for (int i = 0; i < mvmt.size(); i++)
			if (mvmt.get(i) != null)
				mouvement(mvmt.get(i));
	}

	/**
	 * Accesseur de visuel associe au robot
	 * 
	 * @return le visuel associe au robot
	 */
	public RobotVisual getVisual() {
		return visuel;
	}

	/**
	 * Execute une etape de l'automate behavior
	 */
	public void step() {
		behavior.Run(this);
	}

	/**
	 * donne l'automate correspondant a la string s comme comportement au robot
	 * 
	 * @param s
	 *            la chaine de caracteres decrivant l'automate
	 */
	public void setBehavior(String s) {
		try {
			behavior = new Automate(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Calcule le nouveau montant de PV du robot, puis indique s'il est encore
	 * en vie
	 */
	public boolean estEnVie() {
		if (nbCoupsRecus > 0)
			PV -= nbCoupsRecus;
		nbCoupsRecus = 0;
		return PV > 0;
	}

	/**
	 * Donne le nombre de PV actuels du robot
	 * 
	 * @return le nombre de PV du robot
	 */
	public int getHealth() {
		return PV;
	}
}
