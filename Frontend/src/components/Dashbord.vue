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
            @click="Register"
            class="cyan lighten-1 white--text">
            Register
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="BookRanking"
            class="cyan lighten-1 white--text">
            Ranking
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="BookLot"
            class="cyan lighten-1 white--text">
            AddBook
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="Receipt"
            class="cyan lighten-1 white--text">
            SellBook
            </v-btn>
            </v-row>
            <p></p>
            <v-row justify="center">
            <v-btn
            @click="Collect"
            class="cyan lighten-1 white--text">
            CollectPoint
            </v-btn>
            </v-row>
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
Register(){
     this.$router.push({name: 'Register', params: {em: this.emid} });
 },
 BookLot(){
     this.$router.push({name: 'AddBook', params: {em: this.emid} });
 },
 BookRanking(){
     this.$router.push({name: 'Ranking', params: {em: this.emid} });
 },
 Receipt(){
     this.$router.push({name: 'SellBook', params: {em: this.emid} });
 },
 Collect(){
     this.$router.push({name: 'Collect', params: {em: this.emid} })
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