package readwriteproxy;

import model.deposit.DepositADT;
import model.deposit.DepositSafeADT;
import model.treasure.TreasureRoomRead;

public class TreasureRoomReadProxy implements TreasureRoomRead
{
  private TreasureRoomRead realSubject;
  private boolean hasReadAccess;

  public TreasureRoomReadProxy(TreasureRoomRead treasureRoom)
  {
    this.realSubject = treasureRoom;
    this.hasReadAccess = true;
  }

  @Override public DepositADT look()
  {
    if (hasReadAccess)
    {
      return realSubject.look();
    }
    else
    {
      throw new IllegalStateException("No read access");
    }
  }

  public void restrictAccess()
  {
    hasReadAccess = false;
  }
}
