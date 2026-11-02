import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Task1 {

    static final int THRESHOLD = 100;

    /** TODO 1: задержка не менее THRESHOLD */
    static Predicate<Metric> slow() {
        return null; // TODO 1
    }

    /** TODO 2: хост заканчивается на ".prod". */
    static Predicate<Metric> prod() {
        return null; // TODO 2
    }

    /** TODO 3: конъюнкция slow и prod — одним выражением через and. */
    static Predicate<Metric> slowProd() {
        return null; // TODO 3
    }

    /** TODO 4: НЕ медленный ИЛИ хост начинается с "staging-" — одним выражением. */
    static Predicate<Metric> fastOrStaging() {
        return null; // TODO 4
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<Metric> metrics = ShiftData.metrics();
        System.out.println("данные: " + metrics.size() + " метрик, порог " + THRESHOLD);

        check("slow",          4, count(metrics, slow()));
        check("prod",          4, count(metrics, prod()));
        check("slowProd",      2, count(metrics, slowProd()));
        check("fastOrStaging", 4, count(metrics, fastOrStaging()));

        Integer sp = count(metrics, slowProd());
        Integer fs = count(metrics, fastOrStaging());
        if (sp != null && fs != null) {
            System.out.println("дополнительно: slowProd + fastOrStaging = " + (sp + fs)
                    + ", размер списка = " + metrics.size() + " — объясните расхождение");
        }
    }

    static Integer count(List<Metric> metrics, Predicate<Metric> p) {
        if (p == null) return null;
        int n = 0;
        for (Metric m : metrics) if (p.test(m)) n++;
        return n;
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