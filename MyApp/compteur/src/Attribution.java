import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Attribution {

  static String[] tableau_participants;
  static String[][] classement;

  public static void main(String[] args) {
    Scanner nbr_participants = new Scanner(System.in);
    System.out.print("Nombre de participants : ");
    int participants = nbr_participants.nextInt();

    int tailleClassement = (participants / 2) + 1;
    classement = new String[4][tailleClassement];

    classement[0][0] = "Nom des equipes";
    classement[1][0] = "Joueur 1";
    classement[2][0] = "Joueur 2";
    classement[3][0] = "Score";
    classement[3][1] = "0";
    classement[3][2] = "0";

    enregistrerParticipants(participants);
    melangerTableau();
    afficherNomParticipants();

    creationPool(participants);

    for (;;) {
      Scanner faireAction = new Scanner(System.in);
      System.out.print("score ou ajout point ou afficher participants : ");
      String action = faireAction.nextLine();

      switch (action) {
        case "score":
          descriptifEquipe();
          break;
        case "afficher participants":
          afficherNomParticipants();
          break;
        case "ajout point":
          int point = Attribution.points();
          int numeroEquipe = Attribution.equipe();
          addPoint(numeroEquipe, point);
          break;
        default:
          System.out.print("Erreur de syntaxe.");
          break;
      }
    }
  }

  // Enregistrement du nom des joueurs
  static void enregistrerParticipants(int participants) {
    tableau_participants = new String[participants];

    for (int i = 0; i < participants; i++) {
      int j = i + 1;
      Scanner nom_participant = new Scanner(System.in);
      System.out.print("Nom du participant numéro " + j + " : ");

      String nom_du_participant = nom_participant.nextLine();

      tableau_participants[i] = nom_du_participant;
    }
  }

  // Melange des joueurs
  static void melangerTableau() {
    Random rnd = ThreadLocalRandom.current();
    for (int i = tableau_participants.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);

      String a = tableau_participants[index];
      tableau_participants[index] = tableau_participants[i];
      tableau_participants[i] = a;
    }
  }

  // Affiche le nom des entrees
  static void afficherNomParticipants() {
    for (int i = 0; i < tableau_participants.length; i++) {
      System.out.println(tableau_participants[i]);
    }
  }

  // ___________________________________________________________________________________________________________________________________

  // Creation et attribution au hasard des equipes
  static void creationPool(int nbrEquipe) {
    for (int i = 1; (i * 2) <= nbrEquipe; i++) {
      // Noms d equipe personnalises
      Scanner nomEquipe = new Scanner(System.in);
      System.out.print("Nom equipe " + i + " : ");
      String equipe = nomEquipe.nextLine();

      classement[0][i] = equipe;
    }

    // Attribution des équipes
    boolean premier1 = true;
    boolean premier2 = true;

    for (int i = tableau_participants.length - 1; i >= 0; i--) {
      if ((i < (tableau_participants.length + 1) / 2)) {
        if (premier1) {
          classement[1][1] = tableau_participants[i];
          premier1 = false;
        } else {
          classement[2][1] = tableau_participants[i];
        }
      } else {
        if (premier2) {
          classement[1][2] = tableau_participants[i];
          premier2 = false;
        } else {
          classement[2][2] = tableau_participants[i];
        }
      }
    }
  }

  // Afficher toutes les informations du tableau
  static void descriptifEquipe() {
    int equipe = equipe();
    System.out.println(
      "Nom de l\'equipe " +
      equipe +
      " : " +
      classement[0][equipe] +
      ". Joueurs : " +
      classement[1][equipe] +
      " et " +
      classement[2][equipe] +
      ". Avec un score actuel de : " +
      classement[3][equipe] +
      " points!!"
    );
  }

  // Ajout de points
  static void addPoint(int equipe, int points) {
    int pointsEquipeInt = Integer.parseInt(classement[3][equipe]);
    pointsEquipeInt += points;
    classement[3][equipe] = String.valueOf(pointsEquipeInt);
  }

  static int points() {
    Scanner nbr_points = new Scanner(System.in);
    System.out.print("Nombre de points a attribuer : ");
    int points = nbr_points.nextInt();

    return points;
  }

  static int equipe() {
    Scanner numeroEquipe = new Scanner(System.in);
    System.out.print(
      "Choisissez une equipe entre 1 et " +
      (tableau_participants.length / 2) +
      " : "
    );
    int equipe = numeroEquipe.nextInt();

    return equipe;
  }
}
