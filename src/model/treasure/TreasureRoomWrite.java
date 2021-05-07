package model.treasure;

import model.valuables.Valuable;

public interface TreasureRoomWrite extends TreasureRoomRead
{
  void add(Valuable valuable);
  Valuable retrieve();
}
