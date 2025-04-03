package com.devsu.transfer_api.model;

import jakarta.persistence.*;
import reactor.core.publisher.Mono;

import java.util.List;


@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"numberAccount", "typeAccount"}))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_account", nullable = false)
    private String numberAccount;


    @Column(name = "type_account")
    private String typeAccount;

    @Column(name = "available_balance")
    private Double availableBalance;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "client_id",nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transfers;


    public Account() {
    }

    public Account(Long id, String numberAccount, String typeAccount, Double availableBalance, Boolean status, Long clientId, List<Transfer> transfers) {
        this.id = id;
        this.numberAccount = numberAccount;
        this.typeAccount = typeAccount;
        this.availableBalance = availableBalance;
        this.status = status;
        this.clientId = clientId;
        this.transfers = transfers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
