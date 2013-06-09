(ns test2.mimic.speclj
  "Helpers to transition from speclj"
  ;; (:require [test2.expect :as ex])
  )

(defmacro describe
  "Just like speclj's describe"
  [name & body]
  `(do ~@body)
  ;; `(defn ~(with-meta name {:test true}) []
  ;;    ~@body)
  )

(defmacro it
  "Just like speclj's it"
  [name & body]
  `(defn ~(with-meta (symbol name) {:test true}) []
     ~@body)
  ;; `(ex/expect ~f ~@args)
  )

(defmacro should=
  "Just like speclj's should="
  [a b]
  ;; `(do ~@body)
  )
