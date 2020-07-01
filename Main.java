import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        final String[] players;
        final String[] words;
        final Scanner scanner = new Scanner(System.in);

        // デバッグしたいときは、$ java Main debug と実行する
        boolean debug = false;
        try {
            if (args[0].equals("debug")) {
                debug = true;
            }
        } catch (Exception e) {
        }

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
            // キーボードからの入力が"y"か"Y"だったときに無限ループを抜ける
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
            // キーボードからの入力が"y"か"Y"だったときに無限ループを抜ける
            if (answer.equals("y") | answer.equals("Y")) {
                words = inArray;
                break;
            }
        }

        // 入力してもらったデータを元にインスタンスを生成する
        WordWolf wordWolf = new WordWolf(players, words);

        if (debug) {
            System.out.println(wordWolf.wolfNumber);
        }

        // OSに合わせて画面の表示をクリアするコマンドを用意する
        String[] command;
        if ("\\".equals(System.getProperty("file.separator"))) {
            command = new String[]{"cmd", "/c", "cls"};
        } else {
            command = new String[]{"bash", "-c", "clear"};
        }
        ProcessBuilder clearProcess = new ProcessBuilder(command).inheritIO();

        // 各プレイヤーにお題を表示する
        for (String name : wordWolf.players) {
            // 出力内容をクリアする
            try {
                clearProcess.start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(name + "さんのお題を表示します");
            System.out.print(name + "さんはEnterキーを押してください");
            scanner.nextLine();

            System.out.println("お題は「" + wordWolf.wordFor(name) + "」です");
            System.out.print("確認したらEnterキーを押してください");
            scanner.nextLine();
        }

        try {
            clearProcess.start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // お話タイム
        try {
            int sleepSeconds = 5 * 60;
            if (debug) {
                sleepSeconds = 1;
            }
            for (int i = 0; i < sleepSeconds; i++) {
                int timeRemaining = sleepSeconds - i;
                System.out.println("残り時間は" + (timeRemaining / 60) + "分" + (timeRemaining % 60) + "秒です");
                Thread.sleep(1000);
                clearProcess.start().waitFor();
            }
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
