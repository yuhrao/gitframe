(ns gitframe.api.github.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :github.user/repos
  (fn [db _]
    (-> db :github :repos)))
