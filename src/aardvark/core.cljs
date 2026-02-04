(ns aardvark.core
  (:require-macros [aardvark.core]))

(def lang (atom :en))
(def translate-instances (atom {}))

(defn watch-lang []
  (add-watch
   lang "watch-lang"
   (fn [_ _ _ new-lang]
     (doall
      (map (fn [{:keys [el translation]}]
             (set! (.-textContent el) (new-lang translation)))
           (vals @translate-instances))))))

