<script>
import axios from 'axios';
import FotoCard from '../components/FotoCard.vue';
export default {
    components: {
        FotoCard
    },
    data() {
        return {
            apiURL: 'http://127.0.0.1:8080/api/v1/foto',
            apiURL_FILTRO: 'http://127.0.0.1:8080/api/v1/foto/filtro?titolo=',
            foto: [],
            titolo: ''
        }
    },
    methods: {
        getFoto() {
            axios.get(this.apiURL)
                .then((response) => {
                    this.foto = response.data;
                })
        },
        getFotoFiltro() {
            axios.post(`http://127.0.0.1:8080/api/v1/foto/filtro?titolo=${this.titolo}`)
                .then((response) => {
                    this.foto = [];
                    this.foto = response.data;
                })
        },
    },
    created() {
        this.getFoto();
    }
}


</script>

<template>
    <main class="mt-5 pt-1">
        <div class="container">
            <div class="row">
                <div class="col-12 my-3">
                    <input type="text" v-model="titolo" id="titolo" placeholder="filtra" @keypress="getFotoFiltro">
                </div>
            </div>
            <div class="d-flex">
                <FotoCard v-for="foto in foto" :foto="foto" :show="true"></FotoCard>
            </div>


        </div>
    </main>
</template>

<style scoped lang="scss">
.card:hover {
    position: relative;
    scale: 1.2;
    transition: 0.5s;
}
</style>