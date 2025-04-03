package com.devsu.transfer_api.service;

import com.devsu.transfer_api.dto.request.TransferRequestDto;
import com.devsu.transfer_api.dto.response.TransferReportResponseDto;
import com.devsu.transfer_api.dto.response.TransferResponseDto;
import reactor.core.publisher.Flux;

public interface ITransferService {
    TransferResponseDto createTransfer(TransferRequestDto transfer);
    Flux<TransferReportResponseDto> reportClient(Long clientId, String from, String to);
}
