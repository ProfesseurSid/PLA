package Visual;

import Engine.Timer;
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

	static int marge = Tuile.getTaille() / 5;

	public static void main(String[] args) {
		Application.launch(Test.class, args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("ARF - Autonomous Robot Fight");
		Group root = new Group();
		int dimX = Terrain.getTuileX() * Tuile.getTaille() + 2 * Barre.getDimX() + 3 * marge;
		int dimY = Barre.getDimX() + Boite.getHeight() + 4 * marge;
		Scene scene = new Scene(root, dimX, dimY);

		Terrain monTerrain = new Terrain();
		monTerrain.setTranslateX(20 + Barre.getDimX());
		monTerrain.setTranslateY(10);
		Boite boiteGauche = new Boite(monTerrain.getpersonnage1());
		Boite boiteDroite = new Boite(monTerrain.getpersonnage2());
		root.getChildren().add(boiteDroite);
		root.getChildren().add(boiteGauche);

		Team team1 = new Team(0);
		Team team2 = new Team(1);
		root.getChildren().add(team1);
		root.getChildren().add(team2);
		Champexpr champBleu = new Champexpr("", 0);
		Champexpr champRouge = new Champexpr("", 1);
		root.getChildren().add(champBleu);
		root.getChildren().add(champRouge);

		Keyboard keyboard = new Keyboard(monTerrain.getpersonnage1(), monTerrain.getpersonnage2(), root, boiteGauche,
				boiteDroite, team1, team2, champBleu, champRouge);

		scene.setOnKeyPressed(keyboard);

		OperateursVisual operateur;
		operateur = monTerrain.getoperateur();

		Timeline blinker = operateur.Blinker(monTerrain.getImageOperateur());
		FadeTransition fader = operateur.Fader(monTerrain.getImageOperateur());

		SequentialTransition blinkThenFade = new SequentialTransition(monTerrain.getImageOperateur(), blinker, fader);

		blinkThenFade.play();

		root.getChildren().add(monTerrain);

		ImageView PersoRouge = new ImageView(new Image(Test.class.getResourceAsStream("images/PersoRouge.png")));
		PersoRouge.setFitWidth(Barre.getDimX());
		PersoRouge.setFitHeight(Barre.getDimX());
		PersoRouge.setTranslateX(Tuile.getTaille() * Terrain.getTuileX() + Barre.getDimX() + 3 * marge);
		PersoRouge.setTranslateY(Boite.getHeight() + 4 * marge);
		root.getChildren().add(PersoRouge);

		ImageView PersoBleu = new ImageView(new Image(Test.class.getResourceAsStream("images/PersoBleu.png")));
		PersoBleu.setFitWidth(Barre.getDimX());
		PersoBleu.setFitHeight(Barre.getDimX());
		PersoBleu.setTranslateX(marge);
		PersoBleu.setTranslateY(Boite.getHeight() + 4 * marge);
		root.getChildren().add(PersoBleu);

		scene.setFill(Color.rgb(210, 200, 190, 1.0));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		Timer game = new Timer();
		game.start();
	}

}
