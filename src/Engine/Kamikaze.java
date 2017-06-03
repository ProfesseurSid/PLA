package Engine;

public class Kamikaze implements Operateurs {

	int x, y;

	@Override
	public void detruire() {
		// TODO Auto-generated method stub

	}

	@Override
	public void apparaitre() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	/*
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since ver 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('K');
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "K";
	}

}
