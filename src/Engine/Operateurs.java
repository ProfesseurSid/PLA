package Engine;

public interface Operateurs extends Entite {

	/*
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since ver 1.0
	 */
	public void stock(Personnages p);

	public void action(Automates a, Robots nono);

	public String toString();
}