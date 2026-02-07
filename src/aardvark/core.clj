(ns aardvark.core
  (:require [aardvark.prototype :refer [dictionary]]
            [clojure.set :refer [difference]]))

(def tr-instances (atom {}))
(def lang-instances (atom {}))

(defmacro change-lang [lang]
  (swap! lang-instances assoc lang (meta &form))
  `#(reset! aardvark.core/lang ~lang))

;; Rethink this!
;; I need to change the output cljs code for each input type
;; fallback strings:
;; - don't get added to watch & added to live-translations
;; - are directly rendered as a simple span with text
;; - I need to log that the string exists

(defn- sort [input form]
  (cond
    (keyword? input) {:phrase }))

(defmacro tr [phrase]
  (let [translations (phrase dictionary)
        id (keyword (gensym phrase))]
    (swap! tr-instances assoc id {:phrase phrase  :form (meta &form)})
    `[:span {:ref #(swap! aardvark.core/live-translations assoc ~id {:el % :translations ~translations})}
      (@aardvark.core/lang ~translations)]))

(defmacro tr-prep []
  `(aardvark.core/watch-lang))

(defmacro tr-logs []
  (let [used-langs (set (keys @lang-instances))
        all-langs (reduce #(into %1 (keys %2)) #{} (vals dictionary))
        unused-langs (difference all-langs used-langs)
        dead-langs (difference used-langs all-langs)
        used-phrases (set (keys @tr-instances))
        all-phrases (set (keys dictionary))
        unused-phrases (difference all-phrases used-phrases)
        dead-phrases (difference used-phrases all-phrases)]
    
    (println "Langs")
    (println "unused: " unused-langs)
    (println "dead:   " dead-langs)

    (println "Phrases")
    (println "unused: " unused-phrases)
    (println "dead:   " dead-phrases)))

