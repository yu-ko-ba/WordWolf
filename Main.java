import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        final String[] players;
        final String[] words;
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("プレイヤー名を,（カンマ）区切りで入力してください＞");
            String[] inArray = scanner.nextLine().split(",");

            System.out.print("プレイヤーは" + inArray[0]);
            for (String i : inArray) {
                if (i.equals(inArray[0])) {
                    continue;
                }
                System.out.print("、" + i);
            }
            System.out.print("ですね？(y/N)");
            String answer = scanner.nextLine();
            if (answer.equals("y") | answer.equals("Y")) {
                players = inArray;
                break;
            }
        }

        while (true) {
            System.out.print("ワードを,（カンマ）区切りで入力してください＞");
            String[] inArray = scanner.nextLine().split(",");

            System.out.print("ワードは" + inArray[0]);
            for (String i : inArray) {
                if (i.equals(inArray[0])) {
                    continue;
                }
                System.out.print("、" + i);
            }
            System.out.print("でいいですか？(y/N)");
            String answer = scanner.nextLine();
            if (answer.equals("y") | answer.equals("Y")) {
                words = inArray;
                break;
            }
        }

        WordWolf wordWolf = new WordWolf(players, words);

        scanner.close();
    }
}
