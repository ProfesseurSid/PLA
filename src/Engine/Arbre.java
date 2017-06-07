package Engine;

/**
 * Classe qui implante le type arbre avec un operateur en élément.
 */
public class Arbre {
	private Operateurs op;
	private Arbre gauche, droit;

	/**
	 * Constructeur d'arbre vide avec un element nul et des fils nuls.
	 */
	public Arbre() {
		op = null;
		gauche = null;
		droit = null;
	}

	/**
	 * Constructeur d'arbre vide avec un element et des fils nuls.
	 * 
	 * @param elem
	 *            Operateur qui sera en element de l'arbre.
	 */
	public Arbre(Operateurs elem) {
		op = elem;
	}

	/**
	 * Constructeur d'arbre avec un element et ses fils.
	 * 
	 * @param elem
	 *            Operateur qui sera élément du neoud
	 * @param g
	 *            Fils gauche.
	 * @param d
	 *            Fils droit.
	 */
	public Arbre(Operateurs elem, Arbre g, Arbre d) {
		op = elem;
		g = gauche;
		d = droit;
	}

	/**
	 * Getter
	 * 
	 * @return pointeur droit.
	 */
	public Arbre droit() {
		return droit;
	}

	/**
	 * Getter
	 * 
	 * @return pointeur gauche.
	 */
	public Arbre gauche() {
		return gauche;
	}

	/**
	 * Remplace le fils droit du noeud courant par un arbre donné en paramètre.
	 * 
	 * @param a
	 *            Arbre à mettre en fils droit.
	 */
	public void AjouterFilsDroit(Arbre a) {
		droit = a;
	}

	/**
	 * Remplace le fils droit du noeud courant par un arbre contenant un
	 * operateur donné en paramètre.
	 * 
	 * @param op
	 *            Operateur qui doit se retrouver en fils droit.
	 */
	public void AjouterFilsDroit(Operateurs op) {
		droit = new Arbre(op);
	}

	/**
	 * Remplace le fils gauche du noeud courant par un arbre donné en paramètre.
	 * 
	 * @param a
	 *            Arbre à mettre en fils gauche.
	 */
	public void AjouterFilsGauche(Arbre a) {
		gauche = a;
	}

	/**
	 * Remplace le fils gauche du noeud courant par un arbre contenant un
	 * operateur donné en paramètre.
	 * 
	 * @param op
	 *            Operateur qui doit se retrouver en fils gauche.
	 */
	public void AjouterFilsGauche(Operateurs op) {
		gauche = new Arbre(op);
	}
	
	/**
	 * Setter de op.
	 * @param op Operateur à mettre dans le noeud.
	 */
	public void setValeurNoeud(Operateurs op){
		this.op = op;
	}

	/**
	 * Methode qui ajoute un operateur sur un noeud, décallant le noeud déja en
	 * place (et son sous arbre) sur le fils gauche.
	 * 
	 * @param op
	 *            Operateur qui doit constituer le nouveau noeud.
	 */
	public void RemplacerDecaler(Operateurs op) {
		Arbre fils = new Arbre(this.op, this.gauche, this.droit);
		this.op = op;
		this.gauche = fils;
		this.droit = null;
	}
}
