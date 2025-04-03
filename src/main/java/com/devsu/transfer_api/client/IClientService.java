package com.devsu.transfer_api.client;

import com.devsu.transfer_api.dto.response.ClientResponseDto;
import reactor.core.publisher.Mono;

public interface IClientService {

    public Mono<ClientResponseDto> obtenerClientePorId(Long id);
}
