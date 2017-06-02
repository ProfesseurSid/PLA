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
		 * Construction d'un point au point {0, 0}.
		 */
		public Coord() {
			positionner(0.0, 0.0);
		}

		/**
		 * Construction d'un point
		 */
		public Coord(double abscisse, double ordonnee) {
			positionner(abscisse, ordonnee);
		}

		/**
		 * Positionne le point à {x, y}
		 */
		public void positionner(double abscisse, double ordonnee) {
			abs = abscisse;
			ord = ordonnee;
		}
	}

	int PV = 5;

	void mouvement(Coord p, int nb);

}
