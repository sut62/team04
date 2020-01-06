<template>
  <div class="ManageEquipment">
    <div class="outside">
      <br>
      <v-col cols = 12>
        <v-container>
          <v-layout row wrap>
            <v-col cols = 3>

              <v-select
              v-model="ManageEquipment.EmName"
              :items="Employee"
              label="Employee"
              item-text="em_name"
              item-value="em_id"

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

              outlined
              dense
              ></v-select>

              <br>
              <v-text-field
              v-model="ManageEquipment.Amount"
              label="amount"
              :rules="[(v) => !!v || 'Item is required']"
              required
              ></v-text-field>

{{ManageEquipment}}
              <br>
              <v-btn small @click="saveData">Add Menu</v-btn>
              <!-- @click="saveData" -->
            </v-col>
            <v-col cols = 7>
              <v-card
              class="mx-auto"
              max-width="550"
              outlined
              >
              <v-simple-table style = "center" >
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-left">Menu name</th>
                      <th class="text-left">Price</th>
                      <th class="text-left">Category</th>
                      <th class="text-left">Ingredient</th>
                      <th class="text-left">Type</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in showtable" :key="item.name">
                      <!-- <td class="text-center">{{ item.sel_patient.id }}</td>
                      <td class="text-center">{{ item.sel_patient.name }}</td>
                      <td class="text-center">{{ item.sel_cate.cname }}</td>
                      <td class="text-center">{{ item.sel_ingre.iname }}</td>
                      <td class="text-center">{{ item.sel_type.tname }}</td> -->
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-card>
          </v-col>
        </v-layout>
      </v-container>
    </v-col>
  </div>
</div>
</template>

<script>
import http from "../https";
export default {
  name:"ManageEquipment",
  data(){
    return{
      ManageEquipment:{
        EmName: "",
        Typename: "",
        EQname: "",
        Amount: "",
      },
      showtable:[],
      Employee:[],
      EquipmentType:[],
      EquipmentName:[]
    };
  },
  methods:{
    saveData(){
      http.post("/manageEquipments/"+
      this.ManageEquipment.EmName
      +"/"+
      this.ManageEquipment.Typename
      +"/"+
      this.ManageEquipment.EQname
      +"/" +
      this.ManageEquipment.Amount)
      .then(response => {
        console.log(response);
        alert("เพิ่มเมนูสำเร็จ")
        location.reload();
        // this.getDataTable();

      })
      .catch(e => {
        console.log(e);
        alert("เพิ่มเมนูไม่สำเร็จ")
      })
      ;
      this.submitted = true;
    },
    getEmployee(){

      http.get("/employees").then(response => {
        this.Employee = response.data;
      });
      this.submitted = true;
    },
    getEquipmentType(){

      http.get("/equipmentTypes").then(response =>{
        this.EquipmentType = response.data;
      });
      this.submitted = true;
    },
    getEquipmentName(){
      http.get("/equipmentNames").then(response =>{
        this.EquipmentName = response.data;
      });
      this.submitted = true;

    },
    getDataTable(){
      http.get("/manageEquipments").then(response =>{
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted(){
    this.getEmployee();
    this.getEquipmentType();
    this.getEquipmentName();
    this.getDataTable();
  }
}
</script>
