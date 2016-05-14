package battleship;
/**
 * This interface defines actions with map to use from class, which implements IContiolLogic
 * @author Nk185
 */
public interface IMap 
{   
    String GetMaps(boolean showEnemyShips);
    String GetSpecifiedMap(MapSettings ms);
    
    MapSettings GetUserMap();

    boolean HitEnemyCell(int x, int y);
    boolean CheckVictory(boolean checkPlayerMap);
    boolean SetUserMap(MapSettings userMap);
    boolean SetEnemyMap(MapSettings enemyMap);
}
