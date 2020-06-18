import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        final String[] players;
        final String[] words;
        final Scanner scanner = new Scanner(System.in);

        // プレイヤー名を入力してもらう
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

        // ワードを入力してもらう
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

        // 入力してもらったデータを元にインスタンスを生成する
        WordWolf wordWolf = new WordWolf(players, words);

        // 各プレイヤーにお題を表示する
        String command;
        if ("\\".equals(System.getProperty("file.separator"))) {
            command = "cls";
        } else {
            command = "clear";
        }

        Runtime runtime = Runtime.getRuntime();
        for (String name : wordWolf.players) {
            try {
                runtime.exec(command);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("お題は「" + wordWolf.wordFor(name) + "」です");
            System.out.print("確認したらEnterキーを押してください");
            scanner.nextLine();
        }

        try {
            runtime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // お話タイム
        try {
            Thread.sleep(10 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 答え合わせのお時間
        System.out.print("ウルフは誰ですか？＞");
        String answer = scanner.nextLine();
        if (wordWolf.wolfIs(answer)) {
            System.out.println("市民の勝ち！");
        } else {
            System.out.println("ウルフの勝ち！");
        }

        // 標準入力を閉じる
        scanner.close();
    }
}
