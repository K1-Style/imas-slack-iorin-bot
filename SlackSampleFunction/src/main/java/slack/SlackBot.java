package slack;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import java.io.IOException;

public class SlackBot {

	/**
	 * slack botでメッセージを送る
	 * @param token Botトークン
	 * @param channelName チャンネル名
	 * @param message メッセージ
	 * @throws IOException slackのsessionコネクションエラー
	 */
	public static void sendMessage(String token, String channelName, String message) throws IOException {

		// BotのAPI Tokenを設定
		SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);

		session.connect();

		SlackChannel channel = session.findChannelByName(channelName);
		session.sendMessage(channel, message);

		session.disconnect();

	}
}
