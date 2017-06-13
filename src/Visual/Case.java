package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Case extends Parent {

	private int posX;
	private int posY;

	String op;
	int nb;

	private static int taille = (3 * Tuile.getTaille()) / 2;

	Rectangle fond_case;
	Text typeOp;
	Text compt;

	public Case(int X, int Y, char o) {
		posX = X;
		posY = Y;
		op = "" + o;

		fond_case = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));
		fond_case.setStroke(Color.rgb(150, 150, 150, 1.0));
		fond_case.setStrokeWidth(2);
		fond_case.setArcHeight(10);
		fond_case.setArcWidth(10);

		typeOp = new Text(op);
		typeOp.setFont(Font.font("Verdana", Tuile.getTaille()));
		typeOp.setTranslateY(Tuile.getTaille());
		typeOp.setTranslateX(Tuile.getTaille() / 2);

		this.getChildren().add(fond_case);
		this.getChildren().add(typeOp);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public Case(int X, int Y, char o, Engine.Personnages p) {
		posX = X;
		posY = Y;
		op = "" + o;
		nb = p.getInventory().get(o);
		String nombre = "" + nb;

		fond_case = new Rectangle(taille, taille);
		if(nb == 0){
			fond_case.setFill(Color.rgb(150, 150, 150, 1.0));
			fond_case.setStroke(Color.rgb(100, 100, 100, 1.0));
		}
		else {
			fond_case.setFill(Color.rgb(220, 220, 220, 1.0));
			fond_case.setStroke(Color.rgb(150, 150, 150, 1.0));
		}
		fond_case.setStrokeWidth(2);
		fond_case.setArcHeight(10);
		fond_case.setArcWidth(10);

		typeOp = new Text(op);
		typeOp.setFont(Font.font("Verdana", Tuile.getTaille()));
		typeOp.setTranslateY(Tuile.getTaille());
		typeOp.setTranslateX(Tuile.getTaille() / 2);

		compt = new Text(nombre);
		compt.setFont(Font.font("Verdana", Tuile.getTaille() / 3));
		compt.setTranslateY((Tuile.getTaille() * 3) / 2 - Test.marge / 2);
		compt.setTranslateX(Test.marge / 2);

		this.getChildren().add(fond_case);
		this.getChildren().add(typeOp);
		this.getChildren().add(compt);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

}
