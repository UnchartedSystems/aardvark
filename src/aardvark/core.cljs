(ns aardvark.core
  (:require-macros [aardvark.core]))

;; initial-locale: how is the initial locale set?
;; change-locale: how do phrase translations react to initial-locale
(defprotocol ITranslate
  (initial-locale [this])
  (change-locale [this]))

(defn tr [& args]
  (throw (ex-info "tr is being used as a function, but it is a macro")))

(def current-locale (atom))
(def live-translations (atom {}))

(defn watch-locale []
  (add-watch
   current-locale "watch-locale"
   (fn [_ _ _ new-locale]
     (doall
      (map (fn [{:keys [el translation]}]
             (set! (.-textContent el) (new-lang translation)))
           (vals @live-translations))))))

