package ua.goit.homework10.task1.validator;

public class PhoneValidator implements StringValidator{
    @Override
    public boolean isValid(String phone) {
        return phone.matches("^(\\(\\d{3}\\)\\s|\\d{3}-)\\d{3}-\\d{4}$");
    }
}
