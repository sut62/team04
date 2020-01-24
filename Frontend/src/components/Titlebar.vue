<template>
  <nav>

    <v-app-bar
      app
      clipped-left
      class="light-blue darken-4"
    >

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
/*eslint-disable */
import CustomerNavigation from "./CustomerNavigation";
export default {
  components: {
    CustomerNavigation
  },
  data() {
    return {
      drawer: ""
    };
  },
  methods: {},
  computed: {}
}; //v-bind:drawer="drawer"    v-on:childToParent="drawer = $event"
/*eslint-disable */
</script>
