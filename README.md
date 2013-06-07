# test2

Test2 is a modular testing lib for Clojure.

It has a ring-like [SPEC](SPEC.md), so that different parts can be
changed out for alternatives. For example, if you want a different
reporter function, you can write one or use another reporter lib that
conforms to this spec.

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

```bash
$ lein test2
```

```clojure
(use 'test2.core)

(run-all-tests)
(run-ns-tests 'foo.test.core 'foo.test.other)
(run-matching-tests :migration)
```

Read more about [Swapping out the Runner or Reporter](../../wiki/Home#swapping-out-the-runner-or-reporter).

You can also run [Test Suites](../../wiki/Home#test-suites), or only tests matching a given function.

### Benefits over other libs




## License

Copyright © 2013 evanescence

Distributed under the Eclipse Public License, the same as Clojure.
