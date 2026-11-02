import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Task5 {

    /** TODO 1: метрики, сгруппированные по хосту. */
    static Map<String, List<Metric>> byHost(List<Metric> metrics) {
        return null; // TODO 1
    }

    /** TODO 2: количество метрик по хостам. */
    static Map<String, Long> countsByHost(List<Metric> metrics) {
        return null; // TODO 2
    }

    /** TODO 3: хост с наибольшим числом метрик. */
    static Optional<String> busiestHost(List<Metric> metrics) {
        return null; // TODO 3
    }

    /** TODO 4: суммарная задержка по каждому хосту. */
    static Map<String, Integer> latencySumByHost(List<Metric> metrics) {
        return null; // TODO 4
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<Metric> metrics = MetricData.metrics();

        check("byHost: db-1.prod", 4, sizeAt(byHost(metrics), "db-1.prod"));
        check("byHost: cache",     3, sizeAt(byHost(metrics), "cache"));
        check("byHost: api.prod",  2, sizeAt(byHost(metrics), "api.prod"));
        check("countsByHost", Map.of("db-1.prod", 4L, "cache", 3L, "api.prod", 2L),
                countsByHost(metrics));
        check("busiestHost", Optional.of("db-1.prod"), busiestHost(metrics));
        check("latencySumByHost",
                Map.of("db-1.prod", 505, "cache", 555, "api.prod", 260),
                latencySumByHost(metrics));
    }

    static Integer sizeAt(Map<String, List<Metric>> map, String key) {
        if (map == null) return null;
        List<Metric> list = map.get(key);
        return list == null ? null : list.size();
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