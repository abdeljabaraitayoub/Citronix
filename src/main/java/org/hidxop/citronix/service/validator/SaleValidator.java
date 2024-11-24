package org.hidxop.citronix.service.validator;

import org.hidxop.citronix.domain.entitiy.Sale;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaleValidator {

    public void SaleCreateValidation(Sale sale) {
        validateHarvestQuantity(sale);
    }

    public void SaleUpdateValidation(Sale sale) {
        validateHarvestQuantity(sale);
    }

    private void validateHarvestQuantity(Sale sale) {


        UUID currentSaleId=sale.getId();
        double totalQuantitySold = sale.getHarvest().getSale().stream()
                .filter(s -> currentSaleId == null || !s.getId().equals(currentSaleId))
                .mapToDouble(Sale::getQuantity)
                .sum();

        double totalQuantityAfterOperation = totalQuantitySold + sale.getQuantity();

        if (totalQuantityAfterOperation > sale.getHarvest().getTotalQuantity()) {
            double availableQuantity = sale.getHarvest().getTotalQuantity() - totalQuantitySold;
            throw new InvalidStateException("This quantity not available anymore.");
        }

        if (sale.getQuantity() <= 0) {
            throw new InvalidStateException("Sale quantity must be greater than 0");
        }
    }
}