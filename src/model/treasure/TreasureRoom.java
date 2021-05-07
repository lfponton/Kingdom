package model.treasure;

import model.deposit.DepositADT;
import model.deposit.DepositListBased;
import model.valuables.Valuable;

public class TreasureRoom implements TreasureRoomWrite
{
  private DepositADT treasure;

  public TreasureRoom()
  {
    treasure = new DepositListBased();
  }

  @Override public void add(Valuable valuable)
  {
    treasure.add(valuable);
  }

  @Override public Valuable retrieve()
  {
    return treasure.remove(0);
  }

  @Override public DepositADT look()
  {
    return treasure;
  }

}
