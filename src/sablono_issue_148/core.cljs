(ns sablono-issue-148.core
  (:require
   [rum.core :as rum]))

(rum/defc ControlledInput []
  [:input {:type "text"
           :value "Sablono"
           :on-change #(js/console.log (-> % .-target .-value))}])

(rum/defc UncontrolledInput []
  [:input {:type "text"}])

(rum/defc Root []
  [:div.w-30.flex.flex-column.pa3
   [:span.f5 "Controlled"]
   (ControlledInput)

   [:span.f5.mt3 "Uncontrolled"]
   (UncontrolledInput)])

(defn ^:dev/after-load render []
  (rum/mount (Root) (.getElementById js/document "app")))

(defn ^:export init []
  (render))