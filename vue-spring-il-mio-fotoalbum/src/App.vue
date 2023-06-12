<script>
//import AppHeader from './components/AppHeader.vue';
export default {
  name: 'App',
  components: {}
}
</script>

<template>
  <!-- <AppHeader /> -->
  <section id="recipe-board" class=" py-5">
    <div class="container">
      <main>
        <router-view></router-view>
      </main>
    </div>
  </section>
</template>

<style>
#recipe-board {
  background-size: cover;
  min-height: calc(100vh - 56px);
}
</style>