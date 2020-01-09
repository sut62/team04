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


                        <!-- ComboBox อุปกรณ์-->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                              label="อุปกรณ์"
                              outlined
                              v-model="Borrow.equipId"
                              :items="Result"
                              item-text="name"
                              item-value="equipmentname_id"
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


                      </v-card-text>
                    </v-form>

                    <v-card-actions>
                      <!-- ปุ่มกด -->
                      <div class="flex-grow-1"></div>
                      <v-btn color="primary" @click="save">ยืมอุปกรณ์</v-btn>
                      <v-btn color="primary" @click="clear">ล้างข้อมูล</v-btn>
                      <v-btn color="primary" @click="back" >ย้อนกลับ</v-btn>
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


<script>import http from "../http-common";
export default {
  name: "Borrow",
  data() {
    return {
      manageEquipmentss:[],
      Borrow: {
        customerId: "",
        employeeId: "",
        equipId:""
      },
        Borrows:[],
        customers:[],
        employees:[],
        equips:[],
        EQNameID:[], //จัดการอุปรกรณ์
        equipsFull:[],
        lock: "",
        emid:''
    };
  },
  methods: {
    /* eslint-disable */
   ResultALL(){
      this.equipsFull = this.Result;
      // this.
      
   },
    CheckSum(){
      
      if(this.manageEquipmentss != null)
      return true;
    },
    
    
    lockemployee(){
      this.emid = this.$route.params.em;
      this.Borrow.employeeId  = this.emid;
      this.lock = true;
    },
    back(){
      this.$router.push({name: 'Dashbord' , params: {em: this.emid} }); 
    },
    SumAllEq(){
      this.manageEquipmentss.forEach((element1,index1) => {
      });
    },
    
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employees")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    getCustomers() {
      http
      .get("/customer")
      .then(response => {
        this.customers = response.data;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
    },
    
    
    getManageequipments() {
      http
      .get("/manageEquipments")
      .then(response => {
        this.manageEquipmentss = response.data;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
    },

    save() {
      if (
        this.Borrow.employeeId == "" ||
        this.Borrow.customerId == "" ||
        this.Borrow.equipId == ""
      
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
              this.Borrow.equipId,
            this.Borrow
          )
          .then(response => {
            console.log(response);
            alert("ทำรายการเรียบร้อยแล้ว");
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
      //this.Borrow.employeeId = "";
      this.Borrow.customerId = "";
      this.Borrow.equipId = "";
    },
    refreshList() {
     // this.getEmployees();
      this.getCustomers();
      this.getManageequipments();
      //this.lockemployee();
      
    }
    
  },
  mounted() {
    this.getEmployees();
    this.getCustomers();
    this.getManageequipments();
    this.lockemployee();
    this.ResultALL();
  },
  computed:{
    Result(){
      let result1 = new Set();
      let result = new Array();
      // let a = new Array();
      if(this.CheckSum()){
        for (let i = 0; i < this.manageEquipmentss.length; i++) {
          result1.add(this.manageEquipmentss[i].equipmentName.equipmentname_id);
        }
      }
      result1.forEach((value =>{
        for (let i = 0; i < this.manageEquipmentss.length; i++) {
          let name = this.manageEquipmentss[i].equipmentName.equipmentname_id;
          if(value === name){
            result.push(this.manageEquipmentss[i].equipmentName);
            break;
          }
        }
      }))
      return result;
      1
    },

  }
  
};
/* eslint-disable no-console*/

</script>
