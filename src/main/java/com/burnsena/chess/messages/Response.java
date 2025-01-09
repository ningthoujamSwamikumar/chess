package com.burnsena.chess.messages;

import lombok.Builder;

@Builder
public record Response<T>(MessageType type, T message) {
}
