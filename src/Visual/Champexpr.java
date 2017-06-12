package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Champexpr extends Parent {
	Rectangle champ;
	String expr;

	public Champexpr(String expression, int equipe) {
		this.champ = new Rectangle();
		this.expr = expression;
		champ.setHeight(Tuile.getTaille());
		champ.setWidth((Terrain.getTuileX() / 2) * Tuile.getTaille());
		if (equipe == 0)
			champ.setTranslateX(2 * Test.marge + Barre.getDimX());
		else
			champ.setTranslateX(2 * Test.marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
		champ.setTranslateY(2 * Test.marge + Terrain.getTuileY() * Tuile.getTaille());
		this.getChildren().add(champ);
		Text expre = new Text(expr);
		expre.setFont(new Font(Tuile.getTaille() - Test.marge));
		if (equipe == 0) {
			expre.setFill(Color.rgb(72, 145, 220, 1.0));
			expre.setX(3 * Test.marge + Barre.getDimX());
		} else {
			expre.setFill(Color.rgb(220, 41, 30, 1.0));
			expre.setX(3 * Test.marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
		}
		expre.setY(Test.marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		this.getChildren().add(expre);
		System.out.println("equipe"+equipe+"position X ="+expre.getX()+"position Y ="+expre.getY()+" String = " + this.getExpr());
	}
	
	public String getExpr(){
		return this.expr;
	}

}
