<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex>
        <v-app id="inspire">
          <v-content>
            <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                <v-col cols="12" sm="8" md="4">
                  <v-card class="elevation-12">
                    <v-toolbar color="primary" dark flat>
                      <!-- กรอบข้างบนสีฟ้า -->

                      <v-toolbar-title>
                        <h2>ยืมอุปกรณ์</h2>
                      </v-toolbar-title>
                      <div class="flex-grow-1"></div>
                    </v-toolbar>
                    <v-form>
                      <v-card-text>
                        <!-- ComboBox รายชื่อลูกค้า -->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                              v-on:change="getfindByCustomer"
                              label="รายชื่อลูกค้า"
                              outlined
                              v-model="Borrow.customerId"
                              :items="customers"
                              item-text="name"
                              item-value="id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                            ></v-select>
                          </v-col>
                        </v-row>
                        <!-- ComboBox รายการอุปการณ์ -->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                              label="อุปการณ์"
                              outlined
                              v-model="Borrow.equiptId"
                              :items="object"
                              item-text="RE_id"
                              item-value="RE_id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                            ></v-select>
                          </v-col>
                        </v-row>
                        
                        <!-- ComboBox รายชื่อพนักงาน -->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                              label="รายชื่อพนักงาน"
                              outlined
                              :readonly="true"
                              v-model="Borrow.employeeId"
                              :items="employees"
                              item-text="em_name"
                              item-value="em_id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                            ></v-select>
                          </v-col>
                        </v-row>

                        <!-- กรอกแต้ม 
                        <v-row justify="center">
                          <v-col cols="3">
                            <v-text-field label="กรอกแต้ม" outlined v-model="Borrow.cpointId"></v-text-field>
                          </v-col>
                        </v-row>
                        -->

                      </v-card-text>
                    </v-form>
                    <v-card-actions>
                      <!-- ปุ่มกด -->
                      <div class="flex-grow-1"></div>
                      <v-btn color="primary" @click="save">ยืนยัน</v-btn>
                      <v-btn color="primary" @click="clear">ล้างข้อมูล</v-btn>
                      <v-btn rounded style="margin: 30px" @click="back" >ย้อนกลับ</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-col>
              </v-row>
            </v-container>
          </v-content>
        </v-app>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
import http from "../http-common";
export default {
  name: "Borrow",
  data() {
    return {
      Borrow: {
        equiptId: "",
        customerId: "",
        employeeId: ""
      },
     // object: [], //ค่าที่มาจาการกรอง Receipt
      Borrows: [],
      equipts: [],
      customers: [],
      employees: []
    };
  },
  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.Borrow.EmployeeId  = this.emid;
      this.lock = true;
    },
    back(){
      this.$router.push({name: 'Dashbord' , params: {em: this.emid} }); 
    },
    /*
    cheack(value) {
      for (let coll in this.Borrows) {
        let cusid = this.Borrows[coll].customer.id;
        let recpid = this.Borrows[coll].receipt.RE_id;

        if (cusid == this.Borrows.customerId && recpid == value.RE_id) {
          return false;
        }
      }
      return true;
    },
    /* eslint-disable no-console */
   /* getfindByCustomer() {
      this.object = []; //clear ค่า

      let recp = this.Borrows.customerId;
      for (let key in this.receipts) {
        let recp1 = this.receipts[key].customer.id;
        if (recp == recp1 && this.cheack(this.receipts[key]) == true) {
          this.object.push(this.receipts[key]);
        }
      }
    },*/

    // ดึงข้อมูล Receipt ใส่ combobox
    getEquiptments() {
      http
        .get("/receipt")
        .then(response => {
          this.receipts = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Customer ใส่ combobox
    getCustomers() {
      http
        .get("/customer")
        .then(response => {
          this.customers = response.data;
          console.log("cus ", response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม submit
    save() {
      if (
        this.Borrow.employeeId == "" ||
        this.Borrow.customerId == "" ||
        this.Borrow.equiptmentId == ""
      ) {
        alert("เลือกให้ครบจ้า");
      } else {
        http
          .post(
            "/Borrow/" +
              this.Borrow.customerId +
              "/" +
              this.Borrow.employeeId +
              "/" +
              this.Borrow.cpointId,
            this.Borrow
          )
          .then(response => {
            console.log(response);
            this.getCpoint();
            alert("สะสมแต้มให้แล้วนะ");
          })
          .catch(e => {
            console.log(e);
          });

        this.submitted = true;
      }
    },
    clear() {
      //this.$refs.form.reset();
      //this.$v.$reset();
      this.Borrow.employeeId = "";
      this.Borrow.customerId = "";
    },
    refreshList() {
      this.getEmployees();
      this.getCustomers();
      this.getEquipments();
      this.lockemployee();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getCustomers();
    this.getEquipments();
    this.lockemployee();
  }
};
</script>
