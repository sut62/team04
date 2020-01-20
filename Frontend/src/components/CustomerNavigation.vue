<template>
  <v-navigation-drawer
    v-model="drawer"
    app
    :clipped="$vuetify.breakpoint.lgAndUp"
    color="grey lighten-4"
    temporary
  >

    <!-- v-list -->

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
          @click="Logout"
        >Logout</v-btn>
      </div>
    </template>
  </v-navigation-drawer>

</template>

<script>
export default {
  props: { drawer: Boolean },
  data() {
    return {};
  },
  methods: {
    Resvationopen() {
      this.$router.push({
        name: "Reservations"
        // params: { cus: this.customer.id }
      });
    },
    Logout(){
      this.$store.dispatch("setOpendrawer", false);
      this.$router.push("/");
    }
  }
};
</script>

<style>
</style>