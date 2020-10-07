import http from "../http-common";

const create = (data) => {
  return http.post("/registration", data);
};

export default {
  create,
};
