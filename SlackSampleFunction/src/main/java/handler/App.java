package handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import iorin.IorinMessage;
import slack.SlackBot;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Object, Object> {

    public Object handleRequest(final Object input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        try {
            final String message = IorinMessage.randomMessage().getMessage();
            String token = System.getenv("SLACK_BOT_TOKEN");
            String channel = System.getenv("SLACK_BOT_CHANNEL");
			SlackBot.sendMessage(token, channel, message);
			String output = String.format(Locale.ENGLISH, "{ \"message\": \"%s\" }", message);
            return new GatewayResponse(output, headers, 200);
        } catch (IOException e) {
            return new GatewayResponse("{}", headers, 500);
        }
    }
}
