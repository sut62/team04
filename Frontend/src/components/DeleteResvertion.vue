<template>
  <v-container>
    <h1 class="display-2 dodoboder">ยกเลิกจองห้องค้นคว้าออนไลน์</h1>
    <v-row
      :align="center"
      :justify="center"
    >
      <v-col cols=" 7 md-auto">
        <v-data-table
          :headers="headers"
          :items="desserts"
          :items-per-page="5"
          class="elevation-1"
        >

        </v-data-table>
      </v-col>
      <v-col cols="5 md-auto">
        <v-card
          class="mx-5 "
          outlined
          width="500"
          height="500"
        >
          <p class="ma-6">ชื่อผู้ใช้บริการ</p>
          <v-select
            :readonly="true"
            outlined
            class="ma-10"
            label="Customer"
            :items="customers"
            item-text="name"
            item-value="id"
            v-model="lockcustomer"
          ></v-select>
          <p class="ma-6">รหัสการจองห้อง</p>
          <v-select
            outlined
            class="ma-10"
            label="Resvertion"
            :items="Reservation"
          ></v-select>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
/*eslint-disable */
import http from "../http-common";
export default {
  data() {
    return {
      customers: [], // ดึง customer จาก DB
      resvertionCustomer: [], // ดึง resvertion จาก DB
      lockcustomer: "",
      headers: [
        {
          text: "Dessert (100g serving)",
          align: "left",
          sortable: false,
          value: "name"
        },
        { text: "Calories", value: "calories" },
        { text: "Fat (g)", value: "fat" },
        { text: "Carbs (g)", value: "carbs" },
        { text: "Protein (g)", value: "protein" },
        { text: "Iron (%)", value: "iron" }
      ]
    };
  },
  methods: {
    getcustomer() {
      //ดึงห้องที่มีค่า null ออกมา
      http
        .get("/customer")
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          console.log(e);
        });

      this.lockcustomer = this.$store.getters.customer;
    },
    getResvertionRoom() {
      http
        .get("/reservationses" + "/" + this.$store.getters.customer)
        .then(response => {
          this.resvertionCustomer = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getcustomer();
  }
};

/*eslint-disable */
</script>
<style scoped>
.dodoboder {
  border: 4px solid #90caf9;
  border-radius: 40px 40px 40px 40px;
  padding: 1.5%;
  margin: 3% 0 3% 30%;
  width: 56%; /*  ความยาว border*/
}
</style>
