import java.util.List;

public final class MetricData {
    private MetricData() { }

    /** Метрики ночной смены по трем хостам — фиксированный набор для воспроизводимого контроля. */
    public static List<Metric> metrics() {
        return List.of(
                new Metric("db-1.prod", 120),
                new Metric("db-1.prod",  45),
                new Metric("db-1.prod", 260),
                new Metric("db-1.prod",  80),
                new Metric("cache",     310),
                new Metric("cache",     150),
                new Metric("cache",      95),
                new Metric("api.prod",  200),
                new Metric("api.prod",   60));
    }
}