import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        Opendrawer: '',
        customer: '',
        drawer: ''
    },
    mutations: {
        setOpendrawer(state, value) {
            state.Opendrawer = value;
        },
        setCustomer(state, value) {
            state.customer = value;
        },
        setDrawer(state, value) {
            state.drawer = value;
        }
    },
    getters: {
        Opendrawer: state => state.Opendrawer,
        customer: state => state.customer,
        drawer: state => state.drawer
    },
    actions: {
        setOpendrawer(context, payload) {
            context.commit("setOpendrawer", payload);
        },
        setCustomer(context, payload) {
            context.commit("setCustomer", payload);
        },
        setDrawer(context, payload) {
            context.commit("setDrawer", payload);
        }
    }
})