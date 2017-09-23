# PearForm - Creating Forms, less Boilerplate code! 

## Synopsis 

Tired of always writing similar logic when implementing different Forms in Android ? 

**PearForm** cuts-off **Boilerplate** code for you, and provides you with a **Frame** to start composing 

Every [Form](https://en.wikipedia.org/wiki/Form_(document)) hides indeed a common logic set that could be abstracted in order to reduce boilerplate code. 

1. Reset
2. Validation 
3. Pre-fill 
4. Result Collect

## Usage 

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

// Form Build
FormView formView = findViewById(R.id.formView);
FormBuilder builder = formView.getFormBuilder();
formView
    .add(builder.text(USERNAME_KEY, "Username", error, minLengthValidator)) // Username
    .add(builder.text(PASSWORD_KEY, "Password", error, minLengthValidator)) // Password
    .resetWith(resetButton)
    .validateWith(validateButton, new IFormValidationListener() {
        @Override public void onSuccess(Bundle result) {}
        @Override public void onError() {}
    })
    .build();
```

The `FormView` will collect the `Form` result `Bundle` object by iterating on its fields an calling their `Bundle getResult()` methods, which include **validation**.

Individual field results can be retrieved through the key (such as `USERNAME_KEY` in the example) specified for every field.

`PearForm` comes with a **set of predefined Widget**, that can be used as **Form Fields**, as well as with a set of **UI widgets**, since it supports adding **normal views**. 

The **developer** is provided with a **Frame** to build forms. He can build his own set of **Fields** to be used in **Form creation**, thus extending the library of components he disposes to build the forms, putting effort in reusable components, instead of in single screens implementations. 

Every widget can declare multiple **validators**, to be reused as well across the application. 

## Use cases 
The **power** of this framework in reducing boilerplate code and pushing the developer to write reusable code cannot really be seen when implementing a little forms. 

The advantages come when implementing a suite of **form screens**, where 
- often **fields validation** is shared among multiple fields
- every **Field** of the same type should appear and behave the same
- the **Form result** for every field has to be collected for every screen 
- we have to attach listeners to **reset / validation** buttons 

## Remarks 
`formView.add()` method accepts **normal views** as well as **Form Fields**. `FormView` will perform its iterative operations (such as validating or resetting the whole form) on **Form Fields** only. 

## Form Field 




