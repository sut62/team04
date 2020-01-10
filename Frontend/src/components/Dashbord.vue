<template>
    <v-container class="ml-auto mt-3 pt-3 mr-auto">
        
        <v-card justify="center">
            <h1 class="text mt-5 pt-5">
          <strong>Menu</strong>
            </h1>
            <v-form>
            <p></p>
            <v-row justify="center">
            <v-btn 
            @click="ManageEquipment"
            class="cyan lighten-1 white--text">
            Manage Equipment
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="ManageStatus"
            class="cyan lighten-1 white--text">
            Manage Status
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="Borrow"
            class="cyan lighten-1 white--text">
            Borrow
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="Returns"
            class="cyan lighten-1 white--text">
            Returns
            </v-btn>
            </v-row>
            <p></p>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="Logout"
            class="red white--text">
            Logout
            </v-btn>
            </v-row>
            <p></p>
            </v-form>
        </v-card>
    </v-container>
</template>
<script>
export default {
    
    data(){
        return{
            emid:-66
        };
    },
 methods: {
getLockId(){
     this.emid = this.$route.params.em;
     
},
Logout(){
    this.$router.push("/")
},
ManageEquipment(){
     this.$router.push({name: 'ManageEquipment', params: {em: this.emid} });
 },
 ManageStatus(){
     this.$router.push({name: 'ManageStatus', params: {em: this.emid} });
 },
 Borrow(){
     this.$router.push({name: 'Borrow', params: {em: this.emid} });
 },
 Returns(){
     this.$router.push({name: 'Returns', params: {em: this.emid} });
 }
},
mounted(){
    this.getLockId();
}
}
</script>
<style scoped>
span {
  font: 20px BankGothic Md BT, sans-serif;
  -width: 100%;
}
h1.text {
  text-align: center;
  font: 30px BankGothic Md BT, sans-serif;
  width: 100%;
}

</style>