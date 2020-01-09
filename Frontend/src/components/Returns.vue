<template>
  <v-app id="inspire">
    <v-app-bar app clipped-right color="#00796B" dark>

      <v-toolbar-title>
        <h1>Returns</h1>
      </v-toolbar-title>

      <v-spacer></v-spacer>
    </v-app-bar>
    <br/><br/>
    <br/><br/>
    <br/><br/>
    <v-container fluid>
    <v-row align="center" justify="center">
      <v-col class="d-flex" cols="12" sm="6">
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

    <br/><br/>
    <v-row align="center" justify="center">
      <v-col class="d-flex" cols="12" sm="6">
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

    <br/><br/>
    <v-row align="center" justify="center">
      <v-col class="d-flex" cols="12" sm="6">
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
    
    
    <br/><br/>
    <br/><br/>
   
    <v-row class="text-center" cols="12" sm="4">
        <v-btn Style ="margin-left:500px;" @click="save" color="light-blue darken-4">Save</v-btn>
        <v-btn Style ="margin-left:450px;" @click="clear" color="light-blue darken-1">Clear</v-btn>
    </v-row>

  </v-container>
  </v-app>
  

  
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
        employeeId: ""
      },
     // object: [], //ค่าที่มาจาการกรอง Receipt
      Borrows: [],
      items: [],
      customer: [],
      employees: [],
      BorrowsResult: [] // ค่าใหม่ใช้กับ combobox
    };
  },
  methods: {
    /* eslint-disable no-console */
    lockemployee(){
      this.emid = this.$route.params.em;
      this.Borrow.EmployeeId  = this.emid;
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
      console.log("Save");
      console.log( this.Returns.customerId );
      console.log( this.Returns.borrowId);
      console.log( this.Returns.employeeId);
      http
        .post(
          "/returns/" +
            this.Returns.customerId +
            "/" +
             this.Returns.borrowId +
            "/" +
            this.Returns.employeeId,
          this.Returns
        )
        .then(responses => {
          console.log(responses);
          alert("เรียบร้อยแล้ว");
        })
        .catch(e => {
          console.log(e);
        });
    },
    comboboxBorrow(){
        // BorrowsResult
      this.Borrows.find((value) =>{
        // console.log(value.bid);
        // console.log(value.manageequipment.manageEquipment_amount);
        // console.log(value.manageequipment.equipmentName);

        this.BorrowsResult.push({
          // "bid": value.bid,
          // "name": "หมายเลขการยืม "+value.bid+"  สิ่งของที่ยืม  "+"value.manageequipment.equipmentName"+"  จำนวนที่ยืม  "+value.manageequipment.manageEquipment_amount
          "bid": value.bid,
          "name": `หมายเลขการยืม `+value.bid+`  สิ่งของที่ยืม  `+value.manageequipment.equipmentName+`  จำนวนที่ยืม  `+value.manageequipment.manageEquipment_amount

        });
      

      })
    },
    clear() {
        console.log( "clear");
     window.location.reload();

    }, 
     refreshList() {
    this.getEmployee();
    this.getCustomers();
    this.getBorrows();
    this.lockemployee();
      
    }
    
},
     
  mounted() {
    this.getEmployee();
    this.getCustomers();
    this.getBorrows();
    this.lockemployee();
    
  },
  watch:{

    Borrows(){
      this.comboboxBorrow();
    }
  }
     
};

 /* eslint-disable no-console */
</script>