package battleship;

public class ControlLogic implements IControlLogic
{

    private final IUser       _view;
    private final IMap        _map;
    private final IEnemyLogic _enemy;

    public ControlLogic(IUser _view, IMap _map, IEnemyLogic _enemy)
    {
        this._view  = _view;
        this._map   = _map;
        this._enemy = _enemy;
    }

    @Override
    public void StartTheGame()
    {
        MapSettings userMap = new MapSettings();
        MapSettings enemyMap;
        boolean restoreGame;

        do
        {            
            for (int i = 0; i < 10; i++) 
                for (int j = 0; j < 10; j++)
                    userMap.Map[i][j] = ECellStatus.ClosedEmpty;
            restoreGame = true;
            userMap     = AskForUserMap();

            if (_map.SetUserMap(userMap))            
                _view.DisplayMap(_map.GetSpecifiedMap(userMap));
            else
            {
                _view.ShowMessage("\nЩось пішло не так... Налаштуйте мапу знову."); 
                continue;
            }


            enemyMap = _enemy.GenerateEnemyMap();
            if (_map.SetEnemyMap(enemyMap))
            {
                _view.ShowMessage("Противник готовий до бою! Нехай почнеться гра!!!");
                _view.DisplayMap(_map.GetMaps(false));
            }
            else
            {
                _view.ShowMessage("Щось пішло не так... Налаштуйте мапу знову.");
                continue;
            }

            restoreGame = PlayGame();

        } while (restoreGame);
    }

    private MapSettings AskForUserMap()
    {
        MapSettings ms       = new MapSettings();
        MapWorker mw         = new MapWorker();
        byte oneBoardShips   = 0; // Total count 4
        byte twoBoardShips   = 0; // Total count 3
        byte threeBoardShips = 0; // Total count 2
        byte fourBoardShips  = 0; // Total count 1
        String[] userCommand;

        for (int i = 0; i < 10; i++) 
            for (int j = 0; j < 10; j++)
                ms.Map[i][j] = ECellStatus.ClosedEmpty;

        while ((oneBoardShips < 4) || (twoBoardShips < 3) || (threeBoardShips < 2)
                || (fourBoardShips < 1))
        {
            _view.ShowMessage("\n====| Встановіть свої кораблі |====");
            _view.DisplayMap(_map.GetSpecifiedMap(ms));
            _view.ShowMessage("Однопалубних кораблів залишилось: " + (4 - oneBoardShips)
                    + "\nДвопалубних кораблів залишилось: " + (3 - twoBoardShips)
                    + "\nТрипалубних кораблів залишилось: " + (2 - threeBoardShips)
                    + "\nЧотирипалубних кораблів залишилось: " + (1 - fourBoardShips));

            userCommand = _view.AskCommand();
            if (userCommand[0].equals("set"))
            {
                switch (userCommand[3])
                {
                    case "1":
                    {
                        if (oneBoardShips < 4)
                        {
                            if (mw.SetShipByCoord(ms, Integer.parseInt(userCommand[1]), Integer.parseInt(userCommand[2]), Integer.parseInt(userCommand[3]), userCommand[4]))
                                oneBoardShips++;
                            else
                                _view.ShowMessage("Хибні параметри команди set. Можливо, ви намагаєтесь встановити корабель за границями поля"
                                        + " або занадто близько до іншого корабля.");
                        } else
                            _view.ShowMessage("Усі доступні кораблі цього типу вже встановлені.");
                        break;
                    }
                    case "2":
                    {
                        if (twoBoardShips < 3)
                        {
                            if (mw.SetShipByCoord(ms, Integer.parseInt(userCommand[1]), Integer.parseInt(userCommand[2]), Integer.parseInt(userCommand[3]), userCommand[4]))
                                twoBoardShips++;
                            else
                                _view.ShowMessage("Хибні параметри команди set. Можливо, ви намагаєтесь встановити корабель за границями поля"
                                        + " або занадто близько до іншого корабля.");
                        } else
                            _view.ShowMessage("Усі доступні кораблі цього типу вже встановлені.");
                        break;
                    }
                    case "3":
                    {
                        if (threeBoardShips < 2)
                        {
                            if (mw.SetShipByCoord(ms, Integer.parseInt(userCommand[1]), Integer.parseInt(userCommand[2]), Integer.parseInt(userCommand[3]), userCommand[4]))
                                threeBoardShips++;
                            else
                                _view.ShowMessage("Неверные параметры комманды set. Возможно, вы пытаетесь построить корабль за пределами поля"
                                        + " или слишком близко к другому кораблю.");
                        } else
                            _view.ShowMessage("Усі доступні кораблі цього типу вже встановлені.");
                        break;
                    }
                    case "4":
                    {
                        if (fourBoardShips < 1)
                        {
                            if (mw.SetShipByCoord(ms, Integer.parseInt(userCommand[1]), Integer.parseInt(userCommand[2]), Integer.parseInt(userCommand[3]), userCommand[4]))
                                fourBoardShips++;
                            else
                                _view.ShowMessage("Неверные параметры комманды set. Возможно, вы пытаетесь построить корабль за пределами поля"
                                        + " или слишком близко к другому кораблю.");
                        } else
                            _view.ShowMessage("Усі доступні кораблі цього типу вже встановлені.");
                        break;
                    }
                }
            } else if (userCommand[0].equals("restore"))
            {
                oneBoardShips   = 0;
                twoBoardShips   = 0;
                threeBoardShips = 0;
                fourBoardShips  = 0;

                for (int i = 0; i < 10; i++) 
                    for (int j = 0; j < 10; j++)
                        ms.Map[i][j] = ECellStatus.ClosedEmpty;

                _view.ShowMessage("Налаштування мапи та прогрес були скинуті.");
            } else
                _view.ShowMessage("Хибна команда. Вам необхідно встановити поле перед початком гри.");
        }

        return ms;
    }

    private boolean PlayGame() // Returns "true" if game was restored
    {
        boolean isVictory     = false; // true if User have won
        boolean isRestored    = false;
        boolean isStillInGame = true; // false if PC have won
        boolean isUserTurn    = true;
        String[] userCommand;

        do
        {
            do                        
            {
                userCommand = _view.AskCommand();

                switch (userCommand[0])
                {
                    case "hit":
                    {
                        isUserTurn = _map.HitEnemyCell(Integer.parseInt(userCommand[1]), Integer.parseInt(userCommand[2]));
                        isVictory  = _map.CheckVictory(false);

                        _view.DisplayMap(_map.GetMaps(false));

                        break;
                    }
                    case "restore":
                    {
                        isUserTurn = false;
                        isRestored = true;

                        break;
                    }
                    default:                    
                        _view.ShowMessage("Мапу вже налаштовано, ви не можете встановити корабель. Щоб почати спочатку, введіть \"restore\"");
                        break;                    
                }

            } while (isUserTurn && !isVictory);

            if (!isRestored && !isVictory)
            {
                _view.ShowMessage("Зачекайте, ходить комп'ютер...");
                _enemy.Fire(_map.GetUserMap());
                _view.DisplayMap(_map.GetMaps(false));
                isStillInGame = !_map.CheckVictory(true);
            }

        } while (!isVictory && !isRestored && isStillInGame);

        if (!isStillInGame)
        {
            _view.ShowMessage("Комп'ютер виграв!!! \nОсь, де були кораблі супротивника:");
            _view.DisplayMap(_map.GetMaps(true));
        }
        if (isVictory)
            _view.ShowMessage("Ви виграли!!!");
        if (isRestored)
            _view.ShowMessage("Налаштування мапи та прогрес були скинуті.\n");

        return isRestored;
    }

}
