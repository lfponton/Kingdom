package model.valuables;

import java.util.Random;

public class Mine
{
  private Valuable valuable;

  public Valuable getValuable()
  {
    Random random = new Random();
    int option = random.nextInt(4);
    switch (option) {
      case 0:
        valuable = Valuable.getInstance(Valuable.Key.DIAMOND);
        break;
      case 1:
        valuable = Valuable.getInstance(Valuable.Key.GOLDNUGGET);
        break;
      case 2:
        valuable = Valuable.getInstance(Valuable.Key.JEWEL);
        break;
      case 3:
        valuable = Valuable.getInstance(Valuable.Key.GEM);
        break;
    }
    return valuable;
  }
}
