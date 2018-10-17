package before;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdPartyCustomerValidator implements CustomerValidator{

    @Override
    public void validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("Name can not be null");
        }
    }

    @Override
    public void validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (StringUtils.isEmpty(email) || !emailMatcher.matches()) {
            throw new RuntimeException("Email is empty or is invalid");
        }
    }
}
