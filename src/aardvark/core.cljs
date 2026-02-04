(ns aardvark.core
  (:require-macros [aardvark.core]))

(def lang (atom :en))
(def translate-instances (atom {}))

(defn watch-lang []
  (add-watch
   lang "watch-lang"
   (fn [_ _ _ lang]
     (println @translate-instances)
     (doall
      (map (fn [{:keys [el phrases]}]
             (set! (.-textContent el) (lang phrases)))
           (vals @translate-instances))))))

