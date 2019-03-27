import {AUTH_REQUEST} from "../constants";
import {AUTH_REGISTER} from "../constants";
import {AUTH_LOGOUT} from "../constants";
import axios from "axios";

const state = {user: localStorage.getItem('user') || ''};

const getters = {
    isAuthenticated: state => !!state.user
};

const actions = {
    [AUTH_REQUEST]: ({commit, dispatch}, user) => {
        return new Promise((resolve, reject) => {
            commit(AUTH_REQUEST, user);
            resolve()
        })
    },

    [AUTH_REGISTER]: ({commit, dispatch}, user) => {
        return new Promise((resolve, reject) => {
            commit(AUTH_REGISTER, user);
            resolve()
        })
    },

    [AUTH_LOGOUT]: ({commit, dispatch}) => {
        return new Promise((resolve, reject) => {
            commit(AUTH_LOGOUT);
            resolve()
        })
    }
};

const mutations = {
    [AUTH_REQUEST]: (state, user) => {
        let data = 'username=' + user.username + '&password=' + user.password;
        let headers = {
            'Content-type': 'application/x-www-form-urlencoded'
        };

        axios.post('http://localhost:8080/api/login', data, {
            headers: headers,
        }).then(response => {
            state.user = user;
            localStorage.setItem('user', user);
        }).catch(error => {
            state.user = '';
            localStorage.removeItem('user');
        });
    },

    [AUTH_REGISTER]: (state, user) => {
        let data = JSON.stringify(user);
        //let data = '"username": "'+user.username+'"&password" : "' +user.password + '"';
        let headers = {
            'Content-type': 'application/json'
        };
        axios.post('http://localhost:8080/api/register', data, {
            headers: headers,
        }).then(resp => {
            state.user = '';
            localStorage.removeItem('user');
        }).catch(error => {
            state.user = '';
            localStorage.removeItem('user');
        })
    },
    [AUTH_LOGOUT]: (state) => {
        axios.get('http://localhost:8080/api/logout').then(
            resp => {
                state.user = '';
                localStorage.removeItem('user');
            }
        ).catch(error => {
                state.user = '';
                localStorage.removeItem('user');
            }
        )

    }
};

export default {
    state,
    getters,
    actions,
    mutations,
}