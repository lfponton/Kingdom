package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log
{
  private static Log instance;
  private static Lock lock = new ReentrantLock();

  private Log() {

  }

  public static Log getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null)
        {
          instance = new Log();
        }
      }
    }
    return instance;
  }

  public void log(String text) {
    System.out.println(text);
  }

}
