import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
// example vuex   https://github.com/vuejs/vuex
export const store = new Vuex.Store({
    //ตัวแปรที่ประกาศไว้ ใช้เป็น Global Value
    state: {
        Opendrawer: false,
        customer: '',
        drawer: false
    },

    //ทำการบันทึกค่าลง state(Global Value)
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

    // ถึงค่าออกไปยัง component ที่ต้องการ
    getters: {
        Opendrawer: state => state.Opendrawer,
        customer: state => state.customer,
        drawer: state => state.drawer
    },

    //ก่อนจะบันทึกค่าลงใน store อยากให้ทำอะไรก่อน
    actions: {
        setOpendrawer(context, payload) {
            context.commit("setOpendrawer", payload);
        },
        setCustomer(context, payload) {
            context.commit("setCustomer", payload);
        },
        setDrawer(context, payload) {
            context.commit("setDrawer", payload);
        },
        //ทำการเปลี่ยนค่าของ Navigator bar ก่อนบันทึกค่า
        setChangeDrawer(context) {
            this.state.drawer = !this.state.drawer;
            context.commit("setDrawer", this.state.drawer);
        },
    }
})