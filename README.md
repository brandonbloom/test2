# test2

Test2 is a modular testing lib for Clojure.

It has a ring-like [SPEC](SPEC.md), so that different parts can be changed out for alternatives.

Specifically, this lib splits tests into: test definers, assertion functions, test runners, and test-result reporters.

For example, if you want a different reporter function, you can write one, or use a third-party reporter lib, as long as it conforms to this spec.

Coming from clojure.test? Midje? Speclj? [Read here](#benefits-over-other-libs) to see how test2 can help you.

## Install

Copy/paste as needed into your project.clj:

```clojure
[test2 "1.0.0"]              ;; in :dependencies
[lein-difftest "2.0.0"]      ;; in :plugins
```

## Documentation

* [Wiki](https://github.com/evanescence/test2/wiki)
* [API Docs](http://evanescence.github.com/test2)

## Community

* #clojure on [Freenode](http://freenode.net/) IRC

## Usage

Define some tests:

```clojure
(use 'test2.core)

(defn ^:test user-creation
  "Creating users adds them to the list, but they're disable by default." []
  (expect empty? (all-users))
  (create-user "bob")
  (expect = 1 (count (all-users)))
  (expect truthy? (:disabled (first (all-users)))))
```

Read more about [Defining Tests](../../wiki/Home#defining-tests).

Then run them:

```bash
$ lein test2
```

Or in Clojure:

```clojure
(use 'test2.core)

(run-all-tests)
(run-ns-tests 'foo.test.core 'foo.test.other)
(run-matching-tests :migration)
```

Read more about [Swapping out the Runner or Reporter](../../wiki/Home#swapping-out-the-runner-or-reporter).

You can also run [Test Suites](../../wiki/Home#test-suites), or only tests matching a given function.

## Benefits over other libs

The runner, asserters, definers, and reporter are separate. This means:

* if you want a different reporter, you don't have to switch libs
* if you want to use different assert functions, you don't have to switch libs
* if you want to have an auto-runner, *you don't have to switch libs*
* if you prefer nested tests like rspec, you can find a plugin for that instead of switching libs

Just add a dependency that adds whatever functionality you want.





## License

Copyright © 2013 evanescence

Distributed under the Eclipse Public License, the same as Clojure.
