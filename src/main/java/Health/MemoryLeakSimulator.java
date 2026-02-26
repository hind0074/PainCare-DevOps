package Health;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MemoryLeakSimulator {
     private static final Logger logger = LoggerFactory.getLogger(MemoryLeakSimulator.class);
    private static final List<byte[]> memoryLeak = new ArrayList<>();

    public static void leak() {
        memoryLeak.add(new byte[1024 * 1024]); // 1MB
        logger.info("MEMORY_LEAK_SIMULATED currentLeakSize={}MB", memoryLeak.size());
    }
}
