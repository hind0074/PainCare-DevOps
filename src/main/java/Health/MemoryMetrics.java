package Health;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MemoryMetrics {

    public static void registerMemoryMetrics(PrometheusMeterRegistry registry) {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        Gauge.builder("memory_used_bytes", memoryBean, m -> m.getHeapMemoryUsage().getUsed())
             .description("Heap memory used in bytes")
             .register(registry);

        Gauge.builder("memory_max_bytes", memoryBean, m -> m.getHeapMemoryUsage().getMax())
             .description("Max heap memory in bytes")
             .register(registry);
    }
}

