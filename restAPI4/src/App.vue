<template>
  <v-app>
    <v-app-bar color="primary" dark>
      <v-toolbar-title>사용자 관리 시스템</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn to="/" text>홈</v-btn>
    </v-app-bar>

    <v-main>
      <v-container>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App'
}
</script> 