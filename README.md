# test2

Test2 is a modular testing lib for Clojure.

The major feature is its [SPEC](SPEC.md) (like ring-clojure), allowing different parts to be changed out for alternatives without having to switch to another lib entirely.

Coming from clojure.test, midje, or speclj? Read on about the [benefits of using test2](#benefits-over-other-libs).

## Install

Copy/paste as needed into your project.clj:

```clojure
[test2 "1.0.0"]             ;; in dependencies
[lein-test2 "1.0.0"]        ;; in plugins
```

## Documentation

* [Wiki](https://github.com/evanescence/test2/wiki)
* [API Docs](http://evanescence.github.com/test2)

## Community

* #clojure on [Freenode](http://freenode.net/) IRC

## Usage

Define some tests:

```clojure
(use 'test2.expect)
(use 'test2.def)

(defn ^:test user-creation
  "Creating users adds them to the list, but they're disabled by default." []
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

Or, in Clojure:

```clojure
(use 'test2.run)

(run-tests) ;; runs all in project
(run-tests :namespaces ['foobar.test.core 'foobar.test.extra])
(run-tests :matching :migration)
(run-tests :matching #(-> % :name name (.startsWith "users")))

;; read the API docs for more details
```

Read more about [Swapping out the Runner or Reporter](../../wiki/Home#swapping-out-the-runner-or-reporter).

You can also define [Test Suites](../../wiki/Home#test-suites), which act as filters to run only tests matching a given function.

## Benefits over other libs

TODO: rewrite this.

The runner, asserters, definers, and reporter are separate. This means:

* if you want a different reporter, you don't have to switch libs
* if you want to use different assert functions, you don't have to switch libs
* if you want to have an auto-runner, *you don't have to switch libs*
* if you prefer nested tests like rspec, you can find a plugin for that instead of switching libs

Just add a dependency that adds whatever functionality you want.





## License

Copyright © 2013 evanescence

Distributed under the Eclipse Public License, the same as Clojure.
