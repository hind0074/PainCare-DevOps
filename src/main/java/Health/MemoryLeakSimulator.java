package Health;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

public class MemoryLeakSimulator {

    private static final Logger logger = LoggerFactory.getLogger(MemoryLeakSimulator.class);
    private static final List<byte[]> memoryLeak = new ArrayList<>();

    private static final Tracer tracer =
        GlobalOpenTelemetry.getTracer("PainCareTracer");

    public static void leak() {

        Span span = tracer.spanBuilder("MemoryLeakSimulator.leak").startSpan();

        try (Scope scope = span.makeCurrent()) {

            memoryLeak.add(new byte[1024 * 1024]); // 1MB

            int currentSize = memoryLeak.size();

            logger.info("MEMORY_LEAK_SIMULATED currentLeakSize={}MB", currentSize);

            span.setAttribute("leak.size.mb", currentSize);

        } finally {
            span.end();
        }
    }
}