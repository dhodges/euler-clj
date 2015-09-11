(ns dh.euler.problems.059
  (:use [clojure.test]))

;; Problem 59
;; 19 December 2003

;;http://projecteuler.net/index.php?section=problems&id=59
;;
;; Each character on a computer is assigned a unique code and the
;; preferred standard is ASCII (American Standard Code for
;; Information Interchange). For example, uppercase A = 65,
;; asterisk (*) = 42, and lowercase k = 107.
;;
;; A modern encryption method is to take a text file,
;; convert the bytes to ASCII, then XOR each byte with a given value,
;; taken from a secret key. The advantage with the XOR function is that
;; using the same encryption key on the cipher text, restores the plain text;
;; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
;;
;; For unbreakable encryption, the key is the same length as
;; the plain text message, and the key is made up of random bytes.
;; The user would keep the encrypted message and the encryption key in
;; different locations, and without both "halves", it is impossible to
;; decrypt the message.
;;
;; Unfortunately, this method is impractical for most users,
;; so the modified method is to use a password as a key.
;; If the password is shorter than the message, which is likely,
;; the key is repeated cyclically throughout the message.
;; The balance for this method is using a sufficiently long password key
;; for security, but short enough to be memorable.
;;
;; Your task has been made easy, as the encryption key consists of
;; three lower case characters. Using cipher1.txt (right click and
;; 'Save Link/Target As...'), a file containing the encrypted ASCII codes,
;; and the knowledge that the plain text must contain common English words,
;; decrypt the message and find the sum of the ASCII values in the original text.


(defn get-text
  []
  (map #(Integer/parseInt %)
       (.split (.trim (slurp "cipher1.txt"))
               ",")))

(defn text-xor
  [text key]
  (if (empty? text)
    '()
    (cons (bit-xor  (first text) (first key))
               (text-xor (rest text)  (rest key)))))

(defn convert
  [text key]
   (reduce str (map #(char %)
                    (text-xor text key))))

(defn char-in-range?
  [c]
  (let [i (int c)]
    (or (= i  9)
        (= i 10)
        (= i 13)
        (and (<= 32 i) (<= i 126)))))

(defn text-in-range?
  [text]
  (= 0 (count (filter #(not (char-in-range? %)) text))))


(defn decrypted?
  [text]
  (and (text-in-range? text)
       (< -1 (.indexOf text "and"))
       (< -1 (.indexOf text "the"))
       (< -1 (.indexOf text ". "))
       (< -1 (.indexOf text ", "))
       ))

(defn euler-059
  []
  (time
   (let [text (get-text)]
     (doseq [a (range 97 123)
             b (range 97 123)
             c (range 97 123)]
       (let [test-text (convert text (cycle [a b c]))]
         (if (decrypted? test-text)
           (let [sum (apply + (map #(int (char %)) test-text))]
             (println test-text)
             (printf "%s\n\n" sum)
             sum)))))))

(deftest test-euler-059
  []
  (is (= (euler-059) 107359)))
