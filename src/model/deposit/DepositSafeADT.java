package model.deposit;

import model.valuables.Valuable;

public interface DepositSafeADT
{
  void add(Valuable valuable);
  Valuable remove(int index);
}
