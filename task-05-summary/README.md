# Задание 5 · Сводка по хостам смены

## Постановка

Дано: 9 метрик за смену на трех хостах (см. `src/MetricData.java`);
у одного хоста несколько замеров за ночь.

Реализуйте четыре метода — **одно выражение на каждый**:

1. `byHost` — все метрики, сгруппированные по хосту
   (`Map<String, List<Metric>>`);
2. `countsByHost` — количество метрик по хостам (`Map<String, Long>`);
3. `busiestHost` — хост с наибольшим числом метрик
   (`Optional<String>`, сведение через `max(comparingByValue)`);
4. `latencySumByHost` — суммарная задержка по каждому хосту
   (`Map<String, Integer>`, `summingInt`).

## Указания

- Пункты 2 и 4 — `groupingBy` с **downstream**-коллектором: `counting()`
  и `summingInt(Metric::latency)`; без него группа — список, а не число.
- Пункт 3: `countsByHost(metrics).entrySet().stream()` и
  `Map.Entry.comparingByValue()`;
- Явные типы у лямбды в `max(...)` — компилятор не всегда выводит их в
  цепочке; при отказе укажите `Map.Entry<String, Long>` явно.

## Контроль

```
javac -encoding UTF-8 -d out src/*.java
java -cp out Task5
```

При правильном решении:

```
OK   byHost: db-1.prod
OK   byHost: cache
OK   byHost: api.prod
OK   countsByHost
OK   busiestHost
OK   latencySumByHost
```

Ожидания: db-1.prod — 4 метрики, cache — 3, api.prod — 2; самый активный
хост — db-1.prod; суммарные задержки — db-1.prod 505, cache 555,
api.prod 260.

## Дополнительно

Хост с максимальной **суммарной** задержкой (`heaviestHost`). Сравните с
`busiestHost`: у db-1.prod больше метрик, но у cache больше сумма —
«самый активный» и «самый нагруженный» — разные вопросы.