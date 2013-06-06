# test2

This lib mostly just a SPEC (compojure-style) with a few helper functions and default implementations. The goal is that anyone can write new third-party peices that fit into this to do cool new things.

Coming from clojure.test? Midje? Speclj. [Read here](#coming-from-other-libs) to see how test2 can help you.

## Spec

Running tests is split into 5 distinct responsibilities:

* [Defining tests](#defining-tests)
* [Assertions](#assertions)
* [Finding tests](#finding-tests)
* [Running tests](#running-tests)
* [Reporting on tests](#reporting-on-tests)

### Defining tests

Any Clojure function in your project that has a truthy `:test` metadata key is a valid test. To eliminate ambiguity, we refer to these as test-fns in this document. Example:

```clojure
(defn ^:test some-test []
  ...)
```

A test function may also have the metadata `:test-name`, a short (1-line) string representing what this test does. Example:

```clojure
(defn ^:test ^{:test-name "makes sure the system isn't totally hosed"}
  some-test []
  ...)
```

There are some helper functions for defining them. Look at `test2.transition/deftest` and `test2.transition/use-fixtures` if you're migrating from clojure.test.

TODO: perhaps add more helper functions in `test2.core` to define tests.

### Assertions

While a test is being run, it has access to a variable `*test-results*`. It's a seq, and every assertion should conj a test-result onto it.

A test-result is a Clojure map with these keys and values:

* `:status` - either `:pass` `:fail` or `:error`
* `:description` - an optional string if the user provided one for this individual assertion
* `:failure-report` - a Map with the following keys:
  * `:result` - a plain Clojure value, the result of the test being run
  * `:fn` - a raw form of the function that was used in the test
  * `:args` - seq of the post-eval'd arg-values passed to :fn
  * `:raw-args` - seq of the pre-eval'd arg-forms passed to :fn





### Finding tests

### Running tests

### Reporting on tests


## Coming from other libs

Gotta do this part later.
