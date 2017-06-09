package Engine;

import java.util.ArrayList;

import Exception.PanicException;

/**
 * Implémentation du code executable des robots en version simplifiee (pas
 * d'encapsulation) et sous format de tableau d'operateurs
 * 
 * @author CHANET Zoran
 *
 */
public class Automates {
	/**
	 * la suite d'operateurs executable
	 */
	ArrayList<Operateurs> code = new ArrayList<Operateurs>();
	private int lastAccoladeOMet;
	private int lastStarMet;
	private int fermetureEtoile;
	/**
	 * l'indice dans code du prochain operateur a executer
	 */
	private int exec;
	private Operateurs enExec;
	private Operateurs barreEnCours;
	/**
	 * indique à l'appel d'un opérateur d'action s'il doit s'exécuter ou
	 * verifier la suite du code
	 */
	private boolean execution = false;
	/**
	 * Indique le nombre de répétitions de l'action (nécessaire en cas de :)
	 */
	private int nbExec = 1;

	/**
	 * crée un automate de base : se dirige vers l'ennemi le plus proche
	 */
	public Automates() {
		lastAccoladeOMet = -1;
		lastStarMet = -1;
		fermetureEtoile = -1;
		code.add(new Star());
		code.add(new AccoladeO());
		code.add(new Others());
		code.add(new AccoladeF());
	}

	/**
	 * crée un automate en ajoutant le premier élément
	 * 
	 * @param o
	 *            l'opérateur à placer dans l'automate (comportement *{o}) créé
	 * @require l'opérateur décrit une action (pas un test comme >, |... par
	 *          exemple)
	 */
	public Automates(Operateurs o) {
		lastAccoladeOMet = -1;
		lastStarMet = -1;
		fermetureEtoile = -1;
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
	 *            l'automate à copier
	 */
	public Automates(Automates a) {
		lastAccoladeOMet = -1;
		lastStarMet = -1;
		fermetureEtoile = -1;
		for (int i = 0; i < a.code.size(); i++)
			code.add(a.code.get(i));
	}

	/**
	 * Création d'un automate depuis une chaine de caractères
	 * 
	 * @require les caractères de la chaine correspondent à des opérateurs
	 * @ensure l'automate est correct
	 * @param s
	 *            la chaine servant à la création de l'automate
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
	 * d'opérateurs) Ajout du test de non-imbrication (au plus 3 operateurs
	 * entre deux point virgules
	 * 
	 * @return
	 */
	boolean estValide() {
		boolean retour = true;
		int accolades = 0;
		int pointvirg = 0;
		retour = code.get(0) instanceof Star && code.get(1) instanceof AccoladeO
				&& code.get(code.size() - 1) instanceof AccoladeF;
		for (int i = 2; pointvirg < 4 && accolades >= 0 && retour && i < code.size() - 1; i++) {
			if (code.get(i) instanceof PointVirgule)
				pointvirg = 0;
			else
				pointvirg++;

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

		return retour && (accolades == 0) && (pointvirg < 4);
	}

	public String toString() {
		String retour = "";
		for (int i = 0; i < code.size(); i++)
			retour += code.get(i).toString();
		return retour;
	}

	public boolean realAction() {
		return execution;
	}

	/**
	 * Execute la prochaine action de l'automate
	 * 
	 * @param nono
	 */
	public void execute(Robots nono) {
		code.get(exec).action(this, nono);
		exec++;
	}

	/**
	 * Met à jour la dernière étoile rencontrée et la fin de l'action répétée
	 * 
	 * @param nono
	 */
	public void metStar(Robots nono) {
		lastStarMet = exec;
		int accolades = 1;
		int i;
		for (i = exec + 2; (accolades != 0) && (i < code.size()); i++) {
			if (code.get(i) instanceof AccoladeO)
				accolades++;
			else if (code.get(i) instanceof AccoladeF)
				accolades--;
		}
		fermetureEtoile = i;

		execute(nono);
	}

	/**
	 * Teste si l'accolade passée en paramètres est l'accolade fermant la
	 * dernière étoile trouvée
	 * 
	 * @param a
	 * @return
	 */
	public boolean isFermetureEtoile(AccoladeF a) {
		if (fermetureEtoile >= 0)
			return a == code.get(fermetureEtoile);
		else
			throw new PanicException("Pas d'étoile ouverte");
	}

	/**
	 * Retourne au début de la dernière étoile rencontrée
	 * 
	 * @param nono
	 */
	public void retourAlEtoile(Robots nono) {
		exec = lastStarMet + 1;
		execute(nono);
	}

	/**
	 * change l'opérateur d'action en execution
	 * 
	 * @param o
	 * @param nono
	 */
	public void opeAExec(Operateurs o, Robots nono) {
		enExec = o;
		exec++;
		execute(nono);
	}

	/**
	 * Execute au hasard l'opérateur précédent ou le suivant
	 */
	public void random(Robots nono) {
		if (Math.random() < 0.5)
			enExec = code.get(exec - 1);
		else
			enExec = code.get(exec + 1);
		realExec(nono);
	}

	/**
	 * Indique si la barre donnée est celle du test actuel
	 * 
	 * @param b
	 *            la barre a tester
	 * @return la barre est la barre du test en cours
	 */
	public boolean isBarreExec(Barre b) {
		return b == barreEnCours;
	}

	/**
	 * Avance l'execution jusqu'à l'opérateur suivant le prochain point virgule
	 */
	public void skipToPointVirgule() {
		while (!(code.get(exec) instanceof PointVirgule))
			exec++;
		exec++;
	}

	/**
	 * Execute les actions réelles comme Hit, Protect etc depuis le robot nono
	 * 
	 * @param nono
	 *            le robot effectuant l'action
	 * @require enExec != null
	 */
	public void realExec(Robots nono) {
		if (enExec != null) {
			execution = true;
			enExec.action(this, nono);
			execution = false;
			enExec = null;
			nbExec = 1;
			skipToPointVirgule();
		} else
			throw new PanicException("appel de realExec sur un opérateur null");
	}

	/**
	 * renvoie le nombre d'executions de l'action à effectuer
	 * 
	 * @return
	 */
	public int nbExec() {
		return nbExec;
	}

	/**
	 * Incrémente le nombre d'éxecutions de l'action en cours
	 */
	public void execIncr(Robots nono) {
		nbExec++;
		exec++;
		execute(nono);
	}

	public void preference() {

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
