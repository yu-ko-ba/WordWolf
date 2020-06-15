import java.util.Random;

class WordWolf {
  final String[] players;
  final String[] words;

  private final int wolfNumber;

  private final String wolfWord;
  private final String citizenWord;


  public WordWolf(String[] players, String[] words) {
    this.players = players;
    this.words = words;

    wolfNumber = new Random().nextInt(players.length);

    // ウルフのワードを決める
    wolfWord = words[new Random().nextInt(words.length)];

    // 市民のワードを決める
    while (true) {
      int citizenWordNumber = new Random().nextInt(words.length);

      // ウルフと異なるワードが選ばれたら採用する
      if (!wolfWord.equals(words[citizenWordNumber])) {
        citizenWord = words[citizenWordNumber];
        break;
      }
    }
  }

  // nameがウルフかを返す
  boolean WolfIs(String name) {
      if (name.equals(players[wolfNumber])) {
          return true;
      } else {
          return false;
      }
  }

  // nameのお題を返す
  String MessageFor(String name) {
      String word = citizenWord;
      if (WolfIs(name)) {
          word = wolfWord;
      }

      return "お題は「" + word + "」です";
  }
}
