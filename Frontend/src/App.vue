<template>
  <v-app>
    <Titlebar />
    <v-content>
      <router-view />
    </v-content>
    <footer>
      <v-col class="ml-12">
        <label>Theme</label>
        <v-switch v-model="$vuetify.theme.dark" primary label="Dark"></v-switch>
      </v-col>
    </footer>
  </v-app>
</template>

<script>
//import HelloWorld from './components/HelloWorld';
//import Todos  from './components/Todos';
import Titlebar from "./components/Titlebar";
export default {
  name: "App",
  components: {
    Titlebar
  },
  data: () => ({
    //
  })
};
</script>
<style scoped>
span.Reg {
  font: 30px Abyssopelagic, sans-serif;
  width: 100%;
}

</style>>
