package Visual;

import Engine.Entite;

public class Plateau {
	public static final int nblignes = 11;
	public static final int nbcolonnes = 21;

	Entite[][] plateau = new Entite[nblignes][nbcolonnes];

	/**
	 * Initialise le tableau des éléments du jeu à null (vide)
	 */
	public void init() {
		for (int i = 0; i < nblignes; i++)
			for (int j = 0; j < nbcolonnes; j++)
				plateau[i][j] = null;
	}

	/**
	 * Affiche à l'écran chaque élément du tableau
	 */
	public void afficher() {
		for (int i = 0; i < nblignes; i++)
			for (int j = 0; j < nbcolonnes; j++)
				plateau[i][j].apparaitre();
	}

	/**
	 * récupère l'entité contenue dans la case d'indices lig,col
	 * @param lig
	 * @param col
	 * @return
	 */
	public Entite unsafeGet(int lig, int col){
		return 
	}

}
