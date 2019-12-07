package MenuConsola;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainMenu extends MenuBuilder {
    private static final Logger stdout = LoggerFactory.getLogger("CONSOLE_OUT");

    @Override
    protected void menuDisplay() {
        super.menuDisplay();
    }

    @Override
    public int getUserChoice() {
        super.getUserChoice();
        return userChoice;
    }

    private static List<String> setMenuOptions() {

        String menuHeadLine = "MAIN MENU";
        String menuOption1 = "HOLIDAY PLANNING";
        String menuOption2 = "HOLIDAYS REVIEW";
        String menuOption3 = "EMPLOYEES MANAGING";
        String menuOption4 = "CONFIGURATION";

        menuOptions.add(menuHeadLine);
        menuOptions.add(menuOption1);
        menuOptions.add(menuOption2);
        menuOptions.add(menuOption3);
        menuOptions.add(menuOption4);

        return menuOptions;
    }

    static void executeUserChoice() {

        menuOptions.clear();

        switch (userChoice) {
            case 0:
                break;
            case 1:
                HolidaysPlanningMenu.main();
                break;
            case 2:
                HolidaysReviewMenu.main();
                break;
            case 3:
                EmployeesManagingMenu.main();
                break;
            case 4:
                ConfigurationMenu.main();
                break;
//                default:
//                    stdout.info("Wrong number - try again");
        }

    }

    public static void main(String[] args) {

        setMenuOptions();
        MainMenu menu = new MainMenu();
        menu.menuDisplay();
        menu.getUserChoice();
        executeUserChoice();


    }
}