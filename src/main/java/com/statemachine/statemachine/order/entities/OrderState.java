package com.statemachine.statemachine.order.entities;

public enum OrderState {

    CREATED,
    ACCEPTED,
    IN_PREPARATION,
    READY,
    DELIVERING,
    COMPLETED,
    CLOSED
}
