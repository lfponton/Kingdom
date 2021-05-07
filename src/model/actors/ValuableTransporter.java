package model.actors;

import model.Log;
import model.deposit.DepositADT;
import model.deposit.DepositSafeADT;
import model.deposit.DepositListBased;
import readwriteproxy.TreasureRoomDoor;
import model.treasure.TreasureRoomWrite;
import model.valuables.Valuable;

import java.util.Random;

public class ValuableTransporter implements Runnable
{
  private DepositSafeADT deposit;
  private DepositADT cargo;
  private TreasureRoomDoor access;

  public ValuableTransporter(DepositSafeADT deposit, TreasureRoomDoor access)
  {
    this.deposit = deposit;
    this.access = access;
    this.cargo = new DepositListBased();
  }

  @Override public void run()
  {
    Random random = new Random();

    while (true) {
      int targetWorth = random.nextInt(151) + 50;
      int currentWorth = 0;
      Log.getInstance().log(Thread.currentThread().getName()
          + ": Valuables Transporter is getting valuables "
          + "from the deposit.");

      while (currentWorth < targetWorth)
      {
        Valuable valuable = deposit.remove(0);
        cargo.add(valuable);
        currentWorth += valuable.getWorth();
      }

      try
      {
        Log.getInstance().log(Thread.currentThread().getName()
            + ": Valuable Transporter travelling with "
                + currentWorth + " worth of valuables.");
        Thread.sleep(1000);
      }

      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

      Log.getInstance().log(Thread.currentThread().getName()
          + ": Valuable Transporter wants to deposit "
          + "valuables in the treasure room.");
      TreasureRoomWrite treasureRoom= access.acquireWrite();

      Log.getInstance().log(Thread.currentThread().getName() +
          ": Valuable Transporter is adding the valuables "
          + " of a worth of " + currentWorth +" to the treasure room.");

      try
      {
        Thread.sleep(500);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

      for (int i = 0; i < cargo.size(); i++)
      {
        treasureRoom.add(cargo.remove(i));
      }

      access.releaseWrite();

      Log.getInstance().log(Thread.currentThread().getName()
          + ": Valuable Transporter travelling back to the deposit.");
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
