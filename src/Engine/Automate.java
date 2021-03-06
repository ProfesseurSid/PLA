package Engine;

import Exception.PanicException;
import Parsing.*;
import Exception.ParsingException;

/**
 * Classe qui implante l'automate d'un robot. Elle contient les methodes de
 * lecture sous forme de chaine de caractère et les methodes d'execution.
 */
public class Automate {
	private Arbre code;
	private Arbre aExec;
	private Arbre StarExec;
	private Arbre PrefExec = null;
	private int count;
	private boolean exec;
	private boolean pref;

	/**
	 * Constructeur d'arbre d'automate par défault.
	 */
	public Automate() {
		code = new Arbre(new Star(), null, new Arbre(new Others()));
		aExec = code;
		StarExec = code;
		exec = false;
		pref = false;
		count = 1;
	}

	/**
	 * Constructeur d'arbre d'automate avec une seul instruction.
	 * 
	 * @param op
	 *            Instruction unique de l'automate crée.
	 */
	public Automate(Operateurs op) {
		code = new Arbre(new Star(), null, new Arbre(op));
		aExec = code;
		StarExec = code;
		exec = false;
		pref = false;
		count = 1;
	}

	/**
	 * Constructeur d'arbre d'automate à partir d'une string.
	 * 
	 * @param s
	 *            Code de l'automate.
	 */
	public Automate(String s) throws ParseException {
		if (s.length() < 4)
			throw new ParsingException("Chaine vide");

		ComportementRobots parser = new ComportementRobots(s);
		code = parser.loop();
		aExec = code;
		StarExec = code;
		exec = false;
		pref = false;
		count = 1;
	}

	/**
	 * Methode qui transforme la string de code en arbre.
	 * 
	 * @param s
	 *            String transformer en arbre
	 * @return Arbre complet de l'execution.
	 */
	private Arbre stringToArbre(String s) {
		int i;
		char c;
		Arbre racine = new Arbre(new Star());
		Arbre AC = racine;
		Arbre AN;
		Arbre LastPointVirgule = racine;
		Arbre OldPointVirgule = null;
		Arbre LastOu = racine;
		Arbre OldOu = racine;
		Arbre LastEtoile = racine;

		for (i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '*') {
				AN = new Arbre(new Star());
				AC.AjouterFilsDroit(AN);
				AC = AC.droit();
				LastEtoile = AC;
				OldPointVirgule = LastPointVirgule;
				OldOu = LastOu;
				LastOu = AC;
				LastPointVirgule = AC;
				i++; // On ignore l'accolade
			} else if (c == ';') {
				AC = LastPointVirgule.droit();
				AC.RemplacerDecaler(new PointVirgule());
				LastPointVirgule = AC;
				LastOu = AC;
			} else if (c == '>') {
				AC.RemplacerDecaler(new Preference());
			} else if (c == '|') {
				AC = LastOu.droit();
				AC.RemplacerDecaler(new Barre());
				LastOu = AC;
			} else if (c == ':') {
				AC.RemplacerDecalerDroit(new DeuxPoints());
			} else if (c == '}') {
				AC = LastEtoile;
				LastPointVirgule = OldPointVirgule;
				LastOu = OldOu;
			} else {
				// Normalement c est un operateur d'action
				AN = new Arbre(charToOperator(c));
				AC.AjouterFilsDroit(AN);
				AC = AC.droit();
			}
		}
		return racine;
	}

	/**
	 * Methode à appeler pour executer l'automate d'un robot.
	 * 
	 * @param bot
	 *            Robot qui doit executer l'automate.
	 */
	public void Run(Robots bot) {
		RunAutomate(aExec, bot);
	}

	/**
	 * Methode reccursive d'execution de l'automate code.
	 * 
	 * @param a
	 *            Parametre reccursif, automate restant a executer.
	 * @param bot
	 *            Robot sur lequel l'automate s'execute.
	 */
	private void RunAutomate(Arbre a, Robots bot) {
		if (a.op() instanceof Star) {
			StarExec = a;
			aExec = StarExec;
			exec = false;
			RunAutomate(a.droit(), bot);
		} else if (a.op() instanceof PointVirgule) {
			if (exec == false) {
				exec = true;
				aExec = a;
				RunAutomate(a.gauche(), bot);
			} else {
				exec = false;
				aExec = StarExec;
				RunAutomate(a.droit(), bot);
			}
		} else if (a.op() instanceof Barre) {
			// aExec = StarExec;
			if (Math.random() < 0.5) {
				RunAutomate(a.gauche(), bot);
			} else {
				RunAutomate(a.droit(), bot);
			}
		} else if (a.op() instanceof Preference) {
			aExec = StarExec;
			PrefExec = a.droit();
			if (a.gauche().op().isPossible(bot)) {
				pref = true;
				RunAutomate(a.gauche(), bot);
			} else {
				RunAutomate(a.droit(), bot);
			}
		} else if (a.op() instanceof DeuxPoints) {
			count++;
			RunAutomate(a.droit(), bot);
		} else {
			if (pref && !(a.op().isPossible(bot))) {
				count = 1;
				RunAutomate(PrefExec, bot);
			} else {
				if(a.op() instanceof Kamikaze)
					count = 1;
				for (int i = 0; i < count; i++) {
					a.op().action(bot);
					// System.out.println(a.op().toString() + " s'execute.");
				}
				count = 1;
			}

		}
	}

	/**
	 * Methode qui recupere un caractere et lui assigne son operateur.
	 * 
	 * @param c
	 *            Caractere dont on a besoin de connaitre l'operateur.
	 * @return Operateur correspondant au caractère passé en paramètre.
	 * @require Le caractere correspond a une action.
	 */
	private Operateurs charToOperator(char c) {
		Operateurs op = null;
		switch (c) {
		case 'H':
			op = new Hit();
			break;
		case 'K':
			op = new Kamikaze();
			break;
		case 'O':
			op = new Others();
			break;
		case 'P':
			op = new Protect();
			break;
		case 'J':
			op = new Rapport();
			break;
		default:
			throw new PanicException("Non-action operator");
		}
		return op;
	}

	private void CheckValide(String s) {
		char c, cs;
		boolean open = false;
		for (int i = 0; i < s.length() - 1; i++) {
			c = s.charAt(i);
			cs = s.charAt(i + 1);
			if (c == 'H' || c == 'K' || c == 'O' || c == 'P' || c == 'J')
				c = '.';
			if (cs == 'H' || cs == 'K' || cs == 'O' || cs == 'P' || cs == 'J')
				cs = '.';
			if (i == 0 && (c != '.' && c != '*'))
				throw new ParsingException("Chaine Incorrecte");
			switch (c) {
			case '*':
				if (cs != '{')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case '{':
				open = true;
				if (cs != '.')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case '}':
				if ((cs != ';' && cs != '>' && cs != '|') || !open)
					throw new ParsingException("Chaine Incorrecte");
				open = false;
				break;
			case '>':
				if (cs != '.' && cs != '*')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case '|':
				if (cs != '.' && cs != '*')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case ';':
				if (cs != '.' && cs != '*')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case ':':
				if (cs != ':' && cs != ';' && cs != '>' && cs != '|' && cs != '}')
					throw new ParsingException("Chaine Incorrecte");
				break;
			case '.':
				if (cs != ':' && cs != ';' && cs != '|' && cs != '>' && cs != '}')
					throw new ParsingException("Chaine Incorrecte");
				break;
			}
		}
	}

	public String toString(){
		return code.toString();
	}
}
