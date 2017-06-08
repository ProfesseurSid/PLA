package Engine;

import java.util.HashMap;

import Exception.PanicException;
import Visual.Plateau;

/**
 * Classe repr�sentant un personnage, avec des coordon�es, un inventaire
 * d'objet, et un inventaire de robots.
 * 
 * @version Version 1.0
 */
public class Personnages implements Vivante {
	private HashMap<Character, Integer> Inventory = new HashMap<>();
	private Robots Units[] = new Robots[3];
	private int x, y, numberRobots;
	int equipe;

	/**
	 * Contructeur de personnage de l'équipe e
	 * 
	 * @since Version 1.0
	 */
	public Personnages(int e) {
		if (e == 0) {
			x = 0;
			y = 0;
		} else if (e == 1) {
			x = Plateau.nblignes;
			y = Plateau.nbcolonnes;
		}
		equipe = e;
		numberRobots = 0;
		initInventory();
	}

	/**
	 * Methode qui initialise l'inventaire d'op�rateurs
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
	 *            Operateur dont le nombre est � augmenter.
	 * @require op est un op�rateur connu de l'inventaire.
	 * @since version 1.0
	 */
	public void addOperator(char op) {
		if (!(Inventory.containsKey(op))) {
			throw new PanicException("Ajout d'objet � l'inventaire du personnage : Objet inconnu");
		}
		Inventory.put(op, Inventory.get(op) + 1);
	}

	/**
	 * Methode permettant de décrementer le nombre d'un operateur connu par
	 * l'inventaire.
	 * 
	 * @param op
	 *            Operateur dont le nombre est à décrementer.
	 * @require op est un opérateur connu de l'inventaire.
	 */
	public void removeOperator(char op) {
		if (!(Inventory.containsKey(op))) {
			throw new PanicException("Suppression d'objet de l'inventaire du personnage : Objet inconnu");
		}
		if (Inventory.get(op) == 0) {
			throw new PanicException(
					"Suppression d'objet de l'inventaire du personnage : Il y a déja 0 objets de ce type dans l'inventaire.");
		}
		Inventory.put(op, Inventory.get(op) - 1);
	}

	/**
	 * Methode qui permet l'ajout d'un robots � l'�quipe du personnage.
	 * 
	 * @param robot
	 *            Robot � ajouter � l'inventaire de robot du personnage.
	 * @param room
	 *            Case dans laquelle mettre le robot.
	 * @require room comprit entre 1 et 3. Pas plus de 3 robots dans
	 *          l'inventaire. room ne contient pas d�ja un robot.
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
			throw new PanicException("Ajout d'un robot au personnage : Case occup�e");
		}
		Units[indexUnit] = robot;
		numberRobots++;
	}

	/**
	 * Methode qui permet la suppression d'un robots � l'�quipe du personnage.
	 * 
	 * @param room
	 *            case ou se trouve le robot � supprimer
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
	 * @return La chaine de caract�re correspondant � l'affichage.
	 * @since Version 1.0
	 */
	public String toString() {
		String retour = new String();
		retour = "Position : (" + x + "," + y + ")\n";
		retour += "Nombre de robots : " + numberRobots + "\n";
		retour += Units[0].toString() + " " + Units[2].toString() + " " + Units[2].toString() + "\n";
		retour += "Inventaire d'operateurs : \n";
		for (HashMap.Entry<Character, Integer> entry : Inventory.entrySet()) {
			retour = retour + entry.getKey().toString() + " " + entry.getValue().toString() + "\n";
		}
		return retour;
	}

	public int getEquipe() {
		return equipe;
	}

	public boolean memeEquipe(Vivante v) {
		return equipe == v.getEquipe();
	}
}