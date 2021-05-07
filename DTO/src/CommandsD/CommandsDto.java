package CommandsD;

import command.Command;

import java.util.List;

public class CommandsDto {
    private final List<Command> commandsBuyList;
    private final List<Command> commandsSellList;
    private final int TotalCycleBuyCommand;
    private final int TotalCycleSellCommand;

    public CommandsDto(List<Command> commandsBuyList, List<Command> commandsSellList, int totalCycleBuyCommand, int totalCycleSellCommand) {
        this.commandsBuyList = commandsBuyList;
        this.commandsSellList = commandsSellList;
        TotalCycleBuyCommand = totalCycleBuyCommand;
        TotalCycleSellCommand = totalCycleSellCommand;
    }

    public List<Command> getCommandsBuyList() {
        return commandsBuyList;
    }

    public List<Command> getCommandsSellList() {
        return commandsSellList;
    }

    public int getTotalCycleBuyCommand() {
        return TotalCycleBuyCommand;
    }

    public int getTotalCycleSellCommand() {
        return TotalCycleSellCommand;
    }

    @Override
    public String toString() {
        return "The list of commands are: " +
                "commandsBuyList: " + commandsBuyList +
                "\n commandsSellList=" + commandsSellList;
    }
}
