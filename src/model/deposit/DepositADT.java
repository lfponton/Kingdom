package model.deposit;

import model.valuables.Valuable;

public interface DepositADT extends DepositSafeADT
{
  Valuable get(int index);
  int size();
  boolean isEmpty();
}
