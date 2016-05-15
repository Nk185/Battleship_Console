package battleship;

class MapEngine
{

    // MapSettings (distMap) ALLWAYS WILL BE PASSED BY REFERENCE!!!!!
    public static final boolean SetShipByCoord(MapSettings distMap, int xPos, int yPos, int boardCount, String direction)
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

    // fix vertical surrounding
    public static final void SurroundShipWithEmptyCell(int xCoord, int yCoord, MapSettings distMap, char shipDirection)
    {
        if (shipDirection == 'h') // horizontalShip
        {
            int lastBoardLeft = -1;
            int lastBoardRight = -1;

            for (int i = xCoord; i <= xCoord + 5; i++)
            {
                if (i <= 9)
                {
                    if (distMap.Map[i][yCoord] == ECellStatus.Hited)
                    {
                        if (yCoord - 1 >= 0)
                            distMap.Map[i][yCoord - 1] = ECellStatus.Empty;
                        if (yCoord + 1 <= 9)
                            distMap.Map[i][yCoord + 1] = ECellStatus.Empty;
                    } else
                    {
                        lastBoardRight = i;
                        break;
                    }
                } else
                {
                    lastBoardRight = -1;
                    break;
                }
            }

            for (int i = xCoord; i >= xCoord - 5; i--)
            {
                if (i >= 0)
                {
                    if (distMap.Map[i][yCoord] == ECellStatus.Hited)
                    {
                        if (yCoord - 1 >= 0)
                            distMap.Map[i][yCoord - 1] = ECellStatus.Empty;
                        if (yCoord + 1 <= 9)
                            distMap.Map[i][yCoord + 1] = ECellStatus.Empty;
                    } else
                    {
                        lastBoardLeft = i;
                        break;
                    }
                } else
                {
                    lastBoardLeft = -1;
                    break;
                }
            }

            if (lastBoardLeft != -1)
            {
                distMap.Map[lastBoardLeft][yCoord] = ECellStatus.Empty;

                if (yCoord - 1 >= 0)
                    distMap.Map[lastBoardLeft][yCoord - 1] = ECellStatus.Empty;

                if (yCoord + 1 <= 9)
                    distMap.Map[lastBoardLeft][yCoord + 1] = ECellStatus.Empty;
            }

            if (lastBoardRight != -1)
            {
                distMap.Map[lastBoardRight][yCoord] = ECellStatus.Empty;

                if (yCoord - 1 >= 0)
                    distMap.Map[lastBoardRight][yCoord - 1] = ECellStatus.Empty;

                if (yCoord + 1 <= 9)
                    distMap.Map[lastBoardRight][yCoord + 1] = ECellStatus.Empty;
            }

        } else if (shipDirection == 'v')
        {
            int lastBoardTop = -1;
            int lastBoardBottom = -1;

            for (int i = yCoord; i <= yCoord + 5; i++)
            {
                if (i <= 9)
                {
                    if (distMap.Map[xCoord][i] == ECellStatus.Hited)
                    {
                        if (xCoord - 1 >= 0)
                            distMap.Map[xCoord - 1][i] = ECellStatus.Empty;
                        if (xCoord + 1 <= 9)
                            distMap.Map[xCoord + 1][i] = ECellStatus.Empty;
                    } else
                    {
                        lastBoardBottom = i;
                        break;
                    }
                } else
                {
                    lastBoardBottom = -1;
                    break;
                }
            }

            if (lastBoardBottom != -1)
            {
                distMap.Map[xCoord][lastBoardBottom] = ECellStatus.Empty;

                if (xCoord - 1 >= 0)
                    distMap.Map[xCoord - 1][lastBoardBottom] = ECellStatus.Empty;

                if (xCoord + 1 <= 9)
                    distMap.Map[xCoord + 1][lastBoardBottom] = ECellStatus.Empty;
            }

            for (int i = yCoord; i >= yCoord - 5; i--)
            {
                if (i >= 0)
                {
                    if (distMap.Map[xCoord][i] == ECellStatus.Hited)
                    {
                        if (xCoord - 1 >= 0)
                            distMap.Map[xCoord - 1][i] = ECellStatus.Empty;
                        if (xCoord + 1 <= 9)
                            distMap.Map[xCoord + 1][i] = ECellStatus.Empty;
                    } else
                    {
                        lastBoardTop = i;
                        break;
                    }
                } else
                {
                    lastBoardTop = -1;
                    break;
                }
            }

            if (lastBoardTop != -1)
            {
                distMap.Map[xCoord][lastBoardTop] = ECellStatus.Empty;

                if (xCoord - 1 >= 0)
                    distMap.Map[xCoord - 1][lastBoardTop] = ECellStatus.Empty;

                if (xCoord + 1 <= 9)
                    distMap.Map[xCoord + 1][lastBoardTop] = ECellStatus.Empty;
            }
        }
    }

