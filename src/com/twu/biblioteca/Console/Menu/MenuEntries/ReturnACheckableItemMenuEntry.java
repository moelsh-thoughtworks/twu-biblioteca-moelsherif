package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Checkable.CheckableItem;
import com.twu.biblioteca.Console.Menu.MenuEntries.MenuEntry;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;
import com.twu.biblioteca.Utilities.Utilities;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class ReturnACheckableItemMenuEntry extends MenuEntry {
    private final HashMap<String, CheckableItem> checkablesMap;
    private final CheckableController controllerItems;
    private final String itemName;

    public ReturnACheckableItemMenuEntry(HashMap<String, CheckableItem> checkablesMap, CheckableController controllerItems) {
        this.checkablesMap = checkablesMap;
        this.controllerItems = controllerItems;
        this.itemName = Utilities.getRandomHashMapElement(checkablesMap).getItemName();
    }

    public boolean execute(PrintStream printStream, String input) {
        CheckableItem item = checkablesMap.get(input);
        if (item != null) {
            try {
                controllerItems.returnAnItem(item.getName());
                printStream.println("Thank you for returning the " + itemName);
                return true;
            } catch (ItemAlreadyReturnedException e) {
                e.printStackTrace();
            }
        }
        printStream.println("This is not a valid " + itemName + " for return.");
        return false;
    }


    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        printStream.println("Please type in the name of the " + itemName + " you want to return");
        boolean returnVal=false;
        while (!returnVal) {
            returnVal=execute(printStream,scan.nextLine());
        }
    }
}