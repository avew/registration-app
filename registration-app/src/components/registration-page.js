import React, { Component } from "react";
import RegistrationService from "../services/registration-service";
import Consts from "../utils/const";
import { validateFields } from "../utils/validate-fields";
import classnames from "classnames";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";
const initialState = {
  phone: {
    value: "",
    validateOnChange: false,
    error: "",
  },
  firstname: {
    value: "",
    validateOnChange: false,
    error: "",
  },
  lastname: {
    value: "",
    validateOnChange: false,
    error: "",
  },
  month: {
    value: "Month",
  },
  date: {
    value: "Date",
  },
  year: {
    value: "Year",
  },
  gender: {
    value: "Male",
  },
  email: {
    value: "",
  },
  submitCalled: false,
  allFieldsValidated: false,
};
export default class RegistrationPage extends Component {
  constructor(props) {
    super(props);
    this.state = initialState;
  }
  /*
   * validates the field onBlur if sumbit button is not clicked
   * set the validateOnChange to true for that field
   * check for error
   */
  handleBlur(validationFunc, evt) {
    const field = evt.target.name;
    // validate onBlur only when validateOnChange for that field is false
    // because if validateOnChange is already true there is no need to validate onBlur
    if (
      this.state[field]["validateOnChange"] === false &&
      this.state.submitCalled === false
    ) {
      this.setState((state) => ({
        [field]: {
          ...state[field],
          validateOnChange: true,
          error: validationFunc(state[field].value),
        },
      }));
    }
    return;
  }

  /*
   * update the value in state for that field
   * check for error if validateOnChange is true
   */
  handleChange(validationFunc, evt) {
    const field = evt.target.name;
    const fieldVal = evt.target.value;
    this.setState((state) => ({
      [field]: {
        ...state[field],
        value: fieldVal,
        error: state[field]["validateOnChange"] ? validationFunc(fieldVal) : "",
      },
    }));
  }

  handleChangeNoValidate(evt) {
    const field = evt.target.name;
    const fieldVal = evt.target.value;
    this.setState((state) => ({
      [field]: {
        ...state[field],
        value: fieldVal,
      },
    }));
  }

  /*
   * validate all fields
   * check if all fields are valid if yes then submit the Form
   * otherwise set errors for the feilds in the state
   */
  handleSubmit(evt) {
    evt.preventDefault();
    // validate all fields

    const { phone, firstname, lastname, email } = this.state;
    const phoneError = validateFields.validatePhone(phone.value);
    const firstnameError = validateFields.validateFirstname(firstname.value);
    const lastnameError = validateFields.validateLastname(lastname.value);
    const emailError = validateFields.validateEmail(email.value);

    if (
      [phoneError, firstnameError, lastnameError, emailError].every(
        (e) => e === false
      )
    ) {
      // no errors submit the form

      const data = {
        phone: this.state.phone.value,
        firstname: this.state.firstname.value,
        lastname: this.state.lastname.value,
        month:
          this.state.month.value === "Month"
            ? null
            : Consts.months.findIndex((x) => x === this.state.month.value) + 1,
        date: this.state.date.value === "Date" ? null : this.state.date.value,
        year: this.state.year.value === "Year" ? null : this.state.year.value,
        gender: this.state.gender.value,
        email: this.state.email.value,
      };
      RegistrationService.create(data)
        .then((response) => {
          toast.success("Registration Success");
          // clear state and show all fields are validated
          this.setState({ ...initialState, allFieldsValidated: true });
          // this.showAllFieldsValidated();
        })
        .catch((error) => {
          if (error.response != null) {
            if (error.response.data.title === "Constraint Violation") {
              error.response.data.violations.forEach((item) => {
                switch (item.field) {
                  case "email":
                    this.setState((state) => ({
                      email: {
                        ...state.email,
                        validateOnChange: true,
                        error: item.message,
                      },
                    }));
                    break;
                  case "phone":
                    this.setState((state) => ({
                      phone: {
                        ...state.phone,
                        validateOnChange: true,
                        error: item.message,
                      },
                    }));
                    break;
                  default:
                    break;
                }
              });
            } else {
              toast.error(error.response.data.detail);
            }
          }
        });
    } else {
      // update the state with errors
      this.setState((state) => ({
        phone: {
          ...state.phone,
          validateOnChange: true,
          error: phoneError,
        },
        firstname: {
          ...state.firstname,
          validateOnChange: true,
          error: firstnameError,
        },
        lastname: {
          ...state.lastname,
          validateOnChange: true,
          error: lastnameError,
        },
        email: {
          ...state.email,
          validateOnChange: true,
          error: emailError,
        },
      }));
    }
  }

  setGender(event) {
    this.setState({
      gender: {
        value: event.target.value,
      },
    });
  }

