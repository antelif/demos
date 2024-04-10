package com.antelif.fuel.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FuelManagerTest {

  private FuelManager fuelManager;

  @BeforeEach
  public void setUp() {
    fuelManager = new FuelManager();
    fuelManager.setTankQuantity(50.0);
  }

  @Test
  @DisplayName("calculateCurrentFuelWorth: One transaction")
  void calculateCurrentFuelWorthWhenTankContainsFuelFromOneTransaction() {
    FuelTransaction transaction1 = new FuelTransaction();
    transaction1.setQuantity(50);
    transaction1.setPrice(50);

    fuelManager.setFuelTransactions(new LinkedList<>(Collections.singletonList(transaction1)));

    assertEquals(50, fuelManager.calculateCurrentFuelWorth());
  }

  @Test
  @DisplayName("calculateCurrentFuelWorth: Two transactions")
  void calculateCurrentFuelWorthWhenTankContainsFuelFromTwoTransactions() {
    FuelTransaction transaction1 = new FuelTransaction();
    transaction1.setQuantity(25);
    transaction1.setPrice(25.0);

    FuelTransaction transaction2 = new FuelTransaction();
    transaction2.setQuantity(25);
    transaction2.setPrice(50.0);

    fuelManager.setFuelTransactions(new LinkedList<>(Arrays.asList(transaction1, transaction2)));

    assertEquals(75.0, fuelManager.calculateCurrentFuelWorth());
  }

  @Test
  @DisplayName("calculateCurrentFuelWorth: Two transactions. The fuel of the first is already consumed.")
  void calculateCurrentFuelWorthWhenTankContainsFuelFromLastTransactionButThereAreMoreTransactions() {
    FuelTransaction transaction1 = new FuelTransaction();
    transaction1.setQuantity(25);
    transaction1.setPrice(25.0);

    FuelTransaction transaction2 = new FuelTransaction();
    transaction2.setQuantity(50);
    transaction2.setPrice(50.0);

    fuelManager.setFuelTransactions(new LinkedList<>(Arrays.asList(transaction1, transaction2)));

    assertEquals(50.0, fuelManager.calculateCurrentFuelWorth());
  }
}
