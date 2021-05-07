package readwriteproxy;

import model.treasure.TreasureRoomRead;
import model.treasure.TreasureRoomWrite;

public interface TreasureRoomDoor
{
  TreasureRoomWrite acquireWrite();
  void releaseWrite();
  TreasureRoomRead acquireRead();
  void releaseRead();

}
