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

The SPEC:

A test is any function anywhere in your project whose metadata contains `^:test`. (Does it go without saying that dependencies are ignored and only your namespaces are searched?)

A test function may also have the metadata `:test-name`, a short (1-line) string representing what this test does.

There are some helper functions for defining them. Look at `test2.transition/deftest` and `test2.transition/use-fixtures` if you're migrating from clojure.test.

TODO: perhaps add more helper functions in `test2.core` to define tests.

### Assertions

### Finding tests

### Running tests

### Reporting on tests


## Coming from other libs

Gotta do this part later.
