<script>
import axios from 'axios';

import AppLoader from '../components/AppLoader.vue'


export default {
    components: {
        AppLoader
    },
    data() {
        return {
            email: '',
            messaggio: '',
            loading: false,
            goodSuccess: false,
            errors: [],
            apiUrl: 'http://127.0.0.1:8080/api/v1/guida'
        }
    },
    methods: {
        sendMessaggio() {
            this.loading = true;
            axios.post(this.apiUrl, {
                email: this.email,
                messaggio: this.messaggio,
            })
                .then((response) => {




                    this.email = ''
                    this.messaggio = ''
                    // this.loading = false;

                    this.goodSuccess = true;
                    this.loading = false;



                }).catch(errors => {

                    console.log(errors.response.data.errors)

                    this.errors = errors.response.data.errors

                    this.loading = false;
                })
        }
    }
}
</script>

<template>
    <div v-if="this.loading" class="text-center my-5">
        <AppLoader />
    </div>
    <div v-else-if="this.goodSuccess">
        <p class="text-center fs-1 text-success">Messaggio inviato correttamente</p>
    </div>
    <div v-else>
        <div class="alert alert-danger mt-2" v-if="errors.length != 0">
            <p v-for="error in errors" class="mb-0">{{ error }}</p>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">email:</label>
            <input type="email" class="form-control" id="email" v-model="email">

        </div>

        <div class="mb-3">
            <label for="messaggio" class="form-label">messaggio:</label>
            <textarea class="form-control" id="messaggio" rows="15" v-model="messaggio"></textarea>


        </div>

        <button type="submit" @click="sendMessaggio" class="btn btn-primary">Invia</button>
    </div>
</template>


<style></style>