package battleship;

public interface IUser
{    
    void ShowMessage(String message);
    void DisplayMap(String map);
    
    String[] AskCommand();
}
