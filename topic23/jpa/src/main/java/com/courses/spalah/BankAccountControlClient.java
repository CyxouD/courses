package com.courses.spalah;

import java.util.Scanner;

public class BankAccountControlClient {
    public static void main(String[] args) {
        BankCommandParser bankCommandParser = new BankCommandParser();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String command = in.nextLine();
            String[] commandUnits = command.split(" ");
            try {
                bankCommandParser.defineCommand(commandUnits);
            }
            catch (BankCommandParser.NoSuchCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showHelp() {
        System.out.println("add client \u0418\u043d\u043d \u0438\u043c\u044f \u0444\u0430\u043c\u0438\u043b\u0438\u044f\nadd -account \u0418\u043d\u043d_\u043a\u043b\u0438\u0435\u043d\u0442\u0430 [\u0441\u0443\u043c\u043c\u0430_\u0431\u0430\u043b\u0430\u043d\u0441\u0430]\nadd -trans id_\u0441\u0447\u0435\u0442\u0430 \u0441\u0443\u043c\u043c\u0430_\u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438\nfind -client\nfind -account \u0418\u043d\u043d_\u043a\u043b\u0438\u0435\u043d\u0442\u0430\nfind -trans -a id_\u0441\u0447\u0435\u0442\u0430\nfind -trans -c \u0418\u043d\u043d_\u041a\u043b\u0438\u0435\u043d\u0442\u0430\n");
    }
}