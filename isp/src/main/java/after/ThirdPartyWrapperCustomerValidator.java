package after;

public class ThirdPartyWrapperCustomerValidator implements CustomerValidator {

    private final ThirdPartyCustomerValidator validator;

    public ThirdPartyWrapperCustomerValidator(ThirdPartyCustomerValidator validator) {
        this.validator = validator;
    }

    @Override
    public void validateName(String name) {
        try {
            validator.validateName(name);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Illegal name");
        }
    }

    @Override
    public void validateEmail(String email) {
        try {
            validator.validateEmail(email);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Illegal email");
        }
    }
}
