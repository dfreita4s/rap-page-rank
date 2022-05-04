(ns rap-page-rank.core)

(require '[clojure.string :as str])
(use 'clojure.java.io)


(defn artists [] (list "Tim Maia" "Sabotage" "Gilberto Gil" "Caetano" "Djavan" "Pepeu" "Alceu Valença" "Moraes" "Elba"
                       "Ed Motta" "Cidade Negra" "Skank" "Jorge Ben Jor"))
(defn lyrics [] (list "src/lyrics/Gabriel Pensador - Festa da Musica Tupiniquim" "src/lyrics/Jorge Ben Jor - W Brasil"))

(defn print-matches
  [lista-de-matches]
  (println lista-de-matches))

(defn find-artist
  [text list-of-artist]
  (if (empty? list-of-artist)
    (list )
    ((print-matches (list (first list-of-artist)
                    (count (re-seq (re-pattern (first list-of-artist)) text))))
     (find-artist text (rest list-of-artist)))))


(defn reading-lyrics-files
  [text-path artists]
  (if (empty? text-path)
    (println "End of recursion.")
    ((println "Artist: "(first (str/split (first text-path) #"-")) "Music: "(second (str/split (first text-path) #"-")))
     (find-artist (slurp (first text-path)) artists)
     (reading-lyrics-files (rest text-path) (artists)))))

;; re-pattern : string -> pattern(regex)
;; re-seq : find a regex in string

;(re-seq (re-pattern (first (artists)))  (slurp "src/lyrics/Jorge Ben Jor - W Brasil"))
(reading-lyrics-files (lyrics) (artists))