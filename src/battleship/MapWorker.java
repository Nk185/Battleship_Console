package battleship;

class MapWorker  
{
    // MapSettings (distMap) ALLWAYS WILL BE PASSED BY REFERENCE!!!!!
    public final boolean SetShipByCoord(MapSettings distMap, int xPos, int yPos, int boardCount, String direction)
    {
        boolean status = false;
        boolean isSetUpAllowed = true;

        switch (direction)
        {
            case "l":
                if (xPos - boardCount >= 0)
                    status = true;
                break;
            case "r":
                if (xPos + boardCount <= 11)
                    status = true;
                break;
            case "u":
                if (yPos - boardCount >= 0)
                    status = true;
                break;
            case "d":
                if (yPos + boardCount <= 11)
                    status = true;
                break;
        }

        xPos--;
        yPos--;

        if (status)
        {
            switch (direction)
            {
                case "l":
                {
                    for (int i = xPos; i > xPos - boardCount; i--)
                    {
                        if ((distMap.Map[i][yPos] == ECellStatus.ContainsShip)
                                || (distMap.Map[i][yPos] == ECellStatus.LocatedNearShip))
                        {
                            isSetUpAllowed = false;
                            status = false;
                        }
                    }

                    if (isSetUpAllowed)
                    {
                        for (int i = xPos; i > xPos - boardCount; i--)
                        {
                            distMap.Map[i][yPos] = ECellStatus.ContainsShip;
                            if (yPos + 1 <= 9)
                                distMap.Map[i][yPos + 1] = ECellStatus.LocatedNearShip;
                            if (yPos - 1 >= 0)
                                distMap.Map[i][yPos - 1] = ECellStatus.LocatedNearShip;

                            if (xPos + 1 <= 9)
                                for (int j = yPos - 1; j <= yPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[xPos + 1][j] = ECellStatus.LocatedNearShip;
                                }

                            if (xPos - boardCount >= 0)
                                for (int j = yPos - 1; j <= yPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[xPos - boardCount][j] = ECellStatus.LocatedNearShip;
                                }
                        }
                    }

                    break;
                }
                case "r":
                {
                    for (int i = xPos; i < xPos + boardCount; i++)
                    {
                        if ((distMap.Map[i][yPos] == ECellStatus.ContainsShip)
                                || (distMap.Map[i][yPos] == ECellStatus.LocatedNearShip))
                        {
                            isSetUpAllowed = false;
                            status = false;
                        }
                    }

                    if (isSetUpAllowed)
                    {
                        for (int i = xPos; i < xPos + boardCount; i++)
                        {
                            distMap.Map[i][yPos] = ECellStatus.ContainsShip;
                            if (yPos + 1 <= 9)
                                distMap.Map[i][yPos + 1] = ECellStatus.LocatedNearShip;
                            if (yPos - 1 >= 0)
                                distMap.Map[i][yPos - 1] = ECellStatus.LocatedNearShip;

                            if (xPos + boardCount <= 9)
                                for (int j = yPos - 1; j <= yPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[xPos + boardCount][j] = ECellStatus.LocatedNearShip;
                                }

                            if (xPos - 1 >= 0)
                                for (int j = yPos - 1; j <= yPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[xPos - 1][j] = ECellStatus.LocatedNearShip;
                                }
                        }
                    }

                    break;
                }
                case "u":
                {
                    for (int i = yPos; i > yPos - boardCount; i--)
                    {
                        if ((distMap.Map[xPos][i] == ECellStatus.ContainsShip)
                                || (distMap.Map[xPos][i] == ECellStatus.LocatedNearShip))
                        {
                            isSetUpAllowed = false;
                            status = false;
                        }
                    }

                    if (isSetUpAllowed)
                    {
                        for (int i = yPos; i > yPos - boardCount; i--)
                        {
                            distMap.Map[xPos][i] = ECellStatus.ContainsShip;
                            if (xPos - 1 >= 0)
                                distMap.Map[xPos - 1][i] = ECellStatus.LocatedNearShip;
                            if (xPos + 1 <= 9)
                                distMap.Map[xPos + 1][i] = ECellStatus.LocatedNearShip;

                            if (yPos + 1 <= 9)
                                for (int j = xPos - 1; j <= xPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[j][yPos + 1] = ECellStatus.LocatedNearShip;
                                }

                            if (yPos - boardCount >= 0)
                                for (int j = xPos - 1; j <= xPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[j][yPos - boardCount] = ECellStatus.LocatedNearShip;
                                }
                        }
                    }

                    break;
                }
                case "d":
                {
                    for (int i = yPos; i < yPos + boardCount; i++)
                    {
                        if ((distMap.Map[xPos][i] == ECellStatus.ContainsShip)
                                || (distMap.Map[xPos][i] == ECellStatus.LocatedNearShip))
                        {
                            isSetUpAllowed = false;
                            status = false;
                        }
                    }

                    if (isSetUpAllowed)
                    {
                        for (int i = yPos; i < yPos + boardCount; i++)
                        {
                            distMap.Map[xPos][i] = ECellStatus.ContainsShip;
                            if (xPos - 1 >= 0)
                                distMap.Map[xPos - 1][i] = ECellStatus.LocatedNearShip;
                            if (xPos + 1 <= 9)
                                distMap.Map[xPos + 1][i] = ECellStatus.LocatedNearShip;

                            if (yPos - 1 >= 0)
                                for (int j = xPos - 1; j <= xPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[j][yPos - 1] = ECellStatus.LocatedNearShip;
                                }

                            if (yPos + boardCount <= 9)
                                for (int j = xPos - 1; j <= xPos + 1; j++)
                                {
                                    if (j <= 9 && j >= 0)
                                        distMap.Map[j][yPos + boardCount] = ECellStatus.LocatedNearShip;
                                }
                        }
                    }

                    break;
                }
            }
        }

        return status;
    }
}