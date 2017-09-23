# PearForm - Creating Forms, less Boilerplate code! 

Tired of always writing similar logic when implementing different Forms in Android ? 

**PearForm** cuts-off **Boilerplate** code for you, and provides you with a **Frame** to start composing 

Every [Form](https://en.wikipedia.org/wiki/Form_(document)) hides indeed a common logic set that could be abstracted in order to reduce boilerplate code. 

1. Reset
2. Validation 
3. Pre-fill 
4. Result Collect

A `Form` can be seen as a **Vertical** list of **Components** or **Fields**. 
If every field knows how to perform the above mentioned points on itself, it's easy to build the `Form` by adding a list of `Fields`, as follows : 

```java
// Min Length field validator
String error = "Min length must be 2";
MaterialText.IFieldValidator minLengthValidator = new MaterialText.IFieldValidator() {
    @Override
    public boolean validate(TextInputEditText textView) {
        String text = textView.getText().toString();
        if(text == null || text.length() < 2 )
            return false;
        else return true;
    }
};

// Buttons
Button resetButton = findViewById(R.id.resetButton);
Button validateButton = findViewById(R.id.signupButton);

FormView formView = findViewById(R.id.formView);
FormBuilder builder = formView.getFormBuilder();
formView
    .add(title)
    .add(builder.text(USERNAME_KEY, "Username", error, minLengthValidator)) // Username
    .add(builder.text(PASSWORD_KEY, "Password", error, minLengthValidator)) // Password
    .resetWith(resetButton)
    .validateWith(validateButton, new IFormValidationListener() {
        @Override public void onSuccess(Bundle result) {}
        @Override public void onError() {}
    })
    .build();
```
