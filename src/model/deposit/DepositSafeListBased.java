package model.deposit;

import mediator.ArrayList;
import model.Log;
import mediator.ListADT;
import model.valuables.Valuable;

public class DepositSafeListBased implements DepositSafeADT
{
  private ListADT<Valuable> deposit;

  public DepositSafeListBased()
  {
    deposit = new ArrayList<>();
  }

  @Override public synchronized void add(Valuable valuable)
  {
    deposit.add(valuable);
    notifyAll();
  }

  @Override public synchronized Valuable remove(int index)
  {
    while (deposit.isEmpty())
    {
      try
      {
        Log.getInstance().log(Thread.currentThread().getName()
            + ": Valuable Transporter waiting for valuables.");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    Valuable valuable = deposit.remove(index);
    Log.getInstance().log(Thread.currentThread().getName() +
        ": Valuable Transporter took a " + valuable.getValuable() + " worth "
        + valuable.getWorth() + " from the deposit.");
    notifyAll();
    return valuable;
  }

}