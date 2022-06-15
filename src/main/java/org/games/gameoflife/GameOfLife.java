package org.games.gameoflife;

/**
 * jei pries tai buvusioj iteracijoj langelyje nebuvo gyventojo:
 * naujoj iteracijoj gyventojas atsiras, jei senoj buvo 3 kaimynai
 * naujoj iteracijoj gyventojas neatsiras, jei senoj buvo maziau nei 3 arba daugiau nei 3 kaimynai
 * <p>
 * jei pries tai buvusioj iteracijoj langelyje buvo gyventojas:
 * naujoj iteracijoj gyventojas liks gyventi, jei senoj buvo 2 arba 3 kaimynai
 * naujoj iteracijoj gyventojas mirs (nebus), jei senoj buvo maziau nei 2 arba daugiau nei 3 kaimynai
 **/

public class GameOfLife {
    private static int kartaiMax = 1000;
    private static int x = 20;
    private static int y = 20;
    private static char[][][] fields = new char[kartaiMax][x][y];
    private static int i = 0;
    private static String msg = "";
    private static GameOfLife single_instance = null;
    private static char[][] lastField;

    public static char[][] getField() {
        if (i < kartaiMax) {
            if (i == 0) {
                fields[0] = fillRandomField(x, y);
                msg = "lapas: " + i;
            } else {
                fields[i] = generateNewField(fields[i-1]);
                msg = "lapas: " + i;
                // 2 etapas
                if (isFieldNull(fields[i])) {
                    msg = "lapas: " + i + " EMPTY";
                }
                if (isFieldsIdentical(fields[i], fields[i-1])) {
                    msg = "lapas: " + i + " identiskas pries tai buvusiam";
                }
                // 3 uzduotis
                for (int j = 0; j <= i-1; j++) {
                    if (isFieldsIdentical(fields[j], fields[i])) {
                        msg = "lapas: " + i + " identiskas " + j;
                        i = kartaiMax;
                    }
                }

            }
            i++;
            lastField = fields[i-1];
            return fields[i-1];
       }
        return lastField;
    }

    public static String getMsg() {
        return msg;
    }

    public static char[][] fillRandomField(int x, int y) {
        char[][] field = new char[x][y];
        // random uzpildymas
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = (Math.random() < 0.2) ? 'X' : '.';
            }
        }
        return field;
    }

    public static char[][] generateNewField(char[][] fieldOld) {
        char[][] field = new char[fieldOld[0].length][fieldOld[1].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                switch (kaimynuSk(fieldOld, i, j)) {
                    case 1:
                        field[i][j] = '.';
                        break;
                    case 2:
                        if (fieldOld[i][j] == 'X') {
                            field[i][j] = 'X';
                        } else {
                            field[i][j] = '.';
                        }
                        break;
                    case 3:
                        field[i][j] = 'X';
                        break;
                    case 4:
                        field[i][j] = '.';
                        break;
                    default:
                        field[i][j] = '.';
                }
            }
        }
        return field;
    }

    public static void printField(char[][] field) {
        System.out.println(msg);
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field[1].length; j++) {
                System.out.print(" " + field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isFieldNull(char[][] field) {
        int count = 0;
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field[1].length; j++) {
                if (field[i][j] == 'X')
                    count++;
            }
        }
        if (count == 0) return true;
        return false;
    }

    private static boolean isFieldsIdentical(char[][] field1, char[][] field2) {
        int count = 0;
        for (int i = 0; i < field1[0].length; i++) {
            for (int j = 0; j < field1[1].length; j++) {
                if (field1[i][j] != field2[i][j])
                    count++;
            }
        }
        if (count == 0) return true;
        return false;
    }

    private static int kaimynuSk(char[][] field, int x, int y) {
        int kaimynai = 0;
        //virsus X..
        if ((x - 1 >= 0 && y - 1 >= 0)) {
            if (field[x - 1][y - 1] == 'X') {
                kaimynai++;
            }
        }
        //virsus .X.
        if ((y - 1 >= 0)) {
            if (field[x][y - 1] == 'X') {
                kaimynai++;
            }
        }
        //virsus ..X
        if ((x + 1 < field[0].length && y - 1 >= 0)) {
            if (field[x + 1][y - 1] == 'X') {
                kaimynai++;
            }
        }
        //vidurys X-.
        if (x - 1 >= 0) {
            if (field[x - 1][y] == 'X') {
                kaimynai++;
            }
        }
        //vidurys .-X
        if (x + 1 < field[0].length) {
            if (field[x + 1][y] == 'X') {
                kaimynai++;
            }
        }
        //apacia X..
        if (x - 1 >= 0 && y + 1 < field[1].length) {
            if (field[x - 1][y + 1] == 'X') {
                kaimynai++;
            }
        }
        //apacia .X.
        if (y + 1 < field[1].length) {
            if (field[x][y + 1] == 'X') {
                kaimynai++;
            }
        }
        //apacia ..X
        if (x + 1 < field[0].length && y + 1 < field[1].length) {
            if (field[x + 1][y + 1] == 'X') {
                kaimynai++;
            }
        }
        return kaimynai;
    }
}
