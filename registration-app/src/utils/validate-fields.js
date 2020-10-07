import validator from "validator";

class ValidateFields {
  validatePhone(v) {
    if (validator.isEmpty(v)) {
      return "Phone is required";
    }
    return false;
  }
  validateFirstname(v) {
    if (validator.isEmpty(v)) {
      return "Firstname is required";
    } else if (!validator.isLength(v, { min: 4, max: 50 })) {
      return "Atleast 4 characaters required";
    }

    return false;
  }
  validateLastname(v) {
    if (validator.isEmpty(v)) {
      return "Lastname is required";
    } else if (!validator.isLength(v, { min: 4, max: 50 })) {
      return "Atleast 4 characaters required";
    }

    return false;
  }
  validateEmail(email) {
    if (validator.isEmpty(email)) {
      return "Email is required";
    } else if (!validator.isEmail(email)) {
      return "Invalid Email";
    }
    return false;
  }
}

const validateFields = new ValidateFields();

export { validateFields };
