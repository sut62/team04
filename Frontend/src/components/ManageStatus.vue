<template>
<v-app
    style="
    background: #B1DAFA;;
    font:50px Comic Sans MS, sans-serif;
    font-style: normal;
    line-height: 1.4em;
    "
>
  <v-card >
    <v-app-bar
      absolute
      color="#43a047"
      dark
      shrink-on-scroll
      prominent
      src="https://picsum.photos/1020/1080?random"
   
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-5"
      scroll-threshold="500"
      
    >
     
    <template v-slot:img="{ props }">
        <v-img v-bind="props" gradient="to top right, rgba(55,236,186,.7), rgba(25,32,72,.7)"></v-img>
      </template>

    

  <v-toolbar-title>ระบบจัดการสถานะ</v-toolbar-title>

      <v-spacer></v-spacer>
      

    </v-app-bar>

     

      <v-sheet id="scrolling-techniques-5"  >
      <v-container style="height: 120px;"></v-container>
      <v-form>
      <v-card-text>
        <v-row align="center" justify="center">


        <v-col cols="12" sm="5" md ="4">
        <p  class="display-1 font-weight-black px-0"
        style="margin: 10px ">Room</p>
         <v-select
                
                  label="Room"
                  outlined
                  v-model="ManageStatus.RoomId"
                  :items="Rooms"
                  item-text="room_name"
                  item-value="room_id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required

                ></v-select>
        
        <p class="display-1 font-weight-black  px-0"
        style="margin: 10px ">Status</p>
        <v-select
                  label="Status"
                  outlined
                  v-model="ManageStatus.StatusId"
                  :items="Statuss"
                  item-text="status_name"
                  item-value="status_id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                <p   class="display-1 font-weight-black  px-0"
        style="margin: 10px "
        >Employee</p>
         <v-select
                  label="Employee"
                  outlined
                  v-model="ManageStatus.EmployeeId"
                  :items="Employees"
                  item-text="em_name"
                  item-value="em_id"
                  :readonly="true"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
        <v-row  align="center" justify="center">
        <v-col>
                <v-btn  @click="saveManageStatus"  color="green"  >save</v-btn>
                <v-btn style="margin-left:12px;" @click="clear"  color="red">clear</v-btn>
                <v-btn style="margin-left:15px;" @click="Back"  color="teal lighten-1">back</v-btn>
                 <!-- {{ManageStatuss[0].status.status_id}} -->
        </v-col>
        </v-row>
          </v-col>
        </v-row>
      </v-card-text>
      </v-form>
      
    </v-sheet>
   
  </v-card>
  </v-app>

</template>

<script>
import http from "../http-common";
export default {
  name: "ManageStatus",
  data() {
    return {
      ManageStatus: {
        RoomId: "",
        StatusId: "",
        EmployeeId: ""
      },
      Rooms:[],
      Statuss:[],
      Employees:[],
      ManageStatuss:[],
      ManageStatusRoom:[],
      emid:''
      
    };
  },
  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.ManageStatus.EmployeeId  = this.emid;
      this.lock = true;
    },
    Back(){
      this.$router.push({name: 'Dashbord' , params: {em: this.emid} }); 
      },
    /* eslint-disable no-console */
    // ดึงข้อมูล Room ใส่ combobox
    getRooms() {
      http
        .get("/Room")
        .then(response => {
          this.Rooms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // ดึงข้อมูล Status ใส่ combobox
    getStatuss() {
      http
        .get("/Status")
        .then(response => {
          this.Statuss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employees")
        .then(response => {
          this.Employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getStatus_id(){
       http
        .get("/ManageStatus")
        .then(response => {
          this.ManageStatuss = response.data;
          console.log("MS",response.data);
          console.log("MS",this.ManageStatuss);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getManageStatusRoom(){
      http
      .get("/ManageStatusRoom/"+this.ManageStatus.RoomId)
       .then(response => {
          this.ManageStatusRoom = response.data;
          console.log("MSR"+this.ManageStatus.RoomId,response.data);
          console.log("ttt", typeof response.data);
          if(typeof response.data == 'object'){
            console.log("object");
            if(response.data.status.status_id == this.ManageStatus.StatusId){
              alert("ซ้ำ");
            }else{
              console.log("ไม่ซ้ำ");
              this.savePost()
            }
          }else{
            console.log("else out");
            this.savePost()
          }
        })
        .catch(e => {
          console.log(e);
        });

    },

    savePost(){
      http
        .post(
          "/ManageStatus/" +
            this.ManageStatus.RoomId+
            "/" +
            this.ManageStatus.StatusId+
            "/"+
             this.ManageStatus.EmployeeId 
             ,this.ManageStatus
        )
        .then(response => {
          console.log(response,"cc");
          this.$router.push("/view");
        })
        .catch(e => {
          console.log("error",e);    
        });

      this.submitted = true;
      window.location.reload()
      alert("บันทึกข้อมูลสำเร็จ");
      
    },
    
    // function เมื่อกดปุ่ม save
    saveManageStatus() {
      // var check = false;
      if(this.ManageStatus.RoomId == "" || 
      this.ManageStatus.StatusId == "" || 
      this.ManageStatus.EmployeeId == ""){
        alert("กรุณากรอกข้อมูลให้ครบถ้วน");
      }
      

      else {
        
        // for (let i = 0; i < this.ManageStatuss.length; i++) {
        //   let elementstatus = this.ManageStatuss[i].status.status_id;
        //   let elementroom = this.ManageStatuss[i].room.room_id;
        //   let elementdelete = this.ManageStatuss[i].Delete_at ;

        //   if((elementstatus == this.ManageStatus.StatusId) &&
        //    (elementroom == this.ManageStatus.RoomId  )&&
        //    (elementdelete != null)
        //    ){
        //     alert("สถานะที่เลือกไม่ถูกต้อง");
        //     check = true;
        //     break;
        //   } 

        // }
      
        // if(check == false){
          this.getManageStatusRoom();
      
      }
      
      //
      // }
      
    },
    clear() {
      //this.$refs.form.reset();
      //this.$v.$reset();
      this.ManageStatus.RoomId= 'Room';
      this.ManageStatus.StatusId= 'Status';
      // this.ManageStatus.EmployeeId= 'Employee';

    },
    refreshList() {
      this.getRooms();
      this.getStatuss();
      // this.getEmployees();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getRooms();
    this.getStatuss();
    this.getEmployees();
    this.getStatus_id();
    this.lockemployee();
  },
  
};
</script>
