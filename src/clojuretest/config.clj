(ns clojuretest.config
	(:require [clojure.java.io :refer [file]]
			  [useful.config :refer [load-config]]))

;; Loads config.clj into a config variable

(def config (load-config "config.clj"))

;; Define author if exists in config.clj or throw exception

(def author (or (:author config)
	(throw (Exception. "config.clj needs an author defined"))))

(println author)