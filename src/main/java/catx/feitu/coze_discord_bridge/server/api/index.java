package catx.feitu.coze_discord_bridge.server.api;

import catx.feitu.coze_discord_bridge.server.APIHandler;
import catx.feitu.coze_discord_bridge.server.HandleType;
import catx.feitu.coze_discord_bridge.server.ResponseType;
import com.alibaba.fastjson.JSONObject;

import java.time.Instant;

public class index implements APIHandler {

    @Override
    public ResponseType handle(HandleType Handle) {
        ResponseType Response = new ResponseType();

        JSONObject json = new JSONObject(true);
        json.put("code", 200);
        json.put("message", "这里是Coze-Discord-Bridge的根节点~");

        JSONObject json_data = new JSONObject(true);
        json_data.put("now", Instant.now().toEpochMilli() / 1000);

        json.put("data", json_data);

        Response.msg = json.toJSONString();
        Response.code = 200;

        return Response;
    }
}
