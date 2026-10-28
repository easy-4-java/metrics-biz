/*
 * Copyright (c) 2018-present, easy-4-java (https://github.com/easy-4-java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codahale.metrics.biz.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Placeholder {@link HealthCheck} reserved for the future Tengine
 * connectivity probe.
 *
 * <p>The current implementation reports an unhealthy result until a probe
 * is configured. Subclasses may override {@link #check()} with real probe logic.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public class TengineHealthStatusCheck extends HealthCheck {

    /**
     * Reports that the Tengine probe is not configured.
     *
     * @return an unhealthy result explaining the missing probe.
     * @throws Exception reserved for future implementations.
     */
    @Override
    protected Result check() throws Exception {
        return Result.unhealthy("Tengine health probe is not configured");
    }

}