import model.actors.Accountant;
import model.actors.King;
import model.actors.Miner;
import model.actors.ValuableTransporter;
import model.deposit.DepositSafeADT;
import model.deposit.DepositSafeListBased;
import readwriteproxy.Guardsman;
import model.treasure.TreasureRoom;
import readwriteproxy.TreasureRoomDoor;
import model.valuables.Mine;

public class Main
{
  public static void main(String[] args)
  {
    DepositSafeADT deposit = new DepositSafeListBased();
    TreasureRoom treasureRoom = new TreasureRoom();
    TreasureRoomDoor guardsman = new Guardsman(treasureRoom);
    Mine mine = new Mine();

    for (int i = 0; i < 2; i++)
    {
      new Thread(new Miner(deposit, mine), "MINER" + i).start();
      new Thread(new ValuableTransporter(deposit, guardsman), "TRANSPORTER" + i).start();
      new Thread(new Accountant(guardsman), "ACCOUNTANT" + i).start();
    }

    new Thread(new King(guardsman), "KING").start();
  }
}
