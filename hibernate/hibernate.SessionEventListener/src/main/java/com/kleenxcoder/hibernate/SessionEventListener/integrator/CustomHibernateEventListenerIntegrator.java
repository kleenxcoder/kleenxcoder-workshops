package com.kleenxcoder.hibernate.SessionEventListener.integrator;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import com.kleenxcoder.hibernate.SessionEventListener.listener.CustomPostInsertEventListener;
import com.kleenxcoder.hibernate.SessionEventListener.listener.CustomPostUpdateEventListener;
import com.kleenxcoder.hibernate.SessionEventListener.listener.CustomPreDeleteEventListener;

public class CustomHibernateEventListenerIntegrator implements Integrator {
	
	public static final CustomHibernateEventListenerIntegrator INSTANCE =
	        new CustomHibernateEventListenerIntegrator();

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		final EventListenerRegistry eventListenerRegistry =
                serviceRegistry.getService(EventListenerRegistry.class);
 
		
        eventListenerRegistry.appendListeners(
            EventType.POST_INSERT,
            new CustomPostInsertEventListener()
        );
         
        eventListenerRegistry.appendListeners(
            EventType.POST_UPDATE,
            new CustomPostUpdateEventListener()
        );
         
        eventListenerRegistry.appendListeners(
            EventType.PRE_DELETE,
            new CustomPreDeleteEventListener()
        );		
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
	}

}
