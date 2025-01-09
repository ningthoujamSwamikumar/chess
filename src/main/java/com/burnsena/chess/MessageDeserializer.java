package com.burnsena.chess;

import com.burnsena.chess.messages.InitMessage;
import com.burnsena.chess.messages.Message;
import com.burnsena.chess.messages.MessageType;
import com.burnsena.chess.messages.MoveMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MessageDeserializer extends StdDeserializer<Message> {

    public MessageDeserializer(){
        this(null);
    }

    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        MessageType type = MessageType.valueOf(node.get("type").asText());

        switch (type) {
            case INIT_GAME -> {
                return jsonParser.getCodec().treeToValue(node, InitMessage.class);
            }
            case MOVE -> {
                return jsonParser.getCodec().treeToValue(node, MoveMessage.class);
            }
            default -> throw new IllegalArgumentException("Invalid type: "+type);
        }
    }
}
