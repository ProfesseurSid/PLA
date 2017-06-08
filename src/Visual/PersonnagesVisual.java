package Visual;

import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import Engine.Personnages;
import Engine.PointCardinal;

/**
 * S
 ***************************************************************************************
 * PersonnagesVisual est la classe qui affiche et deplace un personnage sur le terrain *
 ***************************************************************************************
 */
public class PersonnagesVisual extends Parent {
	private int indX; // Index d'une colonne du terrain
	private int indY; // Index d'une ligne du terrain

	int taille = Tuile.getTaille();
	// Plateau plateau = new Plateau();

	Personnages p;
	Plateau plateau;

	/**
	 * Constructeur qui affiche l'image "personnage" dans la case de coordonnées
	 * (indY,indX)
	 * 
	 * @param personnage
	 *            L'image avatar du personnage
	 * @param e
	 *            Represente le numero de l'equipe ( 0 pour le personnage 1, 1
	 *            pour le personnage 2)
	 */
	public PersonnagesVisual(ImageView personnage, int e, Plateau plateau) {
		p = new Personnages(e);
		this.indX = p.getX();
		this.indY = p.getY();
		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);
		this.getChildren().add(personnage);
		
		this.plateau = plateau;
		plateau.put(indX, indY, p);
		//plateau.plateau[indY][indX] = p;
	
	}

	/**
	 * Getter de l'index X
	 */
	public int getX() {
		return this.indX;
	}

	/**
	 * Getter de l'index Y
	 */
	public int getY() {
		return this.indY;
	}

	/**
	 * Deplace le personnage d'une case vers le haut
	 */
	public void Haut(ImageView personnage) {
		System.out.println(plateau.verification(indX, indY - 1));
		if (indY > 0 && plateau.verification(indX, indY - 1) == 0 /*plateau.plateau[indX][indY-1]==null*/) {
			indY--;
			p.mouvement(PointCardinal.NORD);
			plateau.move(indX, indY + 1, indX, indY);
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);

		}
	}

	/**
	 * Deplace le personnage d'une case vers le bas
	 */
	public void Bas(ImageView personnage) {
		if (indY < Terrain.getTuileY() - 1 && plateau.verification(indX, indY + 1) == 0) {
			indY++;
			p.mouvement(PointCardinal.SUD);
			plateau.move(indX, indY - 1, indX, indY);
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);

		}

	}

	/**
	 * Delace le personnage d'une case vers la gauche
	 */
	public void Gauche(ImageView personnage) {
		if (indX > 0 && plateau.verification(indX - 1, indY) == 0) {
			indX--;
			p.mouvement(PointCardinal.OUEST);
			plateau.move(indX + 1, indY, indX, indY);
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);
			

		}
	}

	/**
	 * Deplace le personnage d'une case vers la droite
	 */
	public void Droite(ImageView personnage) {
		if (indX < Terrain.getTuileX() - 1 && plateau.verification(indX + 1, indY) == 0) {
			indX++;
			p.mouvement(PointCardinal.EST);
			plateau.move(indX - 1, indY, indX, indY);
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);

		}
	}

}
