package model.actors;

import model.Log;
import model.deposit.DepositSafeADT;
import model.valuables.Mine;
import model.valuables.Valuable;

public class Miner implements Runnable
{
  private DepositSafeADT deposit;
  private Mine mine;

  public Miner(DepositSafeADT deposit, Mine mine)
  {
    this.deposit = deposit;
    this.mine = mine;
  }

  @Override public void run()
  {
    while (true)
    {
      Valuable valuable = mine.getValuable();
      Log.getInstance().log(Thread.currentThread().getName()
          + ": Miner adds a " + valuable.getValuable() + " to the deposit.");
      deposit.add(valuable);
      try
      {
        Thread.sleep(2000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
