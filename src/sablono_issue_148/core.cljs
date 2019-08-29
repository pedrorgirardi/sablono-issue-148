(ns sablono-issue-148.core
  (:require
   [rum.core :as rum]))

(defonce state-ref (atom ""))

(rum/defc ControlledInput []
  [:input {:type "text"
           :value "Sablono"
           :on-change #(js/console.log (-> % .-target .-value))}])

(rum/defc ControlledInput2 < rum/reactive []
  [:input {:type "text"
           :value (rum/react state-ref)
           :on-change #(reset! state-ref (-> % .-target .-value))}])

(rum/defc UncontrolledInput []
  [:input {:type "text"}])

(rum/defc Root < rum/reactive []
  [:*
   [:a.link.underline.black {:href "https://github.com/r0man/sablono/issues/148"}
    [:h1.ma0 "Sablono Issue 148"]]

   [:p.measure-wide "This project uses a fork of Sablono wich removes the logic around wrapping input elements."]
   
   [:p "Please see " [:a {:href "https://github.com/pedrorgirardi/sablono/tree/fix-148"} "this fork."]]

   [:div.w-30.flex.flex-column
    [:span.f5 "Controlled"]
    (ControlledInput)

    [:span.f5.mt3 "Controlled with external state"]
    (ControlledInput2)
    [:span.f7.blue (rum/react state-ref)]

    [:span.f5.mt3 "Uncontrolled"]
    (UncontrolledInput)]])

(defn ^:dev/after-load render []
  (rum/mount (Root) (.getElementById js/document "app")))

(defn ^:export init []
  (render))