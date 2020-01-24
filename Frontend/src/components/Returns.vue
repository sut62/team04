<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex>
        <v-app id="inspire">
          <v-content>
            <v-container class="fill-height" fluid>
              <v-row align="center" justify="center">
                <v-col cols="12" sm="6" md="6">
                 
                  <v-card class="elevation-12">
                    <v-toolbar color="blue-grey lighten-2" dark flat>
                      <!-- กรอบข้างบนสีฟ้า -->
                      <v-toolbar-title>
                        <h2>Manage Status</h2>
                      </v-toolbar-title>
                      <div class="flex-grow-1"></div>
                    </v-toolbar>

                    <v-form>
                      <v-card-text>
                        <!-- ComboBox รายชื่อลูกค้า -->
                        <v-row justify="center">
                          <v-col cols="10">
                             <v-select
                               label="Customer"
                               dense
                               v-model="Returns.customerId"
                               :items="customer"
                               item-text="name"
                               item-value="id"
                               :rules="[(v) => !!v || 'Item is required']"
                               required
                              ></v-select>
                          </v-col>
                        </v-row>

                        <!-- ComboBox พนักงาน-->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                             label="Employee"
                             dense
                             :readonly="true"
                             v-model="Returns.employeeId"
                             :items="employees"
                             item-text="em_name"
                             item-value="em_id"
                             :rules="[(v) => !!v || 'Item is required']"
                             required
                            ></v-select>
                          </v-col>
                        </v-row>

                        <!-- ComboBox อุปกรณ์ -->
                        <v-row justify="center">
                          <v-col cols="10">
                            <v-select
                             label="Borrow"
                             dense
                             v-model="Returns.borrowId"
                             :items="BorrowsResult"
                             item-text="name"
                             item-value="bid"
                             :rules="[(v) => !!v || 'Item is required']"
                             required
                            ></v-select>
                          </v-col>
                        </v-row>
                      </v-card-text>
                    </v-form>
                        <!-- text field note-->
                    <v-row justify="center">
                      <v-col cols="12" sm="6">
                        <v-text-field
                         v-model="Returns.note"
                         label="หมายเหตุ(ต้องใส่ทุกครั้ง)"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    
                    <v-card-actions >
                      <!-- ปุ่มกด -->
                        <div class="flex-grow-1" ></div>
                      <v-btn  @click="save"  color="green"  >Save</v-btn>
                      <v-btn style="margin-left:12px;" @click="clear"  color="red">Clear</v-btn>
                      <v-btn style="margin-left:15px;" @click="back"  color="teal lighten-1">Back</v-btn>
                    </v-card-actions>
                    
                  </v-card>
                   
                <p></p>
              <div v-if = "clickReturns == true">
              <div v-if = "returnsCheck == true">
              <v-alert type="success">Returns Completed</v-alert>
              </div>
              <div v-if = "returnsCheck == false">
              <v-alert type="error">Can't Returns</v-alert>
              </div>
              </div>

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
  name: "Returns",
  data() {
    return {
      Returns: {
        borrowId: "",
        customerId: "",
        employeeId: "",
        note: ""
      },
     // object: [], //ค่าที่มาจาการกรอง Receipt
      Borrows: [],
      items: [],
      customer: [],
      employees: [],
      BorrowsResult: [], // ค่าใหม่ใช้กับ combobox
      employee: "",
      returnsCheck: false,
      clickReturns: false,
    };
  },
  methods: {
    /* eslint-disable no-console */
    lockemployee(){
      this.emid = this.$route.params.em;
      this.employee = this.emid;
      this.Returns.employeeId  = this.employee;
      this.lock = true;
    },
    back(){
      this.$router.push({name: 'Dashbord' , params: {em: this.emid} }); 
    },
    getCustomers() {
      http
        .get("/customer")
        .then(response => {
          this.customer = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployee() {
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

     getBorrows() {
      http
      .get("/Borrow")
      .then(response => {
        this.Borrows = response.data;
        console.log(response.data);

      
      })
      .catch(e => {
        console.log(e);
      });
    },

     save() {
       this.clickReturns = true;
       if(this.Returns.customerId==''||this.Returns.employeeId==null||this.Returns.borrowId==''){
       this.returnsCheck = false;
       }
       else if(this.Returns.note.length >50 ||
      !this.Returns.note.match(/^([a-zA-z0-9ก-๙-|\u0020])+$/i)){
        this.returnsCheck = false;
      }
       else{
      this.returnsCheck = true;
      console.log("Save");
      console.log( this.Returns.customerId );
      console.log( this.Returns.borrowId);
      console.log( this.Returns.employeeId);
      console.log(this.Returns.note);
      http
        .post(
          "/returns/" +
            this.Returns.customerId +
            "/" +
             this.Returns.borrowId +
            "/" +
            this.Returns.employeeId +
            "/" +
            this.Returns.note,
          this.Returns
        )
        .then(responses => {
          console.log(responses);
          //alert("เรียบร้อยแล้ว");
        })
        .catch(e => {
          console.log(e);
        });
      }
    },
    comboboxBorrow(){
      this.Borrows.forEach((value) =>{
        this.BorrowsResult.push({
          "bid": value.bid,
          "name": `หมายเลขการยืม `+value.bid+`  สิ่งของที่ยืม  `+value.manageequipment.equipmentName.name+`  จำนวนที่ยืม  `+value.manageequipment.manageEquipment_amount

        });
      

      })
    },
    clear() {
        console.log( "clear");
    //  window.location.reload();
    this.Returns.borrowId = '';
    this.Returns.customerId= '';
    this.Returns.note='';
    this.clickReturns = false;

    }, 
     refreshList() {
    this.getEmployee();
    this.getCustomers();
    this.getBorrows();
    this.lockemployee();
    this.note();
      
    }
    
},
     
  mounted() {
    this.getEmployee();
    this.getCustomers();
    this.getBorrows();
    this.lockemployee();
    this.note();
    
  },
  watch:{

    Borrows(){
      this.comboboxBorrow();
    }
  }
     
};

 /* eslint-disable no-console */
</script>
