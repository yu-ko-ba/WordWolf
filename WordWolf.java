import java.util.Random;

class WordWolf {
  String[] players;
  String[] words;

  int wolfNumber;

  String wolfWord;
  String citizenWord;


  public WordWolf(String[] players, String[] words) {
    this.players = players;
    this.words = words;

    wolfNumber = new Random().nextInt(players.length);

    wolfWord = words[new Random().nextInt(words.length)];
    while (true) {
      int citizenWordNumber = new Random().nextInt(words.length);
      if (!wolfWord.equals(words[citizenWordNumber])) {
        citizenWord = words[citizenWordNumber];
        break;
      }
    }
  }
}
