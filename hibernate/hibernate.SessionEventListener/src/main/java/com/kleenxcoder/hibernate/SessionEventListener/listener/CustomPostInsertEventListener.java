package com.kleenxcoder.hibernate.SessionEventListener.listener;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPostInsertEventListener implements PostInsertEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		log.debug("called onPostInsert ...");
	}

}
