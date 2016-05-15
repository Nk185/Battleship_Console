package battleship;

import java.util.Scanner;

public class UserInterface implements IUser
{
    
    public UserInterface()    
    {
                 
    }
    
    /**
     * Displays user's and enemy's maps
     * @param map two maps in string format;
     */
    @Override
    public void DisplayMap(String map)
    {
        System.out.println("Стан ігрових полів:\n" + map);        
    }
    /**
     * Displays specified message.
     * @param message message to display;
     */
    @Override
    public void ShowMessage(String message)
    {
        System.out.println(message);
    }
    
    
    /**
     * Asks user for a command. 
     * <p>Possible commands:
     * <ul>
     *   <li>"set x y t dr", where t - is a type of ship [1..4] and dr is a direction [left (l), right(r), up(u), down(d)];</li>
     *   <li>"hit x y"</li>
     *   <li>"reset"</li>
     * </ul>
     * @return verified array of strings with command. 
     * <p><i>Example: {"set","2","7","4", "u"}</i>;
     */
    @Override
    public String[] AskCommand()
    {
        boolean success = false;
        String command;
        String[] resCommand;
        
        do
        {
            do
            {
                System.out.println("\nВведіть команду:");        
                command = new Scanner(System.in).nextLine();     
            }
            while ((!command.contains("set")) && (!command.contains("hit")) && (!command.contains("reset")));


            resCommand = command.split(" ");

            switch(resCommand[0])
            {
                case "set":
                    {
                        if (resCommand.length == 5)
                            success = isValidDigit(resCommand[1]) && isValidDigit(resCommand[2]) &&
                                isValidShipType(resCommand[3]) && isValidDirec(resCommand[4]);
                        else
                            success = false;

                        break;
                    }
                case "hit":
                    {
                        if (resCommand.length == 3)
                            success = isValidDigit(resCommand[1]) && isValidDigit(resCommand[2]);
                        else
                            success = false;

                        break;
                    }
                case "reset":
                    {
                        success = true;
                        break;
                    }
            }
            
            if (!success) 
                System.out.println("Неверный формат комманды. Попробуйте снова.");
            
        } while (!success);

        return resCommand;
    }
    
    
    /**
     * Verifies if input string possible to convert to integer.
     * And also if parameter in map range.
     * @param input string line to verify
     * @return true if acceptable, false if not.
     */
    private boolean isValidDigit(String input)
    {
        int i;
        boolean result;

        try
        {
            i = Integer.parseInt(input);
            
            result = (i >= 1) && (i <= 10);
        }
        catch (Exception e) { result = false; }

        return result;
    }
    /**
     * Verifies if input string possible to convert to integer.
     * And also if parameter represent ship length.
     * @param input string line to verify
     * @return true if acceptable, false if not.
     */
    private boolean isValidShipType(String input)
    {
        int i;
        boolean result;

        try
        {
            i = Integer.parseInt(input);
            
            result = (i >= 1) && (i <= 4);
        }
        catch (Exception e) { result = false; }

        return result;
    }
    /**
     * Verifies if input string possible to convert to direction.
     * And also if parameter represent ship direction.
     * @param input string line to verify
     * @return true if acceptable, false if not.
     */
    private boolean isValidDirec(String input)
    {
        return ("l".equals(input)) || ("r".equals(input)) || ("u".equals(input)) || ("d".equals(input));
    }
}
