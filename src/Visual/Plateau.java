package Visual;

import Engine.Entite;

public class Plateau {
	static final int nblignes = 11;
	static final int nbcolonnes = 21;

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

}
