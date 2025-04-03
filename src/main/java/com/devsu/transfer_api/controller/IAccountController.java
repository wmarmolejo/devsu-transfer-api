package com.devsu.transfer_api.controller;

import com.devsu.transfer_api.dto.request.AccountRequestDto;;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountController {

    @RequestMapping(value = "cuentas",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    default ResponseEntity<Mono<AccountResponseDto>> save(@RequestBody AccountRequestDto accountRequestDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "cuentas",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<Mono<AccountResponseDto>> getAccountNumberAndType(
            @RequestParam(value = "numberAccount", required = false) String numberAccount,
            @RequestParam(value = "typeAccount", required = false) String typeAccount){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "cuentas/all",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    default ResponseEntity<Flux<AccountResponseDto>> getAccounts() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "cuentas",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PATCH)
    default ResponseEntity<?> disableAccountNumberAndType(
            @RequestParam(value = "numberAccount", required = false) String numberAccount,
            @RequestParam(value = "typeAccount", required = false) String typeAccount){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
