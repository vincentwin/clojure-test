(ns clojuretest.handler
  (:require [clojure.tools.logging :as logger]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.core :refer :all]
            [clojure.edn :as edn]))

;(require 'clojure.edn)

(defroutes app-routes
  (GET "/" [] "Server Started!"))

(def app
  (handler/site app-routes))

(defn load-props
  [file-name]
  (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)]
    (let [props (java.util.Properties.)]
      (.load props reader)
      into {} (for [[k v] props] [(keyword k) (read-string v)]))))

(defn initApp
  []
  (logger/trace "Starting App....")
  (logger/trace "Loading configs..")
  (load-props "resources/config.properties")
  )

(def config
  (edn/read-string (slurp "resources/config.edn")))


(println (:GithubUser1 (:user-map config)))
