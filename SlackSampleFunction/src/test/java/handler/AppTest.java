package handler;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import slack.SlackBot;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SlackBot.class)
public class AppTest {

	@Test
	public void successfulResponse() throws Exception {
		App app = new App();

		// slackの送信をモック化
		mockStatic(SlackBot.class);
		doNothing().when(SlackBot.class, "sendMessage", anyString(), anyString(), anyString());

		GatewayResponse result = (GatewayResponse) app.handleRequest(null, null);
		System.out.println(result.getBody());

		assertThat(result.getStatusCode()).isEqualTo(200);
		assertThat(result.getHeaders().get("Content-Type")).isEqualTo("application/json");
		with(result.getBody())
			.assertThat("$.message",
				anyOf(
					is("おはよう！ さあ、今日も伊織ちゃんの素晴らしさを、世界にひろめに行くわよ！"),
					is("アンタ、ちゃんと仕事してんの？　…私のプロデューサーなんだからしっかりしなさいよねっ！"),
					is("もっと予定を入れてもいいわよ。今ぐらいのペースじゃ、ヒマすぎてあくびが出ちゃうわ。"),
					is("あら～？結構役に立つのね♪"),
					is("次もこの調子でいくわよ！")
				));
  }
}
