package com.devsu.transfer_api.enums;

public enum ErrorState {

    INSUFFICIENT_BALANCE("Insufficient balance to make the transfer"),
    ACCOUNT_NOT_FOUND("Account not Found"),

    ACCOUNT_STATUS_CHANGE("the account status has been changed"),
    INTERNAL_ERROR("Internal Server Error");
    private final String message;

    ErrorState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
