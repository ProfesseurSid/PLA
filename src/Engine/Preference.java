package Engine;

public class Preference implements Operateurs {

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
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/*
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since ver 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('>');
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return ">";
	}

}
