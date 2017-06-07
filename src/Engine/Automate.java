package Engine;

import java.util.StringTokenizer;

import Exception.PanicException;

/**
 * Classe qui implante l'automate d'un robot. Elle contient les methodes de
 * lecture sous forme de chaine de caractère et les methodes d'execution.
 */
public class Automate {
	private Arbre code;

	/**
	 * Constructeur d'arbre d'automate par défault.
	 */
	public Automate() {
		code = new Arbre(new Star(), new Arbre(new Others()), null);
	}

	/**
	 * Constructeur d'arbre d'automate avec une seul instruction.
	 * 
	 * @param op
	 *            Instruction unique de l'automate crée.
	 */
	public Automate(Operateurs op) {
		code = new Arbre(new Star(), new Arbre(op), null);
	}

	/**
	 * Constructeur d'arbre d'automate à partir d'une string.
	 * 
	 * @param s
	 *            Code de l'automate.
	 */
	public Automate(String s) {
		// code = new Arbre(new Star());
		// code.AjouterFilsGauche(stringToArbre(s.substring(2, s.length() -
		// 1)));

		// code = new Arbre(new Star(), stringToArbre(s.substring(2, s.length()
		// - 1)), null);

		code = stringToArbre(s.substring(2, s.length() - 1));
	}

	private Arbre stringToArbre(String s) {
		int i;
		char c;
		Arbre racine = new Arbre(new Star());
		Arbre AC = racine;
		Arbre AP;
		Arbre AN;
		Arbre LastPointVirgule = racine;

		for (i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '*') {
				
			} else if (c == ';') {
				AC = LastPointVirgule.droit();
				AC.RemplacerDecaler(new PointVirgule());
				LastPointVirgule = AC;
			} else if (c == '>') {
				AC.RemplacerDecaler(new Preference());
			} else if (c == '|') {
				AC.RemplacerDecaler(new Barre());
			} else if (c == ':') {
				
			} else if (c == '}') {
				
			} else {
				//Normalement c est un operateur d'action
				AN = new Arbre(charToOperator(c));
				AC.AjouterFilsDroit(AN);
				AP = AC;
				AC = AC.droit();
			}

			// if (s.charAt(i) == ';') {
			// AC.RemplacerDecaler(new PointVirgule());
			// } else {
			// AP = new Arbre(charToOperator(s.charAt(i)));
			// AC.AjouterFilsDroit(AP);
			// AC = AC.droit();
			// }
		}

		return racine;

		// throw new PanicException("Not Yet Implemented");
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

	public static void main(String[] args) {
		Arbre a = new Arbre(new Protect(), new Arbre(new Hit()), new Arbre(new Rapport()));
		Automate auto = new Automate("*{K>H;P}");
		System.out.println(auto.code.toString());
	}
}
