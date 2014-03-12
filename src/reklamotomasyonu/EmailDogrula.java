/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamotomasyonu;

/**
 *
 * @author Kursat
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailDogrula {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public EmailDogrula(){
        pattern = Pattern.compile(EMAIL_REGEX);
    }
    
    public boolean dogrula(final String gelen){
        matcher = pattern.matcher(gelen);
        return matcher.matches();
    }
}
