package org.hidxop.citronix.http.controller.impl;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hidxop.citronix.http.controller.ISaleController;
import org.hidxop.citronix.dto.sale.SaleBasicResponseDto;
import org.hidxop.citronix.dto.sale.SaleCreateRequestDto;
import org.hidxop.citronix.dto.sale.SaleDetailedResponseDto;
import org.hidxop.citronix.dto.sale.SaleUpdateRequestDto;
import org.hidxop.citronix.service.ISaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/sale")
public class SaleController implements ISaleController {
    private final ISaleService saleService;

    @Override
    @GetMapping
    public ResponseEntity<List<SaleBasicResponseDto>> findAll(){
        List<SaleBasicResponseDto> sales=saleService.findAll();
        return ResponseEntity.ok(sales);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailedResponseDto> findById(@PathVariable @Valid UUID id){
        SaleDetailedResponseDto sale= saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @Override
    @PostMapping
    public ResponseEntity<SaleBasicResponseDto> save(@RequestBody @Valid SaleCreateRequestDto saleCreateRequestDto) {
        SaleBasicResponseDto detailedResponseDto = saleService.save(saleCreateRequestDto);
        return new ResponseEntity<>(detailedResponseDto, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid UUID id){
        saleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SaleBasicResponseDto> update(@Valid @PathVariable UUID id,@RequestBody SaleUpdateRequestDto saleUpdateRequestDto) {
        SaleBasicResponseDto updatedFarm = saleService.update(id, saleUpdateRequestDto);
        return ResponseEntity.ok(updatedFarm);
    }


}
