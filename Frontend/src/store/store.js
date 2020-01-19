import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        Opendrawer: '',
        customer: ''
    },
    mutations: {
        setOpendrawer(state, value) {
            state.Opendrawer = value;
        },
        setCustomer(state, value) {
            state.customer = value;
        }
    },
    getters: {
        Opendrawer: state => state.Opendrawer,
        customer: state => state.customer
    },
    actions: {
        setOpendrawer(context, payload) {
            context.commit("setOpendrawer", payload);
        },
        setCustomer(context, payload) {
            context.commit("setCustomer", payload);
        }
    }
})