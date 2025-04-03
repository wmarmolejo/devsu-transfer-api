package com.devsu.transfer_api.mapper.impl;

import com.devsu.transfer_api.dto.request.AccountRequestDto;
import com.devsu.transfer_api.dto.response.AccountResponseDto;
import com.devsu.transfer_api.mapper.IAccountMapper;
import com.devsu.transfer_api.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements IAccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Account toEntityFromCreate(AccountRequestDto dto){
        return modelMapper.map(dto, Account.class);
    }
    @Override
    public AccountResponseDto toResponseDto(Account account) {
        return modelMapper.map(account, AccountResponseDto.class);
    }


}
