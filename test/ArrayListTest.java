import mediator.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mediator.ListADT;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest
{
  private ListADT list;

  @BeforeEach void setup() {
    list = new ArrayList();
  }

  // The add() method is calling the the add(index, element) so by testing the
  // latter we are testing the former.


  @Test void addNullElement() {
    list.add(null);
    assertNull(list.get(0));
  }
  @Test void addOneElement() {
    list.add("A");
    assertEquals("A", list.get(0));
  }

  @Test void addManyElements() {
    list.add("A");
    list.add("B");
    list.add("C");
    assertEquals("{A, B, C}", list.toString());
  }

  @Test void addNullElementByIndex() {
    list.add(0,null);
    assertNull(list.get(0));
  }

  @Test void addOneElementByIndex() {
    list.add(0, "A");
    assertEquals("A", list.get(0));
  }

  @Test void addManyElementsByIndex() {
    list.add(0, "A");
    list.add(1, "B");
    list.add(2, "C");
    list.add(3, "D");
    list.add(4, "E");
    assertEquals("{A, B, C, D, E}", list.toString());
  }

  @Test void addElementWithNegativeIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "A"));
  }

  @Test void addElementWithIndexGreaterThanZero() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));
  }

  @Test void addElementsBeyondCapacity() {
    for (int i = 0; i < 201; i++)
    {
      list.add(i, i);
    }
    assertEquals(200, list.get(200));
  }

  @Test void setElementToNull()
  {
    list.add("A");
    list.add("B");
    list.set(1, null);
    assertNull(list.get(1));
  }

  @Test void setOneElement()
  {
    list.add("A");
    list.set(0, "A");
    assertEquals("A", list.get(0));
  }

  @Test void setManyElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.set(0, "C");
    list.set(1, "A");
    list.set(2, "B");
    assertEquals("{C, A, B}", list.toString());
  }

  @Test void setOnEmptyList()
  {
    assertThrows(IndexOutOfBoundsException.class, ()-> list.set(0, "A"));
  }

  @Test void setWithNegativeIndex()
  {
    list.add("A");
    assertThrows(IndexOutOfBoundsException.class, ()-> list.set(-1, "A"));
  }

  @Test void setWithIndexGreaterThanListSizeMinusOne()
  {
    list.add("A");
    list.add("B");
    assertThrows(IndexOutOfBoundsException.class, ()-> list.set(2, "C"));
  }

  @Test void getNullElement() {
    list.add(null);
    assertNull(list.get(0));
  }

  @Test void getOneElement()
  {
    list.add("A");
    assertEquals("A", list.get(0));
  }

  @Test void getFromEmptyList()
  {
    assertThrows(IllegalStateException.class, ()-> list.get(0));
  }

  @Test void getWithNegativeIndex()
  {
    list.add("A");
    assertThrows(IllegalStateException.class, ()-> list.get(-1));
  }

  @Test void getWithIndexGreaterThanListSizeMinusOne()
  {
    list.add("A");
    assertThrows(IllegalStateException.class, ()-> list.get(1));
  }

  @Test void removeWithIndexNullElement()
  {
    list.add(null);
    assertNull(list.remove(0));
  }

  @Test void removeWithIndexOneElement()
  {
    list.add("A");
    list.remove(0);
    assertEquals(0, list.size());
  }

  @Test void removeManyElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    list.remove(0);
    list.remove(1);
    list.remove(2);
    assertEquals(2, list.size());
  }

  @Test void removeNegativeIndex()
  {
    list.add("A");
    assertThrows(IndexOutOfBoundsException.class, ()-> list.remove(-1));
  }

  @Test void removeWithIndexFromEmptyList()
  {
    assertThrows(IndexOutOfBoundsException.class, ()->list.remove(0));
  }

  @Test void removeWithIndexGreaterThanListSizeMinusOne()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    assertThrows(IndexOutOfBoundsException.class, ()->list.remove(3));
  }

  // The remove(element) method is calling the the remove(index) and indexOf()
  // so by testing the latter we are testing the former. Only one the following
  // test is needed:

  @Test void removeNonExistingElement()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    assertThrows(IllegalStateException.class, ()-> list.remove("F"));
  }

  @Test void indexOfEmptyList()
  {
    assertEquals(-1, list.indexOf("A"));
  }

  // NB: Will only return the index of the first found element
  @Test void indexOfListWithTwoEqualElements()
  {
    list.add("A");
    list.add("A");
    assertEquals(0, list.indexOf("A"));

  }

  @Test void indexOfListWithManyElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    assertEquals(2,list.indexOf("C"));
  }

  @Test void indexOfNonExistingElement()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    assertEquals(-1, list.indexOf("F"));
  }

  // contains() calls indexOf() so only the following tests are necessary:

  @Test void containsNonExistingElement()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    assertFalse(list.contains("F"));
  }

  @Test void containsElement()
  {
    list.add("A");
    assertTrue(list.contains("A"));
  }

  @Test void isEmptyOnEmptyList()
  {
    assertTrue(list.isEmpty());
  }

  @Test void isEmptyOnListWithElements()
  {
    list.add("A");
    assertFalse(list.isEmpty());
  }

  @Test void isFullOnEmptyList()
  {
    assertFalse(list.isFull());
  }

  @Test void isFullOnListWithElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    assertFalse(list.isFull());
  }

  @Test void isFullOnListBeyondCapacity()
  {
    for (int i = 0; i < 201; i++)
    {
      list.add(i, i);
    }
    assertFalse(list.isFull());
  }

  // Tests the constructor too:
  @Test void sizeOfEmptyList()
  {
    assertEquals(0, list.size());
  }

  @Test void sizeOfListWithManyElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    assertEquals(5, list.size());
  }

  @Test void sizeofListBeyondCapacity()
  {
    for (int i = 0; i < 200; i++)
    {
      list.add(i, i);
    }
    assertEquals(200, list.size());
  }

  @Test void sizeAfterRemovingAnElement()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.remove(1);
    assertEquals(2, list.size());
  }

  @Test void sizeAfterSettingAnElement()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    list.set(2, "D");
    assertEquals(3, list.size());
  }

  @Test void toStringEmptyList()
  {
    assertEquals("{}", list.toString());
  }

  @Test void toStringListWithManyElements()
  {
    list.add("A");
    list.add("B");
    list.add("C");
    assertEquals("{A, B, C}", list.toString());
  }

  @Test void toStringNullElement()
  {
    list.add(null);
    assertEquals("{null}", list.toString());
  }
}