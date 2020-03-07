package org.oznecniv97.telegramserverplugin.telegram.commandbot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RuntimeTelegramException extends RuntimeException {

	private static final long serialVersionUID = 3067465983547506844L;

	private final TelegramError error;

	public RuntimeTelegramException(TelegramError error, Throwable e) {
		super(e);
		this.error = error;
	}

	@Override
	public String getMessage() {
		String ret = super.getMessage();
		if(error!=null) {
			ret = error.getMessage();
		}
		return ret;
	}

}
