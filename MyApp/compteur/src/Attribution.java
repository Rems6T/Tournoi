import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Attribution {

  static String[] tableau_participants;

  public static void main(String[] args) {
    Scanner nbr_participants = new Scanner(System.in);
    System.out.print("Nombre de participants : ");
    int participants = nbr_participants.nextInt();

    enregistrerParticipants(participants);

    melangerTableau(tableau_participants); // voir la méthode en bas

    afficherNomParticipants();
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
  static void melangerTableau(String[] shuffle_list) {
    Random rnd = ThreadLocalRandom.current();
    for (int i = shuffle_list.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);

      String a = shuffle_list[index];
      shuffle_list[index] = shuffle_list[i];
      shuffle_list[i] = a;
    }
  }

  // Afficher toutes les informations du tableau
  static void tableauDesScores() {}

  // Affiche le nom des entrées
  static void afficherNomParticipants() {
    for (int i = 0; i < tableau_participants.length; i++) {
      System.out.println(tableau_participants[i]);
    }
  }
}
