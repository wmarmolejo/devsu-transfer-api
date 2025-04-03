package com.devsu.transfer_api.mapper;


import com.devsu.transfer_api.dto.request.AccountRequestDto;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.model.Account;

public interface IAccountMapper {

    Account toEntityFromCreate(AccountRequestDto dto);
    AccountResponseDto toResponseDto(Account account);

}
