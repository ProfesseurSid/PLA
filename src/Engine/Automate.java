package Engine;

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
		code = new Arbre(new Star());
		s.substring(2, s.length() - 1);
		code.AjouterFilsGauche(stringToArbre(s));
	}

	public Arbre stringToArbre(String s) {

		throw new PanicException("Not Yet Implemented");
	}

	public static void main(String[] args) {
		String s = "*{A>B;C}";
		System.out.println(s.substring(2, s.length() - 1));
	}
}
