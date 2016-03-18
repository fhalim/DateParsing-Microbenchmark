package net.fawad.microbenchmarking.bm;

import java.time.ZonedDateTime;
import java.util.Date;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class DateBenchmarks {

	@State(Scope.Thread)
	public static class ThreadState {

		DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();
	}


	@Benchmark
	public void parseEpochDate(Blackhole bh) {
		int timestamp = 1458320867;
		bh.consume(new Date(timestamp));
	}

	@Benchmark
	public void parseISODateWithJoda(Blackhole bh, ThreadState state) {
		String timestamp = "2016-03-18T17:10:26+00:00";
		bh.consume(state.parser.parseDateTime(timestamp));
	}

	@Benchmark
	public void parseISODateWithJavaTime(Blackhole bh, ThreadState state) {
		String timestamp = "2016-03-18T17:10:26+00:00";
		bh.consume(ZonedDateTime.parse(timestamp));
	}
}
