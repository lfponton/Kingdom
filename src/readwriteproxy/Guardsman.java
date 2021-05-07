package readwriteproxy;

import model.treasure.TreasureRoom;
import model.treasure.TreasureRoomRead;
import model.treasure.TreasureRoomWrite;

import java.util.HashMap;
import java.util.Map;

public class Guardsman implements TreasureRoomDoor
{
  private int readers;
  private int writers;
  private int waitingWriters;
  private TreasureRoom treasureRoom;
  private Map<Thread, TreasureRoomReadProxy> readProxies;
  private Map<Thread, TreasureRoomWriteProxy> writeProxies;

  public Guardsman(TreasureRoom treasureRoom)
  {
    this.treasureRoom = treasureRoom;
    this.readers = 0;
    this.writers = 0;
    this.waitingWriters = 0;
    this.readProxies = new HashMap<>();
    this.writeProxies = new HashMap<>();
  }

  @Override public synchronized TreasureRoomWrite acquireWrite()
  {
    while (readers > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    writers++;
    TreasureRoomWriteProxy writeProxy = new TreasureRoomWriteProxy(treasureRoom);
    writeProxies.put(Thread.currentThread(), writeProxy);
    return writeProxy;
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    notifyAll();
    TreasureRoomWriteProxy writeProxy = writeProxies.get(Thread.currentThread());
    if (writeProxy != null)
    {
      writeProxy.restrictAccess();
      writeProxies.remove(Thread.currentThread());
    }
  }

  @Override public synchronized TreasureRoomRead acquireRead()
  {
    while (writers > 0 || waitingWriters > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    readers++;
    TreasureRoomReadProxy readProxy = new TreasureRoomReadProxy(treasureRoom);
    readProxies.put(Thread.currentThread(), readProxy);
    return readProxy;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    if (readers == 0)
    {
      notifyAll();
    }
    TreasureRoomReadProxy readProxy = readProxies.get(Thread.currentThread());
    if (readProxy != null)
    {
      readProxy.restrictAccess();
      readProxies.remove(Thread.currentThread());
    }
  }
}
