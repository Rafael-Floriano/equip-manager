import Axios from "axios";

const axiosClient = Axios.create({
    baseURL: 'http://localhost:8081',
    timeout: 10000,
    headers: {
      'Content-Type': 'application/json',
    },
});

export default axiosClient;