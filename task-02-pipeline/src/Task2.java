import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Task2 {

    public static void main(String[] args) {
        List<Metric> metrics = ShiftData.metrics();
        Pipeline<Metric> shift = Pipeline.from(metrics);   // один конвейер на все проверки

        check("hosts", List.of("db-1.prod", "cache", "staging-db.prod", "cache"),
                tryPipe(() -> shift.filter(m -> m.latency() > 100)
                        .map(Metric::host)
                        .toList()));

        check("total", 1060,
                tryPipe(() -> shift.reduce(0, (acc, m) -> acc + m.latency())));

        check("slowCount", 4,
                tryPipe(() -> shift.countBy(m -> m.latency() >= 100)));

        check("fastCount", 3,
                tryPipe(() -> shift.countBy(m -> m.latency() < 100)));

        check("reduce на пустом", 42,
                tryPipe(() -> Pipeline.from(List.<Metric>of())
                        .reduce(42, (acc, m) -> acc + m.latency())));
    }

    /** Выполняет цепочку; UnsupportedOperationException от нереализованных
     *  ступеней превращается в null — контроль печатает FAIL «реализуйте TODO». */
    static <R> R tryPipe(Supplier<R> pipe) {
        try {
            return pipe.get();
        } catch (UnsupportedOperationException e) {
            return null;
        }
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