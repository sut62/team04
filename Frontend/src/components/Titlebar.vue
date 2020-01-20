<template>
  <nav>

    <CustomerNavigation v-bind:drawer="drawer"></CustomerNavigation>
    <v-app-bar
      app
      clipped-left
      class="light-blue darken-4"
    >
      <v-app-bar-nav-icon
        class="white--text"
        v-if="$store.getters.Opendrawer"
        @click.stop="CheckTitle"
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="headline text-uppercase white--text">
        <span class="Reg">Room </span>
        <span class="Reg"> Book</span>

      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2 white--text">Latest Release</span>
      </v-btn>
    </v-app-bar>
  </nav>
</template>

<script>
//drawer = ! drawer
/*eslint-disable */
// import { mapGetters } from "vuex";
import CustomerNavigation from "./CustomerNavigation";
export default {
  components: {
    CustomerNavigation
  },
  data() {
    return {
      drawer: false
    };
  },
  methods: {
    CheckTitle() {
      this.drawer = !this.drawer;
      this.$store.commit("setDrawer", this.drawer);
    },
    upDateDrawer(upDateDrawer) {
      this.drawer = upDateDrawer;
    }
  },
  computed: {},
  watch: {
    drawer() {}
  }
}; //v-bind:drawer="drawer"
/*eslint-disable */
</script>