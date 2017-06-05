package Engine;

import java.awt.Robot;
import java.util.ArrayList;

import Exception.PanicException;

public class Automates {
	ArrayList<Operateurs> code = new ArrayList<Operateurs>();
	int lastAccoladeOMet = -1;
	int lastStarMet = -1;
	int exec = 0;

	/**
	 * crée un automate de base : se dirige vers l'ennemi le plus proche
	 */
	public Automates() {
		code.add(new Star());
		code.add(new AccoladeO());
		code.add(new Others());
		code.add(new AccoladeF());
	}

	/**
	 * crée un automate en ajoutant le premier élément
	 * 
	 * @param o
	 */
	public Automates(Operateurs o) {
		code.add(new Star());
		code.add(new AccoladeO());
		code.add(o);
		code.add(new AccoladeF());
		if (!estValide())
			throw new PanicException("Opérateur donné incorrect");
	}

	/**
	 * Copie l'automate a dans ce nouvel automate
	 * 
	 * @param a
	 */
	public Automates(Automates a) {
		for (int i = 0; i < a.code.size(); i++)
			code.add(a.code.get(i));
	}

	/**
	 * Création d'un automate depuis une chaine de caractères
	 * 
	 * @require les caractères de la chaine correspondent à des opérateurs
	 * @ensure l'automate est correct
	 * @param a
	 */
	public Automates(String s) {
		char[] c = new char[1];
		code.add(new Star());
		code.add(new AccoladeO());
		for (int i = 0; i < s.length(); i++) {
			s.getChars(i, i + 1, c, 0);
			switch (c[0]) {
			case '{':
				code.add(new AccoladeO());
				break;
			case '}':
				code.add(new AccoladeF());
				break;
			case '|':
				code.add(new Barre());
				break;
			case ':':
				code.add(new DeuxPoints());
				break;
			case 'H':
				code.add(new Hit());
				break;
			case 'K':
				code.add(new Kamikaze());
				break;
			case 'O':
				code.add(new Others());
				break;
			case '>':
				code.add(new Preference());
				break;
			case 'P':
				code.add(new Protect());
				break;
			case 'J':
				code.add(new Rapport());
				break;
			case '*':
				code.add(new Star());
				break;
			case ';':
				code.add(new PointVirgule());
				break;
			case ' ':
				break;
			default:
				throw new PanicException("Caractère incorrect dans la chaine");
			}
		}
		code.add(new AccoladeF());
		// if(!estValide())
		// throw new PanicException("Chaine invalide");
	}

	/**
	 * Ajoute un élément à la fin de l'automate
	 * 
	 * @param o
	 */
	public void add(Operateurs o) {
		code.add(o);
	}

	/**
	 * Ajoute l'élément o en ind_ième position de l'automate
	 * 
	 * @param ind
	 * @param o
	 */
	public void add(int ind, Operateurs o) {
		code.add(ind, o);
	}

	/**
	 * retire le ind_ième élément de l'automate
	 * 
	 * @param ind
	 */
	public void remove(int ind) {
		code.remove(ind);
	}

	/**
	 * Renvoie la taille en nombre d'opérateurs de l'automate
	 */
	int length() {
		return code.size();
	}

	/**
	 * Permet de tester la validité de l'automate (cohérence de la chaine
	 * d'opérateurs)
	 * 
	 * @return
	 */
	boolean estValide() {
		boolean retour = true;
		int accolades = 0;
		retour = code.get(0) instanceof Star && code.get(1) instanceof AccoladeO
				&& code.get(code.size() - 1) instanceof AccoladeF;
		for (int i = 2; retour && i < code.size() - 1; i++) {
			if (code.get(i) instanceof AccoladeO) {
				accolades++;
				retour &= !(code.get(i - 1) instanceof AccoladeF || code.get(i - 1) instanceof DeuxPoints
						|| code.get(i - 1) instanceof Hit || code.get(i - 1) instanceof Kamikaze
						|| code.get(i - 1) instanceof Others || code.get(i - 1) instanceof Protect
						|| code.get(i - 1) instanceof Rapport);
			} else if (code.get(i) instanceof AccoladeF) {
				accolades--;
				retour &= (accolades >= 0) && !(code.get(i - 1) instanceof AccoladeO);
			} else if (code.get(i) instanceof DeuxPoints)
				retour &= !(code.get(i - 1) instanceof AccoladeO || code.get(i - 1) instanceof PointVirgule
						|| code.get(i - 1) instanceof Barre || code.get(i - 1) instanceof Kamikaze
						|| code.get(i - 1) instanceof Preference || code.get(i - 1) instanceof Star);
			else if (code.get(i) instanceof Barre || code.get(i) instanceof PointVirgule
					|| code.get(i) instanceof Preference)
				retour &= !(code.get(i - 1) instanceof AccoladeO || code.get(i - 1) instanceof PointVirgule
						|| code.get(i - 1) instanceof Barre || code.get(i - 1) instanceof Preference
						|| code.get(i - 1) instanceof Star);
			else if (code.get(i) instanceof Hit || code.get(i) instanceof Kamikaze || code.get(i) instanceof Others
					|| code.get(i) instanceof Protect || code.get(i) instanceof Rapport)
				retour &= !(code.get(i - 1) instanceof AccoladeF || code.get(i - 1) instanceof DeuxPoints
						|| code.get(i - 1) instanceof Hit || code.get(i - 1) instanceof Kamikaze
						|| code.get(i - 1) instanceof Others || code.get(i - 1) instanceof Protect
						|| code.get(i - 1) instanceof Rapport || code.get(i - 1) instanceof Star);
			else if (code.get(i) instanceof Star)
				retour &= (code.get(i + 1) instanceof AccoladeO)
						&& !(code.get(i - 1) instanceof AccoladeF || code.get(i - 1) instanceof DeuxPoints
								|| code.get(i - 1) instanceof Hit || code.get(i - 1) instanceof Kamikaze
								|| code.get(i - 1) instanceof Others || code.get(i - 1) instanceof Protect
								|| code.get(i - 1) instanceof Rapport || code.get(i - 1) instanceof Star);
			else
				throw new PanicException("Opérateur incorrect présent dans l'automate");
		}
		return retour && (accolades == 0);
	}

	public String toString() {
		String retour = "";
		for (int i = 0; i < code.size(); i++)
			retour += code.get(i).toString();
		return retour;
	}

	/**
	 * Execute la prochaine action de l'automate
	 * 
	 * @param nono
	 */
	public void execute(Robot nono) {
		
	}

	public static void main(String[] args) {
		Automates a = new Automates("*{H >K > {P::}}");
		String aff = a.toString();
		if (a.estValide())
			aff += " est un automate correct";
		else
			aff += " est un automate incorrect";
		System.out.println(aff);
	}
}
