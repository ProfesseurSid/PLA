package Visual;

import UserInterface.Keyboard;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {

	public static void main(String[] args) {
		Application.launch(Test.class, args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("ARF - Autonomous Robot Fight");
		Group root = new Group();
		int dimX = Terrain.getTuileX() * Tuile.getTaille() + 2 * Barre.getDimX() + 30;
		int dimY = Terrain.getTuileY() * Tuile.getTaille() + Case.getTaille() + 20;
		Scene scene = new Scene(root, dimX, dimY);

		Terrain monTerrain = new Terrain();
		monTerrain.setTranslateX(20 + Barre.getDimX());
		monTerrain.setTranslateY(10);

		Keyboard keyboard = new Keyboard(monTerrain.getpersonnage1(), monTerrain.getpersonnage2());

		scene.setOnKeyPressed(keyboard);

		OperateursVisual operateur;
		operateur = monTerrain.getoperateur();

		Timeline blinker = operateur.Blinker(monTerrain.getImageOperateur());
		FadeTransition fader = operateur.Fader(monTerrain.getImageOperateur());

		SequentialTransition blinkThenFade = new SequentialTransition(monTerrain.getImageOperateur(), blinker, fader);

		blinkThenFade.play();

		Boite boiteGauche = new Boite();
		boiteGauche.setTranslateX(10);
		boiteGauche.setTranslateY(10);

		Barre barreDroite = new Barre();
		barreDroite.setTranslateX(10 + Tuile.getTaille() * Terrain.getTuileX() + Barre.getDimX() + 20);
		barreDroite.setTranslateY(10);

		root.getChildren().add(monTerrain);
		root.getChildren().add(barreDroite);
		root.getChildren().add(boiteGauche);

		ImageView logo = new ImageView(new Image(Test.class.getResourceAsStream("images/ARF.png")));
		logo.setTranslateX(10 + Barre.getDimX() + Tuile.getTaille() / 2);
		logo.setTranslateY(dimY - Case.getTaille());
		logo.setFitHeight(2 * Tuile.getTaille());
		logo.setFitWidth(4 * Tuile.getTaille());
		root.getChildren().add(logo);

		scene.setFill(Color.rgb(210, 200, 190, 1.0));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		Timer game = new Timer();
		game.start();
	}

}
