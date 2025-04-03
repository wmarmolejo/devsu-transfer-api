package com.devsu.transfer_api.service.impl;

import com.devsu.transfer_api.client.IClientService;
import com.devsu.transfer_api.dto.request.TransferRequestDto;
import com.devsu.transfer_api.dto.response.TransferReportResponseDto;
import com.devsu.transfer_api.dto.response.TransferResponseDto;
import com.devsu.transfer_api.enums.ErrorState;
import com.devsu.transfer_api.filter.TransferSpecification;
import com.devsu.transfer_api.model.Account;
import com.devsu.transfer_api.model.Transfer;
import com.devsu.transfer_api.repository.ITransferRepository;
import com.devsu.transfer_api.service.IAccountService;
import com.devsu.transfer_api.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class TransferService implements ITransferService {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private ITransferRepository transferRepository;


    @Transactional
    @Override
    public TransferResponseDto createTransfer(TransferRequestDto transferRequestDto) {

        Account account=accountService.findById(transferRequestDto.getAccountId()).get();

        if((account.getAvailableBalance()+transferRequestDto.getValor())>=0){
            Transfer transfer=new Transfer();
            transfer.setValor(transferRequestDto.getValor());
            transfer.setClientId(account.getClientId());
            transfer.setTransferType(transferRequestDto.getTipoMovimiento());
            transfer.setAccount(account);
            transfer.setSaldoInicial(account.getAvailableBalance());
            transferRepository.save(transfer);
            account.setAvailableBalance(account.getAvailableBalance()+transferRequestDto.getValor());
            accountService.update(account.getId(),account);
            TransferResponseDto response=new TransferResponseDto();
            response.setStatus(account.getStatus());
            response.setSaldoInicial(account.getAvailableBalance());
            response.setNumberAccount(account.getNumberAccount());
            response.setTypeAccount(account.getTypeAccount());
            response.setMovimiento(transferRequestDto.getTipoMovimiento()+" de "+transferRequestDto.getValor());
            return response;
        }
        throw new RuntimeException(ErrorState.INSUFFICIENT_BALANCE.getMessage());

    }

    @Transactional(readOnly = true)
    @Override
    public Flux<TransferReportResponseDto> reportClient(Long clientId, String from, String to) {

        Specification<Transfer> spec =  TransferSpecification.filterTickets(clientId, from, to);

        List<Transfer> listTransfer=transferRepository.findAll(spec);
        Flux<Transfer> transferFlux = Flux.fromIterable(listTransfer);

        return transferFlux.flatMap(movement -> {
            TransferReportResponseDto transferResponse=new TransferReportResponseDto();
            transferResponse.setFecha(movement.getCreatedAt().toString());
            transferResponse.setMovimiento(movement.getValor());
            transferResponse.setSaldoInicial(movement.getSaldoInicial());
            transferResponse.setSaldo(movement.getSaldoInicial()+movement.getValor());
            Account account=accountService.findById(movement.getAccount().getId()).get();
            transferResponse.setNumberAccount(account.getNumberAccount());
            transferResponse.setTypeAccount(account.getTypeAccount());
            transferResponse.setStatus(account.getStatus());

            return clientService.obtenerClientePorId(clientId)
                    .doOnNext(clientResponseDto -> {
                        transferResponse.setCliente(clientResponseDto.getName());
                    })
                    .then(Mono.just(transferResponse));
        });
    }


}
