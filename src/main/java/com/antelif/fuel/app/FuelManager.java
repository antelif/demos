package com.antelif.fuel.app;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * FuelManager.
 *
 * <p>Contains information about the vehicle and methods to view and handle fuel transactions.
 */
@Getter
@Setter
public class FuelManager {

  private double tankQuantity;
  private List<FuelTransaction> fuelTransactions;

  /**
   * Calculates the current worth of fuel remaining on vehicle tank, based on the specific price of
   * fuel unit.
   *
   * @return a double representing the total price.
   */
  public double calculateCurrentFuelWorth() {
    LinkedList<FuelTransaction> tempFuelTransactions = new LinkedList<>(this.fuelTransactions);

    double calculatedPrice = .0;
    double calculatedQuantity = .0;

    while (calculatedQuantity < tankQuantity) {
      FuelTransaction transaction = tempFuelTransactions.getLast();

      if (tankQuantity < calculatedQuantity + transaction.getQuantity()) {
        calculatedPrice +=
            (tankQuantity - calculatedQuantity) * transaction.getPricePerQuantityUnit();
        break;
      }

      calculatedQuantity += transaction.getQuantity();
      calculatedPrice += transaction.getPrice();
      tempFuelTransactions.removeLast();
    }

    return calculatedPrice;
  }
}
