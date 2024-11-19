package org.hidxop.citronix.service.validator;

import lombok.RequiredArgsConstructor;
import org.hidxop.citronix.domain.entitiy.Tree;
import org.hidxop.citronix.exceptionHandling.exceptions.InvalidStateException;
import org.hidxop.citronix.service.impl.FieldService;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
@RequiredArgsConstructor
public class TreeValidator {

    private final FieldService fieldService;

    public void validateTreeUpdate(Tree tree){
        validateTreeCount(tree);
    }
    public void validateTreeCreation(Tree tree){
        validatePlantationDate(tree);
        validateTreeCount(tree);
    }

    public void validatePlantationDate(Tree tree) {
        Month month = tree.getPlantedAt().getMonth();
        if (month != Month.MARCH && month != Month.APRIL && month != Month.MAY) {
            throw new InvalidStateException("The tree can only be planted between March and May.");
        }
    }

    private void validateTreeCount(Tree tree){
        double treeRatePerField=fieldService.calculateTreePerAreaRate(tree.getField());
        if (treeRatePerField>10.00){
            throw new InvalidStateException("The Field is already full of trees.");
        }
    }
}
