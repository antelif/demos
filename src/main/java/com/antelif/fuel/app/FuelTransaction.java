package com.antelif.fuel.app;

import lombok.Getter;
import lombok.Setter;

/**
 * FuelTransaction.
 *
 * <p>Contains information about a gas station transaction.
 */
@Getter
@Setter
public class FuelTransaction {
  private double quantity;
  private double price;

  public double getPricePerQuantityUnit() {
    return price / quantity;
  }
}
