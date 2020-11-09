package com.kleenxcoder.hibernate.SessionEventListener.integrator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;

public class IntegrationProvider implements IntegratorProvider{

	@Override
	public List<Integrator> getIntegrators() {
		List<Integrator> integrators = new ArrayList<Integrator>();
		integrators.add(new CustomHibernateEventListenerIntegrator());
		return integrators;
	}

}
