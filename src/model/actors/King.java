package model.actors;

import model.Log;
import model.deposit.DepositADT;
import model.deposit.DepositListBased;
import readwriteproxy.TreasureRoomDoor;
import model.valuables.Valuable;
import model.treasure.TreasureRoomWrite;

import java.util.Random;

public class King implements Runnable
{
  private TreasureRoomDoor access;
  private DepositADT valuables;

  public King(TreasureRoomDoor access)
  {
    this.access = access;
  }

  @Override public void run()
  {
    Random random = new Random();

    while (true)
    {
      valuables = new DepositListBased();
      int targetWorth = random.nextInt(101) + 50;
      int currentWorth = 0;
      Log.getInstance().log(Thread.currentThread().getName()
          + ": The King wants to throw a party!");
      TreasureRoomWrite treasureRoom = access.acquireWrite();

      DepositADT treasure = treasureRoom.look();
      Log.getInstance().log(Thread.currentThread().getName()
          + ": The King is looking for funds.");

      if (!treasure.isEmpty())
      {
        for (int i = 0; i < treasure.size(); i++)
        {
          Valuable valuable = treasureRoom.retrieve();
          valuables.add(valuable);
          currentWorth += valuable.getWorth();
        }

        if (currentWorth < targetWorth)
        {
          Log.getInstance().log(Thread.currentThread().getName()
              + ": Not enough funds for the party."
              + " Returning all valuables.");
          for (int i = 0; i < valuables.size(); i++)
          {
            treasureRoom.add(valuables.get(i));
          }
        }
        else
        {
          Log.getInstance().log(Thread.currentThread().getName()
              + ": PARTY!!!");
        }
      }
      else
      {
        Log.getInstance().log(Thread.currentThread().getName()
            + ": The treasure room is empty!");
      }

      access.releaseWrite();

      try
      {
        Thread.sleep(3000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
