package skuska24h;

public class Main24 {
    private static String ctFormat(String origCt) {
        StringBuilder newCt = new StringBuilder(origCt);
        for (int i = 0; i < newCt.length(); i++) {
            if (newCt.charAt(i) == ' ') {
                newCt.deleteCharAt(i--); // delete spaces
            }
        }
        for (int i = 2; i < newCt.length(); i+=3) {
            newCt.deleteCharAt(i--); // delete x
        }
        return newCt.toString();
    }

    public static void main(String[] args) {
        // semestralne zadanie to vyriesilo
        // i: 4
        // hint: 3
        String ct = "ylxzy xerxh  fxoox mqxki xhbxb  zxjax zjxqh xcxxx  ixbrx oxxfa xphxj  qxndx frxne xdpxl  qxezx zxxlt xpoxd  oxmnx jpxhx xfkxe  qxxwx kcxbl xqcxf  uxakx doxpm xktxc  kxbrx jqxke xhdxp  exdsx xxxlo xnixf  rxahx daxmd xnpxt  nxddx ojxdj xqnxn  bxbhx xhxiq xgaxf  qxnlx kaxxr xpexd  dxbzx zlxep xedxb  xxlex ixxpf xlax";

        ct = ctFormat(ct);
        System.out.println(ct);

        Decryption decryption = new Decryption(3, ct);
        decryption.decrypt();
    }
}
