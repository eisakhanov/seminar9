import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task3 {

    // -------------------- дано: не изменяйте --------------------

    static final List<Shift> SHIFTS = List.of(
            new Shift("anna", List.of(
                    new Metric("db-1.prod", 120),
                    new Metric("cache", 90))),
            new Shift("oleg", List.of(
                    new Metric("staging-web", 80),
                    new Metric("api.prod", 70))),
            new Shift("irina", List.of()));

    static Optional<Shift> findShift(String engineer) {
        return SHIFTS.stream()
                .filter(s -> s.engineer().equals(engineer))
                .findFirst();
    }

    static Optional<Metric> findPeak(Shift shift) {
        return shift.metrics().stream()
                .max(Comparator.comparingInt(Metric::latency));
    }

    static String pageOf(String host) {
        return "runbook/" + host;
    }

    // -------------------- задание --------------------

    /**
     * TODO: конвейер flatMap / filter / map / orElse, без if != null.
     * Учитываются только метрики с задержкой свыше 100 мс;
     * отсутствие смены, метрики или страницы → "нет данных".
     */
    static String describeShift(String engineer) {
        return null; // TODO
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        check("смена anna (пик 120)", "runbook/db-1.prod",
                describeShift("anna"));
        check("смена oleg (пик 80, отфильтруется)", "нет данных",
                describeShift("oleg"));
        check("смена irina (метрик нет)", "нет данных",
                describeShift("irina"));
        check("смены нет (boris)", "нет данных",
                describeShift("boris"));
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