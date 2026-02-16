package Health;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakSimulator {
    private static final List<byte[]> memory = new ArrayList<>();

    public static void leak() {
        memory.add(new byte[1024 * 1024]); // 1 MB ajouté à chaque appel
    }
}
