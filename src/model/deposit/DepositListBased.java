package model.deposit;

import mediator.ArrayList;
import mediator.ListADT;
import model.valuables.Valuable;

public class DepositListBased implements DepositADT
{
  private ListADT<Valuable> deposit;

  public DepositListBased()
  {
    deposit = new ArrayList();
  }

  @Override public void add(Valuable valuable)
  {
    deposit.add(valuable);
  }

  @Override public Valuable remove(int index)
  {
    return deposit.remove(index);
  }

  @Override public Valuable get(int index)
  {
    return deposit.get(index);
  }

  @Override public int size()
  {
    return deposit.size();
  }

  @Override public boolean isEmpty()
  {
    return deposit.isEmpty();
  }
}
