# test2

Test2 is a modular, composable testing lib for Clojure.

It has a single SPEC, and any functions which conform to the spec can be used.

You can use any assertion functions that conform

Test2 is to testing as Ring is to web apps.

This lib is mostly just a SPEC (ring-style) with a few helper functions and default implementations. The goal is that anyone can write new third-party peices that fit into this to do cool new things.

Coming from clojure.test? Midje? Speclj? [Read here](#coming-from-other-libs) to see how test2 can help you.

## Install

`[test2 "1.0.0"]`

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
(use 'test2.core)

;; each of these functions take :reporter and :runner keys, as fns.

(run-all-tests)
(run-ns-tests 'foo.test.core 'foo.test.other)
(run-matching-tests :db) ;; runs only tests which pass (f test-vars-metadata)
(run-suite-tests :db) ;; see Suites section
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
