# test2

This lib mostly just a SPEC (compojure-style) with a few helper functions and default implementations. The goal is that anyone can write new third-party peices that fit into this to do cool new things.

Coming from clojure.test? Midje? Speclj. [Read here](#coming-from-other-libs) to see how test2 can help you.

## Usage

### Defining tests

To define tests, add the `:test` key to a function's metadata. Tests can be defined anywhere. If they have a docstring, that will be used in the report. (Depending on the test-reporter you use, you may want to keep this short, like only one line).

```clojure
(defn ^:test some-test []
  ...)
```

There are some helper functions for defining tests. Look at `test2.transition/deftest` and `test2.transition/use-fixtures` if you're migrating from clojure.test.

**TODO:** perhaps add more helper functions in `test2.core` to define tests.

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

### High Level

Running tests is split into 5 distinct responsibilities:

* [Defining tests](#defining-tests)
* [Assertions](#assertions)
* [Finding tests](#finding-tests)
* [Running tests](#running-tests)
* [Reporting on tests](#reporting-on-tests)

### Flow

1. The user initiates running the tests somehow (lein, repl, whatever).
2. All tests are found, and filtered appropriately.
3. The ones allowed to run right now are passed to `run-test-fns`.
4. This function lazily maps each test-fn into a test-result.
5. It passes this lazy seq to the reporter, which then reports somehow.

### Defining tests

Any Clojure function in your project that has a truthy `:test` metadata key is a valid test. To eliminate ambiguity, we refer to these as test-fns in this document. If a test-fn has a docstring, that will be considered its "description" for the reporter.

### Assertions

While a test is being run, it has access to a variable `*test-results*`, a seq that every assertion should conj a test-result onto.

A test-result is a Clojure map with these keys and values:

* `:status` - either `:pass` `:fail` or `:error`
* `:description` - an optional string if the user provided one for this individual assertion
* `:failure-report` - a Map with the following keys:
  * `:result` - a plain Clojure value, the result of the assertion-function
  * `:fn` - a raw form of the function that was used in the test
  * `:args` - seq of the post-eval'd arg-values passed to :fn
  * `:raw-args` - seq of the pre-eval'd arg-forms passed to :fn

### Finding tests

There are some concrete helper functions for finding tests in your namespace.

* `test2.core/get-test-fns` - Return a seq of all test-fns in all this project's namespaces matching the given regex (loading them if necessary?), or all if no regex provided.
* `test2.core/run-test-fn` - Runs a single test-fn, wrapping `*test-results*` around it, and returning the test-results.
* `default-runner` - Just maps `get-test-fns` with `run-test-fn` and passes results to reporter.
* `default-randomized-runner` - Same as `default-runner` but runs tests in random order. Note: this does not affect the order they are reported in.

If you're running tests from in a REPL, you'd call `default-runner` as it is the highest-level entry-point.

TODO: figure out where the selector-fn fits into all this. I'm starting to think it needs to have a single stable high-level runner, that filters tests by sel-fn, finds the inner-runner, finds the report, and puts them all together. Seems legit? If you don't pass them, they'll be found out from your configs.

### Running tests

`run-all-tests` will run all tests, finds your reporter, and passes the results to it. Your reporter should do the rest.

### Reporting on tests


## Coming from other libs

Gotta do this part later.






Questions:

Where are all the places you can specify things?

- Runner can be specified by passing it to `run-tests`
- Runner can be specified by putting it in `project.clj`
