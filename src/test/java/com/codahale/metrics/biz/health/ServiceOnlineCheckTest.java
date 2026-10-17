package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceOnlineCheckTest {

    @Test
    public void shouldReportUnconfiguredProbeAsUnhealthy() {
        ServiceOnlineCheck check = new ServiceOnlineCheck();
        assertFalse(check.execute().isHealthy());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        ServiceOnlineCheck check = new ServiceOnlineCheck();
        assertNotNull(check);
    }

}
