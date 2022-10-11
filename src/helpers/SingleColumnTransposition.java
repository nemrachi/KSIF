package helpers;

public class SingleColumnTransposition {
    private Integer[] key;

    public SingleColumnTransposition(String keyPhrase) {
        this(Permutations.getPermFromString(keyPhrase));
    }

    public SingleColumnTransposition(Integer[] key) {
        this.key = key;
    }

    public String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        int textI = 0, textLen = text.length();
        int n = key.length;
        int m = (textLen/n)+1;
        Character[][] tab = new Character[m][n];

        // set last unused cells in table (to ignore them)
        for (int i = m, j = n; j > 0; j--) {
            if (i*j > textLen) {
                tab[i-1][j-1] = ' ';
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i][j] == null) {
                    tab[i][j] = text.charAt(textI++);
                }
            }
        }

        // get encrypted text from columns in order of key
        for (int k : key) {
            for (int i = 0; i < m; i++) {
                if (tab[i][k] != ' ') {
                    sb.append(tab[i][k]);
                }
            }
        }

        return sb.toString().trim();
    }

    public String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        int textI = 0, textLen = text.length();
        int n = key.length;
        int m = (textLen/n)+1;
        Character[][] tab = new Character[m][n];

        // set last unused cells in table (to not write there later)
        for (int i = m, j = n; j > 0; j--) {
            if (i*j > textLen) {
                tab[i-1][j-1] = ' ';
            }
        }

        // write text to columns in key order
        for (int k : key) {
            for (int i = 0; i < m; i++) {
                if (tab[i][k] == null) {
                    tab[i][k] = text.charAt(textI++);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(tab[i][j]);
            }
        }

        return sb.toString().trim();
    }

    public Integer[] getKey() {
        return key;
    }
    public void setKey(Integer[] key) {
        this.key = key;
    }
}
