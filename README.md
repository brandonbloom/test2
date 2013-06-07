# test2

This lib is mostly just a SPEC (ring-style) with a few helper functions and default implementations. The goal is that anyone can write new third-party peices that fit into this to do cool new things.

Coming from clojure.test? Midje? Speclj? [Read here](#coming-from-other-libs) to see how test2 can help you.

## Usage

### Defining tests

To define tests, add the `:test` key to a function's metadata. Tests can be defined anywhere. If they have a docstring, that will be used in the report.

```clojure
(defn ^:test some-test []
  ...)
```

There are some helper functions for defining tests. Look at `test2.transition/deftest` and `test2.transition/use-fixtures` if you're migrating from clojure.test.

**TODO:** Add more helper functions in `test2.core` to define tests.

### Making assertions inside tests

There are some helper functions for assertions. Look at `test2.transition/is` and `test2.transition/are` if you're migrating from clojure.test.

**TODO:** Add more! Especially if coming from Speclj or Midje.

### Running tests

```bash
lein test2
lein test2 -suite :suite-name
```

Or if you want to run it from the REPL:

```clojure
(test2.core/run-tests) ;; runs all tests
(test2.core/run-ns-tests 'namespace1 'namespace2) ;; limit to namespaces
(test2.core/run-matching-tests f) ;; runs only tests whose metadata passes (f)
(test2.core/run-suite-tests suite-name) ;; runs only tests in given suite
```








## Spec

### Flow

1. The user initiates running the tests somehow (lein, repl, whatever).
2. All tests are found, and filtered appropriately.
3. The ones allowed to run right now are passed to `run-test-fns`.
4. This function lazily maps each test-fn into a test-result.
5. It passes this lazy seq to the reporter, which then reports somehow.

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
