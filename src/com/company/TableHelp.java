package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TableHelp {
    Whowinbin whowinbin = new Whowinbin();
    public void help(Map<Integer, String> detrmWinner){
        String[][] arrayOk = new String[detrmWinner.size() + 1][detrmWinner.size() + 1];
        arrayOk[0][0] = "PC\\User";
        for (int i = 0; i < arrayOk.length; i++) {
            for (int j = 0; j < arrayOk[i].length; j++) {
                if(i == 0 && j != 0){
                    arrayOk[i][j] = detrmWinner.get(j);
                }
                if(i != 0 && j == 0){
                    arrayOk[i][j] = detrmWinner.get(i);
                }
                if(i != 0 && j != 0){
                    arrayOk[i][j] = whowinbin.winner(detrmWinner.size(), i, j);
                }
            }
        }


        //еееееееееееееХО!!!

        //leftJustifiedRows - Если true, он добавит "-" в качестве флага для форматирования строки в
        //выровнять по левому краю. В противном случае право оправдано.
        boolean leftJustifiedRows = true;
        //Рассчитайте соответствующую длину каждого столбца, глядя на ширину данных в
        //каждый столбец.
        //Map columnLengths is <column_number, column_length>
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(arrayOk).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        //System.out.println("columnLengths = " + columnLengths);
        //Подготовить строку формата
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        //System.out.println("formatString = " + formatString.toString());
        //Подготовьте строку для верхнего, нижнего и нижнего ряда заголовков.
        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        //System.out.println("Line = " + line);
        //Print table
        System.out.print(line);
        Arrays.stream(arrayOk).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line);

        Stream.iterate(1, (i -> i < arrayOk.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), arrayOk[a]));
        System.out.print(line);
    }
}
