import domain.Account;
import domain.Client;
import domain.Transaction;
import service.BankAccountControlService;

import java.util.List;

public class BankCommandParser {
    private final int NUMBER_ADD_CLIENT_PARAM = 3;
    private final int NUMBER_ADD_TRANSACTION_PARAM = 2;
    private final int NUMBER_FIND_CLIENT_PARAM = 0;
    private final int NUMBER_FIND_ACCOUNT_PARAM = 1;
    private final int NUMBER_FIND_TRANSACTION_PARAM = 2;
    private final int NUMBER_ADD_ACCOUNT_MAX_PARAM = 2;
    private final int NUMBER_ADD_ACCOUNT_MIN_PARAM = 1;
    private final int COMMAND_NUMBER = 2;
    private final String DEFAULT_BALANCE = "0";
    private BankAccountControlService bankAccountControlService = new BankAccountControlService();
    private BankCommandProcessor bankCommandProcessor = new BankCommandProcessor();

    public void defineCommand(String[] commandUnits) throws NoSuchCommandException {
        String mainCommand = commandUnits[IndexCommandCorrespondence.MAIN_COMMAND.getIndex()];
        if (mainCommand.equals("add")) {
            try {
                this.processAddCommand(commandUnits);
            }
            catch (WrongNumberOfParameters wrongNumberOfParameters) {
                System.out.println(wrongNumberOfParameters.getMessage());
            }
        } else if (mainCommand.equals("find")) {
            try {
                this.processFindCommand(commandUnits);
            }
            catch (WrongNumberOfParameters wrongNumberOfParameters) {
                System.out.println(wrongNumberOfParameters.getMessage());
            }
        } else if (mainCommand.equals("--help")) {
            BankAccountControlClient.showHelp();
        } else {
            throw new NoSuchCommandException("\u041d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430 " + mainCommand);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void processAddCommand(String[] commandUnits) throws NoSuchCommandException, WrongNumberOfParameters {
        String additionalCommand = commandUnits[IndexCommandCorrespondence.ADDITIONAL_COMMAND.getIndex()];
        int commandParamsNumber = commandUnits.length - 2;
        if (additionalCommand.equals("-client")) {
            if (commandParamsNumber != 3) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f \u043a\u043b\u0438\u0435\u043d\u0442\u0430");
            Client client = this.processAddClientCommand(commandUnits);
            this.bankAccountControlService.addClient(client);
            return;
        } else if (additionalCommand.equals("-account")) {
            if (1 > commandParamsNumber || commandParamsNumber > 2) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f \u0441\u0447\u0435\u0442\u0430");
            Account account = this.processAddAccountCommand(commandUnits, commandParamsNumber);
            this.bankAccountControlService.addStartBalance(account);
            return;
        } else {
            if (!additionalCommand.equals("-trans")) throw new NoSuchCommandException("\u041d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430 " + additionalCommand);
            if (commandParamsNumber != 2) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438");
            Transaction transaction = this.processAddTransactionCommand(commandUnits);
            try {
                this.bankAccountControlService.addTransaction(transaction);
                return;
            }
            catch (BankAccountControlService.WithdrawMoreThanOnAccountException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Client processAddClientCommand(String[] commandUnits) {
        Client client = this.bankCommandProcessor.formClientToAdd(commandUnits[IndexCommandCorrespondence.FIRST_PARAM.getIndex()], commandUnits[IndexCommandCorrespondence.SECOND_PARAM.getIndex()], commandUnits[IndexCommandCorrespondence.THIRD_PARAM.getIndex()]);
        return client;
    }

    private Account processAddAccountCommand(String[] commandUnits, int commandParamsNumber) {
        String clientInn = commandUnits[IndexCommandCorrespondence.FIRST_PARAM.getIndex()];
        String accountBalance = commandParamsNumber == 2 ? commandUnits[IndexCommandCorrespondence.SECOND_PARAM.getIndex()] : "0";
        Account account = this.bankCommandProcessor.formAccountToAdd(clientInn, accountBalance);
        return account;
    }

    private Transaction processAddTransactionCommand(String[] commandUnits) {
        Transaction transaction = this.bankCommandProcessor.formTransactionToAdd(commandUnits[IndexCommandCorrespondence.FIRST_PARAM.getIndex()], commandUnits[IndexCommandCorrespondence.SECOND_PARAM.getIndex()]);
        return transaction;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void processFindCommand(String[] commandUnits) throws NoSuchCommandException, WrongNumberOfParameters {
        String additionalCommand = commandUnits[IndexCommandCorrespondence.ADDITIONAL_COMMAND.getIndex()];
        int commandParamsNumber = commandUnits.length - 2;
        List results = null;
        if (additionalCommand.equals("-client")) {
            if (commandParamsNumber != 0) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0432\u0441\u0435\u0445 \u043a\u043b\u0438\u0435\u043d\u0442\u043e\u0432");
            results = this.bankAccountControlService.getClients();
        } else if (additionalCommand.equals("-account")) {
            if (commandParamsNumber != 1) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0441\u0447\u0435\u0442\u0430");
            int clientInn = Integer.parseInt(commandUnits[IndexCommandCorrespondence.FIRST_PARAM.getIndex()]);
            results = this.bankAccountControlService.findAccountsOfClient(clientInn);
        } else {
            if (!additionalCommand.equals("-trans")) throw new NoSuchCommandException("\u041d\u0435\u0432\u0435\u0440\u043d\u0430\u044f \u043a\u043e\u043c\u0430\u043d\u0434\u0430 " + additionalCommand);
            if (commandParamsNumber != 2) throw new WrongNumberOfParameters("\u041d\u0435\u0432\u0435\u0440\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u043e\u0432 \u0434\u043b\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438");
            String firstParam = commandUnits[IndexCommandCorrespondence.FIRST_PARAM.getIndex()];
            int transactionCriteria = Integer.parseInt(commandUnits[IndexCommandCorrespondence.SECOND_PARAM.getIndex()]);
            if (firstParam.equals("-a")) {
                results = this.bankAccountControlService.findTransactionsOfAccount(transactionCriteria);
            } else if (firstParam.equals("-c")) {
                results = this.bankAccountControlService.findTransactionsOfClient(transactionCriteria);
            } else {
                System.out.println("\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440 \u0434\u043b\u044f \u043f\u043e\u0438\u0441\u043a\u0430 \u0442\u0440\u0430\u0437\u0430\u043a\u0446\u0438\u0439 - {-a,-c} ]");
            }
        }
        System.out.println(results);
    }

private class WrongNumberOfParameters
extends BankCommandParser.CommandSyntaxError {
    public WrongNumberOfParameters(String s) {
        super(s);
    }
}

public class NoSuchCommandException
extends CommandSyntaxError {
    public NoSuchCommandException(String s) {
        super(s);
    }
}

private static enum IndexCommandCorrespondence {
    MAIN_COMMAND(0),
    ADDITIONAL_COMMAND(1),
    FIRST_PARAM(2),
    SECOND_PARAM(3),
    THIRD_PARAM(4);
    
    private int commandIndex;

    private IndexCommandCorrespondence(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    public int getIndex() {
        return this.commandIndex;
    }
}

public class CommandSyntaxError
extends Exception {
    CommandSyntaxError(String s) {
        super(s + ". \u041f\u043e\u0441\u043c\u043e\u0442\u0440\u0438\u0442\u0435 --help");
    }
}



}