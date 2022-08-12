import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Attribution {

  static String[] tableau_participants;

  // 0.1 : Nom du participant

  static String[][] classement = new String[4][3];

  // 0.0 : Nom des equipes
  // 1.0 : Equipe 1
  // 2.0 : Equipe 2
  // 1.1 : Nom membre 1
  // 2.1 : Nom membre 2
  // 3.0 : Score

  static int pointsEquipe1 = 0;
  static int pointsEquipe2 = 0;

  public static void main(String[] args) {
    Scanner nbr_participants = new Scanner(System.in);
    System.out.print("Nombre de participants : ");
    int participants = nbr_participants.nextInt();

    classement[0][0] = "Nom des equipes";
    classement[1][0] = "Joueur 1";
    classement[2][0] = "Joueur 2";
    classement[3][0] = "Score";
    classement[3][1] = "0";
    classement[3][2] = "0";

    enregistrerParticipants(participants);
    melangerTableau();
    afficherNomParticipants();

    creationEquipe();

    afficherClassement(1);
    afficherClassement(2);

    addPoint(1);

    afficherClassement(1);
    afficherClassement(2);
    addPoint(2);

    afficherClassement(1);
    afficherClassement(2);
    addPoint(1);

    afficherClassement(1);
    afficherClassement(2);
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

  static void creationEquipe() {
    // Noms d equipe personnalises
    Scanner nomEquipe1 = new Scanner(System.in);
    System.out.print("Nom equipe 1 : ");
    String equipe1 = nomEquipe1.nextLine();

    classement[0][1] = equipe1;

    Scanner nomEquipe2 = new Scanner(System.in);
    System.out.print("Nom equipe 2 : ");
    String equipe2 = nomEquipe2.nextLine();

    classement[0][2] = equipe2;

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
  static void afficherClassement(int equipe) {
    if (equipe == 1 || equipe == 2) {
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
  }

  static void addPoint(int equipe) {
    if (equipe == 1) {
      pointsEquipe1++;
      String pointsEquipe1str = String.valueOf(pointsEquipe1);
      classement[3][equipe] = pointsEquipe1str;
    } else if (equipe == 2) {
      pointsEquipe2++;
      String pointsEquipe2str = String.valueOf(pointsEquipe2);
      classement[3][equipe] = pointsEquipe2str;
    } else {
      System.out.println("Erreur de syntaxe.");
    }
  }
}
