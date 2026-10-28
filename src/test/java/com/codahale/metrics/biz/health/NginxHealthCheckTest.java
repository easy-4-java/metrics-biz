package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Test;

public class NginxHealthCheckTest {

    @Test
    public void shouldReportUnhealthyUntilProbeIsConfigured() {
        NginxHealthCheck check = new NginxHealthCheck();

        HealthCheck.Result result = check.execute();
        assertFalse(result.isHealthy());
        assertEquals("Nginx health probe is not configured", result.getMessage());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        NginxHealthCheck check = new NginxHealthCheck();
        assertNotNull(check);
    }

}
