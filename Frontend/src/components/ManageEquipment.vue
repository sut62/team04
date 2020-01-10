<template>
  <div class="ManageEquipment">
    <div class="outside">
      <v-row justify="center">
        <br>
        <!-- <v-col justify="center"> -->
        <!-- <v-container> -->
        <!-- <v-layout row wrap> -->
        <v-col
          cols=5
          justify="center"
        >

          <v-toolbar
            color="black"
            dark
            flat
          >
            <!-- กรอบข้างบนสีฟ้า -->
            <v-toolbar-title>

              <h2 class="headline ">

                ระบบจัดการอุปกรณ์

              </h2>

            </v-toolbar-title>
            <div class="flex-grow-1"></div>
          </v-toolbar>
          <br>
          <br>
          <v-sheet>

            <v-select
              v-if="lock"
              v-model="ManageEquipment.EmName"
              :items="Employee"
              label="Employee"
              item-text="em_name"
              item-value="em_id"
              :readonly="true"
              solo
              outlined
              dense
            ></v-select>

            <br>
            <v-select
              v-model="ManageEquipment.Typename"
              :items="EquipmentType"
              label="EquipmentType"
              item-text="type"
              item-value="equipmenttype_id"
              solo
              outlined
              dense
            ></v-select>

            <br>
            <v-select
              v-model="ManageEquipment.EQname"
              :items="EquipmentName"
              label="EquipmentName"
              item-text="name"
              item-value="equipmentname_id"
              solo
              outlined
              dense
            ></v-select>

            <br>
            <v-text-field
              v-model="ManageEquipment.Amount"
              label="amount"
              :rules="[(v) => !!v || 'Item is required']"
              required
              solo
              outlined
            ></v-text-field>

            <br>
            <v-btn
              small
              @click="saveData"
            >Add Equipment</v-btn>
          </v-sheet>
          <!-- @click="saveData" -->
        </v-col>

        <!-- <v-col cols = 7>
              <br>
              <br>
              <br>
              <br>
              <v-card
              class="mx-auto"
              max-width="550"
              outlined
              >
              <v-simple-table style = "center" >
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-left">Employee</th>
                      <th class="text-left">EquipmentType</th>
                      <th class="text-left">EquipmentName</th>
                      <th class="text-left">Amount</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in showtable" :key="item.name">
                      <td class="text-center">{{ item.Employee.em_name }}</td>
                      <td class="text-center">{{ item.EquipmentType.type }}</td>
                      <td class="text-center">{{ item.EquipmentName.name }}</td>
                      <td class="text-center">{{ item.manageEquipment_amount+" ea" }}</td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-card>
          </v-col> -->
        <!-- </v-layout> -->
        <!-- </v-container> -->
        <!-- </v-col> -->
      </v-row>
    </div>
  </div>
</template>

<script>
/* eslint-disable no-console */
import http from "../http-common";
export default {
  name: "ManageEquipment",
  data() {
    return {
      ManageEquipment: {
        EmName: "",
        Typename: "",
        EQname: "",
        Amount: ""
      },

      Employee: [],
      EquipmentName: [],
      EquipmentType: [],
      EquipmentAmount: "",
      showtable: [],
      lock: "",
      employee: ""
    };
  },
  methods: {
    lockemployee() {
      this.emid = this.$route.params.em;
      this.employee = this.emid;
      this.ManageEquipment.EmName = this.employee;
      this.lock = true;
    },
    saveData() {
      http
        .post(
          "/manageEquipments/" +
            this.ManageEquipment.EmName +
            "/" +
            this.ManageEquipment.Typename +
            "/" +
            this.ManageEquipment.EQname +
            "/" +
            this.ManageEquipment.Amount
        )
        .then(response => {
          console.log(response);
          alert("เพิ่มเมนูสำเร็จ");
          // location.reload();
          // this.getDataTable();
          this.ManageEquipment.Typename = "";
          this.ManageEquipment.EQname = "";
          this.ManageEquipment.Amount = "";
        })
        .catch(e => {
          console.log(e);
          alert("เพิ่มเมนูไม่สำเร็จ");
        });
      this.submitted = true;
    },
    getEmployee() {
      http.get("/employees").then(response => {
        this.Employee = response.data;
      });
      this.submitted = true;
    },
    getEquipmentType() {
      http.get("/equipmentTypes").then(response => {
        this.EquipmentType = response.data;
      });
      this.submitted = true;
    },
    getEquipmentName() {
      http.get("/equipmentNames").then(response => {
        this.EquipmentName = response.data;
      });
      this.submitted = true;
    },
    getDataTable() {
      http.get("/manageEquipments").then(response => {
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted() {
    this.getEmployee();
    this.getEquipmentType();
    this.getEquipmentName();
    this.getDataTable();
    this.lockemployee();
  }
};
/* eslint-disable no-console */
</script>
