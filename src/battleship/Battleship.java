package battleship;

public class Battleship
{
    public static void main(String[] args)
    {
        IMap map            = new Map();
        IUser user          = new UserInterface();
        IEnemyLogic enemy   = new EnemyLogic();
        IControlLogic logic = new ControlLogic(user, map, enemy);
        
        logic.StartTheGame();
    }    
}
