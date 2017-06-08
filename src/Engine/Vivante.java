package Engine;

public interface Vivante extends Entite {

	void mouvement(PointCardinal p);
	public void mouvement(PointCardinal p, int nb);

	public int getEquipe();

	public boolean memeEquipe(Vivante ent);

}