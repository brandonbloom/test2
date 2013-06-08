# test2

Test2 is a modular testing lib for Clojure.

The major feature is its [SPEC](SPEC.md) (like ring-clojure), which allows you to change out individual parts for alternatives, like the test-runner, test-reporter, and assertion functions, without having to switch to another testing lib.

## Rationale

The other day, I started a new project and wanted to write some tests. But I kept going back and forth between clojure.test, midje, speclj, and expectations (among others). Each one had some feature I really needed, but was lacking all the other features I needed.

This aims to solve that problem for anyone needing a Clojure test lib. You might want to swap out your runner, or your reporter, or your definer, or your asserters. But you don't have to change testing libs just to do it. You just add a lib to yor project, *that's all*.

The goal is that you can extend the lib from the outside to do what you want without having to switch to another lib and rewrite all your tests.


## Install

Copy/paste as needed into your project.clj:

```clojure
:dependencies [[test2 "1.0.0"]]
:aliases {"test2" ["run" "-m" "test2.run"]}
```

## Documentation

* [Wiki](https://github.com/evanescence/test2/wiki)
* [API Docs](http://evanescence.github.com/test2)

## Community

* [Google Group](http://groups.google.com/group/clojure-test2)
* #clojure on [Freenode](http://freenode.net/) IRC

## Usage

There's plenty of ways to [Define Tests](../../wiki/Home#defining-tests) and [Make Assertions](../../wiki/Home#making-assertions). Choose whichever you prefer best. Or write your own if you feel like it.



<!-- ```clojure -->
<!-- (use 'test2.expect) -->

<!-- (defn ^:test user-creation -->
<!--   "Creating users adds them to the list, but they're disabled by default." [] -->
<!--   (expect empty? (all-users)) -->
<!--   (create-user "bob") -->
<!--   (expect = 1 (count (all-users))) -->
<!--   (expect truthy? (:disabled (first (all-users))))) -->
<!-- ``` -->



To run them:

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
```



<!-- When running at the command line, you can choose a different Runner or Reporter. And you can pass a matcher function. Read more about it in [Command-line options](../../wiki/Home#command-line-options). -->

## Notable Extensions

* [test2-rspec](#) - Provides definers and asserters similar to RSpec / Speclj.
* [test2-midje](#) - Provides similar functions to Midje's `facts`/`fact`.
* [test2-autorunner](#) - Watches your files and re-runs tests when something changes.
* [test2-async-runner](#) - Runs tests concurrently.
* [test2-difftest](#) - Reports tests using a diff-style format.

See the [Third Party Extensions](../../wiki/Home#third-party-extensions) section of the wiki for a comprehensive list.

## License

Copyright © 2013 evanescence

Distributed under the Eclipse Public License, the same as Clojure.
