package com.devsu.transfer_api.controller.impl;

import com.devsu.transfer_api.controller.ITransferController;
import com.devsu.transfer_api.dto.request.TransferRequestDto;
import com.devsu.transfer_api.dto.response.TransferReportResponseDto;
import com.devsu.transfer_api.dto.response.TransferResponseDto;
import com.devsu.transfer_api.mapper.IAccountMapper;
import com.devsu.transfer_api.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class TransferController implements ITransferController {

    @Autowired
    private ITransferService transferService;

    @Override
    public ResponseEntity<TransferResponseDto> save(@RequestBody TransferRequestDto transferRequestDto) {
         return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(transferRequestDto));
    }

    @Override
    public ResponseEntity<Flux<TransferReportResponseDto>> getReportClient(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(value = "clientId", required = false) Long clientId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transferService.reportClient(clientId, from,to));
    }


}
