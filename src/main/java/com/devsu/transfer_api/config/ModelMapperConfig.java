package com.devsu.transfer_api.config;

import com.devsu.transfer_api.dto.request.AccountRequestDto;
import com.devsu.transfer_api.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AccountRequestDto.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        return modelMapper;
    }
}
