package ch.zhaw.ads.Praktikum_07;

/**
 * @author K. Rege
 * @version 1.0 -- Experimentierkasten
 */

public class ExBox {

    public static void main(String[] args) throws Exception {
        ExBoxFrame f = new ExBoxFrame();
        f.setLocationRelativeTo(null);  
        f.setVisible(true);
    }

    private static void p(int i)
    {
        if (i<10){
            p(i+1);
            System.out.println(i);
        }
    }
}