package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import org.junit.Test;

public class NginxHealthCheckTest {

    @Test
    public void shouldReportUnconfiguredProbeAsUnhealthy() {
        NginxHealthCheck check = new NginxHealthCheck();
        assertFalse(check.execute().isHealthy());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        NginxHealthCheck check = new NginxHealthCheck();
        assertNotNull(check);
    }

}
