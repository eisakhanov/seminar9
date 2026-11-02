import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task4 {

    /** TODO 1: хосты с задержкой свыше 100 мс, без повторов,
     *  в лексикографическом порядке — одним конвейером. */
    static List<String> slowestHosts(List<Metric> metrics) {
        return null; // TODO 1
    }

    /** TODO 2: количество медленных метрик без сохранения списка (count). */
    static Long slowCount(List<Metric> metrics) {
        return null; // TODO 2
    }

    /** TODO 3: есть ли медленные метрики (anyMatch). */
    static Boolean hasSlow(List<Metric> metrics) {
        return null; // TODO 3
    }

    /** TODO 4: первая медленная метрика (findFirst). */
    static Optional<Metric> firstSlow(List<Metric> metrics) {
        return null; // TODO 4
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<Metric> metrics = ShiftData.metrics();

        check("slowestHosts", List.of("cache", "db-1.prod", "staging-db.prod"),
                slowestHosts(metrics));
        check("count", 4L, slowCount(metrics));
        check("anyMatch", true, hasSlow(metrics));
        check("findFirst", Optional.of(new Metric("db-1.prod", 120)),
                firstSlow(metrics));
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