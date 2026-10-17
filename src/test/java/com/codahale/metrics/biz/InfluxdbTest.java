/*
 * Copyright (c) 2018 (https://github.com/loong10k).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codahale.metrics.biz;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @className	： InfluxdbTest
 * @description	：https://github.com/influxdata/influxdb-java
 * @author [@Loong Wan](https://github.com/loong10k)
 * @date		： 2017年9月12日 下午11:33:12
 * @version 	V1.0
 */
public class InfluxdbTest {
	
	@Test
	public void testName() throws Exception {
		AtomicInteger queries = new AtomicInteger();
		AtomicInteger writes = new AtomicInteger();
		HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
		server.createContext("/", exchange -> {
			String path = exchange.getRequestURI().getPath();
			if ("/write".equals(path)) {
				writes.incrementAndGet();
				exchange.sendResponseHeaders(204, -1);
			} else if ("/query".equals(path)) {
				queries.incrementAndGet();
				byte[] response = "{\"results\":[{\"statement_id\":0}]}".getBytes(StandardCharsets.UTF_8);
				exchange.getResponseHeaders().set("Content-Type", "application/json");
				exchange.sendResponseHeaders(200, response.length);
				exchange.getResponseBody().write(response);
			} else {
				exchange.sendResponseHeaders(404, -1);
			}
			exchange.close();
		});
		server.start();
		try {
			InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:" + server.getAddress().getPort(), "admin", "admin");
			try {
				String dbName = "aTimeSeries";
				influxDB.createDatabase(dbName);

				BatchPoints batchPoints = BatchPoints.database(dbName)
						.tag("async", "true")
						.retentionPolicy("autogen")
						.consistency(ConsistencyLevel.ALL)
						.build();
				Point point1 = Point.measurement("cpu")
						.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
						.addField("idle", 90L)
						.addField("user", 9L)
						.addField("system", 1L)
						.build();
				Point point2 = Point.measurement("disk")
						.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
						.addField("used", 80L)
						.addField("free", 1L)
						.build();
				batchPoints.point(point1);
				batchPoints.point(point2);
				influxDB.write(batchPoints);
				Query query = new Query("SELECT idle FROM cpu", dbName);
				influxDB.query(query);
				influxDB.deleteDatabase(dbName);
				assertEquals(3, queries.get());
				assertEquals(1, writes.get());
			} finally {
				influxDB.close();
			}
		} finally {
			server.stop(0);
		}
	}
	
}
