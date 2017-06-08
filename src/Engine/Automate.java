package Engine;

import Exception.PanicException;

/**
 * Classe qui implante l'automate d'un robot. Elle contient les methodes de
 * lecture sous forme de chaine de caractère et les methodes d'execution.
 */
public class Automate {
	private Arbre code;
	private Arbre aExec;
	private Arbre StarExec;
	private int count = 1;
	private boolean exec = false;

	/**
	 * Constructeur d'arbre d'automate par défault.
	 */
	public Automate() {
		code = new Arbre(new Star(), new Arbre(new Others()), null);
		aExec = code;
		StarExec = code;
	}

	/**
	 * Constructeur d'arbre d'automate avec une seul instruction.
	 * 
	 * @param op
	 *            Instruction unique de l'automate crée.
	 */
	public Automate(Operateurs op) {
		code = new Arbre(new Star(), new Arbre(op), null);
		aExec = code;
		StarExec = code;
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
		aExec = code;
		StarExec = code;
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

	public void Run(Robots bot) {
		RunAutomate(aExec, bot);
	}

	// FIXME : Cas
	// de l'exemple dans main.
	public void RunAutomate(Arbre a, Robots bot) {
		if (a.op() instanceof Star) {
			StarExec = a;
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
			aExec = StarExec;
			if (Math.random() < 0.5) {
				RunAutomate(a.gauche(), bot);
			} else {
				RunAutomate(a.droit(), bot);
			}
		} else if (a.op() instanceof Preference) {
			aExec = StarExec;
			// if (a.gauche().op().isPossible(bot)) {
			if (true) {
				RunAutomate(a.gauche(), bot);
			} else {
				RunAutomate(a.droit(), bot);
			}
		} else if (a.op() instanceof DeuxPoints) {
			count++;
			RunAutomate(a.droit(), bot);
		} else {
			for (int i = 0; i < count; i++)
				// a.op().action(bot);
				System.out.println(a.op().toString() + " s'execute.");
			count = 1;
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

	public static void main(String[] args) {
		// Automate auto = new Automate("*{K>J;H>*{K;J>H::;J}|K}");
		Automate auto = new Automate("*{O::;*{H>K}}");
		Arbre a1 = new Arbre(new Preference(), new Arbre(new Rapport()), new Arbre(new Hit()));
		Arbre a2 = new Arbre(new PointVirgule(), a1, new Arbre(new Rapport()));
		Arbre a3 = new Arbre(new PointVirgule(), new Arbre(new Kamikaze()), a2);
		Arbre a4 = new Arbre(new Star(), null, a3);
		Arbre a = new Arbre(new Star(), null,
				new Arbre(new PointVirgule(),
						new Arbre(new Preference(), new Arbre(new Kamikaze()), new Arbre(new Rapport())),
						new Arbre(new Preference(), new Arbre(new Hit()),
								new Arbre(new Barre(), a4, new Arbre(new Kamikaze())))));
		System.out.println(auto.code.toString());
		Robots bot = new Robots(1);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
		auto.Run(bot);
	}
}
