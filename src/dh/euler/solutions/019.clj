;; You are given the following information, but you may prefer to do
;; some research for yourself.
;;
;;     * 1 Jan 1900 was a Monday.
;;     * Thirty days has September,
;;       April, June and November.
;;       All the rest have thirty-one,
;;       Saving February alone,
;;       Which has twenty-eight, rain or shine.
;;       And on leap years, twenty-nine.
;;     * A leap year occurs on any year evenly divisible by 4,
;;       but not on a century unless it is divisible by 400.
;;
;; How many Sundays fell on the first of the month during the twentieth century
;; (1 Jan 1901 to 31 Dec 2000)?
;;
;; http://projecteuler.net/problem=19

(ns dh.euler.solutions.019
  (:use [dh.euler.util.core :refer [divisible-by?]]))


(defstruct date :dow :dom :month :year)

(defn leap-year?
  [y]
  (if (divisible-by? y 100)
    (divisible-by? y 400)
    (divisible-by? y 4)))

(def month-days
     {1 31, 3 31, 4 30, 5 31, 6 30, 7 31, 8 31, 9 30, 10 31, 11 30, 12 31})

(defn days-in-month
  [m y]
  (if (not (= m 2))
    (month-days m)
    (if (leap-year? m)
      29
      28)))

(defn next-month
  [d]
  (let [dm (d :month)
        dy (d :year)
        year  (if (< dm 12) dy (inc dy))
        month (if (< dm 12) (inc dm) 1)
        dow   (mod (+ (d :dow) (days-in-month dm dy)) 7)]
    (struct date dow (d :dom) month year)))


(defn euler-019
  []
  (time (loop [date (struct date 2 1 1 1901)
               count-of-sundays 0]
          (if (> (date :year) 2000)
            count-of-sundays
            (let [next-date (next-month date)]
              (if (= 0 (date :dow))
                (recur next-date (inc count-of-sundays))
                (recur next-date count-of-sundays)))))))
