import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Task6 {

    // -------------------- дано: не изменяйте --------------------

    /** Генерация n метрик: хосты host-0..host-49, задержки 0..999. */
    static List<Metric> generate(int n) {
        return ThreadLocalRandom.current()
                .ints(n, 0, 1000)
                .mapToObj(lat -> new Metric("host-" + lat % 50, lat))
                .collect(Collectors.toList());
    }

    // -------------------- задание --------------------

    /** TODO 1: параллельный поток; хосты метрик с задержкой > 500
     *  в общий ArrayList через forEach — намеренная гонка. */
    static List<String> collectRacy(List<Metric> metrics) {
        return null; // TODO 1
    }

    /** TODO 2: то же корректно: map + collect(Collectors.toList()). */
    static List<String> collectSafe(List<Metric> metrics) {
        return null; // TODO 2
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<Metric> metrics = generate(100_000);
        long expected = metrics.stream().filter(m -> m.latency() > 500).count();
        System.out.println("метрик: " + metrics.size() + ", медленных (>500): " + expected);

        System.out.println();
        System.out.println("-- гонка: три прогона --");
        for (int i = 1; i <= 3; i++) {
            try {
                List<String> racy = collectRacy(metrics);
                if (racy == null) {
                    System.out.println("прогон " + i + ": реализуйте TODO 1");
                    break;
                }
                System.out.println("прогон " + i + ": size=" + racy.size()
                        + (racy.size() == expected ? " — совпало (повезло!)" : " — РАСХОЖДЕНИЕ"));
            } catch (RuntimeException e) {
                System.out.println("прогон " + i + ": " + e.getClass().getSimpleName()
                        + " — ArrayList не пережил параллельную запись");
            }
        }

        System.out.println();
        System.out.println("-- безопасная версия --");
        long t0 = System.nanoTime();
        List<String> safe = collectSafe(metrics);
        long safeMs = (System.nanoTime() - t0) / 1_000_000;
        if (safe == null) {
            System.out.println("реализуйте TODO 2");
        } else {
            check("safe.size", expected, (long) safe.size());
            System.out.println("время безопасной версии: " + safeMs + " мс");
        }

        System.out.println();
        System.out.println("-- сравнение времени (пункт 4) --");
        // TODO 3: измерьте время последовательной (metrics.stream()) и
        // параллельной (metrics.parallelStream()) сборки через System.nanoTime
        // и выведите оба числа в миллисекундах.
    }

    static void check(String name, Object expected, Object actual) {
        if (actual == null) {
            System.out.println("FAIL " + name + ": реализуйте TODO");
        } else if (Objects.equals(expected, actual)) {
            System.out.println("OK   " + name);
        } else {
            System.out.println("FAIL " + name + ": ожидалось " + expected
                    + ", получено " + actual);
        }
    }
}