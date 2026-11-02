import java.util.List;

public final class ShiftData {
    private ShiftData() { }

    /** Метрики ночной смены — фиксированный набор для воспроизводимого контроля. */
    public static List<Metric> metrics() {
        return List.of(
                new Metric("db-1.prod",       120),
                new Metric("db-2.prod",        45),
                new Metric("cache",           260),
                new Metric("staging-web",      80),
                new Metric("staging-db.prod", 150),
                new Metric("api.prod",         95),
                new Metric("cache",           310));
    }
}