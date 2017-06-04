package Engine;

import java.util.HashMap;

import Exception.PanicException;

/**
 * Classe représentant un personnage, avec des coordonées, un inventaire
 * d'objet, et un inventaire de robots.
 * 
 * @version Version 1.0
 */
public class Personnages implements Vivante {
	private HashMap<Character, Integer> Inventory = new HashMap<>();
	private Robots Units[] = new Robots[3];
	private int x, y, numberRobots;

	/**
	 * Contructeur de personnage
	 * 
	 * @since Version 1.0
	 */
	public Personnages() {
		x = 0;
		y = 0;
		numberRobots = 0;
		initInventory();
	}

	/**
	 * Methode qui initialise l'inventaire d'opérateurs
	 * 
	 * @since Version 1.0
	 */
	private void initInventory() {
		Inventory.clear();
		Inventory.put('*', 0); // Loop
		Inventory.put('>', 0); // Preference
		Inventory.put('S', 0); // Split
		Inventory.put('H', 0); // Hit
		Inventory.put('E', 0); // Escape
		Inventory.put('K', 0); // Kamikaze
		Inventory.put('P', 0); // Protect
		Inventory.put('F', 0); // Follow
		Inventory.put(':', 0); // Repeat
		Inventory.put('J', 0); // Rapporte
		Inventory.put('X', 0); // Explore
		Inventory.put('C', 0); // Closest
		Inventory.put('B', 0); // Best
		Inventory.put('W', 0); // Where
		Inventory.put('O', 0); // Others
	}

	/**
	 * Getter de position x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter de postion y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Methode permettant d'incrementer le nombre d'un operateur connu par
	 * l'inventaire.
	 * 
	 * @param op
	 *            Operateur dont le nombre est à augmenter.
	 * @require op est un opérateur connu de l'inventaire.
	 * @since version 1.0
	 */
	public void addOperator(char op) {
		if (!(Inventory.containsKey(op))) {
			throw new PanicException("Ajout d'objet à l'inventaire du personnage : Objet inconnu");
		}
		Inventory.put(op, Inventory.get(op) + 1);
	}

	/**
	 * Methode qui permet l'ajout d'un robots à l'équipe du personnage.
	 * 
	 * @param robot
	 *            Robot à ajouter à l'inventaire de robot du personnage.
	 * @param room
	 *            Case dans laquelle mettre le robot.
	 * @require room comprit entre 1 et 3. Pas plus de 3 robots dans
	 *          l'inventaire. room ne contient pas déja un robot.
	 * @since Version 1.0
	 */
	public void addRobot(Robots robot, int room) {
		int indexUnit = room - 1;
		if (numberRobots > 3) {
			throw new PanicException("Ajout d'un robot au personnage : Limite atteinte.");
		}
		if (indexUnit >= 3 || indexUnit < 0) {
			throw new PanicException("Ajout d'un robot au personnage : Case invalide");
		}
		if (Units[indexUnit] != null) {
			throw new PanicException("Ajout d'un robot au personnage : Case occupée");
		}
		Units[indexUnit] = robot;
		numberRobots++;
	}

	/**
	 * Methode qui permet la suppression d'un robots à l'équipe du personnage.
	 * 
	 * @param room
	 *            case ou se trouve le robot à supprimer
	 * @require L'equipe n'est pas vide. La case est comprise entre 1 et 3.
	 * @since Version 1.0
	 */
	public void removeRobot(int room) {
		int indexUnit = room - 1;
		if (numberRobots == 0) {
			throw new PanicException("Suppression d'un robot au personnage : Equipe deja vide.");
		}
		if (indexUnit >= 3 || indexUnit < 0) {
			throw new PanicException("Suppression d'un robot au personnage : Case invalide");
		}
		Units[indexUnit] = null;
		numberRobots--;
	}

	@Override
	public void detruire() {
		// TODO Auto-generated method stub

	}

	@Override
	public void apparaitre() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Methode qui permet de faire avancer le personnage d'un certain pas dans
	 * une certaine direction.
	 * 
	 * @param p
	 *            Direction dans laquelle on avance : NORD, SUD, EST, OUEST.
	 * @param nb
	 *            Distance/Pas duquel on avance.
	 * @since Version 1.0
	 */
	public void mouvement(PointCardinal p, int nb) {
		switch (p) {
		case NORD:
			y -= nb;
			break;
		case SUD:
			y += nb;
			break;
		case EST:
			x += nb;
			break;
		case OUEST:
			x -= nb;
			break;
		default:
			throw new PanicException("Deplacement Personnage : Point Cardinal incorrect.");
		}
	}

	/**
	 * Fonction d'affichage de la classe Personnages.
	 * 
	 * @return La chaine de caractère correspondant à l'affichage.
	 * @since Version 1.0
	 */
	public String toString() {
		String retour = new String();
		retour = "Position : (" + x + "," + y + ")\n";
		retour += "Nombre de robots : " + numberRobots + "\n";
		retour += Units[0].toString() + " " + Units[2].toString() + " " + Units[2].toString() + "\n";
		retour += "Inventaire d'opérateurs : \n";
		for (HashMap.Entry<Character, Integer> entry : Inventory.entrySet()) {
			retour = retour + entry.getKey().toString() + " " + entry.getValue().toString() + "\n";
		}
		return retour;
	}
}