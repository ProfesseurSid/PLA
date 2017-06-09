package Engine;

public interface Operateurs extends Entite {

	/**
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p);

	public void action(Robots nono);
	
	public boolean isPossible(Robots nono);

	public String toString();
}