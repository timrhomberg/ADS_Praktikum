package ch.zhaw.ads.Praktikum_07;

/**
 * AnyServer -- Praktikum Experimentierkasten -- ADS
 *
 * @author E. Mumprecht
 * @version 1.0 -- Geruest fuer irgendeinen Server
 */

public class AnyServer implements CommandExecutor {



    //----- Dies implementiert das CommandExecutor Interface.
    @Override
    public String execute(String command) {
        StringBuffer result = new StringBuffer(100);
        result.append("Die Eingabe war \"");
        result.append(command);
        result.append("\"\n");
        return result.toString();
    }
}//AnyServer   
