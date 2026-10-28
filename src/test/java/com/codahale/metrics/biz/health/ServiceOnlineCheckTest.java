package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Test;

public class ServiceOnlineCheckTest {

    @Test
    public void shouldReportUnhealthyUntilProbeIsConfigured() {
        ServiceOnlineCheck check = new ServiceOnlineCheck();

        HealthCheck.Result result = check.execute();
        assertFalse(result.isHealthy());
        assertEquals("Service health probe is not configured", result.getMessage());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        ServiceOnlineCheck check = new ServiceOnlineCheck();
        assertNotNull(check);
    }

}
