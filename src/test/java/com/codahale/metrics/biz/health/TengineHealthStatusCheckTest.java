package com.codahale.metrics.biz.health;

import static org.junit.Assert.*;

import org.junit.Test;

public class TengineHealthStatusCheckTest {

    @Test
    public void shouldReportUnconfiguredProbeAsUnhealthy() {
        TengineHealthStatusCheck check = new TengineHealthStatusCheck();
        assertFalse(check.execute().isHealthy());
    }

    @Test
    public void shouldInstantiateWithoutErrors() {
        TengineHealthStatusCheck check = new TengineHealthStatusCheck();
        assertNotNull(check);
    }

}
