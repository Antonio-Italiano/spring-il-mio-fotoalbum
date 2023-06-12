<script>
import axios from 'axios';
import FotoCard from '../components/FotoCard.vue';

export default {
    components: {
        FotoCard
    },
    data() {
        return {
            email: "",
            messaggio: "",
            foto: {},
            errors: [],
            urlAddress: 'http://127.0.0.1:8080/api/v1/foto',
            urlApiAddress: 'http://127.0.0.1:8080/api/v1'
        }
    },
    methods: {
        getFoto() {
            axios.get(this.urlAddress + `/${this.$route.params.id}`)
                .then((response) => {
                    console.log(response.data)

                    this.foto = response.data;
                    console.log(this.foto);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        sendCommento() {
            axios.post(this.urlApiAddress + `/${this.$route.params.id}/notifiche`, {
                email: this.email,
                messaggio: this.messaggio

            })
                .then((response) => {
                    console.log(response)
                    this.email = ""
                    this.messaggio = ''

                })
                .catch(errors => {

                    console.log(errors)

                    console.log(errors.response.data.errors)

                    this.errors = errors.response.data.errors


                })
        }

    },
    created() {
        this.getFoto()
    },
}
</script>

<template>
    <div class="container">
        <FotoCard :foto="this.foto" :show="false" />
        <h1>Commenta</h1>
        <div class="alert alert-danger mt-2" v-if="errors.length != 0">
            <p v-for="error in errors" class="mb-0">{{ error }}</p>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">email:</label><br>
            <input type="text" v-model="email" id="email" placeholder="email"><br>
            <label for="messaggio" class="form-label">messaggio:</label>
            <textarea class="form-control" id="messaggio" rows="15" v-model="messaggio"></textarea>
        </div>
        <button type="submit" @click="sendCommento" class="btn btn-primary">Invia</button>
    </div>
</template>

<style lang="scss"></style>