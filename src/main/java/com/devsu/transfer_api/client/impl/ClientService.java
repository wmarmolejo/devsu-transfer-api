package com.devsu.transfer_api.client.impl;

import com.devsu.transfer_api.client.IClientService;
import com.devsu.transfer_api.dto.response.ClientResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }
    public Mono<ClientResponseDto> obtenerClientePorId(Long id) {
        return webClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .bodyToMono(ClientResponseDto.class);
    }

}
