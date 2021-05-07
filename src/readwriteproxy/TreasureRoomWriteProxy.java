package readwriteproxy;

import model.deposit.DepositADT;
import model.deposit.DepositSafeADT;
import model.treasure.TreasureRoomWrite;
import model.valuables.Valuable;

public class TreasureRoomWriteProxy implements TreasureRoomWrite
{
  private TreasureRoomWrite realSubject;
  private boolean hasWriteAccess;

  public TreasureRoomWriteProxy(TreasureRoomWrite treasureRoom)
  {
    this.realSubject = treasureRoom;
    this.hasWriteAccess = true;
  }

  public void restrictAccess()
  {
    hasWriteAccess = false;
  }

  @Override public void add(Valuable valuable)
  {
    realSubject.add(valuable);
  }

  @Override public Valuable retrieve()
  {
    return realSubject.retrieve();
  }

  @Override public DepositADT look()
  {
    return realSubject.look();
  }
}
