package com.kleenxcoder.hibernate.SessionEventListener.listener;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;

public class CustomPreDeleteEventListener implements PreDeleteEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
//		event.getSession().getEventListenerManager().addListener(listeners);
		event.getSession().addEventListeners(new CustomStatisticalLoggingSessionEventListener());
		return false;
	}

}