  render() {
    const {
      phone,
      firstname,
      lastname,
      month,
      date,
      year,
      gender,
      email,
      allFieldsValidated,
    } = this.state;
    return (
      <div classnames="row">
        <div classnames="col-12">
          <div
            className={classnames("jumbotron mt-2", {
              grayOut: allFieldsValidated,
            })}
          >
            <h1 className="display-4">Registration</h1>
            <form onSubmit={(evt) => this.handleSubmit(evt)} noValidate>
              <div className="form-group">
                <label>Mobile Number</label>
                <span
                  className={classnames({
                    "d-inline p-2 bg-danger text-white float-right mb-1":
                      phone.error,
                  })}
                >
                  {phone.error}
                </span>
                <input
                  type="text"
                  value={phone.value}
                  className={classnames(
                    "form-control",
                    { "is-valid": phone.error === false },
                    { "is-invalid": phone.error }
                  )}
                  name="phone"
                  onChange={(evt) =>
                    this.handleChange(validateFields.validatePhone, evt)
                  }
                  onBlur={(evt) =>
                    this.handleBlur(validateFields.validatePhone, evt)
                  }
                />
              </div>

              <div className="form-group">
                <label>First Name</label>
                <span
                  className={classnames({
                    "d-inline p-2 bg-danger text-white float-right mb-1":
                      firstname.error,
                  })}
                >
                  {firstname.error}
                </span>
                <input
                  type="text"
                  value={firstname.value}
                  className={classnames(
                    "form-control",
                    { "is-valid": firstname.error === false },
                    { "is-invalid": firstname.error }
                  )}
                  name="firstname"
                  onChange={(evt) =>
                    this.handleChange(validateFields.validateFirstname, evt)
                  }
                  onBlur={(evt) =>
                    this.handleBlur(validateFields.validateFirstname, evt)
                  }
                />
              </div>

              <div className="form-group">
                <label>Last Name</label>
                <span
                  className={classnames({
                    "d-inline p-2 bg-danger text-white float-right mb-1":
                      lastname.error,
                  })}
                >
                  {lastname.error}
                </span>
                <input
                  type="text"
                  value={lastname.value}
                  className={classnames(
                    "form-control",
                    { "is-valid": lastname.error === false },
                    { "is-invalid": lastname.error }
                  )}
                  name="lastname"
                  onChange={(evt) =>
                    this.handleChange(validateFields.validateLastname, evt)
                  }
                  onBlur={(evt) =>
                    this.handleBlur(validateFields.validateLastname, evt)
                  }
                />
              </div>

              <div className="form-group">
                <label>Date of Birth</label>
                <div className="row">
                  <div className="col-sm">
                    <select
                      value={month.value}
                      className="custom-select"
                      name="month"
                      onChange={(evt) => this.handleChangeNoValidate(evt)}
                    >
                      <option value={"Month"}>Month</option>
                      {Consts.months.map((month, index) => {
                        return (
                          <option
                            key={`month${index}`}
                            value={month}
                            defaultValue={"Month"}
                          >
                            {month}
                          </option>
                        );
                      })}
                    </select>
                  </div>

                  <div className="col-sm">
                    <select
                      value={date.value}
                      onChange={(evt) => this.handleChangeNoValidate(evt)}
                      className="custom-select"
                      name="date"
                    >
                      <option value={"Date"}>Date</option>
                      {Consts.dates.map((date, index) => {
                        return (
                          <option
                            key={`date${index}`}
                            value={date}
                            defaultValue={"Date"}
                          >
                            {date}
                          </option>
                        );
                      })}
                    </select>
                  </div>
                  <div className="col-sm">
                    <select
                      className="custom-select"
                      name="year"
                      value={year.value}
                      onChange={(evt) => this.handleChangeNoValidate(evt)}
                    >
                      <option value={"Year"}>Year</option>
                      {Consts.years.map((year, index) => {
                        return (
                          <option
                            key={`year${index}`}
                            value={year}
                            defaultValue={"Year"}
                          >
                            {year}
                          </option>
                        );
                      })}
                    </select>
                  </div>
                </div>
              </div>

              <div className="form-group">
                <div className="form-check form-check-inline">
                  <input
                    className="form-check-input"
                    type="radio"
                    value="Male"
                    checked={gender.value === "Male"}
                    onChange={(evt) => this.setGender(evt)}
                  />
                  <label className="form-check-label">Male</label>
                </div>
                <div className="form-check form-check-inline">
                  <input
                    className="form-check-input"
                    type="radio"
                    value="Female"
                    checked={gender.value === "Female"}
                    onChange={(evt) => this.setGender(evt)}
                  />
                  <label className="form-check-label">Female</label>
                </div>
              </div>

              <div className="form-group">
                <label>Email</label>
                <span
                  className={classnames({
                    "d-inline p-2 bg-danger text-white float-right mb-1":
                      email.error,
                  })}
                >
                  {email.error}
                </span>
                <input
                  type="text"
                  value={email.value}
                  className={classnames(
                    "form-control",
                    { "is-valid": email.error === false },
                    { "is-invalid": email.error }
                  )}
                  name="email"
                  onChange={(evt) =>
                    this.handleChange(validateFields.validateEmail, evt)
                  }
                  onBlur={(evt) =>
                    this.handleBlur(validateFields.validateEmail, evt)
                  }
                />
              </div>

              <button
                type="submit"
                className="btn btn-primary btn-lg btn-block"
                onMouseDown={() => this.setState({ submitCalled: true })}
              >
                Register
              </button>
            </form>
          </div>
        </div>
        <div classnames="col-12">
          <div
            className={classnames(
              "jumbotron",
              { "bg-light ": allFieldsValidated },
              { "bg-mitrais": !allFieldsValidated }
            )}
          >
            {!allFieldsValidated && (
              <h2 className="text-center text-white">FOOTER</h2>
            )}

            {allFieldsValidated && (
              <Link to="/login">
                <button
                  type="button"
                  className="btn btn-primary btn-lg btn-block"
                >
                  Login
                </button>
              </Link>
            )}
          </div>
        </div>
      </div>
    );
  }
}
