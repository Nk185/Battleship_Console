package battleship;

import java.util.Random;

public class Map implements IMap //This is Model in MVP pattern
{

    private MapSettings _userMap;
    private MapSettings _enemyMap;

    @Override
    public String GetMaps(boolean showEnemyShips)
    {
        String res = "";

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (_userMap.Map[j][i] == ECellStatus.ContainsShip)
                    res += "[S]";
                else if (_userMap.Map[j][i] == ECellStatus.Hited)
                    res += "[X]";
                else if (_userMap.Map[j][i] == ECellStatus.Empty)
                    res += "[*]";
                else
                    res += "[ ]";
            }

            res += "   |   ";
            for (int j = 0; j < 10; j++)
            {
                if (_enemyMap.Map[j][i] == ECellStatus.Empty)
                    res += "[*]";
                else if (_enemyMap.Map[j][i] == ECellStatus.Hited)
                    res += "[X]";
                else if (showEnemyShips &&_enemyMap.Map[j][i] == ECellStatus.ContainsShip)
                    res += "[S]";
                else
                    res += "[ ]";
            }

            res += "\n";
        }

        return res;
    }

    @Override
    public String GetSpecifiedMap(MapSettings ms)
    {
        String res = "";

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (ms.Map[j][i] == ECellStatus.ContainsShip)
                    res += "[S]";
                else if (ms.Map[j][i] == ECellStatus.LocatedNearShip)
                    res += "[-]";
                else
                    res += "[ ]";
            }
            res += "\n";
        }

        return res;
    }

    @Override
    public MapSettings GetUserMap()
    {
        return this._userMap;
    }

    @Override
    public boolean HitEnemyCell(int x, int y) // returns true if we hit enemy ship
    {
        if (x <= 10 && y <= 10)
        {
            x--;
            y--;

            switch (this._enemyMap.Map[x][y])
            {
                case ContainsShip:
                {
                    this._enemyMap.Map[x][y] = ECellStatus.Hited;

                    if (MapEngine.isOneBoardShip(x, y, this._enemyMap))
                        MapEngine.SurroundShipWithEmptyCell(x, y, this._enemyMap, 'h');
                    else
                        CheckShipOnDistruction(x, y, this._enemyMap);

                    return true;
                }
                case ClosedEmpty:
                case LocatedNearShip:
                {
                    this._enemyMap.Map[x][y] = ECellStatus.Empty;
                    return false;
                }
                case Empty:
                    return false;
                case Hited:
                    return false;
                default:
                    return false;
            }
        } else
            return false;
    }

    // Total 20 cells supposed to be hited for win
    @Override
    public boolean CheckVictory(boolean checkPlayerMap)
    {
        byte cellsHited = 0;

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (checkPlayerMap)
                {
                    if (this._userMap.Map[j][i] == ECellStatus.Hited)
                        cellsHited++;
                } else if (this._enemyMap.Map[j][i] == ECellStatus.Hited)
                    cellsHited++;
            }
        }

        return (cellsHited == 20);
    }

    /**
     * Sets user map for use it in future.
     *
     * @param userMap Defines user map.
     * @return true if seted-up successfuly, or false if not.
     */
    @Override
    public boolean SetUserMap(MapSettings userMap)
    {
        try
        {
            this._userMap = userMap;
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     * Sets enemy map for use it in future.
     *
     * @param enemyMap Defines enemy map.
     * @return true if seted-up successfuly, or false if not.
     */
    @Override
    public boolean SetEnemyMap(MapSettings enemyMap)
    {
        try
        {
            this._enemyMap = enemyMap;
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    private void CheckShipOnDistruction(int x, int y, MapSettings distMap)
    {
        if (MapEngine.getShipDirection(x, y, distMap) == 'h')
        {
            if (MapEngine.isShipDestroyed(x, y, distMap, 'h'))            
                MapEngine.SurroundShipWithEmptyCell(x, y, distMap, 'h');
        }
        else if (MapEngine.getShipDirection(x, y, distMap) == 'v')
        {
            if (MapEngine.isShipDestroyed(x, y, distMap, 'v'))            
                MapEngine.SurroundShipWithEmptyCell(x, y, distMap, 'v');
        }
    }
}
