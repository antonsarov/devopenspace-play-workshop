package actors;

import akka.actor.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.Item;
import play.Logger;
import play.libs.Akka;
import play.libs.Json;

import java.math.BigDecimal;

public class MyWebSocketActor extends UntypedActor {

    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
        out.tell(Json.toJson(Item.find.all()), self());
    }

    public void onReceive(Object message) throws Exception {
        Logger.debug(message.toString());
        if (message instanceof JsonNode) {
            JsonNode jn = (JsonNode) message;
            String action = jn.get("action").asText();
            switch (action) {
                case "answer":
                    Long id = jn.get("itemId").asLong();
                    Item itemFromDB = Item.find.byId(id);
                    itemFromDB.setPrice(new BigDecimal(jn.get("price").asText()));
                    itemFromDB.save();
                    Akka.system().actorSelection("system/websockets/*/handler").tell(JsonNodeFactory.instance.objectNode().put("action", "update"), self());
                    break;
                case "update":
                    out.tell(Json.toJson(Item.find.all()), self());
                    break;
            }
        }
    }
}