package Visual;

import Exception.PanicException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class RobotVisual extends Parent {
	private int indX; // Index d'une colonne du terrain
	private int indY; // Index d'une ligne du terrain

	int taille = Tuile.getTaille();

	Plateau plateau;
	ImageView robot;

	/**
	 * Constructeur qui affiche l'image "robot" dans la case de coordonn�es
	 * (indY,indX)
	 * 
	 * @param robot
	 *            L'image avatar du robot
	 * @param e
	 *            Represente le numero de l'equipe ( 0 pour le robot 1, 1
	 *            pour le robot 2)
	 * @require e == 0 || e == 1
	 */
	public RobotVisual(ImageView robot, int e, Plateau plateau) {
		if (e == 0) {
			indX = 1;
			indY = plateau.nbLignes() / 2;
		} else if (e == 1) {
			indX = plateau.nbColonnes() - 2;
			indY = plateau.nbLignes() / 2;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		this.robot = robot;
		robot.xProperty().set(indX*taille);
		robot.yProperty().set(indY*taille);
		//robot.setTranslateX(indX * taille);
		//robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);
		this.getChildren().add(robot);

		this.plateau = plateau;
	}
	
	public RobotVisual(ImageView robot, int x, int y, Plateau plateau) {
		indX = x;
		indY = y;
		this.robot = robot;
		robot.xProperty().set(indX*taille);
		robot.yProperty().set(indY*taille);
		//robot.setTranslateX(indX * taille);
		//robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);
		this.getChildren().add(robot);

		this.plateau = plateau;
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
	 * Deplace le robot d'une case vers le haut
	 */
	public void Haut() {
		/*System.out.println(plateau.verification(indX, indY - 1));
		indY--;
		robot.setTranslateX(indX * taille);
		robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);
		*/
		
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(robot.yProperty(),(indY-1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indY--;
		 System.out.print("up:"+robot.yProperty());
	}

	/**
	 * Deplace le robot d'une case vers le bas
	 */
	public void Bas() {
		/*indY++;
		robot.setTranslateX(indX * taille);
		robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);*/
		
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(robot.yProperty(),(indY+1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indY++;
		 System.out.print("down:"+robot.yProperty());
	}

	/**
	 * Delace le robot d'une case vers la gauche
	 */
	public void Gauche() {
		/*indX--;
		robot.setTranslateX(indX * taille);
		robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);*/
		Timeline timeline = new Timeline();
		 timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(robot.xProperty(),(indX-1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indX--;
		 System.out.print("left:"+robot.xProperty());
	}

	/**
	 * Deplace le robot d'une case vers la droite
	 */
	public void Droite() {
		/*indX++;
		robot.setTranslateX(indX * taille);
		robot.setTranslateY(indY * taille);
		robot.setFitWidth(taille);
		robot.setFitHeight(taille);*/
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(0);
		 timeline.setAutoReverse(true);
		 KeyValue keyvalue = new KeyValue(robot.xProperty(),(indX+1)*taille);
		 KeyFrame keyframe=new KeyFrame(Duration.millis(1000/24),keyvalue);
		 timeline.getKeyFrames().add(keyframe);
		 timeline.play();
		 indX++;
		 System.out.print("right:"+robot.xProperty());
	}
	
	public void remove(){
		this.getChildren().remove(robot);
	}

}
