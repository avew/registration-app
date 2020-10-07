const regexEmail = RegExp(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/);
const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
const years = Array.from(
  new Array(20),
  (val, index) => index + new Date().getFullYear() - 35
);
const dates = Array.from(new Array(31), (val, index) => index + 1);

export default {
  regexEmail,
  months,
  years,
  dates,
};
