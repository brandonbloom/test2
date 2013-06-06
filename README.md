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

Any Clojure function in your project that has a truthy `:test` metadata key is a valid test. To eliminate ambiguity, we refer to these as test-fns in this document.

Example:

```clojure
(defn ^:test some-test []
  ...)
```

A test function may also have the metadata `:test-name`, a short (1-line) string representing what this test does.

Example:

```clojure
(defn ^:test ^{:test-name "makes sure the system ain't totally hosed"}
  some-test []
  ...)
```

There are some helper functions for defining them. Look at `test2.transition/deftest` and `test2.transition/use-fixtures` if you're migrating from clojure.test.

TODO: perhaps add more helper functions in `test2.core` to define tests.

### Assertions

Inside a test-fn, you

### Finding tests

### Running tests

### Reporting on tests


## Coming from other libs

Gotta do this part later.
