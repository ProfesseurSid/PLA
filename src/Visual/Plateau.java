package Visual;

import Engine.Entite;

public class Plateau {
	public static final int nblignes = 11;
	public static final int nbcolonnes = 21;

	Entite[][] plateau = new Entite[nblignes][nbcolonnes];

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
	 * 
	 * @param lig
	 *            la ligne recherchée
	 * @param col
	 *            la colonne recherchée
	 * @require lig E [0, nblignes-1] ; col E [0, nbcolonnes-1]
	 * @return l'élément contenu dans le plateau
	 */
	public Entite unsafeGet(int lig, int col) {
		return plateau[lig][col];
	}

}
