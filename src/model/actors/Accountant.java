package model.actors;

import model.deposit.DepositADT;
import model.deposit.DepositListBased;
import readwriteproxy.TreasureRoomDoor;
import model.treasure.TreasureRoomRead;
import model.Log;

public class Accountant implements Runnable
{
  private TreasureRoomDoor access;
  private DepositADT valuables;

  public Accountant(TreasureRoomDoor access)
  {
    this.access = access;
  }

  @Override public void run()
  {
    while (true)
    {
      valuables = new DepositListBased();
      int valuesWorth = 0;

      Log.getInstance().log(Thread.currentThread().getName()
          + ": An accountant wants to check to values in the"
              + " treasure room");
      TreasureRoomRead treasureRoom = access.acquireRead();

      valuables = treasureRoom.look();
      Log.getInstance().log(Thread.currentThread().getName()
          + ": An accountant is calculating the valuables'"
          + " worth.");

      if (!valuables.isEmpty())
      {
        try
        {
          Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }

        for (int i = 0; i < valuables.size(); i++)
        {
          valuesWorth += valuables.get(i).getWorth();
        }
        Log.getInstance().log(Thread.currentThread().getName()
            + ": The total worth of the treasure is: " + valuesWorth);
        }
      else
      {
        Log.getInstance().log(Thread.currentThread().getName()
            + ": The treasure room is empty!");
      }

      access.releaseRead();

      try
      {
        Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
