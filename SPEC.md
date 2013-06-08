Test2 Spec (1.1)
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


### Asserter

During execution of a test-fn, `*assertion-results*` is a ref to a
vec. Asserters are helper functions that should take some assertion,
evaluate it, and conj an assertion-result onto it.

Note: if an unexpected error occurred (not specified by the
assertion), the assertion-result's :status should be :error.


### Runner

Signature: `(runner reporter ns-syms matcher-fn) => pass?`

Runners are functions that take in a Reporter, some user-given options
on which tests to run, and are expected to find tests and turn them
into test-results, and pass these test-results to the Reporter.

There are two user-given options right now: a seq of namespace-syms,
and a matcher-fn.

If namespace-syms is non-nil, find and run tests only inside these
namespaces. Otherwise find and run tests on every namespace within the
project.

If matcher-fn is nil, run all found tests. Otherwise, call the
function on each test-fn's metadata, and only run tests for which this
passes.

There are helpers available in `test2.api.runners` to make it easy to
find test-fns and turn them into test-results suitable for passing
into the Reporter.

If the Runner returns, its return value's truthiness is used to
determine whether the test passed or failed.

Runners are what you'd write if you want to make a lib that runs test
concurrently, or watches for changes and re-runs tests as you save
your files.


### Reporter

Signature: `(reporter test-results) => pass?`

Reporter are functions that take a seq of test-results and do
something special with them.

For a given test-fn, the reporter should consider its docstring to be
the description of the test. If the test-fn has no docstring, the
reporter may use the test-fn's name itself.

The reporter must NOT exit the test. It should return truthy or falsy
depending on if all tests passed or any failed/errored, respectively.

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
