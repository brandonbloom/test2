Test2 Spec (1.0)
================

Overview
--------

Testing is broken down into the following responsibilities:

- Defining tests (Definer)
  - Making assertions within them (Asserter)

- Finding tests
  - Filtering which ones to run
  - Running them (Runner)
  - Reporting the results (Reporter)


### Definer

Definers are just helpers to create test-fns. They're not strictly
necessary, since it's pretty easy to make a test-fn like this:

```clojure
(defn ^:test some-test [] ...)
```

However, it's possible to get creative and make something as complex
as RSpec's `describe` and `it` using macros, by `def`ing new vars in
the namespace that have ^:test.


### Asserters

During execution of a test-fn, `*assertion-results*` is a ref to a
vec. Asserters are helper functions that should take some assertion,
evaluate it, and conj an assertion-result onto it.

Note: if an unexpected error occurred (not specified by the
assertion), the assertion-result's :status should be :error.


### Runner

Signature: (runner ns-syms matcher-fn) => test-results

Runners are functions that take in user-options and return a seq of
test-results.

There are two user-options right now: seq of namespace-syms, and a
matcher-fn.

If namespace-syms is non-nil, find and run tests only inside these
namespaces. Otherwise find and run tests on every namespace within the
project.

If matcher-fn is nil, run all found tests. Otherwise, call the
function on each test-fn's metadata, and only run tests for which this
passes.

There are helpers available in `test2.api.runners` to make it easy to
find test-fns and turn them into test-results.

Runners are what you'd write if you want to make a lib that runs test
concurrently, or watches for changes and re-runs tests as you save
your files.


### Reporter

Signature: (reporter test-results)

Reporter are functions that take a seq of test-results and do
something special with them.

This is what you'd write if you want to turn a test suite's results
into HTML, or just print dots, or do something fancy like difftest.



Types
-----

### test-fn
Type: Var

Any Clojure function in your project that has a truthy `:test`
metadata key is a test-fn. If it has a docstring, that will be
considered its "description" for the reporter.


### assertion-result
Type: Map

```
:status
  (Required, Keyword)
  :pass if the assertion passed
  :fail if the assertion failed
  :error if there was an _unexpected_ exception

:description
  (Optional, String)
  Used by the reporter to describe the assertion

:file
  (Required, String)
  The file the assertion was in.

:line
  (Required, Number)
  The line the assertion was on.

:exception
  (Required, Only for :error, Exception)
  The exception that unexpectedly occurred

:result
  (Required, Only for :fail, Any type)
  The result of the assertion

:fn
  (Required, Only for :fail or :error, Raw form)
  The literal callable-form that was used in the assertion

:raw-args
  (Required, Only for :fail or :error, Vector of raw forms)
  The args before they were evaluated in the assertion

:args
  (Required, Only for :fail, Vector of any types)
  The args after they were evaluated in the assertion
```

### test-result
Type: Map

```
:results
  (Required, Seq of assertion-results)
  Results of every assertion in the function.

:test
  (Required, Var)
  The test-fn's var.
```
