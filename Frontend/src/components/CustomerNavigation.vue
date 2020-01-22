<template>
  <v-navigation-drawer
    v-model="checkShow"
    app
    :clipped="$vuetify.breakpoint.lgAndUp"
    color="grey lighten-4"
  >
    <v-divider
      dark
      class="my-3"
    ></v-divider>
    <div class="pa-2">
      <v-btn
        block
        v-on:click="Resvationopen"
      >Reservation</v-btn>
    </div>
    <v-divider
      dark
      class="my-3"
    ></v-divider>
    <div class="pa-2">
      <v-btn
        block
        to="Profile"
      >Profile</v-btn>
    </div>
    <v-divider
      dark
      class="my-3"
    ></v-divider>

    <template v-slot:append>
      <div class="pa-2">
        <v-btn
          block
          v-on:click="Logout"
        >Logout</v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
/*eslint-disable */
export default {
  props: { drawer: { type: Boolean } },
  // props: ["value"],
  data() {
    return {
      checkShow: ""
    };
  },
  methods: {
    Resvationopen() {
      this.$router.push({
        name: "Reservations"
        // params: { cus: this.customer.id }
      });
    },
    Logout() {
      this.$store.commit("setDrawer", false);
      this.$store.commit("setOpendrawer", false);
      this.checkShow = this.$store.getters.drawer;
      this.$router.push("/");
    },
    ShowDrawer() {
      this.checkShow = this.$store.state.drawer;
      // หรือ เขียนว่า this.checkShow = this.drawer;
    }
  },
  watch: {
    drawer() {
      this.ShowDrawer();
    }
  },
  computed: {},
  mounted() {}
};
/*eslint-disable */
</script>

<style></style>
