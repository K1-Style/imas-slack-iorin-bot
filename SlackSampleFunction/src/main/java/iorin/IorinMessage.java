package iorin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum IorinMessage {

	HELLO("おはよう！ さあ、今日も伊織ちゃんの素晴らしさを、世界にひろめに行くわよ！"),

	HOW_ARE_YOU("アンタ、ちゃんと仕事してんの？　…私のプロデューサーなんだからしっかりしなさいよねっ！"),

	I_AM_FINE("もっと予定を入れてもいいわよ。今ぐらいのペースじゃ、ヒマすぎてあくびが出ちゃうわ。"),

	THANK_YOU("あら～？結構役に立つのね♪"),

	SEE_YOU_AGAIN("次もこの調子でいくわよ！");

	private static final List<IorinMessage> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();

	private static final Random RANDOM = new Random();

	private final String message;

	IorinMessage(String text){
		message = text;
	}

	public static IorinMessage randomMessage()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public String getMessage() {
		return message;
	}

}