    public static final boolean isOneBoardShip(int xCoord, int yCoord, MapSettings map)
    {
        boolean isOneBoardShip = false;

        if (xCoord - 1 >= 0)
        {
            isOneBoardShip = map.Map[xCoord - 1][yCoord] == ECellStatus.LocatedNearShip || map.Map[xCoord - 1][yCoord] == ECellStatus.Empty;
        } else if (xCoord - 1 < 0)
            isOneBoardShip = true;

        if (isOneBoardShip)
            if (xCoord + 1 <= 9)
            {
                isOneBoardShip = map.Map[xCoord + 1][yCoord] == ECellStatus.LocatedNearShip || map.Map[xCoord + 1][yCoord] == ECellStatus.Empty;
            } else if (xCoord + 1 > 9)
                isOneBoardShip = true;

        if (isOneBoardShip)
            if (yCoord - 1 >= 0)
            {
                isOneBoardShip = map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty;
            } else if (yCoord - 1 < 0)
                isOneBoardShip = true;

        if (isOneBoardShip)
            if (yCoord + 1 <= 9)
            {
                isOneBoardShip = map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty;
            } else if (yCoord + 1 > 9)
                isOneBoardShip = true;

        return isOneBoardShip;
    }

    public static final char getShipDirection(int xCoord, int yCoord, MapSettings map)
    {
        boolean isHorizontalShip = false;

        if ((xCoord < 9) && (xCoord > 0) && (yCoord < 9) && (yCoord > 0))
        {
            isHorizontalShip = (map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty)
                    && (map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty);
        } else if (xCoord == 0)
        {
            if (yCoord > 0 && yCoord < 9)
            {
                isHorizontalShip = (map.Map[xCoord + 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord + 1][yCoord] == ECellStatus.Hited)
                        && ((map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty)
                        && (map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty));
            } else if (yCoord == 0)
            {
                isHorizontalShip = (map.Map[xCoord + 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord + 1][yCoord] == ECellStatus.Hited)
                        && (map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty);
            } else if (yCoord == 9)
            {
                isHorizontalShip = (map.Map[xCoord + 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord + 1][yCoord] == ECellStatus.Hited)
                        && (map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty);
            }
        } else if (xCoord == 9)
        {
            if (yCoord > 0 && yCoord < 9)
            {
                isHorizontalShip = (map.Map[xCoord - 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord - 1][yCoord] == ECellStatus.Hited)
                        && ((map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty)
                        && (map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty));
            } else if (yCoord == 0)
            {
                isHorizontalShip = (map.Map[xCoord - 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord - 1][yCoord] == ECellStatus.Hited)
                        && (map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty);
            } else if (yCoord == 9)
            {
                isHorizontalShip = (map.Map[xCoord - 1][yCoord] == ECellStatus.ContainsShip || map.Map[xCoord - 1][yCoord] == ECellStatus.Hited)
                        && (map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty);
            }
        } else if (yCoord == 0)
        {
            isHorizontalShip = map.Map[xCoord][yCoord + 1] == ECellStatus.LocatedNearShip
                    || map.Map[xCoord][yCoord + 1] == ECellStatus.Empty;
        } else if (yCoord == 9)
        {
            isHorizontalShip = map.Map[xCoord][yCoord - 1] == ECellStatus.LocatedNearShip
                    || map.Map[xCoord][yCoord - 1] == ECellStatus.Empty;
        }

        return (isHorizontalShip == true ? 'h' : 'v');
    }

    public static final boolean isShipDestroyed(int xCoord, int yCoord, MapSettings map, char shipDirection)
    {
        boolean isDestroyed = true;
        
        if (shipDirection == 'h')
        {
            int offset = 0;

            while ((xCoord + offset <= 9) && map.Map[xCoord + offset][yCoord] != ECellStatus.LocatedNearShip)
            {
                if (map.Map[xCoord + offset][yCoord] == ECellStatus.ContainsShip)
                {
                    isDestroyed = false;
                    break;
                } else
                    offset++;
            }

            offset = 0;

            if (isDestroyed)
            {
                while ((xCoord - offset >= 0) && map.Map[xCoord - offset][yCoord] != ECellStatus.LocatedNearShip)
                {
                    if (map.Map[xCoord - offset][yCoord] == ECellStatus.ContainsShip)
                    {
                        isDestroyed = false;
                        break;
                    } else
                        offset++;
                }
            }

        } else if (shipDirection == 'v') // include empty in alg.
        {
            int offset = 0;

            while ((yCoord + offset <= 9) && map.Map[xCoord][yCoord + offset] != ECellStatus.LocatedNearShip)
            {
                if (map.Map[xCoord][yCoord + offset] == ECellStatus.ContainsShip)
                {
                    isDestroyed = false;
                    break;
                } else
                    offset++;
            }

            offset = 0;

            if (isDestroyed)
            {
                while ((yCoord - offset >= 0) && map.Map[xCoord][yCoord - offset] != ECellStatus.LocatedNearShip)
                {
                    if (map.Map[xCoord][yCoord - offset] == ECellStatus.ContainsShip)
                    {
                        isDestroyed = false;
                        break;
                    } else
                        offset++;
                }
            }
        }

        return isDestroyed;
    }
}