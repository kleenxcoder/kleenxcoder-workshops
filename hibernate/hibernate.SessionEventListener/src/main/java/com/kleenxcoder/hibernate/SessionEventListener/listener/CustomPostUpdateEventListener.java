package com.kleenxcoder.hibernate.SessionEventListener.listener;

import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPostUpdateEventListener implements PostUpdateEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		log.debug("called onPostUpdate ...");
	}

}
