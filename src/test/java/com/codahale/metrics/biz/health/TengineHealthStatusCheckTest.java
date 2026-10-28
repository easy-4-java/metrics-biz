package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import com.codahale.metrics.health.HealthCheck;
import org.junit.Test;

public class TengineHealthStatusCheckTest {

    @Test
    public void shouldReportUnhealthyUntilProbeIsConfigured() {
        TengineHealthStatusCheck check = new TengineHealthStatusCheck();

        HealthCheck.Result result = check.execute();
        assertFalse(result.isHealthy());
        assertEquals("Tengine health probe is not configured", result.getMessage());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        TengineHealthStatusCheck check = new TengineHealthStatusCheck();
        assertNotNull(check);
    }

}
