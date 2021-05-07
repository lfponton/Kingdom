package model.valuables;

import java.util.HashMap;
import java.util.Map;

public class Valuable
{
  public enum Key {
    DIAMOND, GOLDNUGGET, JEWEL, GEM;
}
  private static Map<Key, Valuable> allInstances = new HashMap<>();
  private Key key;
  private String valuable;
  private int worth;

  private Valuable(Key key)
  {
    this.key = key;
    switch (key) {
      case DIAMOND:
        valuable = "Diamond";
        worth = 50;
        break;
      case GOLDNUGGET:
        valuable = "Gold nugget";
        worth = 10;
        break;
      case JEWEL:
        valuable = "Jewel";
        worth = 20;
        break;
      case GEM:
        valuable = "Gem";
        worth = 30;
        break;
    }
  }

  public static Valuable getInstance(Key key)
  {
    Valuable instance = allInstances.get(key);
    if (instance == null)
    {
      synchronized (allInstances)
      {
        instance = allInstances.get(key);
        if (instance == null)
        {
          instance = new Valuable(key);
          allInstances.put(key, instance);
        }
      }
    }
    return instance;
  }

  public String getValuable()
  {
    return valuable;
  }

  public int getWorth()
  {
    return worth;
  }
}
