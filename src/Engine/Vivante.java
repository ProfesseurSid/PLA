package Engine;

public interface Vivante extends Entite {
	public class Coord {
		private double abs, ord;

		/**
		 * Retourne l'abscisse du point
		 */
		public double abscisse() {
			return abs;
		}

		/**
		 * Retourne l'ordonnee du point
		 */
		public double ordonnee() {
			return ord;
		}

		/**
		 * Positionne le point � {x, y}
		 */
		public void positionner(double abscisse, double ordonnee) {
			abs = abscisse;
			ord = ordonnee;
		}
	}

	void mouvement(Coord p, int nb);

}