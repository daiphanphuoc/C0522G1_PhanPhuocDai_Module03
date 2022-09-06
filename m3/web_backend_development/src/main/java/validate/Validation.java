package validate;

import java.util.regex.Pattern;

public class Validation {
    private static final String NAME_OR_ADDRESS = "^([\\p{Lu}][\\p{Ll}]{1,8})(\\s([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,5}$";
    private static final String DATE_STANDARD = "^\\b(0?[1-9]|[12]\\d|3[01])[\\/\\-.](0?[1-9]|[12]\\d|3[01])[\\/\\-.](\\d{2}|19\\d{2}|20\\d{2})\\b$";
    private static final String DATE_STANDARD1 = "^(?=\\d{2}([-.,\\/])\\d{2}\\1\\d{4}$)(?:0[1-9]|1\\d|[2][0-8]|29(?!.02.(?!(?!(?:[02468][1-35-79]|[13579][0-13-57-9])00)\\d{2}(?:[02468][048]|[13579][26])))|30(?!.02)|31(?=.(?:0[13578]|10|12))).(?:0[1-9]|1[012]).\\d{4}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z][.\\w]{7,}@[a-z]{2,9}([.][a-z]{2,3}){1,2}$";
    private static final String REGEX_PHONE_NUMBER = "^((\\(\\+84\\-\\))|0)(90|91)[0-9]{7}$";
    private static final String REGEX_CARD = "^[0-9]{12}|[0-9]{9}$";

    public static boolean checkName(String name){
        return Pattern.matches(NAME_OR_ADDRESS,name);
    }
    public static boolean checkDate(String date){
        return Pattern.matches(DATE_STANDARD1,date);
    }
    public static boolean checkEmail(String email) {
        return Pattern.matches(REGEX_EMAIL,email);
    }
    public static boolean checkPhoneNumber(String phoneNumber) {
        return Pattern.matches(REGEX_PHONE_NUMBER,phoneNumber);
    }

    public static boolean checkCard(String card) {
        return Pattern.matches(REGEX_CARD,card);
    }
}
