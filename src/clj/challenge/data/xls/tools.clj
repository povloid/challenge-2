(ns challenge.data.xls.tools
  (:use dk.ative.docjure.spreadsheet))

(defn test-1 []
  (->> (load-workbook "/home/pacman/Documents/test-table.xlsx")
       (select-sheet "Sheet1")
       (select-columns {:A :name, :B :price})
       ))
