(ns app.core
  (:require [reagent.dom.client :as rdom]
            #_[aardvark.core :refer-macros [tr lang tr-prep tr-logs]]))

(defn app []
  [:div
   [:h1 "Aardvark i18n Test"]
   #_[:h1 (tr :hello)]
   #_[:p (tr :thanks)]
   #_[:p (tr "string")]
   #_[:button {:on-click (change-lang :en)} "English"]
   #_[:button {:on-click (change-lang :fr)} "French"]
   [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."]
   [:p "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]])


(defonce root (atom nil))

(defn init []
  (let [container (js/document.getElementById "app")]
    (reset! root (rdom/create-root container))
    (rdom/render @root [app])))
