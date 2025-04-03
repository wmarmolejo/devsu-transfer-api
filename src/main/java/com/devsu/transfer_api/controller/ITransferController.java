package com.devsu.transfer_api.controller;

import com.devsu.transfer_api.dto.request.TransferRequestDto;
import com.devsu.transfer_api.dto.response.TransferReportResponseDto;
import com.devsu.transfer_api.dto.response.TransferResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

public interface ITransferController {

    @RequestMapping(value = "movimientos",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    default ResponseEntity<TransferResponseDto> save(@RequestBody TransferRequestDto transferRequestDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "movimientos/reportes",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<Flux<TransferReportResponseDto>> getReportClient(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(value = "clientId", required = false) Long clientId){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
