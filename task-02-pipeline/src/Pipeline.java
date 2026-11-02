import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Мини-стрим: конвейер обработки списков.
 * Каждая ступень возвращает НОВЫЙ конвейер — этот не изменяется
 * и может использоваться повторно (настоящий Stream одноразовый).
 */
public class Pipeline<T> {

    private final List<T> items;

    private Pipeline(List<T> items) {
        this.items = items;
    }

    /** Начало конвейера — копирует источник. */
    public static <T> Pipeline<T> from(List<T> source) {
        return new Pipeline<>(List.copyOf(source));
    }

    /** TODO 1: оставить элементы, для которых p.test возвращает true;
     *  вернуть новый конвейер. */
    public Pipeline<T> filter(Predicate<T> p) {
        throw new UnsupportedOperationException("TODO 1"); // TODO 1
    }

    /** TODO 2: применить f к каждому элементу; вернуть новый конвейер. */
    public <R> Pipeline<R> map(Function<T, R> f) {
        throw new UnsupportedOperationException("TODO 2"); // TODO 2
    }

    /** TODO 3: acc = init; для каждого элемента acc = op.apply(acc, t); вернуть acc. */
    public <U> U reduce(U init, BiFunction<U, T, U> op) {
        throw new UnsupportedOperationException("TODO 3"); // TODO 3
    }

    /** TODO 4: количество элементов, удовлетворяющих p, — через filter и reduce. */
    public int countBy(Predicate<T> p) {
        throw new UnsupportedOperationException("TODO 4"); // TODO 4
    }

    /** Элементы конвейера. */
    public List<T> toList() {
        return items;
    }
}