package Visual;

import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import Exception.PanicException;

/**
 * S
 ***************************************************************************************
 * PersonnagesVisual est la classe qui affiche et deplace un personnage sur le
 * terrain *
 ***************************************************************************************
 */
public class PersonnagesVisual extends Parent {
	private int indX; // Index d'une colonne du terrain
	private int indY; // Index d'une ligne du terrain

	int taille = Tuile.getTaille();

	Plateau plateau;
	ImageView personnage;

	/**
	 * Constructeur qui affiche l'image "personnage" dans la case de coordonn�es
	 * (indY,indX)
	 * 
	 * @param personnage
	 *            L'image avatar du personnage
	 * @param e
	 *            Represente le numero de l'equipe ( 0 pour le personnage 1, 1
	 *            pour le personnage 2)
	 * @require e == 0 || e == 1
	 */
	public PersonnagesVisual(ImageView personnage, int e, Plateau plateau) {
		if (e == 0) {
			indX = 0;
			indY = plateau.nbLignes() / 2;
		} else if (e == 1) {
			indX = plateau.nbColonnes() -1;
			indY = plateau.nbLignes() / 2;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		
		this.personnage = personnage;
		this.personnage.xProperty().set(indX*taille);
		this.personnage.yProperty().set(indY*taille);
		//personnage.setTranslateX(indX * taille);
		//personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);
		this.getChildren().add(personnage);

		this.plateau = plateau;
		//personnage.xProperty().set(indX*taille);
		//personnage.yProperty().set(indY*taille);
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
	public void Haut() {
	/*	System.out.println(plateau.verification(indX, indY - 1));
		indY--;
		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);*/
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(personnage.yProperty(),(indY-1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indY--;
		 System.out.print("up:"+personnage.yProperty());
	}
		

	/**
	 * Deplace le personnage d'une case vers le bas
	 */
	public void Bas() {
		/*indY++;
		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);*/
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(personnage.yProperty(),(indY+1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indY++;
		 System.out.print("down:"+personnage.yProperty());
	}

	/**
	 * Delace le personnage d'une case vers la gauche
	 */
	public void Gauche() {
		/*indX--;
		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);*/
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(personnage.xProperty(),(indX-1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indX--;
		 System.out.print("left:"+personnage.xProperty());
	}

	/**
	 * Deplace le personnage d'une case vers la droite
	 */
	public void Droite() {
		/*indX++;
		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);*/
		Timeline timeline = new Timeline();
		timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(personnage.xProperty(),(indX+1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indX++;
		 System.out.print("right:"+personnage.xProperty());
	}
	
	public void setX(int x){
		indX = x;
	}
	
	public void setY(int y){
		indY = y;
	}

}
