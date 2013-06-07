# test2

Test2 is a modular, composable testing lib for Clojure.

It has a ring-like [SPEC](SPEC.md), so that different parts can be
changed out for alternatives. For example, if you want a different
reporter function, you can write one or use another reporter lib that
conforms to this spec.

Coming from clojure.test? Midje? Speclj? [Read here](#coming-from-other-libs) to see how test2 can help you.

## Install

```clojure
[test2 "1.0.0"]
```

## Example

```clojure
(use 'test2.core)

(defn ^:test user-creation
  "Creating users adds them to the list, but they're disable by default." []
  (expect empty? (all-users))
  (create-user "bob")
  (expect = 1 (count (all-users)))
  (expect truthy? (:disabled (first (all-users)))))
```

Read more about [Defining Tests](../../wiki/Defining-Tests).

### Running tests

```bash
$ lein test2
```

```clojure
(use 'test2.core)

(run-all-tests)
(run-ns-tests 'foo.test.core 'foo.test.other)
(run-matching-tests :migration)
```

### Using different runners or reporters

Put this in your `project.clj` file:

```clojure
:test2 {:runner fancy-runner.core/some-runner-fn
        :reporter fancy-reporter.core/some-reporter-fn}
```

### Defining and runnign suites

If you put this in `project.clj`:

```clojure
:test2 {:suites {:database :db}}
```

Then you can do:

```bash
lein test2 :suite :database
```

<!-- Each suite's val is a fn. For each test function, its var is passed, and if its run, then its run. -->

## Coming from other libs

Gotta do this part later.

## License

Copyright © 2013 evanescence

Distributed under the Eclipse Public License, the same as Clojure.
