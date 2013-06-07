# test2

This lib is mostly just a SPEC (ring-style) with a few helper functions and default implementations. The goal is that anyone can write new third-party peices that fit into this to do cool new things.

Coming from clojure.test? Midje? Speclj? [Read here](#coming-from-other-libs) to see how test2 can help you.

## Usage

### Writing tests

```clojure
(use 'test2.core)

(defn ^:test user-creation
  "Creating users adds them to the list, but they're disable by default." []
  (expect empty? (all-users))
  (create-user "bob")
  (expect = 1 (count (all-users)))
  (expect truthy? (:disabled (first (all-users)))))
```

Any function in your project with the `:test` key on its metadata is a valid test. If they have a docstring, that will be used in the report.

As a shortcut, you can use `test2.core/deftest` to omit the params-list and the metadata:

```clojure
(use 'test2.core)

(deftest user-creation
  "Creating users adds them to the list, but they're disable by default."
  (expect empty? (all-users))
  ...)
```

If you're coming from clojure.test, you can use `deftest`, `use-fixtures`, and `is`, in `test2.transition.test`.

**TODO**: Make some assertion functions to help transition from Midje or Speclj maybe?

### Running tests

```bash
lein test2
```

Or if you want to run it from the REPL:

```clojure
(test2.core/run-tests) ;; runs all tests
(test2.core/run-ns-tests 'namespace1 'namespace2) ;; limit to namespaces
(test2.core/run-matching-tests f) ;; runs only tests whose metadata passes (f)
(test2.core/run-suite-tests suite-name) ;; runs only tests in given suite
```

### Using different assertion functions

Don't like `expect`? Just require another lib that conforms to test2's spec, such as: **TODO**.


### Using different runners or reporters

Put this in your `project.clj` file:

```clojure
:test2 {:runner fancy-runner.core/some-runner-fn
        :reporter fancy-reporter.core/some-reporter-fn}
```

### Defining and runnign suites

You can do:

```bash
lein test2 -suite :suite-name
```

If you put this in `test/test2_config.clj`:

```clojure
(def suites {:database :db
             :default (complement :db)
             :all true)
```

With this config:

- running the `:all` suite runs every test
- running the `:database` suite runs only tests defined with `:db` in its metadata
- running the `:default` suite runs only tests defined *without* `:db` in its metadata

The `:default` suite is what's run when you don't specify a suite. It defaults to `true`, meaning run every test.




***
Ignore stuff below this
***








### Finding tests

There are some concrete helper functions for finding tests in your namespace.

* `test2.core/get-test-fns` - Return a seq of all test-fns in all this project's namespaces matching the given regex (loading them if necessary?), or all if no regex provided.
* `test2.core/run-test-fn` - Runs a single test-fn, wrapping `*test-results*` around it, and returning the test-results.
* `default-runner` - Just maps `get-test-fns` with `run-test-fn` and passes results to reporter.
* `default-randomized-runner` - Same as `default-runner` but runs tests in random order. Note: this does not affect the order they are reported in.

If you're running tests from in a REPL, you'd call `default-runner` as it is the highest-level entry-point.

TODO: figure out where the selector-fn fits into all this. I'm starting to think it needs to have a single stable high-level runner, that filters tests by sel-fn, finds the inner-runner, finds the report, and puts them all together. Seems legit? If you don't pass them, they'll be found out from your configs.









## Coming from other libs

Gotta do this part later.






Questions:

Where are all the places you can specify things?

- Runner can be specified by passing it to `run-tests`
- Runner can be specified by putting it in `project.clj`

- Reporter can be specified by passing it to `run-tests`
- Reporter can be specified by putting it in `project.clj`

- Suites can be specified by making it a map in `test/test2_config.clj/suites`
